package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.example.demo.models.SousCategorie;


@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Long> {

}