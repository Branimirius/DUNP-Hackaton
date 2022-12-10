package com.hackathon.server.controller.geoEntityLike;

import com.hackathon.server.model.dto.geoEntityLike.GeoEntityLikeDTO;
import com.hackathon.server.service.geoEntityLike.GeoEntityLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo-entity-like")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeoEntityLikeController {

    private final GeoEntityLikeService geoEntityLikeService;

    @PostMapping
    public ResponseEntity<GeoEntityLikeDTO> create(@RequestBody GeoEntityLikeDTO geoEntityLikeDTO) {
        return new ResponseEntity<>(geoEntityLikeService.create(geoEntityLikeDTO), HttpStatus.OK);
    }

}
