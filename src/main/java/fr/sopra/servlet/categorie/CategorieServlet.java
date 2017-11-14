package fr.sopra.servlet.categorie;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Categories;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;


@WebServlet("/categories")
public class CategorieServlet extends HttpServlet{

	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB;
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Categories> c = gestionDesCategoriesEJB.findAllCategorie();
		req.setAttribute("categories", c);
		List<Produit> p = gestionDesProduitEJB.findAllProduits();
		req.setAttribute("produits", p);
		req.getRequestDispatcher( "/WEB-INF/index-categorie.jsp" ).forward( req, resp );
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
	}
}
