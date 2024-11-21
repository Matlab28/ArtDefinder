package com.example.artdefinder.controller;

import com.example.artdefinder.dto.request.ArtRequestDto;
import com.example.artdefinder.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/art")
@RequiredArgsConstructor
public class ArtController {
    private final ArtService service;

    @PostMapping("/learnArt")
    public ResponseEntity<?> learnAboutArt(@RequestBody ArtRequestDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.processArtRequest(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
                    .body("Something went wrong while responding or accessing...");
        }
    }

    @GetMapping("/latestResponse")
    public ResponseEntity<?> getLatestResponse() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getLatestResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Something went wrong while responding or processing...");
        }
    }
}
