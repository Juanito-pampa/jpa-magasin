package fr.sopra.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

/**
 * Servlet implementation class FindByIdServelet
 */
@WebServlet("/FindByIdServlet")
public class FindByIdServlet extends HttpServlet {

	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Produit p = gestionDesProduitEJB.findByID(id);
		System.out.println(p.getNom() + " " + p.getCategories().getNom() + " " + p.getFabriquants().getNom());

	}
}
