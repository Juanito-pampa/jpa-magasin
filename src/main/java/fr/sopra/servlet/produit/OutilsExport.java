package fr.sopra.servlet.produit;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import fr.sopra.model.Categories;

public class OutilsExport {
	
	private static HSSFWorkbook wb = new HSSFWorkbook();
	
	private static HSSFSheet sheet = wb.createSheet("catalogue");
	

	public void createLine (int line, String...tabs){
		HSSFRow row = sheet.createRow(line);
		for(int i = 0; i<tabs.length; i++){
		HSSFCell cell = row.createCell(i);
		cell.setCellValue(tabs[i]);
		}
		
		
	}
	
	public void createLine (int line, int...tabs){
		HSSFRow row = sheet.createRow(line);
		for(int i = 0; i<tabs.length; i++){
		HSSFCell cell = row.createCell(i);
		cell.setCellValue(tabs[i]);
		}
		
		
	}
	public void createLine (int line, Integer...tabs){
		HSSFRow row = sheet.createRow(line);
		for(int i = 0; i<tabs.length; i++){
		HSSFCell cell = row.createCell(i);
		cell.setCellValue(tabs[i]);
		}
		
		
	}
	
	
	public void write(ServletOutputStream outputStream) throws IOException {
        wb.write(outputStream);
    }
}
