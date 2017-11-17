package fr.sopra.servlet.categorie;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Categories;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;

@WebServlet("/categorie-supprimer")
public class SupprimerCategorieServlet extends HttpServlet{
	
	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Categories c = gestionDesCategoriesEJB.findCategorieById(id);
		gestionDesCategoriesEJB.supprimerCategorie(c);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
	}

}
