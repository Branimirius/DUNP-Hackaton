package com.hackathon.server.repository.geoEntity;

import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntity.enums.GeoEntityCategory;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoEntityRepository extends AbstractStatusEntityRepository<GeoEntity, Long>, JpaSpecificationExecutor<GeoEntity> {

    List<GeoEntity> findByEntityStatusAndCategoryIn(EntityStatus entityStatus, List<GeoEntityCategory> categories);

}
