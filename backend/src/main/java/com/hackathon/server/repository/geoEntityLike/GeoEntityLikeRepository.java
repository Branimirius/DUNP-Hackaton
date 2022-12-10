package com.hackathon.server.repository.geoEntityLike;

import com.hackathon.server.model.geoEntityLike.GeoEntityLike;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoEntityLikeRepository extends AbstractStatusEntityRepository<GeoEntityLike, Long> {

    GeoEntityLike findByGeoEntityIdAndUserId(Long geoEntityId, Long userId);

}
