package com.devansh.controller;

import com.devansh.dto.SalonCreationDto;
import com.devansh.dto.SalonRequestDto;
import com.devansh.dto.SalonResponseDto;
import com.devansh.dto.UserDto;
import com.devansh.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/salon")
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonResponseDto> createSalon(
            @ModelAttribute SalonCreationDto salonCreationDto
            ) throws IOException {
        return ResponseEntity.ok(salonService.createSalon(salonCreationDto));
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonResponseDto> getSalon(@PathVariable Integer salonId) throws IOException {
        return ResponseEntity.ok(salonService.getSalonById(salonId));
    }




}













