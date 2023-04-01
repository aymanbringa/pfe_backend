package com.example.demo.models;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;
    
    @Column(name = "numero_tel")
    private String numero;
    
    @Column(name = "zip")
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cart cart;

    
    @Column(name = "type_de_payment")
    private String typeDePayment;

    /**
	 * @return the typeDePayment
	 */
	public String getTypeDePayment() {
		return typeDePayment;
	}

	/**
	 * @param typeDePayment the typeDePayment to set
	 */
	public void setTypeDePayment(String typeDePayment) {
		this.typeDePayment = typeDePayment;
	}

	/**
	 * @return the statutCommande
	 */
	public boolean isStatutCommande() {
		return statutCommande;
	}

	/**
	 * @param statutCommande the statutCommande to set
	 */
	public void setStatutCommande(boolean statutCommande) {
		this.statutCommande = statutCommande;
	}

	@Column(name = "statut_commande")
    private boolean statutCommande;

	/**
	 * @param adresse
	 * @param ville
	 * @param pays
	 * @param numero
	 * @param zip
	 * @param user
	 * @param cart
	 */
	public Commande(String adresse, String ville, String pays, String numero, String zip, User user, Cart cart) {
		this.adresse = adresse;
		this.ville = ville;
		this.pays = pays;
		this.numero = numero;
		this.zip = zip;
		this.user = user;
		this.cart = cart;
	}

	/**
	 * 
	 */
	public void setUserId(Long long1)
	{
		this.user.setId(long1);
	}
	public Commande() {
	}

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
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the user
	 */
	public Long getUser() {
		return user.getId();
	}

	/**
	 * @param userId the user to set
	 */
	public void setUser(User user) {
		this.user=user;
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}
	public void setCartId(Long cartId) {
	    Long x=this.cart.getId();
	    x=cartId;
	}
	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", adresse=" + adresse + ", ville=" + ville + ", pays=" + pays + ", numero="
				+ numero + ", zip=" + zip + ", user=" + user + ", cart=" + cart + "]";
	}
	
	

    
}
