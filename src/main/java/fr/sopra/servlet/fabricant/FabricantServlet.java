package fr.sopra.servlet.fabricant;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;
import fr.sopra.outil.Outils;

@WebServlet("/fabricants")
public class FabricantServlet extends HttpServlet {

	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		Utilisateur user = Outils.getUtilisateurConnect(session, req, resp);

		if (user == null) {
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
			return;
		}
		int pagelistFabricant;
		try {
			pagelistFabricant = Integer.parseInt(req.getParameter("pagelistFabricant"));
		} catch (Exception e) {
			pagelistFabricant=0;
		}
		req.setAttribute("pagelistFabricantRetour", pagelistFabricant);
		List<Fabricants> f = gestionDesFabricantsEJB.find10Fabricants(pagelistFabricant);
		req.setAttribute("fabricants", f);
		req.setAttribute("nombreDeProduitParFabricant", gestionDesProduitEJB.getNombreProduitsParFabricant());
		req.getRequestDispatcher("/WEB-INF/index-fabricant.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/fabricants");
	}
}
