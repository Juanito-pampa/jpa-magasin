package fr.sopra.servlet.produit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import fr.sopra.model.Categories;
import fr.sopra.model.Fabricants;
import fr.sopra.model.Produit;
import fr.sopra.model.EJB.GestionDesCategoriesEJB;
import fr.sopra.model.EJB.GestionDesFabricantsEJB;
import fr.sopra.model.EJB.GestionDesProduitsEJB;



@WebServlet("/export")
public class ExportServlet extends HttpServlet{
	
	@EJB
	private GestionDesProduitsEJB gestionDesProduitEJB;
	
	@EJB
	private GestionDesFabricantsEJB gestionDesFabricantsEJB;
	
	@EJB
	private GestionDesCategoriesEJB gestionDesCategoriesEJB; 
	
	private static final String FILENAME = "export.xls";
	
	private static HSSFWorkbook wb = new HSSFWorkbook();
	
	private static HSSFSheet sheet = wb.createSheet("catalogue");
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OutilsExport outil = new OutilsExport();
		
		int line = 0;
	
		outil.createLine(line++, "Export sous format Excel du catalogue");
		
		line = line+2;
	
		//categories
		
		outil.createLine(line++, "Categories");
		
		outil.createLine(line++, "Id", "nom");
		
		List<Categories> c = gestionDesCategoriesEJB.findAllCategorie();
		
		
		for(Categories current : c){
			
			outil.createLine(line++, current.getId().toString(), current.getNom());
		}
		
		line = line +3;
		
		// fabricants
		
		outil.createLine(line++, "Fabricants");
		
		outil.createLine(line++, "Id", "Nom", "Adresse");
		
		List<Fabricants> f = gestionDesFabricantsEJB.findAllFabricants();
		
		for(Fabricants current : f){
			
			outil.createLine(line++, current.getId().toString(), current.getNom(), current.getAdresse());
		}
		
		line = line +3;
		
		// produits
	
		outil.createLine(line++, "Produit");
		
		outil.createLine(line++, "Id", "Nom", "reference", "fabricant", "categorie");
			
		List<Produit> p = gestionDesProduitEJB.findAllProduits();
		
		for(Produit current : p){
			
			outil.createLine(line++, current.getId().toString(), current.getNom(), current.getReference(), current.getFabriquants().getNom(), current.getCategories().getNom());
		}
	
		//envoi
		
		resp.setHeader("Content-disposition", "attachment; filename=" + FILENAME);
		resp.setHeader("content-type", "application/xls");
		outil.write(resp.getOutputStream());
    

		
		
	}
	
	
}
