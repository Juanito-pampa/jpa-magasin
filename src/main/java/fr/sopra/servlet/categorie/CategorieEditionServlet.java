package fr.sopra.servlet.categorie;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

import fr.sopra.model.Categories;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

@WebServlet("/categorie-edition")
public class CategorieEditionServlet extends HttpServlet {

	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Categories c = null;
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			c = gestionDesCategoriesEJB.findCategorieById(id);
		} catch (Exception e) {
			c = gestionDesCategoriesEJB.ajouterCategorie();
			c.setNom("nouvelle categorie");
		}
		req.setAttribute("categorie", c);
		req.getRequestDispatcher("/WEB-INF/editionCategorie.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String newName = (String)req.getParameter("name");
		Categories c = gestionDesCategoriesEJB.findCategorieById(id);
		if(newName.length()>1)
			c.setNom(newName);
		gestionDesCategoriesEJB.modifierCategorie(c);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/categories");
		
	}
}
