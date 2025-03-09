package com.devansh.service;

import com.devansh.dto.ImageResponse;
import com.devansh.dto.SalonRequestDto;
import com.devansh.dto.SalonResponseDto;
import com.devansh.entity.Salon;
import com.devansh.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonMapper {

    private final ImageService imageService;

    public Salon toSalon(SalonRequestDto req) {
        return Salon
                .builder()
                .name(req.name())
                .email(req.email())
                .city(req.city())
                .address(req.address())
                .closeTime(req.closeTime())
                .openTime(req.openTime())
                .phone(req.phone())
                .build();
    }

    public SalonResponseDto toSalonResponseDto(Salon savedSalon) {

        List<ImageResponse> imageResponses = savedSalon
                .getImages()
                .stream()
                .map(imageData -> {
                    String name = imageData.getName();
                    byte[] image = imageService.getImage(name);
                    return new ImageResponse("http://localhost:8082/images/" + name);
                })
                .toList();

        return SalonResponseDto
                .builder()
                .name(savedSalon.getName())
                .email(savedSalon.getEmail())
                .city(savedSalon.getCity())
                .address(savedSalon.getAddress())
                .phone(savedSalon.getPhone())
                .ownerId(savedSalon.getOwnerId())
                .images(imageResponses)
                .openTime(savedSalon.getOpenTime())
                .closeTime(savedSalon.getCloseTime())
                .build();
    }
}
