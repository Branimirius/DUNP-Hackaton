package com.hackathon.server.controller.geoEntityComment;

import com.hackathon.server.model.dto.geoEntityComment.GeoEntityCommentDTO;
import com.hackathon.server.model.dto.geoEntityCommentLike.GeoEntityCommentLikeDTO;
import com.hackathon.server.service.geoEntityComment.GeoEntityCommentService;
import com.hackathon.server.service.geoEntityCommentLike.GeoEntityCommentLikeService;
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
    private final GeoEntityCommentLikeService geoEntityCommentLikeService;

    @PostMapping
    public ResponseEntity<GeoEntityCommentDTO> createGeoEntityComment(@RequestBody GeoEntityCommentDTO commentDTO) {
        return new ResponseEntity<>(geoEntityCommentService.createComment(commentDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{geoEntityCommentId}/like-user-id/{userId}")
    public ResponseEntity<GeoEntityCommentLikeDTO> unlike(@PathVariable Long geoEntityCommentId, @PathVariable Long userId) {
        return new ResponseEntity<>(geoEntityCommentLikeService.delete(geoEntityCommentId, userId), HttpStatus.OK);
    }

}
