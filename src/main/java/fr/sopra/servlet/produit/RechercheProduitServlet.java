package fr.sopra.servlet.produit;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

@WebServlet("/recherche-produit")
public class RechercheProduitServlet extends HttpServlet{
	
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String motClef = (String)req.getParameter("motClef");
		List<Produit> result = gestionDesProduitEJB.rechercheProduit(motClef);
		req.setAttribute("produits", result);
		req.getRequestDispatcher("/WEB-INF/index-produit.jsp").forward(req, resp);
		
		
		
	}

}
