package fr.sopra.outil;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesUtlisateurEJB;

public class Outils {
	
	@EJB
	private GestionDesUtlisateurEJB gestionDesUtlisateurEJB;
	
	public static void setUtilisateurConnect(HttpSession session , Utilisateur user) {
		session.setAttribute("utilisateur", user);
	}
	
	public static Utilisateur getUtilisateurConnect(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		if (user != null) {
			String userConnected = "Utilisateur connecté : " + user.getLogin();
			req.setAttribute("userConnected", userConnected);
		} else {
		String userConnected = "Aucun utilisateur de connecté";
		req.setAttribute("userConnected", userConnected);
		}
		
		return user;
	}
	
	

}
