package fr.sopra.model.EJB;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.sopra.model.Commande;
import fr.sopra.model.Produit;

@Stateless
public class GestionDesCommandesEJB {
	
	@PersistenceContext(name = "catalogue")
	EntityManager em;
	
	public List<Commande> findAllCommande() {
		TypedQuery<Commande> query = em.createQuery("from " + Commande.class.getSimpleName(), Commande.class);
		return query.getResultList();

	}
	
	public Commande ajouterCommande(Commande commande) {
		return em.merge(commande);

	}

}
