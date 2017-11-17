package fr.sopra.servlet.produit;

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
import fr.sopra.model.Produit;
import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesProduitsEJB;
import fr.sopra.outil.Outils;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		Utilisateur user = Outils.getUtilisateurConnect(session, req,resp);

		if (user == null) {
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
			return;
		}
		
		int pagelistProduit;
		try {
			pagelistProduit = Integer.parseInt(req.getParameter("pagelistProduit"));
		} catch (Exception e) {
			pagelistProduit=0;
		}
		req.setAttribute("pagelistProduitRetour", pagelistProduit);
		List<Produit> p = gestionDesProduitEJB.find10Produit(pagelistProduit);
		req.setAttribute("produits", p);
		req.getRequestDispatcher("/WEB-INF/index-produit.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits");
	}
}
