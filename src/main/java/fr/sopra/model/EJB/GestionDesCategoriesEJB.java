package fr.sopra.model.EJB;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.sopra.model.Categories;
import fr.sopra.model.Produit;

@Stateless
public class GestionDesCategoriesEJB {
	@PersistenceContext(name = "catalogue")
	EntityManager em;
	
	public Categories ajouterCategorie() {
		Categories categorie = new Categories();
		categorie.setNom("voiture" + new Date());
		em.persist(categorie);
		return categorie;
	}
	public List<Categories> findAllCategorie(){
		TypedQuery<Categories> query = em.createQuery("from " + Categories.class.getSimpleName(), Categories.class);
		return query.getResultList();
	}
	
	public List<Categories> find10Categorie(int number){
		Query query = em.createQuery("From Categories");
		int firstCategorie = 0 + number;
		int lastCategorie = 10 ;
		query.setFirstResult(firstCategorie); 
		query.setMaxResults(lastCategorie);
		return query.getResultList();
	}
	
	
	
	
	public void afficherCategorieEtProduit(int id){
		Categories categorie = em.find(Categories.class, id);
		System.out.println("Nom Categorie : " + categorie.getNom());
		
		for(Produit current : categorie.getProduits()){
			System.out.println("Nom Produit : " + current.getNom());
			
		}
	}
	public Categories findCategorieById(int id){
		Categories categorie = em.find(Categories.class, id);
		return categorie;
			
		
	}
	
	public void modifierCategorie(Categories categorie){
		em.merge(categorie);
	}
	
	public void supprimerCategorie(Categories categorie){
		em.remove(em.find(Categories.class, categorie.getId()));
	}
	
	public Categories ajouterCategoriePreRemplie(String nom) {
		Categories categorie = new Categories();
		categorie.setNom(nom);
		em.persist(categorie);
		return categorie;
	}

}
