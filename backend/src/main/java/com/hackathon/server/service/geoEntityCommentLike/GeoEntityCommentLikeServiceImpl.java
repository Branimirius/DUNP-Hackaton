package com.hackathon.server.service.geoEntityCommentLike;

import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.dto.geoEntityCommentLike.GeoEntityCommentLikeDTO;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import com.hackathon.server.model.geoEntityCommentLike.GeoEntityCommentLike;
import com.hackathon.server.model.user.User;
import com.hackathon.server.repository.geoEntityComment.GeoEntityCommentRepository;
import com.hackathon.server.repository.geoEntityCommentLike.GeoEntityCommentLikeRepository;
import com.hackathon.server.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityCommentLikeServiceImpl implements GeoEntityCommentLikeService {

    private final GeoEntityCommentLikeRepository geoEntityCommentLikeRepository;
    private final GeoEntityCommentRepository geoEntityCommentRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public GeoEntityCommentLikeDTO create(GeoEntityCommentLikeDTO likeDTO) {
        GeoEntityCommentLike like =
                geoEntityCommentLikeRepository.findByGeoEntityCommentIdAndUserId(
                        likeDTO.getGeoEntityCommentId(),
                        likeDTO.getUserId()
                );
        if(like != null) {
            throw new BadRequestException(ErrorMessageConstants.GEO_ENTITY_COMMENT_LIKE_ALREADY_EXIST);
        }
        GeoEntityCommentLike newLike;
        newLike = convertFromDTO(likeDTO);
        newLike.setEntityStatus(EntityStatus.REGULAR);

        GeoEntityCommentLike saved = geoEntityCommentLikeRepository.save(newLike);

        return convertToDTO(saved);
    }

    @Override
    @Transactional
    public GeoEntityCommentLikeDTO delete(Long geoEntityCommentId, Long userId) {
        GeoEntityCommentLike like =
                geoEntityCommentLikeRepository.findByGeoEntityCommentIdAndUserId(geoEntityCommentId, userId);
        if(like == null) {
            throw new BadRequestException(ErrorMessageConstants.GEO_ENTITY_COMMENT_LIKE_NOT_FOUND);
        }
        geoEntityCommentLikeRepository.delete(like);

        return convertToDTO(like);
    }

    @Override
    public GeoEntityCommentLikeDTO convertToDTO(GeoEntityCommentLike like) {
        GeoEntityCommentLikeDTO dto = new GeoEntityCommentLikeDTO();
        dto.setId(like.getId());

        if(like.getGeoEntityComment() != null) {
            dto.setGeoEntityCommentId(like.getGeoEntityComment().getId());
        }

        if(like.getUser() != null) {
            dto.setUserId(like.getUser().getId());
        };
        return dto;
    }

    @Override
    public GeoEntityCommentLike convertFromDTO(GeoEntityCommentLikeDTO dto) {
        GeoEntityCommentLike like = new GeoEntityCommentLike();
        like.setId(dto.getId());

        if(dto.getGeoEntityCommentId() != null) {
            GeoEntityComment comment = geoEntityCommentRepository.findByIdAndEntityStatus(dto.getGeoEntityCommentId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_COMMENT_LIKE_NOT_FOUND));
            like.setGeoEntityComment(comment);
        }

        if(dto.getUserId() != null) {
            User user = userRepository.findByIdAndEntityStatus(dto.getUserId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
            like.setUser(user);
        }
        return like;
    }

}
