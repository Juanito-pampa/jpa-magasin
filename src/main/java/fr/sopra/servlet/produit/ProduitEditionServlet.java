package fr.sopra.servlet.produit;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;

@WebServlet("/produit-edition")
public class ProduitEditionServlet extends HttpServlet {

	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;
	
	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	
	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Produit produit = null;
		List<Categories> listeCategories = gestionDesCategoriesEJB.findAllCategorie();
		List<Fabricants> listeFabricants = gestionDesFabricantsEJB.findAllFabricants();
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			produit = gestionDesProduitEJB.findByID(id);
		} catch (Exception e) {
			produit = gestionDesProduitEJB.creerProduit(listeFabricants, listeCategories);
		}
		try {
		String newNameFab = (String)req.getParameter("newNameFab");
		String newAdressFab = (String)req.getParameter("newAdressFab");
		String newNameCat = (String)req.getParameter("newNameCat");
			if(newNameFab.length()>1 && newAdressFab.length()>1){
				Fabricants newFab = gestionDesFabricantsEJB.ajouterFabricantsPreRempli(newNameFab, newAdressFab);
				produit.setFabriquants(newFab);
				listeFabricants.clear();
				listeFabricants.add(newFab);
			if(newNameCat.length()>1){
				Categories newCat = gestionDesCategoriesEJB.ajouterCategoriePreRemplie(newNameCat);
				produit.setCategories(newCat);
				listeCategories.clear();
				listeCategories.add(newCat);
			}
				
			}
		} catch (Exception e) {
		}
		req.setAttribute("produit", produit);
		req.setAttribute("categories", listeCategories);
		req.setAttribute("fabricants",listeFabricants );
		req.getRequestDispatcher("/WEB-INF/editionProduit.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String newName = (String)req.getParameter("name");
		String newRef = (String)req.getParameter("reference");
		Categories newCat = gestionDesCategoriesEJB.findCategorieById(Integer.parseInt(req.getParameter("categorie")));
		Fabricants newFab = gestionDesFabricantsEJB.findFabricantById(Integer.parseInt(req.getParameter("fabricant")));
		Produit p = gestionDesProduitEJB.findByID(id);
		if(newName.length()>1)
			p.setNom(newName);
		if(newRef.length()>1)
			p.setReference(newRef);
		p.setCategories(newCat);
		p.setFabriquants(newFab);
		gestionDesProduitEJB.ajouterProduit(p);
		resp.sendRedirect("/jpa-101-1.0-SNAPSHOT/produits");
		
	}
}
