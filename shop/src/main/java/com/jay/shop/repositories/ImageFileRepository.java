package com.jay.shop.repositories;

import com.jay.shop.entities.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    Optional<ImageFile> findByFileKey(String fileKey);
}
