package com.example.demo.models;

import java.util.Arrays;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;



@Entity
public class SousCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;
    
    @Lob
    private String image;
    
 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id",nullable = false)
    private Categorie categorie;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the sousCategories
	 */


	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param nom
	 * @param image
	 * @param sousCategories
	 * @param categorie
	 */
	public SousCategorie(String nom, String image, List<Produit> sousCategories, Categorie categorie) {
		this.nom = nom;
		this.image = image;
		this.categorie = categorie;
	}

	/**
	 * 
	 */
	public SousCategorie() {
	}

	@Override
	public String toString() {
		return "SousCategorie [id=" + id + ", nom=" + nom + ", image=" + image + ", categorie=" + categorie + "]";
	}

	
    

}