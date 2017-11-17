package fr.sopra.servlet.utilisateur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesUtlisateurEJB;

@WebServlet("/inscription")
public class InscriptionUtilisateurServlet extends HttpServlet{
	
	@EJB
	private GestionDesUtlisateurEJB gestionDesUtlisateurEJB;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Utilisateur nouvelUtlisateur = new Utilisateur();
			nouvelUtlisateur.setLogin((String)req.getParameter("login"));
			nouvelUtlisateur.setPassword((String)req.getParameter("password"));
			nouvelUtlisateur.setNom((String)req.getParameter("nom"));
			nouvelUtlisateur.setPrenom((String)req.getParameter("prenom"));
			nouvelUtlisateur.setEmail((String)req.getParameter("email"));
			gestionDesUtlisateurEJB.ajouterUtilisateur(nouvelUtlisateur);
		} catch (Exception e) {
			String errorMessage = "formulaire incomplet ou login déja existant";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/inscriptionUser.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/inscriptionUser.jsp").forward(req, resp);
	}

}
