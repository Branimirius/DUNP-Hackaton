package com.hackathon.server.controller.geoEntityCommentLike;

import com.hackathon.server.model.dto.geoEntityCommentLike.GeoEntityCommentLikeDTO;
import com.hackathon.server.service.geoEntityCommentLike.GeoEntityCommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo-entity-comment-like")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityCommentLikeController {

    private final GeoEntityCommentLikeService geoEntityCommentLikeService;

    @PostMapping
    public ResponseEntity<GeoEntityCommentLikeDTO> create(@RequestBody GeoEntityCommentLikeDTO likeDTO) {
        return new ResponseEntity<>(geoEntityCommentLikeService.create(likeDTO), HttpStatus.OK);
    }

}
