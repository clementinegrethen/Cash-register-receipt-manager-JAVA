

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class Inscription_backend {
	enum Sexe {M,F};
	private String header[];
    private Object body[][];
    private String lastFileName = null;
    private String lastSheetName = null;
	public static final int nombre_parametres = 9;
	
	public Inscription_backend(String fileName, String sheetName)
    {
        try
        {
            this.setLastFileName(fileName);
            this.setLastSheetName(sheetName);
            FileInputStream file = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheet(sheetName);
            int top = sheet.getFirstRowNum();
            int bottom = sheet.getLastRowNum();
            Row line = sheet.getRow(top);
            int start = line.getFirstCellNum();
            int end = line.getLastCellNum();    
            int length = end - start;
            
            while(length == 0)
            {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();    
                length = end - start;
            }
            int hight = bottom - top;
            this.header =  new String[length];
            this.body = new Object[hight][length];
            for (int i = 0; i < length; i++)
            {
                header[i] = line.getCell(start + i).getStringCellValue();    
            }
            
            for (int index = 0; index < hight; index++) 
            {
                line = sheet.getRow(index + top + 1);
                for (int i = 0; i < length; i++)
                {
                    Cell cellule = line.getCell(start + i);
                    switch (cellule.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING : 
                            this.body[index][i] = cellule.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN : 
                            this.body[index][i] = cellule.getBooleanCellValue();
                            break;
                        default :
                            this.body[index][i] = cellule.getNumericCellValue();
                    }
                }
            }
            file.close();
        }
         catch (InvalidFormatException | IOException e) 
        {
             e.printStackTrace();
        }
    }
	
	
	 public void saveAs(String fileName, String sheetName)
	    {
	        try 
	        {
	            if (this.getLastFileName().compareTo(fileName) != 0)
	                this.setLastFileName(fileName);
	            if (this.getLastSheetName() != sheetName)
	                this.setLastSheetName(sheetName);
	            Workbook workbook = new HSSFWorkbook();
	            Sheet sheet = workbook.createSheet(sheetName);
	            Row row = sheet.createRow(0);
	            for(int i = 0; i < this.getHeader().length; i++)
	            {
	                row.createCell(i).setCellValue(this.getHeader()[i]);
	            }
	            
	            for (int index = 0; index < this.getBody().length; index++)
	            {
	                row = sheet.createRow(index + 1);
	                for (int i = 0; i < this.getBody()[index].length; i++)
	                {
	                    String valeur = String.valueOf(this.getBody()[index][i]);
	                    row.createCell(i).setCellValue(valeur);
	                }
	            }
	            FileOutputStream fileOut = new FileOutputStream(fileName);
	            workbook.write(fileOut);
	            fileOut.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    
	    public void save()
	    {
	        this.saveAs(this.getLastFileName(), this.getLastSheetName());
	    }
	    
	    public String[] getHeader() 
	    {
	        return this.header;
	    }
	    
	    public Object[][] getBody() 
	    {
	        return this.body;
	    }

	    public String getLastFileName() {
	    	return this.lastFileName;
	    }

	    private void setLastFileName(String lastFileName) {
	        this.lastFileName = lastFileName;
	    }

	    public String getLastSheetName() {
	        return this.lastSheetName;
	    }

	    private void setLastSheetName(String lastSheetName) {
	        this.lastSheetName = lastSheetName;
	    }
	   
	
    
    public void ajouterUtilisateur(String nom,String prenom,String pseudo,String mdp,String sexe,String ville,String magasin,String mail,String numero) throws IOException,InvalidFormatException, ArrayIndexOutOfBoundsException {
    	//try {
    	
    	String commande = new String (nom + " " + prenom+ " " +pseudo+ " " +mdp+ " " +sexe+ " " +ville+ " " +magasin+ " " +mail+ " " +numero);
		String[] utilisateur = commande.split(" ");
		System.out.println(utilisateur[2]);
		
		
		
        

	       // Read XSL file
	       FileInputStream file = new FileInputStream("bdd_utilisateurs.xlsx");
           Workbook workbook = WorkbookFactory.create(file);
           final Sheet sheet = workbook.getSheet("Feuille1");
           int top = sheet.getFirstRowNum();
           int bottom = sheet.getLastRowNum();
           Row line = sheet.getRow(top);
           int start = line.getFirstCellNum();
           int end = line.getLastCellNum();    
           int length = end - start;
           
           System.out.println(top);
           System.out.println(bottom);
           System.out.println(start);
           System.out.println(end);
           System.out.println(length);

           
           while(length == 0)
            {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();    
                length = end - start;
                
            }
           int hight = bottom - top;
           int index = hight -1;
           
           /*
           
           sheet.shiftRows(1, 2, 1); 
           Row row = sheet.createRow(1);  
           
           Cell cell1 = row.createCell(0);
    	   Cell cell2 = row.createCell(1);
    	   Cell cell3 = row.createCell(2);
    	   Cell cell4 = row.createCell(3);
    	   Cell cell5 = row.createCell(4);
    	   Cell cell6 = row.createCell(5);
    	   Cell cell7 = row.createCell(6);
    	   Cell cell8 = row.createCell(7);
    	   Cell cell9 = row.createCell(8);
    	   Cell cell10 = row.createCell(9);
    	   cell1.setCellValue(utilisateur[0]);
    	   cell2.setCellValue(utilisateur[1]);
    	   cell3.setCellValue(utilisateur[2]);
    	   cell4.setCellValue(utilisateur[3]);
    	   cell5.setCellValue(utilisateur[4]);
    	   cell6.setCellValue(utilisateur[5]);
    	   cell7.setCellValue(utilisateur[6]);
    	   cell8.setCellValue(utilisateur[7]);
    	   cell9.setCellValue(utilisateur[8]);
    	   cell10.setCellValue(utilisateur[9]);
    	   */
    	   
           
           
           Object corps[][] = this.getBody();
           System.out.println(corps[1][2].getClass().getSimpleName());
           System.out.println(top);
           System.out.println(bottom);
           System.out.println(start);
           System.out.println(end);
           System.out.println(length);
           System.out.println(hight);
           System.out.println(index);
           System.out.println(utilisateur[0]);
           
           
           
           corps[index][0] = utilisateur[0];
           
           corps[index][1] = utilisateur[1];
           corps[index][2] = utilisateur[2];
           corps[index][3] = utilisateur[3];
           corps[index][4] = utilisateur[4];
           corps[index][5] = utilisateur[5];
           corps[index][6] = utilisateur[6];
           corps[index][7] = utilisateur[7];
           corps[index][8] = utilisateur[8];
           
           System.out.println(corps[index][2]);
           System.out.println(corps[index][2].getClass().getSimpleName());
           
           this.save();
    //}
    	 //catch (InvalidFormatException  | IOException | ArrayIndexOutOfBoundsException e) 
        //{
           //InscriptionErreur err = new InscriptionErreur(1000,500,"erreur");
          // err.afficherPage();
        //}
    }
}
