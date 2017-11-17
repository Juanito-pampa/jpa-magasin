package fr.sopra.model.EJB;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;

@Stateless
public class GestionDesProduitsEJB {

	@PersistenceContext(name = "catalogue")
	EntityManager em;

	public Produit findByID(int id) {
		Produit p = em.find(Produit.class, id);
		return p;
	}

	public Produit creerProduit(List<Fabricants> listf, List<Categories> listc) {
		Produit produit = new Produit();
		Categories categorie = listc.get(new Random().nextInt(listc.size()));
		Fabricants fabricant = listf.get(new Random().nextInt(listf.size()));
		produit.setCategories(categorie);
		produit.setFabriquants(fabricant);
		produit.setNom("Nouveau produit");
		produit.setReference(UUID.randomUUID().toString());
		em.persist(produit);
		return produit;

	}
	public List<Produit> find10Produit(int number){
		Query query = em.createQuery("From Produit");
		int firstProduit = 0 + number;
		int lastProduit = 50 ;
		query.setFirstResult(firstProduit); 
		query.setMaxResults(lastProduit);
		return query.getResultList();
	}

	public Fabricants ajouterFabricants() {
		Fabricants fabricant = new Fabricants();
		fabricant.setNom("honda" + new Date());
		fabricant.setAdresse("rue" + new Date());
		em.persist(fabricant);
		return fabricant;
	}

	public Categories ajouterCategorie() {
		Categories categorie = new Categories();
		categorie.setNom("voiture" + new Date());
		em.persist(categorie);
		return categorie;
	}

	public Produit ajouterProduit(Produit produit) {
		return em.merge(produit);

	}

	public List<Categories> findAllCategorie() {
		TypedQuery<Categories> query = em.createQuery("from " + Categories.class.getSimpleName(), Categories.class);
		return query.getResultList();
	}

	public List<Fabricants> findAllFabricants() {
		TypedQuery<Fabricants> query = em.createQuery("from " + Fabricants.class.getSimpleName(), Fabricants.class);
		return query.getResultList();
	}

	public List<Produit> findAllProduits() {
		TypedQuery<Produit> query = em.createQuery("from " + Produit.class.getSimpleName(), Produit.class);
		return query.getResultList();

	}

	public void afficherCategorieEtProduit(int id) {
		Categories categorie = em.find(Categories.class, id);
		System.out.println("Nom Categorie : " + categorie.getNom());

		for (Produit current : categorie.getProduits()) {
			System.out.println("Nom Produit : " + current.getNom());

		}
	}

	public void supprimerProduit(Produit produit) {
		em.remove(em.find(Produit.class, produit.getId()));
	}

	public void modifierProduit(Produit produit) {
		em.merge(produit);

	}

	public List<Produit> rechercheProduit(String motClef) {
		TypedQuery<Produit> query = em.createQuery("SELECT p FROM Produit p WHERE p.nom LIKE :motClef", Produit.class);
		return query.setParameter("motClef", "%" + motClef + "%").getResultList();
	}
	
	public Map<Integer, Long> getNombreProduitsParCatgorie() {
		Map<Integer, Long> result = new HashMap<>();

		Query query = em.createQuery("select p.categories.id, count(p) from Produit p group by p.categories");
		for (Object[] item : (List<Object[]>) query.getResultList()) {
			result.put((Integer) item[0], (Long) item[1]);
		}

		return result;
	}
	
	public Map<Integer, Long> getNombreProduitsParFabricant() {
		Map<Integer, Long> result = new HashMap<>();

		Query query = em.createQuery("select p.fabriquants.id, count(p) from Produit p group by p.fabriquants");
		for (Object[] item : (List<Object[]>) query.getResultList()) {
			result.put((Integer) item[0], (Long) item[1]);
		}

		return result;
	}

}
