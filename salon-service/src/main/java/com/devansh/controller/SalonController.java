package com.devansh.controller;

import com.devansh.dto.*;
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

    @PutMapping("/{salonId}")
    public ResponseEntity<SalonResponseDto> updateSalon(
            @ModelAttribute SalonUpdationDto salonUpdationDto,
            @PathVariable Integer salonId
            ) throws IOException {
        return ResponseEntity.ok(salonService.updateSalon(salonUpdationDto, salonId));
    }

    @GetMapping
    public ResponseEntity<List<SalonResponseDto>> getAllSalon() throws IOException {
        return ResponseEntity.ok(salonService.getAllSalons());
    }

    @DeleteMapping("/{salonId}")
    public ResponseEntity<String> deleteSalon(@PathVariable Integer salonId) throws IOException {
        salonService.deleteSalonById(salonId);
        return ResponseEntity.ok("Salon deleted successfully");
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<SalonResponseDto> getSalonByOwnerId(@PathVariable Integer ownerId) throws IOException {
        return ResponseEntity.ok(salonService.getSalonByOwnerId(ownerId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonResponseDto>> searchSalon(@RequestParam String keyword) throws IOException {
        return ResponseEntity.ok(salonService.searchSalon(keyword));
    }


}













