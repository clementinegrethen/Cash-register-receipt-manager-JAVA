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



public class Connexion_backend {
	enum Sexe {M,F};
	private String header[];
    private Object body[][];
    private String lastFileName = null;
    private String lastSheetName = null;
	public static final int nombre_parametres = 9;
	boolean connexion_faite;
	Utilisateur user;
	
	public Connexion_backend(String fileName, String sheetName)
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
	    
	    public Utilisateur getUser() 
	    {
	        return this.user;
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
	   
	
    
    public void connecterUtilisateur(String pseudo,String mdp  ) throws IOException {
    	try {
    	String commande = new String (pseudo + " " + mdp);
		String[] utilisateur = commande.split(" ");
		
		
        

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
           
           Object corps[][] = this.getBody();
           
           boolean deja_present = false;
           int indice = 0;
           while (!deja_present && indice < hight) {
        	   if (utilisateur[0].equals(corps[indice][2]) && utilisateur[1].equals(corps[indice][3])) {
        		   deja_present = true;
        	   }
        	   else {
        		   indice++;
        	   }
           }
           System.out.println(deja_present);
           Utilisateur.Sexe sexe = Utilisateur.Sexe.F;
           if (deja_present) {
        	if ((String)corps[indice][4]=="M") {
        		sexe = Utilisateur.Sexe.M;
        	}
        	String numero_string = (String)corps[indice][8];
        	String[] numero_string_tab = numero_string.split(".");
        	int[] numero = new int[5];
        	for (int i=0; i<numero_string_tab.length;i++) {
        		numero[i]=Integer.parseInt(numero_string_tab[i]);
            }
        	System.out.println(corps[indice][2].getClass().getSimpleName());
       		
        	Utilisateur user = new Utilisateur ((String)corps[indice][0],(String)corps[indice][1],(String)corps[indice][2],(String)corps[indice][3],sexe,(String)corps[indice][4],(String)corps[indice][5],(String)corps[indice][6],numero_string_tab);
       		this.user = user;
       		this.connexion_faite = deja_present;
       		
       		
       		System.out.println(this.user.getPrenom());
       		System.out.println(this.connexion_faite);
           }
           
    }
    	 catch (InvalidFormatException | IOException e) 
        {
             e.printStackTrace();
        }
    	
    	
    }
}