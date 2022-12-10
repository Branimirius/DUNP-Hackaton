package com.hackathon.server.service.geoEntity;

import com.hackathon.server.model.dto.geoEntity.GeoEntityDTO;
import com.hackathon.server.model.dto.user.UserDTO;
import com.hackathon.server.model.geoEntity.GeoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GeoEntityService {

    GeoEntityDTO createGeoEntity(GeoEntityDTO geoEntityDTO);

    GeoEntityDTO updateGeoEntity(Long id, GeoEntityDTO geoEntityDTO);

    GeoEntityDTO convertToDTO(GeoEntity geoEntity);

    GeoEntity convertFromDTO(GeoEntityDTO dto);

    List<GeoEntityDTO> getAllGeoEntities();

    GeoEntityDTO deleteGeoEntity(Long id);

    GeoEntityDTO uploadImage(Long id, MultipartFile profileImage);

    GeoEntityDTO getOneById(Long id);

}
