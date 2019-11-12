package com.scaryterry.ImageManagement.dao;

import com.scaryterry.ImageManagement.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDAO extends JpaRepository<Image,Integer> {
}
