package com.onito.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onito.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>{

}
