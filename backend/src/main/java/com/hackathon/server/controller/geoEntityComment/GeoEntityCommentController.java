package com.hackathon.server.controller.geoEntityComment;

import com.hackathon.server.model.dto.geoEntity.GeoEntityDTO;
import com.hackathon.server.model.dto.geoEntityComment.GeoEntityCommentDTO;
import com.hackathon.server.model.geoEntityComment.GeoEntityComment;
import com.hackathon.server.service.geoEntityComment.GeoEntityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 Geo entity comment controller
 */
@RestController
@RequestMapping("/geo-entity-comment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityCommentController {

    private final GeoEntityCommentService geoEntityCommentService;

    @PostMapping
    public ResponseEntity<GeoEntityCommentDTO> createGeoEntityComment(@RequestBody GeoEntityCommentDTO commentDTO) {
        return new ResponseEntity<>(geoEntityCommentService.createComment(commentDTO), HttpStatus.OK);
    }


}
