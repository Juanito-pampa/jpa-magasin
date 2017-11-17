package fr.sopra.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.sopra.model.Produit;

/**
 * Servlet implementation class FindByIdServelet
 */
@WebServlet("/RemoveByIdServlet")
public class RemoveByIdServlet2 extends HttpServlet {

	@PersistenceContext(name = "catalogue")
	EntityManager em;
	@Resource
	private UserTransaction userTransaction;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			userTransaction.begin();
			Produit p = em.find(Produit.class, id);
			System.out.println(p.getNom()+" "+p.getCategories().getNom()+" "+ p.getFabriquants().getNom());
			em.remove(p);
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				userTransaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
	};
}
