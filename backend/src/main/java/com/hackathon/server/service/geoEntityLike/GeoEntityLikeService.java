package com.hackathon.server.service.geoEntityLike;

import com.hackathon.server.model.dto.geoEntityLike.GeoEntityLikeDTO;
import com.hackathon.server.model.geoEntityLike.GeoEntityLike;

public interface GeoEntityLikeService {

    GeoEntityLikeDTO create(GeoEntityLikeDTO geoEntityLikeDTO);

    GeoEntityLikeDTO delete(Long geoEntityId, Long userId);

    GeoEntityLikeDTO convertToDTO(GeoEntityLike geoEntityLike);

    GeoEntityLike convertFromDTO(GeoEntityLikeDTO geoEntityLikeDTO);

}
