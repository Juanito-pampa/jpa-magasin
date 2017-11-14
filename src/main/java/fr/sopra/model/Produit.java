package fr.sopra.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	
	private String nom;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Fabricants getFabriquants() {
		return fabriquants;
	}

	public void setFabriquants(Fabricants fabriquants) {
		this.fabriquants = fabriquants;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Categories categories;
	

	@ManyToOne(cascade=CascadeType.MERGE)
	private Fabricants fabriquants;
	
	private String reference;
	
	public List<Produit> findProduitByIdFab(List<Produit> list, int id) {
		List<Produit> listP = new ArrayList<>();
		for(Produit current : list){
			if(current.getFabriquants().getId()==id){
				listP.add(current);
			}
		}
		return listP;
	}
	
	public List<Produit> findProduitByIdCat(List<Produit> list, int id) {
		List<Produit> listP = new ArrayList<>();
		for(Produit current : list){
			if(current.getCategories().getId()==id){
				listP.add(current);
			}
		}
		return listP;
	}

	
	

}
