package fr.sopra.model.EJB;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.sopra.model.Produit;
import fr.sopra.model.Utilisateur;

@Stateless
public class GestionDesUtlisateurEJB {
	
	
	@PersistenceContext(name = "catalogue")
	EntityManager em;
	
	public Utilisateur rechercheLoginUtilisateur(String motClef) {
		 Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login LIKE :motClef", Utilisateur.class);
		return (Utilisateur) query.setParameter("motClef", "%" + motClef + "%").getSingleResult();
	}
	
	public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
		return em.merge(utilisateur);
		
	}
	
	public Utilisateur logUtilisateur(String login, String password) {
		Utilisateur user = new Utilisateur(); 
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login LIKE :login AND u.password LIKE :password", Utilisateur.class)
				 .setParameter("login", login)
				 .setParameter("password", password);
		 Utilisateur result;
		 try { user = (Utilisateur) query.getSingleResult();
			
		} catch (Exception e) {
			user = null;
		} 
		return user;
	}

}
