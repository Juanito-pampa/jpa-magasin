package fr.sopra.servlet.fabricant;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Fabricants;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;

@WebServlet("/fabricant-supprimer")
public class SupprimerFabricantsServlet extends HttpServlet{
	
	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Fabricants f = gestionDesFabricantsEJB.findFabricantById(id);
		gestionDesFabricantsEJB.supprimerFabricant(f);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/fabricants");
		
	}

}
