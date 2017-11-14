package fr.sopra.servlet.produit;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;


@WebServlet("/produits")
public class ProduitServlet extends HttpServlet{

	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Produit> p = gestionDesProduitEJB.findAllProduits();
		req.setAttribute("produits", p);
		req.getRequestDispatcher( "/WEB-INF/index-produit.jsp" ).forward( req, resp );
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits");
	}
}
