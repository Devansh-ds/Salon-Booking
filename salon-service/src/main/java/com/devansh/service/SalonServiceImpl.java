package com.devansh.service;

import com.devansh.dto.*;
import com.devansh.entity.Salon;
import com.devansh.image.ImageData;
import com.devansh.image.ImageService;
import com.devansh.repo.SalonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final ImageService imageService;
    private final SalonMapper salonMapper;
    private final SalonRepository salonRepository;
    private final Logger logger = LoggerFactory.getLogger(SalonServiceImpl.class);

    @Transactional
    @Override
    public SalonResponseDto createSalon(SalonCreationDto salonCreationDto) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        SalonRequestDto salonRequestDto = objectMapper.readValue(salonCreationDto.salonRequestDto(), SalonRequestDto.class);
        UserDto userDto = objectMapper.readValue(salonCreationDto.userDto(), UserDto.class);
        List<MultipartFile> files = salonCreationDto.files();

        List<ImageData> imageDataList = imageService.uploadImage(files);
        logger.info("Images are uploaded");

        Salon salon = salonMapper.toSalon(salonRequestDto);
        salon.setOwnerId(userDto.id());
        salon.setImages(imageDataList);

        Salon savedSalon = salonRepository.save(salon);
        logger.info("Salon saved : {}", savedSalon);

        return salonMapper.toSalonResponseDto(savedSalon);
    }

    @Override
    public SalonResponseDto getSalonById(Integer salonId) {

        Salon salon = salonRepository
                .findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon with id : " + salonId + " not found"));

        logger.info("Salon found : " + salon);

        return salonMapper.toSalonResponseDto(salon);
    }

    @Override
    public SalonResponseDto updateSalon(SalonUpdationDto salonUpdationDto, Integer salonId) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        SalonRequestDto salonRequestDto = objectMapper.readValue(salonUpdationDto.salonRequestDto(), SalonRequestDto.class);
        List<MultipartFile> files = salonUpdationDto.files();

        Salon oldSalon = salonRepository
                .findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon with id : " + salonId + " not found"));
        List<ImageData> updatedImageDataList = imageService.updateImage(oldSalon.getImages(), files);

        oldSalon.setImages(updatedImageDataList);
        if (salonRequestDto.name() != null) {
            oldSalon.setName(salonRequestDto.name());
        }
        if (salonRequestDto.address() != null) {
            oldSalon.setAddress(salonRequestDto.address());
        }
        if (salonRequestDto.phone() != null) {
            oldSalon.setPhone(salonRequestDto.phone());
        }
        if (salonRequestDto.email() != null) {
            oldSalon.setEmail(salonRequestDto.email());
        }
        if (salonRequestDto.openTime() != null) {
            oldSalon.setOpenTime(salonRequestDto.openTime());
        }
        if (salonRequestDto.closeTime() != null) {
            oldSalon.setCloseTime(salonRequestDto.closeTime());
        }

        Salon updatedSalon = salonRepository.save(oldSalon);
        logger.info("Salon updated saved : {}", updatedSalon);

        return salonMapper.toSalonResponseDto(updatedSalon);
    }

    @Override
    public List<SalonResponseDto> getAllSalons() throws IOException {
        List<Salon> allSalons = salonRepository.findAll();
        return allSalons
                .stream()
                .map(salonMapper::toSalonResponseDto)
                .toList();
    }

    @Override
    public void deleteSalonById(Integer salonId) {
        salonRepository
                .findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon with id : " + salonId + " not found"));
        salonRepository.deleteById(salonId);
    }
}

























