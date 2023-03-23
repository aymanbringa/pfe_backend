package com.example.demo.models;

import java.util.Arrays;


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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private int quantite;
    
    @Column(nullable = false)
    private float prix;

    /**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Lob
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sous_categorie_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SousCategorie sousCategorie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
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
	 * @return the sousCategorie
	 */
	public SousCategorie getSousCategorie() {
		return sousCategorie;
	}

	/**
	 * @param sousCategorie the sousCategorie to set
	 */
	public void setSousCategorie(SousCategorie sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	/**
	 * @param nom
	 * @param prix
	 * @param image
	 * @param sousCategorie
	 */
	 

	/**
	 * 
	 */
	public Produit() {
	}

	/**
	 * @param id
	 * @param nom
	 * @param quantite
	 * @param prix
	 * @param image
	 * @param sousCategorie
	 * @param categorie
	 */
	public Produit(Long id, String nom, int quantite, float prix, String image, SousCategorie sousCategorie,
			Categorie categorie) {
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.prix = prix;
		this.image = image;
		this.sousCategorie = sousCategorie;
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + ", image=" + image
				+ ", sousCategorie=" + sousCategorie + "]";
	}

	

	

	

    

}

