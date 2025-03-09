package com.devansh.service;

import com.devansh.dto.SalonCreationDto;
import com.devansh.dto.SalonRequestDto;
import com.devansh.dto.SalonResponseDto;
import com.devansh.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SalonService {

    SalonResponseDto createSalon(SalonCreationDto salonCreationDto) throws IOException;
    SalonResponseDto updateSalon(SalonRequestDto salon, UserDto user, Integer salonId);
    SalonResponseDto getSalonById(Integer salonId);
}
