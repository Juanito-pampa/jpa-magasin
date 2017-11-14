package fr.sopra.model.EJB;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;

@Stateless
public class GestionDesFabricantsEJB {
	@PersistenceContext(name = "catalogue")
	EntityManager em;
	
	public Fabricants ajouterFabricants() {
		Fabricants fabricant = new Fabricants();
		fabricant.setNom("honda" + new Date());
		fabricant.setAdresse("rue" + new Date());
		em.persist(fabricant);
		return fabricant;
	}
	
	public List<Fabricants> findAllFabricants(){
		TypedQuery<Fabricants> query = em.createQuery("from " + Fabricants.class.getSimpleName(), Fabricants.class);
		return query.getResultList();
	}
	
	public Fabricants findFabricantById(int id) {
		Fabricants fabricant = em.find(Fabricants.class, id);
		return fabricant;
		
	}
	public void modifierFabricant(Fabricants fabricant) {
		em.merge(fabricant);
		
	}
	
	public void supprimerFabricant(Fabricants fabricant){
		em.remove(em.find(Fabricants.class, fabricant.getId()));
	}
	
	public Fabricants ajouterFabricantsPreRempli(String nom, String adress) {
		Fabricants fabricant = new Fabricants();
		fabricant.setNom(nom);
		fabricant.setAdresse(adress);
		em.persist(fabricant);
		return fabricant;
	}
	
}
