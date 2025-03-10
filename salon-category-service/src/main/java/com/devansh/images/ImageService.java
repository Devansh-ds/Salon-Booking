package com.devansh.images;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageCategoryDataRepository repo;
    private final Logger log = LoggerFactory.getLogger(ImageService.class);

    public ImageCategoryData uploadImage(MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return new ImageCategoryData();
        }

        ImageCategoryData imageCategoryData = ImageCategoryData
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();

        return repo.save(imageCategoryData);

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageCategoryData> imageData = repo.findByName(name);

        if (imageData.isEmpty()) {
            throw new EntityNotFoundException("Image not found with name : " + name);
        }

        byte[] image = ImageUtil.decompressImage(imageData.get().getImageData());
        return image;
    }

    @Transactional
    public ImageCategoryData updateImage(ImageCategoryData oldImages, MultipartFile file) throws IOException {

        log.info("Deleting old images");

        repo.findById(oldImages.getId())
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id : " + oldImages.getId()));

        repo.deleteById(oldImages.getId());

        return uploadImage(file);
    }
}























