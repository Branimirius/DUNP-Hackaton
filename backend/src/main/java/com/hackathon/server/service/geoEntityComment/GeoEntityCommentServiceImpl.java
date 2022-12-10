package com.hackathon.server.service.geoEntityComment;


import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.dto.geoEntityComment.GeoEntityCommentDTO;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import com.hackathon.server.model.user.User;
import com.hackathon.server.repository.geoEntity.GeoEntityRepository;
import com.hackathon.server.repository.geoEntityComment.GeoEntityCommentRepository;
import com.hackathon.server.repository.predicate.GeoEntityCommentSpecification;
import com.hackathon.server.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityCommentServiceImpl implements GeoEntityCommentService {

    private final GeoEntityCommentRepository geoEntityCommentRepository;
    private final GeoEntityRepository geoEntityRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public GeoEntityCommentDTO createComment(GeoEntityCommentDTO commentDTO) {
        GeoEntityComment newComment = convertFromDTO(commentDTO);
        newComment.setEntityStatus(EntityStatus.REGULAR);
        GeoEntityComment saved = geoEntityCommentRepository.save(newComment);
        return convertToDTO(saved);
    }

    @Override
    public GeoEntityCommentDTO convertToDTO(GeoEntityComment geoEntityComment) {
        GeoEntityCommentDTO dto = new GeoEntityCommentDTO();
        dto.setId(geoEntityComment.getId());
        if(geoEntityComment.getGeoEntity() != null) {
            dto.setGeoEntityId(geoEntityComment.getGeoEntity().getId());
        }
        if(geoEntityComment.getUser() != null) {
            dto.setUserId(geoEntityComment.getUser().getId());
            dto.setUsername(geoEntityComment.getUser().getUsername());
        }
        dto.setCommentText(geoEntityComment.getCommentText());
        return dto;
    }

    @Override
    public GeoEntityComment convertFromDTO(GeoEntityCommentDTO dto) {
        GeoEntityComment comment = new GeoEntityComment();
        comment.setId(dto.getId());

        if(dto.getGeoEntityId() != null) {
            GeoEntity geoEntity = geoEntityRepository.findByIdAndEntityStatus(dto.getGeoEntityId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND));
            comment.setGeoEntity(geoEntity);
        }
        if(dto.getUserId() != null) {
            User user = userRepository.findByIdAndEntityStatus(dto.getUserId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
            comment.setUser(user);
        }

        comment.setCommentText(dto.getCommentText());
        return comment;
    }

    @Override
    public List<GeoEntityCommentDTO> getAllCommentsByGeoEntityId(Long geoEntityId) {
        Specification<GeoEntityComment> commentSpecification =
                Specification.where(GeoEntityCommentSpecification.byEntityStatus(EntityStatus.REGULAR)
                        .and(GeoEntityCommentSpecification.byGeoEntity(geoEntityId)));
        List<GeoEntityComment> comments = geoEntityCommentRepository.findAll(commentSpecification);

        return comments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
