package com.hackathon.server.repository.geoEntityComment;

import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import com.hackathon.server.repository.AbstractStatusEntityRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoEntityCommentRepository extends AbstractStatusEntityRepository<GeoEntityComment, Long>, JpaSpecificationExecutor<GeoEntityComment> {
}
