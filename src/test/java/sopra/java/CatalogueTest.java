package sopra.java;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;
import fr.sopra.servlet.categorie.CategorieEditionServlet;
import fr.sopra.servlet.fabricant.FabricantEditionServlet;
import fr.sopra.servlet.produit.ProduitEditionServlet;

@RunWith(Arquillian.class)
public class CatalogueTest {
	@Deployment
	public static Archive<?> createDeployment() {
		// TODO Ajouter la data source pour les tests et l'utiliser à la place
		// de ExempleDS dans le fichier persistence.xml
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Categories.class.getPackage())
				.addPackage(GestionDesProduitsEJB.class.getPackage())
				.addPackage(CategorieEditionServlet.class.getPackage())
				.addPackage(FabricantEditionServlet.class.getPackage())
				.addPackage(ProduitEditionServlet.class.getPackage())
				.addAsLibraries(
						Maven.resolver().resolve("org.apache.poi:poi:3.17").withTransitivity().as(JavaArchive.class))
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");

	}

	@EJB
	GestionDesProduitsEJB gestionDesProduit;

	@EJB
	GestionDesCategoriesEJB gestionDesCategoriesEJB;

	@EJB
	GestionDesFabricantsEJB gestionDesFabricantsEJB;

	@Test
	public void testCreationCategorie() {
		
		Categories categories = new Categories();
		categories = gestionDesCategoriesEJB.ajouterCategoriePreRemplie("test");
		List<Categories> listCategorie = gestionDesCategoriesEJB.findAllCategorie();
		
		for (Categories current : listCategorie)
			Assert.assertEquals("test", current.getNom());
		
		
		
	}

	@Test
	public void testCreationFabricant() {
		
		Fabricants fabricants = new Fabricants();
		fabricants = gestionDesFabricantsEJB.ajouterFabricantsPreRempli("test", "adressTest");
		List<Fabricants> listFabricant = gestionDesFabricantsEJB.findAllFabricants();
		
		for (Fabricants current : listFabricant) {
			Assert.assertEquals("test", current.getNom());
			Assert.assertEquals("adressTest", current.getAdresse());
		}

	}
	
	

	@Test
	public void testCreationProduit() {
		
		Categories categories = new Categories();
		categories = gestionDesCategoriesEJB.ajouterCategoriePreRemplie("test");
		List<Categories> listCategorie = gestionDesCategoriesEJB.findAllCategorie();
		
		Fabricants fabricants = new Fabricants();
		fabricants = gestionDesFabricantsEJB.ajouterFabricantsPreRempli("test", "adressTest");
		List<Fabricants> listFabricant = gestionDesFabricantsEJB.findAllFabricants();
		
		Produit produit = new Produit();
		produit = gestionDesProduit.creerProduit(listFabricant, listCategorie);
		List<Produit> listProduit = gestionDesProduit.findAllProduits();
		
		for (Produit current : listProduit)
			Assert.assertEquals("Nouveau produit", current.getNom());
	}

}
