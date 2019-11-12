package com.scaryterry.ImageManagement.dao;

import com.scaryterry.ImageManagement.model.Canva;
import com.scaryterry.ImageManagement.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/*

select car from Car car
inner join car.dealerships dealership
where dealership in :dealerships
 */
public interface CanvaDAO extends JpaRepository<Canva,Integer> {
    @Query("SELECT d FROM Canva AS d WHERE :image MEMBER OF d.images")
    List<Canva> findCanvasbyimage(@Param("image")Image  image);
}
