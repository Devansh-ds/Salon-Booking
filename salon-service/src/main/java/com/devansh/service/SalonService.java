package com.devansh.service;

import com.devansh.dto.*;

import java.io.IOException;
import java.util.List;

public interface SalonService {

    SalonResponseDto createSalon(SalonCreationDto salonCreationDto) throws IOException;

    SalonResponseDto getSalonById(Integer salonId);

    SalonResponseDto updateSalon(SalonUpdationDto salonUpdationDto, Integer salonId) throws IOException;

    List<SalonResponseDto> getAllSalons() throws IOException;

    void deleteSalonById(Integer salonId);

    SalonResponseDto getSalonByOwnerId(Integer ownerId);

    List<SalonResponseDto> searchSalon(String city);
}
