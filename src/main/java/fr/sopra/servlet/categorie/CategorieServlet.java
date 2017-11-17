package fr.sopra.servlet.categorie;

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
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;
import fr.sopra.outil.Outils;

@WebServlet("/categories")
public class CategorieServlet extends HttpServlet {

	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB;
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

		int pagelistCategorie;
		try {
			pagelistCategorie = Integer.parseInt(req.getParameter("pagelistCategorie"));
		} catch (Exception e) {
			pagelistCategorie = 0;
		}
		req.setAttribute("pagelistCategorieRetour", pagelistCategorie);
		List<Categories> c = gestionDesCategoriesEJB.find10Categorie(pagelistCategorie);
		req.setAttribute("categories", c);
		req.setAttribute("nombreDeProduitParCategorie", gestionDesProduitEJB.getNombreProduitsParCatgorie());
		req.getRequestDispatcher("/WEB-INF/index-categorie.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
	}
}
