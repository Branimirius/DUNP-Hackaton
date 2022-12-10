package com.hackathon.server.service.geoEntityCommentLike;

import com.hackathon.server.model.dto.geoEntityCommentLike.GeoEntityCommentLikeDTO;
import com.hackathon.server.model.geoEntityCommentLike.GeoEntityCommentLike;

public interface GeoEntityCommentLikeService {

    GeoEntityCommentLikeDTO create(GeoEntityCommentLikeDTO likeDTO);

    GeoEntityCommentLikeDTO delete(Long geoEntityCommentId, Long userId);

    GeoEntityCommentLikeDTO convertToDTO(GeoEntityCommentLike like);

    GeoEntityCommentLike convertFromDTO(GeoEntityCommentLikeDTO likeDTO);

}
