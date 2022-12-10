package com.hackathon.server.controller.geoEntity;

import com.hackathon.server.model.dto.geoEntity.GeoEntityDTO;
import com.hackathon.server.model.dto.geoEntityComment.GeoEntityCommentDTO;
import com.hackathon.server.model.dto.geoEntityLike.GeoEntityLikeDTO;
import com.hackathon.server.model.geoEntity.enums.GeoEntityCategory;
import com.hackathon.server.service.geoEntity.GeoEntityService;
import com.hackathon.server.service.geoEntityComment.GeoEntityCommentService;
import com.hackathon.server.service.geoEntityLike.GeoEntityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 Geo entity controller
 */
@RestController
@RequestMapping("/geo-entity")
@CrossOrigin(origins = "http://localhost:3200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityController {

    public final GeoEntityService geoEntityService;
    public final GeoEntityCommentService geoEntityCommentService;
    private final GeoEntityLikeService geoEntityLikeService;

    @PostMapping
    public ResponseEntity<GeoEntityDTO> createGeoEntity(@RequestBody GeoEntityDTO geoEntityDTO) {
        return new ResponseEntity<>(geoEntityService.createGeoEntity(geoEntityDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<GeoEntityDTO>> getAllGeoEntitiesList() {
        return new ResponseEntity<>(geoEntityService.getAllGeoEntities(), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<GeoEntityDTO>> getAllGeoEntitiesList(@RequestBody List<GeoEntityCategory> categories) {
        return new ResponseEntity<>(geoEntityService.getAllGeoEntitiesWithFilters(categories), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeoEntityDTO> getGeoEntityById(@PathVariable Long id) {
        return new ResponseEntity<>(geoEntityService.getOneById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeoEntityDTO> updateGeoEntity(@PathVariable Long id,
                                              @RequestBody GeoEntityDTO geoEntityDTO) {
        return new ResponseEntity<>(geoEntityService.updateGeoEntity(id, geoEntityDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeoEntityDTO> deleteGeoEntity(@PathVariable Long id) {
        return new ResponseEntity<>(geoEntityService.deleteGeoEntity(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}/image", method = RequestMethod.POST)
    public ResponseEntity<GeoEntityDTO> uploadImage(@PathVariable("id") Long id, @RequestPart(value = "image") MultipartFile image) {
        return new ResponseEntity<>(geoEntityService.uploadImage(id, image), HttpStatus.OK);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<GeoEntityCommentDTO>> getAllComments(@PathVariable Long id) {
        return new ResponseEntity<>(geoEntityCommentService.getAllCommentsByGeoEntityId(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{geoEntityId}/like-user-id/{userId}")
    public ResponseEntity<GeoEntityLikeDTO> unlike(@PathVariable Long geoEntityId, @PathVariable Long userId) {
        return new ResponseEntity<>(geoEntityLikeService.delete(geoEntityId, userId), HttpStatus.OK);
    }

}
