package fr.sopra.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

@WebServlet("/test")
public class TestServelet extends HttpServlet {

	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Categories> c = gestionDesProduitEJB.findAllCategorie();
		req.setAttribute("categories", c);
		req.getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( req, resp );
		
		
		
		
		try {
			//genererProduit(100, 10, 10);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public void genererProduit(int nbProduit,int nbFabricant, int nbCategorie ) throws Exception{
		List<Fabricants> f = gestionDesProduitEJB.findAllFabricants();
				if(f.isEmpty()){
					f= new ArrayList<>();
					f=genererFabricant(nbFabricant);
				}
		List<Categories> c = gestionDesProduitEJB.findAllCategorie();
				if(c.isEmpty()){
					c= new ArrayList<>();
					c = genererCategorie(nbCategorie);
				}
		for (int i = 0; i < nbProduit; i++) {
			Produit produit = new Produit();
			Categories categorie = c.get(new Random().nextInt(nbCategorie));
			Fabricants fabricant = f.get(new Random().nextInt(nbFabricant));
			produit.setCategories(categorie);
			produit.setFabriquants(fabricant);
			produit.setNom("playmobil" + new Date());
			produit.setReference(UUID.randomUUID().toString());
			gestionDesProduitEJB.ajouterProduit(produit);
			
		}

	}

	public List<Fabricants> genererFabricant(int nb) throws Exception {
		List<Fabricants> f = new ArrayList<>();
		for (int i = 0; i < nb; i++) {
			f.add(gestionDesProduitEJB.ajouterFabricants());
		}
		return f;
	}

	public List<Categories> genererCategorie(int nb) throws Exception {
		List<Categories> c = new ArrayList<>();
		for (int i = 0; i < nb; i++) {
			c.add(gestionDesProduitEJB.ajouterCategorie());
		}
		return c;

	}
}
