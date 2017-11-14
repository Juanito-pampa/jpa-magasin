package fr.sopra.servlet.fabricant;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

@WebServlet("/fabricants")
public class FabricantServlet extends HttpServlet {
	
	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Fabricants> f = gestionDesFabricantsEJB.findAllFabricants();
		req.setAttribute("fabricants",f );
		List<Produit> p = gestionDesProduitEJB.findAllProduits();
		req.setAttribute("produits", p);
		req.getRequestDispatcher("/WEB-INF/index-fabricant.jsp").forward( req, resp );
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/fabricants");
	}
}	

