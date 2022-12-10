package com.hackathon.server.service.geoEntityComment;

import com.hackathon.server.model.dto.geoEntity.GeoEntityDTO;
import com.hackathon.server.model.dto.geoEntityComment.GeoEntityCommentDTO;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;

import java.util.List;

public interface GeoEntityCommentService {

    GeoEntityCommentDTO createComment(GeoEntityCommentDTO geoEntityCommentDTO);

    GeoEntityCommentDTO convertToDTO(GeoEntityComment geoEntityComment);

    GeoEntityComment convertFromDTO(GeoEntityCommentDTO dto);

    List<GeoEntityCommentDTO> getAllCommentsByGeoEntityId(Long geoEntityId);

}
