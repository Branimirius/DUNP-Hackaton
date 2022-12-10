package com.hackathon.server.service.geoEntity;

import com.hackathon.server.config.error.BadRequestException;
import com.hackathon.server.config.error.ErrorMessageConstants;
import com.hackathon.server.model.EntityStatus;
import com.hackathon.server.model.dto.geoEntity.GeoEntityDTO;
import com.hackathon.server.model.geoEntity.GeoEntity;
import com.hackathon.server.repository.geoEntity.GeoEntityRepository;
import com.hackathon.server.service.util.UtilService;
import com.hackathon.server.util.LocalFileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityServiceImpl implements GeoEntityService {

    private final GeoEntityRepository geoEntityRepository;
    private final UtilService utilService;
    private final LocalFileManager localFileManager;

    @Override
    @Transactional
    public GeoEntityDTO createGeoEntity(GeoEntityDTO geoEntityDTO) {
        GeoEntity newGeoEntity = convertFromDTO(geoEntityDTO);
        newGeoEntity.setEntityStatus(EntityStatus.REGULAR);
        GeoEntity saved = geoEntityRepository.save(newGeoEntity);
        return convertToDTO(saved);
    }

    @Override
    @Transactional
    public GeoEntityDTO updateGeoEntity(Long id, GeoEntityDTO geoEntityDTO) {
        GeoEntity geoEntityForUpdate = geoEntityRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND));

        geoEntityForUpdate.setCategory(geoEntityDTO.getCategory());
        geoEntityForUpdate.setLatitude(geoEntityDTO.getLatitude());
        geoEntityForUpdate.setLongitude(geoEntityDTO.getLongitude());
        geoEntityForUpdate.setDescription(geoEntityDTO.getDescription());

        GeoEntity updatedGeoEntity = geoEntityRepository.save(geoEntityForUpdate);
        return convertToDTO(updatedGeoEntity);
    }

    @Override
    public GeoEntityDTO convertToDTO(GeoEntity geoEntity) {
        GeoEntityDTO dto = new GeoEntityDTO();
        dto.setId(geoEntity.getId());
        dto.setCategory(geoEntity.getCategory());
        dto.setLatitude(geoEntity.getLatitude());
        dto.setLongitude(geoEntity.getLongitude());
        dto.setImageUrl(geoEntity.getImageUrl());
        dto.setLikeNumbers(geoEntity.getLikeNumbers());
        dto.setDescription(geoEntity.getDescription());
        return dto;
    }

    @Override
    public GeoEntity convertFromDTO(GeoEntityDTO dto) {
        GeoEntity geoEntity = new GeoEntity();
        geoEntity.setId(dto.getId());
        geoEntity.setCategory(dto.getCategory());
        geoEntity.setLatitude(dto.getLatitude());
        geoEntity.setLongitude(dto.getLongitude());
        geoEntity.setImageUrl(dto.getImageUrl());
        geoEntity.setLikeNumbers(dto.getLikeNumbers());
        geoEntity.setDescription(dto.getDescription());
        return geoEntity;
    }

    @Override
    public List<GeoEntityDTO> getAllGeoEntities() {
        List<GeoEntity> geoEntities = geoEntityRepository.findByEntityStatus(EntityStatus.REGULAR);
        return geoEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GeoEntityDTO deleteGeoEntity(Long id) {
        GeoEntity geoEntity = geoEntityRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND));
        geoEntity.setEntityStatus(EntityStatus.DELETED);
        return convertToDTO(geoEntityRepository.save(geoEntity));
    }

    @Override
    @Transactional
    public GeoEntityDTO uploadImage(Long id, MultipartFile profileImage) {
        GeoEntity geoEntity = geoEntityRepository.findByIdAndEntityStatus(id, EntityStatus.REGULAR)
                .orElseThrow(() -> new BadRequestException(ErrorMessageConstants.GEO_ENTITY_NOT_FOUND));
        try {
            geoEntity.setImageUrl(
                    utilService.saveFileToSystem(
                            profileImage.getBytes(),
                            localFileManager.GEO_IMAGE_FILES_PATH,
                            "AWS_FILE_PATH"
                    )
            );
            GeoEntity savedGeoEntity = geoEntityRepository.save(geoEntity);
            return convertToDTO(savedGeoEntity);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException(ErrorMessageConstants.FILE_UPLOAD_ERROR);
        }
    }


}