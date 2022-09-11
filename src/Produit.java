
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Random;

public class Produit {
    public String marque;
    public String description;
    private int quantite;
    private double prix;
    private String ecoScore;
    private String NutriScore;
    private String categorie;
    private String magasin;
   
    public Produit(String marque, String description,int quantite,double prix,String eco,String nutri,String categorie,String nom_magasin){
        this.marque=marque;
        this.description=description;
        this.prix=prix;
        this.quantite=quantite;
        this.ecoScore=eco;
        this.NutriScore=nutri;
        this.categorie=categorie;
        this.magasin=nom_magasin;
    }
    public String getCategorie() {
    	return this.categorie;
    }
    public String getMagasin() {
    	return this.magasin;
    }
    public double GetPrix(){
        return this.prix;
    }
     public String GetDescription(){
        return this.description;
    }
     public int GetQuantite(){
        return this.quantite;
    }
     public String GetMarque(){
        return this.marque;
    }   
    
    public String ToString(){
    	double prix_total=this.prix*this.quantite;
    	prix_total=(double) (Math.round(prix_total*100.0)/100.0);
    	String produit_description=this.description+this.quantite+this.marque;
    	int longueur=produit_description.length();
    	String tiret= "-".repeat(50-longueur);
        return(this.description+"    "+this.marque  +"   X"+this.quantite+tiret+prix_total+"€");
    }
    
    public int ConvertirEcoScore() {
    	switch (this.ecoScore) {
		case "A":
		
			return 1;
		case "B" :
		
			return 2;
		case "C":
		
			return 3;
		case "D":
		
			return 4;
		case "E":
			return 5;
		case "V":
			return 0;
		default:
			break;
		}
		return 0;
    }
    
    
    
    public int ConvertirNutriScore() {
    	switch (this.NutriScore) {
		case "A":
		
			return 1;
		case "B" :
		
			return 2;
		case "C":
		
			return 3;
		case "D":
		
			return 4;
		case "E":
			return 5;
		case "V":
			return 0;
		default:
			break;
		}
		return 0;
    }
    // donne les ligne d'apparition d'un produit dans une base de donnée pour chaque magasin
    public int[] LigneApparition(String description,Sheet bdd_excel) {
    	int[] tab_indice=new int[4];
    	int indice=1;
   	for (int i=1;i<286;i++) {
	    	Cell description_cellule=bdd_excel.getRow(i).getCell(4);
	        String description_cellule_2=description_cellule.getStringCellValue();
	        if (description==description_cellule_2) {
	        	tab_indice[indice]=i;
	        	indice+=1;
	        }
    	}
		return tab_indice;
	
    	
    	
    }
    public int[] trietableau (Produit produit,String magasin,Sheet bdd_excel  ) {
    	String description=produit.GetDescription();
    	int[] tab=LigneApparition(description,bdd_excel);
    	int[] nouveau_tab= new int[3];
    	switch (magasin) {
    	case"carrefour":
    		nouveau_tab[1]=tab[2];
    		nouveau_tab[2]=tab[3];
    		nouveau_tab[3]=tab[4];
    	case"auchan":
    		nouveau_tab[1]=tab[1];
    		nouveau_tab[2]=tab[3];
    		nouveau_tab[3]=tab[4];	
    	case"spar":
    		nouveau_tab[1]=tab[1];
    		nouveau_tab[2]=tab[2];
    		nouveau_tab[3]=tab[4];	
    	case"GeantCasino":
    		nouveau_tab[1]=tab[1];
    		nouveau_tab[2]=tab[2];
    		nouveau_tab[3]=tab[3];	
    	}
    	return nouveau_tab;
    }
    
    public Produit produixMin(Produit produit,String magasin,Sheet bdd_excel) {
    	int[] tab=trietableau(produit,magasin,bdd_excel);
    	double[]tab_prix=new double[3];
    	Cell prix_1_cell=bdd_excel.getRow(tab[1]).getCell(0);
        double prix_1=prix_1_cell.getNumericCellValue();
        tab_prix[1]=prix_1;
        Cell prix_2_cell=bdd_excel.getRow(tab[2]).getCell(0);
        double prix_2=prix_1_cell.getNumericCellValue();
        tab_prix[2]=prix_2;
        Cell prix_3_cell=bdd_excel.getRow(tab[3]).getCell(0);
        double prix_3=prix_3_cell.getNumericCellValue();
        tab_prix[3]=prix_3;
        int indice_min=1;
       for (int i=1;i<4;i++) {
    	   if (tab_prix[i]<tab_prix[indice_min]) {
    		   indice_min=i;
    	   }
       }
       Cell mag_cell=bdd_excel.getRow(tab[indice_min]).getCell(4);
       String mag=mag_cell.getStringCellValue();
       Produit produit_min= new Produit(produit.GetMarque(),produit.GetDescription(),produit.GetQuantite(),tab_prix[indice_min],"e","d",produit.getCategorie(),mag);
       return produit_min;
    }
    
    
    public String ToString2(){
    	double prix_total=this.prix*this.quantite;
    	prix_total=(double) (Math.round(prix_total*100.0)/100.0);
    	String produit_description=this.description+"    "+this.quantite +"    "+this.marque+"   "+this.magasin;
    	int longueur=produit_description.length();
    	String tiret= "-".repeat(60-longueur);
        return(this.description+"    "+this.marque  +"   X"+this.quantite+"  "+this.magasin+tiret+prix_total+"€");
    }
    
    
}