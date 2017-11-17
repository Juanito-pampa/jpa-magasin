package fr.sopra.servlet.commande;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.sopra.model.Categories;
import fr.sopra.model.Commande;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.Utilisateur;
import fr.sopra.model.EJB.GestionDesCommandesEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;
import fr.sopra.outil.Outils;

@WebServlet("/commande-edition")
public class CommandeEditionServlet extends HttpServlet{
	
	@EJB
	private GestionDesCommandesEJB gestionDesCommandesEJB;
	
	@EJB
	private GestionDesProduitsEJB gestionDesProduitsEJB;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		Utilisateur user = Outils.getUtilisateurConnect(session, req, resp);
		

		if (user == null) {
			req.getRequestDispatcher("/WEB-INF/loginUser.jsp").forward(req, resp);
			return;
		}
		List<Produit>produits = gestionDesProduitsEJB.findAllProduits();
		String motClef = (String)req.getParameter("motClef");
		if(motClef!=null){
			produits= gestionDesProduitsEJB.rechercheProduit(motClef);
			req.setAttribute("produits", produits);
			req.getRequestDispatcher("/WEB-INF/editionCommande.jsp").forward(req, resp);
		}
		req.setAttribute("produits", produits);
		req.getRequestDispatcher("/WEB-INF/editionCommande.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		Utilisateur user = Outils.getUtilisateurConnect(session, req, resp);
		
		int quantite = Integer.parseInt(req.getParameter("quantite"));
		int id = Integer.parseInt(req.getParameter("produit"));
		Produit produit = gestionDesProduitsEJB.findByID(id);
		Commande nouvelleCommande = new Commande();
		nouvelleCommande.setProduit(produit);
		nouvelleCommande.setQuantité(quantite);
		nouvelleCommande.setUtilisateur(user);
		gestionDesCommandesEJB.ajouterCommande(nouvelleCommande);

	    Properties props = new Properties();
	    props.put("mail.debug", "true");
	    Session sessionMail = Session.getInstance(props, null);

	    try {
	        MimeMessage msg = new MimeMessage(sessionMail);
	        msg.setFrom("me@example.com");
	        msg.setRecipients(Message.RecipientType.TO,
	                          "you@example.com");
	        msg.setSubject("Commande de"+ produit.getNom());
	        msg.setSentDate(new Date());
	        msg.setText("Une commande de"+quantite+" "+produit.getNom()+" a été effectué par "+user.getLogin()+"\n");
	        Transport.send(msg, "me@example.com", "my-password");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/commandes");
		
		
	}
}































