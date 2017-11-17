package fr.sopra.servlet.utilisateur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesUtlisateurEJB;
import fr.sopra.outil.Outils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@EJB
	private GestionDesUtlisateurEJB gestionDesUtlisateurEJB;

	Outils outil = new Outils();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login;
		String password;
		HttpSession session = req.getSession();

		login = (String) req.getParameter("login");
		password = (String) req.getParameter("password");
		if(login.length()<1 || password.length()<1 ){
			String errorMessage = "Veuillez completer tous les champs du formulaire";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
		}
		Utilisateur user = gestionDesUtlisateurEJB.logUtilisateur(login, password);
		if (user != null) {
			outil.setUtilisateurConnect(session, user);
			resp.sendRedirect("index.html");
		} else {
			String errorMessage = "login ou mot de passe éronné";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
	}
}
