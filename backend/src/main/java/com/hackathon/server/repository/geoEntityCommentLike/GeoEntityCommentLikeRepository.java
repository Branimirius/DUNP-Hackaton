package com.hackathon.server.repository.geoEntityCommentLike;

import com.hackathon.server.model.geoEntityCommentLike.GeoEntityCommentLike;
import com.hackathon.server.model.geoEntityLike.GeoEntityLike;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoEntityCommentLikeRepository extends AbstractStatusEntityRepository<GeoEntityCommentLike, Long> {

    GeoEntityCommentLike findByGeoEntityCommentIdAndUserId(Long geoEntityCommentId, Long userId);

}
