package com.hackathon.server.service.geoEntityLike;

import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.dto.geoEntityLike.GeoEntityLikeDTO;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntityLike.GeoEntityLike;
import com.hackathon.server.model.user.User;
import com.hackathon.server.repository.geoEntity.GeoEntityRepository;
import com.hackathon.server.repository.geoEntityLike.GeoEntityLikeRepository;
import com.hackathon.server.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityLikeServiceImpl implements GeoEntityLikeService {

    private final GeoEntityLikeRepository geoEntityLikeRepository;
    private final GeoEntityRepository geoEntityRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public GeoEntityLikeDTO create(GeoEntityLikeDTO geoEntityLikeDTO) {
        GeoEntityLike like =
                geoEntityLikeRepository.findByGeoEntityIdAndUserId(geoEntityLikeDTO.getGeoEntityId(), geoEntityLikeDTO.getUserId());
        if(like != null) {
            throw new BadRequestException(ErrorMessageConstants.GEO_ENTITY_LIKE_ALREADY_EXIST);
        }
        GeoEntityLike newGeoEntityLike;
        newGeoEntityLike = convertFromDTO(geoEntityLikeDTO);
        newGeoEntityLike.setEntityStatus(EntityStatus.REGULAR);

        GeoEntityLike saved = geoEntityLikeRepository.save(newGeoEntityLike);

        return convertToDTO(saved);
    }

    @Override
    @Transactional
    public GeoEntityLikeDTO delete(Long geoEntityId, Long userId) {
        GeoEntityLike like =
                geoEntityLikeRepository.findByGeoEntityIdAndUserId(geoEntityId, userId);
        if(like == null) {
            throw new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND);
        }
        geoEntityLikeRepository.delete(like);

        return convertToDTO(like);
    }

    @Override
    public GeoEntityLikeDTO convertToDTO(GeoEntityLike geoEntityLike) {
        GeoEntityLikeDTO dto = new GeoEntityLikeDTO();
        dto.setId(geoEntityLike.getId());

        if(geoEntityLike.getGeoEntity() != null) {
            dto.setGeoEntityId(geoEntityLike.getGeoEntity().getId());
        }

        if(geoEntityLike.getUser() != null) {
            dto.setUserId(geoEntityLike.getUser().getId());
        };
        return dto;
    }

    @Override
    public GeoEntityLike convertFromDTO(GeoEntityLikeDTO dto) {
        GeoEntityLike like = new GeoEntityLike();
        like.setId(dto.getId());

        if(dto.getGeoEntityId() != null) {
            GeoEntity geoEntity = geoEntityRepository.findByIdAndEntityStatus(dto.getGeoEntityId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND));
            like.setGeoEntity(geoEntity);
        }

        if(dto.getUserId() != null) {
            User user = userRepository.findByIdAndEntityStatus(dto.getUserId(), EntityStatus.REGULAR)
                    .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND));
            like.setUser(user);
        }

        return like;
    }

}
