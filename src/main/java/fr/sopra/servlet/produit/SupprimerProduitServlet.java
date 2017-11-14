package fr.sopra.servlet.produit;

import java.io.IOException;

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

@WebServlet("/produit-supprimer")
public class SupprimerProduitServlet extends HttpServlet{
	
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Produit p = gestionDesProduitEJB.findByID(id);
		gestionDesProduitEJB.supprimerProduit(p);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits");
	}

}
