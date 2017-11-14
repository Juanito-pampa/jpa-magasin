package fr.sopra.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	
	@OneToMany(mappedBy="categories")
	private List<Produit> produits;

	
	
	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

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
	public Categories findCategorieById(List<Categories> list, int id) {
		Categories categorie = new Categories();
		for(Categories current : list){
			if(current.getId()==id){
				categorie=current;
			}
		}
		return categorie;
	}
	
	
}
