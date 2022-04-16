package com.zipzoong.house.repository;

import com.zipzoong.house.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseRepository extends JpaRepository<House, Long> {
}
