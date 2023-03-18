package com.example.demo.models;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sousCategories"})

public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;	

    @Lob
    private String image;


    @OneToMany(mappedBy = "categorie")
    private List<SousCategorie> sousCategories;
  

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNom() {
		return nom;
	}

	
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getImage() {
		return image;
	}

	
	public void setImage(String image) {
		this.image = image;
	}

	
	
	

	


	/**
	 * 
	 */
	public Categorie() {
	}


	public Categorie(String nom, String image, String contentType) {
		this.nom = nom;
		this.image = image;
		
	}

    

}