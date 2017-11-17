package fr.sopra.servlet.commande;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sopra.model.Commande;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesCommandesEJB;
import fr.sopra.outil.Outils;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet{
	
	@EJB
	private GestionDesCommandesEJB gestionDesCommandesEJB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Utilisateur user = Outils.getUtilisateurConnect(session, req, resp);
		

		if (user == null) {
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
			return;
		}
		List<Commande> commandes = gestionDesCommandesEJB.findAllCommande();
		req.setAttribute("commandes", commandes);
		req.getRequestDispatcher("/WEB-INF/index-commande.jsp").forward(req, resp);
	}

}
