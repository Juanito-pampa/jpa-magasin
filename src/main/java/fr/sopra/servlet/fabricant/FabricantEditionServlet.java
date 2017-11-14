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

@WebServlet("/fabricant-edition")
public class FabricantEditionServlet extends HttpServlet {
	
	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Fabricants f = null;
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			f=gestionDesFabricantsEJB.findFabricantById(id);
		} catch (Exception e) {
			f=gestionDesFabricantsEJB.ajouterFabricants();
			f.setNom("nouveau fabricant");
			f.setAdresse("nouvelle adresse");
		}
	req.setAttribute("fabricant", f);
	req.getRequestDispatcher("/WEB-INF/editionFabricant.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Fabricants f = gestionDesFabricantsEJB.findFabricantById(id);
		String newName = (String)req.getParameter("name");
		if(newName.length()>1)
			f.setNom(newName);
		String newAdress = (String)req.getParameter("adress");
		if(newAdress.length()>1)
			f.setAdresse(newAdress);
		gestionDesFabricantsEJB.modifierFabricant(f);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/fabricants");
		
	}

}
