package com.hackathon.server.repository.geoEntity;

import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoEntityRepository extends AbstractStatusEntityRepository<GeoEntity, Long>, JpaSpecificationExecutor<GeoEntity> {
}
