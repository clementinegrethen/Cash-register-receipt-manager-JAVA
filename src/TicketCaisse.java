//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Random;
public class TicketCaisse {
    private Magasin magasin;
    private Date date;
    private Produit[] listeProduit;
    private double prix_total;
    private Sheet excel;
    
    //constructeur ticket de caisse
    public TicketCaisse(Sheet bdd_excel,Date date){
    	
    	this.excel = bdd_excel;
        this.date=date;
        int[] borne_magasin_bdd = new int[4];
        borne_magasin_bdd[0]=72;
        borne_magasin_bdd[1]=145;
        borne_magasin_bdd[2]=211;
        borne_magasin_bdd[3]=286;

        double choix_magasin1=((Math.random()*3));
        int choix_magasin=(int) Math.round(choix_magasin1);

        Cell nom_magasin_cellule=bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-1).getCell(4);
        String nom_magasin=nom_magasin_cellule.getStringCellValue();

        Cell adresse_magasin_cellule=bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-1).getCell(8);
        String adresse_magasin=adresse_magasin_cellule.getStringCellValue();
        this.magasin=new Magasin(nom_magasin,adresse_magasin);
        double  nb_produit1=((Math.random()*70)+2); // choix du nombre de produit sur le ticket entre 1 et 70.

        int nb_produit=(int) Math.round(nb_produit1);

        this.listeProduit=new Produit[nb_produit];

        for (int i=0;i<=nb_produit-1;i ++) {
        	
            double selection_produit1= ((Math.random()*nb_produit)+1);
            int selection_produit=(int) Math.round(selection_produit1); // numéro du produit selectionné parmi les 70
            
            Cell prix_cellule =bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(0);
            Cell marque_cellule =bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(1);
            Cell description_cellule =bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(2);
            Cell nutriScore_cellule= bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(5);
            Cell ecoScore_cellule=bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(6);
            Cell categorie_cellule=bdd_excel.getRow(borne_magasin_bdd[choix_magasin]-selection_produit).getCell(3);
            double quantite1=((Math.random()*1)+1);
            int quantite=(int) Math.round(quantite1);
          
            double  prix = prix_cellule.getNumericCellValue();
            String marque=marque_cellule.getStringCellValue();
            String description=description_cellule.getStringCellValue();
            String categorie=categorie_cellule.getStringCellValue();
            String ecoScore;
            String nutriScore;
            try { ecoScore=ecoScore_cellule.getStringCellValue();
            	
            }catch (Exception e) {
            	ecoScore="V";
            }
            try { nutriScore=nutriScore_cellule.getStringCellValue();
        	
            }catch (Exception e) {
            	nutriScore="V";
            }
            Produit produit_choisi=new Produit(marque,description,quantite,prix,ecoScore,nutriScore,categorie,nom_magasin);
            
            double prix_total_local=prix*quantite;
            prix_total_local=(double) (Math.round(prix_total_local*100.0)/100.0);
            this.prix_total=prix_total_local+prix_total;
            this.prix_total=(double) (Math.round(prix_total*100.0)/100.0);
            listeProduit[i]=produit_choisi;

            
        }

    }
    
    public TicketCaisse(TicketCaisse ticket, Produit produit) {
    	this.magasin = ticket.GetMagasin();
    	this.date = ticket.getDate();
    	this.excel = ticket.getExcel();
    	this.prix_total = ticket.prix_total + produit.GetPrix()*produit.GetQuantite();
    	int nb_produit = ticket.getNb() + 1;
    	this.listeProduit = new Produit[nb_produit];
    	for (int i = 0; i <= nb_produit - 2; i++) {
    		this.listeProduit[i] = ticket.listeProduit[i];
    	}
    	listeProduit[nb_produit - 1] = produit;
    }
    
    public TicketCaisse(Date dateT, Sheet excelT) {
    	this.magasin = new Magasin("", "");
    	this.date = dateT;
    	this.excel = excelT;
    	this.prix_total = 0;
    	this.listeProduit = new Produit[0];
    }
    public Magasin GetMagasin() {
    	return this.magasin;
    }
    
    public TicketCaisse ajouterProduit(Produit produit) {
    	return new TicketCaisse(this, produit);
    }
    
    public String ecoScoreMoyen() {
    	int taille=this.listeProduit.length;
    	int moyenne=0;
    	int nb_produit_avec_eco_score=0;
    	for(int i=0;i<=taille-1;i++) {
    		moyenne+=this.listeProduit[i].ConvertirEcoScore();
    		if (this.listeProduit[i].ConvertirEcoScore()!=0){
    			nb_produit_avec_eco_score+=1;
    		}
    	}
    	if (nb_produit_avec_eco_score==0) {
    		moyenne=0;
    	} else {
    	moyenne=(moyenne/(nb_produit_avec_eco_score))+1;}
		System.out.println(moyenne); // ticket

    	switch (moyenne) {
    			case 1 :
    			
    				return "A";
    			case 2 :
    			
    				return "B";
    			case 3:
    			
    				return "C";
    			case 4:
    			
    				return "D";
    			case 5:
    				return "E";
    			case 0:
    				return "Pas d'ecoScore";
    			default:
    				break;
    			}
    			return "Indisponible pour ce ticket";
    	    }
    
    public String NutriScoreMoyen() {
	int taille=this.listeProduit.length;
	int moyenne=0;
	int nb_produit_avec_nutri_score=0;

	for(int i=0;i<=taille-1;i++) {
		moyenne+=this.listeProduit[i].ConvertirNutriScore();
		if (this.listeProduit[i].ConvertirEcoScore()!=0){
			nb_produit_avec_nutri_score+=1;
		}
	}
	if (nb_produit_avec_nutri_score==0) {
		moyenne=0;
	} else {
	moyenne=moyenne/(nb_produit_avec_nutri_score);}
	
	switch (moyenne) {
			case 1 :
			
				return "A";
			case 2 :
			
				return "B";
			case 3:
			
				return "C";
			case 4:
			
				return "D";
			case 5:
				return "E";
			case 0:
				return "Pas de nutriScore";
			default:
				break;
			}
			return "Indisponible pour ce ticket";
	    }
	





    // Affichage d'un ticket de caisse:
    public void afficher() {

        String fileName="ticket"+this.date.ToStringTXT()+".txt";
        //nom de ticket de caisse différent pour chaque ticket selon la date 
        try{ PrintWriter writer = new PrintWriter(fileName,"UTF-8");

        writer.println(this.date.ToString());
        writer.println(" ");
        writer.println(" ");
        writer.println(" ");
        writer.println(this.magasin.toString());
        writer.println(" ");
        writer.println(" ");

        int taille=this.listeProduit.length;
        for (int i=0;i<=taille-1;i++) {
            writer.println(this.listeProduit[i].ToString());

        }
        writer.println(" ");
        writer.println("TOTAL : --------"+this.prix_total+"euros");
        writer.println("NUTRI SCORE MOYEN :"+this.NutriScoreMoyen());
        writer.println("ECO SCORE MOYEN :"+ this.ecoScoreMoyen());


        writer.close();
        } catch (IOException e ) {
        	System.out.println("erreur du fichier");
        	e.printStackTrace(); //affiche de détails sur le problème du document: existe déja,.
        }
    }
    public int getNb() {
    	return this.listeProduit.length;
    }
    public Date getDate() {
    	return this.date;
    }
    
    public double[] repartitionAchat() {
    double[] tab= new double[15];
    	for(int i=1;i<=this.listeProduit.length-1;i++){
    		double prix_total=this.listeProduit[i].GetPrix()*this.listeProduit[i].GetQuantite();
    		prix_total=(double) (Math.round(prix_total*100.0)/100.0);
	    

    		String cat=this.listeProduit[i].getCategorie();
        	System.out.println(prix_total);
        	System.out.println(cat);


    		if (cat.equals("boisson")) {
    			tab[1]=prix_total+tab[1];
    			
    		}else if (cat.equals("produit sucré")){
    			tab[2]+=prix_total;	

    		}else if (cat.equals("viande et poisson")) {
    			tab[3]+=prix_total;
    			
    		}else if (cat.equals("hygiène")) {
    			tab[4]+=prix_total;
    		}else if (cat.equals("entretien")) {
    			tab[5]+=prix_total;
    		}else if (cat.equals("alcool")) {
    			tab[6]+=prix_total;
    		}else if (cat.equals("aliment protéinés")) {
    			tab[7]+=prix_total;
    		}else if (cat.equals("produit céréaliers et légumineux")) {
    			tab[8]+=prix_total;
    		}else if (cat.equals("légume")){
    			tab[9]+=prix_total;	
    		}else if (cat.equals("matière grasse")) {
    			tab[10]+=prix_total;
    		}else if (cat.equals("fruit")) {
    			tab[11]+=prix_total;
    		}else if (cat.equals("produit laitier")) {
    			tab[12]+=prix_total;
    		}else if (cat.equals("condiment")) {
    			tab[13]+=prix_total;
    		}else if (cat.equals("bureau")) {
    			tab[14]+=prix_total;    		}
    		else {
            	System.out.println("erreur du fichier");

    		}
    		
    	}
    	System.out.println(tab[1]);

    	return tab;
    	
    	
    }
    public Produit[] getProduit() {
    	return this.listeProduit;
    }
    public double getPrix() {
    	return this.prix_total;
    }
    // donne les ligne d'apparition d'un produit dans une base de donnée pour chaque magasin
    public int[] LigneApparition(String description,Sheet bdd_excel) {
    	int[] tab_indice=new int[5];
    	int indice=1;
   	for (int i=1;i<286;i++) {
	    	try{Cell description_cellule=bdd_excel.getRow(i).getCell(2);
	        String description_cellule_2=description_cellule.getStringCellValue();
	        if (description.contentEquals(description_cellule_2) ){
	        	//System.out.println("ui");
	        	tab_indice[indice]=i;
	        	indice+=1;
	        }
	    	}catch (Exception e) {
	        	
	        }
	        
    	}
		return tab_indice;
	
    	
    	
    }
    public int[] trietableau (Produit produit,String magasin,Sheet bdd_excel  ) {
    	String description=produit.GetDescription();
    	//System.out.println(produit.GetDescription());
    	int[] tab=LigneApparition(description,bdd_excel);
    	System.out.println(tab[0]);
    	System.out.println(tab[1]);
    	System.out.println(tab[2]);
    	System.out.println(tab[3]);
    	System.out.println(tab[4]);
    	System.out.println(tab[4]);
    	int[] nouveau_tab= new int[5];
    	//System.out.println(description);
    	//System.out.println("magasin " + magasin);
    	switch (magasin) {
	    	case"carrefour":
	    		nouveau_tab[1]=tab[2];
	    		nouveau_tab[2]=tab[3];
	    		nouveau_tab[3]=tab[4];
	    		break;
	    	case"Auchan":
	    		nouveau_tab[1]=tab[1];
	    		nouveau_tab[2]=tab[3];
	    		nouveau_tab[3]=tab[4];	
	    		break;
	    	case"spar":
	    		nouveau_tab[1]=tab[1];
	    		nouveau_tab[2]=tab[2];
	    		nouveau_tab[3]=tab[4];	
	    		break;
	    	case"GeantCasino":
	    		nouveau_tab[1]=tab[1];
	    		nouveau_tab[2]=tab[2];
	    		nouveau_tab[3]=tab[3];	
	    		break;
	    	}
    	return nouveau_tab;
    }
    
    public Produit produixMin(Produit produit,String magasin,Sheet bdd_excel) {
    	int[] tab=trietableau(produit,magasin,bdd_excel);
    	System.out.println(tab[1]);
    	System.out.println(tab[2]);
    	System.out.println(tab[3]);
    
 
    	double[]tab_prix=new double[4];
    	tab_prix[0] = produit.GetPrix();
    	double prix_1, prix_2, prix_3;
    	if (tab[1] == 0) { prix_1 = 10000; } 
    	else {
    	Cell prix_1_cell=bdd_excel.getRow(tab[1]).getCell(0);
        prix_1=prix_1_cell.getNumericCellValue();
    	}
        tab_prix[1]=prix_1;
        if (tab[2] == 0) { prix_2 = 10000; } 
    	else {
        Cell prix_2_cell=bdd_excel.getRow(tab[2]).getCell(0);
        prix_2=prix_2_cell.getNumericCellValue();
    	}
        tab_prix[2]=prix_2;
        if (tab[3] == 0) { prix_3 = 10000; } 
    	else {
        Cell prix_3_cell=bdd_excel.getRow(tab[3]).getCell(0);
        prix_3=prix_3_cell.getNumericCellValue();
    	}
        tab_prix[3]=prix_3;
        int indice_min=1;
       for (int i=0;i<4;i++) {
    	   if (tab_prix[i]<tab_prix[indice_min]) {
    		   indice_min=i;
    	   }
       }
       Cell mag_cell=bdd_excel.getRow(tab[indice_min]).getCell(4);
       String mag=mag_cell.getStringCellValue();
       Produit produit_min= new Produit(produit.GetMarque(),produit.GetDescription(),produit.GetQuantite(),tab_prix[indice_min],"e","d",produit.getCategorie(),mag);
       return produit_min;
    }
    
    public void ticketMin( Sheet bdd_excel) {
    	int nb_produit=this.getNb();
    	String fileName="ticket"+this.date.ToStringTXT()+"comp"+".txt";
        //nom de ticket de caisse différent pour chaque ticket selon la date 
        try{ PrintWriter writer = new PrintWriter(fileName,"UTF-8");

        writer.println(this.date.ToString());
        writer.println(" ");
        writer.println(" ");
        writer.println(" ");
        writer.println("Liste des produits les moins chers");
        writer.println(" ");
        writer.println(" ");
        double prix_total= 0;
    	for (int i=1;i<nb_produit;i++) {

    		if (LigneApparition(this.getProduit()[i].GetDescription(),bdd_excel)[2]!=0) {
    	       

    			Produit prod_min=produixMin(this.getProduit()[i],this.GetMagasin().getNom(),bdd_excel);
    			prix_total=prix_total+prod_min.GetPrix()*prod_min.GetQuantite();
    			System.out.println(prod_min.ToString());  
    			writer.println(prod_min.ToString2());
    		}
    		
            prix_total=(double) (Math.round(prix_total*100.0)/100.0);
    	}
    	
        writer.println(" ");
        writer.println("TOTAL avec les nouveaux produits : --------"+prix_total+"euros");
        writer.println("Economies réalisées  : --------"+(double) (Math.round((this.prix_total-prix_total)*100.0)/100.0)+"euros");

         writer.close();
        } catch (IOException e ) {
        	System.out.println("erreur du fichier");
        	e.printStackTrace(); //affiche de détails sur le problème du document: existe déja,.
        }
    	
    }


	public Sheet getExcel() {
		return excel;
	}
    
    
  
    
   
}
