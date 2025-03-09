package com.devansh.service;

import com.devansh.dto.SalonCreationDto;
import com.devansh.dto.SalonRequestDto;
import com.devansh.dto.SalonResponseDto;
import com.devansh.dto.UserDto;
import com.devansh.entity.Salon;
import com.devansh.image.ImageData;
import com.devansh.image.ImageService;
import com.devansh.repo.SalonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiListUI;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final ImageService imageService;
    private final SalonMapper salonMapper;
    private final SalonRepository salonRepository;

    @Override
    public SalonResponseDto createSalon(SalonCreationDto salonCreationDto) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        SalonRequestDto salonRequestDto = objectMapper.readValue(salonCreationDto.salonRequestDto(), SalonRequestDto.class);
        UserDto userDto = objectMapper.readValue(salonCreationDto.userDto(), UserDto.class);
        List<MultipartFile> files = salonCreationDto.files();

        System.out.println(files == null ? "null" : files.toString());

        List<ImageData> imageDataList = imageService.uploadImage(files);

        Salon salon = salonMapper.toSalon(salonRequestDto);
        salon.setOwnerId(userDto.id());
        salon.setImages(imageDataList);

        Salon savedSalon = salonRepository.save(salon);

        return salonMapper.toSalonResponseDto(savedSalon);
    }

    @Override
    public SalonResponseDto updateSalon(SalonRequestDto salon, UserDto user, Integer salonId) {
        return null;
    }

    @Override
    public SalonResponseDto getSalonById(Integer salonId) {
        return null;
    }
}
