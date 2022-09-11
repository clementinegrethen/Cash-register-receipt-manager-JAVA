import javax.swing.*;

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

public class Interface_Utilisateur extends Utilisateur {
	
	
	public Interface_Utilisateur(String Nom, String Prenom, String Pseudo, String Mdp, Sexe sexe, String Ville,
			String Magasin_favori, String Mail, String[] Numero) {
		super(Nom, Prenom, Pseudo, Mdp, sexe, Ville, Magasin_favori, Mail, Numero);
	}
	
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("ouverture");
		Sheet bdd_excel = null;
		frame.setSize(500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(); 
        frame.add(panel);
        
		placerComponents(panel,frame, bdd_excel, Pseudo, mdp);
        frame.setVisible(true);
	}
	
	private static void placerComponents(JPanel panel, JFrame frame, Sheet bdd_excel, String Pseudo, String mdp) {
	try {
		panel.setLayout(null);
		
		
		FileInputStream file = new FileInputStream("/home/rmbouza/Annee_1/TOB/tps/projet_long/MN-1/src/bdd_utilisateurs.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		final Sheet sheet = workbook.getSheet("Feuille1");
		int top = sheet.getFirstRowNum();
		int bottom = sheet.getLastRowNum();
		Row line = sheet.getRow(top);
		int start = line.getFirstCellNum();
		int end = line.getLastCellNum();    
		int length = end - start;
		
		
		for (int i=0; i<=length; i++) {
			Row l_u1 = sheet.getRow(i);
			if ((l_u1.getCell(3).getStringCellValue() == Pseudo)&&(l_u1.getCell(4).getStringCellValue()) {
				int ligne_correspondante = i;
			}
		}
		//Row l_u = sheet.getRow(3);
		Row l_u = sheet.getRow(ligne_correspondante);
		
		
		String[] num1 = new String[nombres_tel];
		Cell Cell1 = l_u.getCell(9);
		String num = Cell1.getStringCellValue();
		num1[0]= num.substring(0,  2);
		num1[1]= num.substring(3,  5);
		num1[2]= num.substring(6,  8);
		num1[3]= num.substring(9,  11);
		num1[4]= num.substring(12,  14);
		

		
		Cell Cell2 = l_u.getCell(2);
		Integer age_bdd = (int) Cell2.getNumericCellValue();

		Cell Cell5 = l_u.getCell(5);
		Sexe Genre = Sexe.M;
        
        if (Cell5.getStringCellValue() == "F" ) {
        	Genre = Sexe.F;
        }
		
		Utilisateur user = new Utilisateur(l_u.getCell(0).getStringCellValue(), l_u.getCell(1).getStringCellValue(), age_bdd, l_u.getCell(3).getStringCellValue(), l_u.getCell(4).getStringCellValue(), Genre, l_u.getCell(6).getStringCellValue(), l_u.getCell(7).getStringCellValue(), l_u.getCell(8).getStringCellValue(), num1);
		
		
	

		String num2 = num1[0]+num1[1]+num1[2]+num1[3]+num1[4];

		//Utilisateur user = new Utilisateur("Nial", "Jerome", 12, "JleSang", "ok31", Sexe.M, "Toulouse", "Carrefour", "jerome@gmail.com", num1);
		
		
		JLabel profilLabel = new JLabel("Profil utilisateur");
		profilLabel.setBounds(150,10,200,55);
        panel.add(profilLabel);
		
		JLabel nomLabel = new JLabel("Nom :");
		nomLabel.setBounds(10,50,200,55);
        panel.add(nomLabel);
        
        JLabel nom1Label = new JLabel(user.getNom());
		nom1Label.setBounds(200,50,200,55);
        panel.add(nom1Label);
      
		panel.setLayout(null);
		JLabel prenomLabel = new JLabel("Prenom :");
		prenomLabel.setBounds(10,140,200,55);
        panel.add(prenomLabel);
        
        JLabel prenom1Label = new JLabel(user.getPrenom());
		prenom1Label.setBounds(200,140,200,55);
        panel.add(prenom1Label);
        
        panel.setLayout(null);
		JLabel ageLabel = new JLabel("Age :");
		ageLabel.setBounds(10,230,200,55);
        panel.add(ageLabel);
        
        JLabel age1Label = new JLabel(String.valueOf(user.getAge()));
		age1Label.setBounds(200,230,200,55);
        panel.add(age1Label);
        
        panel.setLayout(null);
		JLabel pseudoLabel = new JLabel("Pseudo :");
		pseudoLabel.setBounds(10,320,200,55);
        panel.add(pseudoLabel);
        
        JLabel pseudo1Label = new JLabel(user.getPseudo());
		pseudo1Label.setBounds(200,320,200,55);
        panel.add(pseudo1Label);
        
        panel.setLayout(null);
		JLabel mdpLabel = new JLabel("Mot De Passe : ");
		mdpLabel.setBounds(10,410,200,55);
        panel.add(mdpLabel);
        
        JLabel mdp1Label = new JLabel(user.getMdp());
		mdp1Label.setBounds(200,410,200,55);
        panel.add(mdp1Label);
        
        panel.setLayout(null);
		JLabel sexeLabel = new JLabel("Genre : ");
		sexeLabel.setBounds(10,500,200,55);
        panel.add(sexeLabel);
        
        if (user.getSexe() == Sexe.M ) {
        	JLabel sexe1Label = new JLabel("M");
    		sexe1Label.setBounds(200,500,200,55);
            panel.add(sexe1Label);
        }
        
        if (user.getSexe() == Sexe.F ) {
        	JLabel sexe1Label = new JLabel("F");
    		sexe1Label.setBounds(200,500,200,55);
            panel.add(sexe1Label);
        }
        
        
        panel.setLayout(null);
		JLabel villeLabel = new JLabel("Ville : ");
		villeLabel.setBounds(10,590,200,55);
        panel.add(villeLabel);
        
        JLabel ville1Label = new JLabel(user.getVille());
		ville1Label.setBounds(200,590,200,55);
        panel.add(ville1Label);
        
        panel.setLayout(null);
		JLabel mfLabel = new JLabel("Magasin Favori : ");
		mfLabel.setBounds(10,680,200,55);
        panel.add(mfLabel);
        
        JLabel mf1Label = new JLabel(user.getMagasin());
		mf1Label.setBounds(200,680,200,55);
        panel.add(mf1Label);
        
        panel.setLayout(null);
		JLabel mailLabel = new JLabel("E-mail : ");
		mailLabel.setBounds(10,770,200,55);
        panel.add(mailLabel);
        
        JLabel mail1Label = new JLabel(user.getMail());
		mail1Label.setBounds(200,770,200,55);
        panel.add(mail1Label);
        
        panel.setLayout(null);
		JLabel numLabel = new JLabel("Numero : ");
		numLabel.setBounds(10,860,200,55);
        panel.add(numLabel);
        
        JLabel num1Label = new JLabel(num2);
		num1Label.setBounds(200,860,200,55);
        panel.add(num1Label);

	}
	catch (InvalidFormatException | IOException e) 
        {
            e.printStackTrace();
    }
	}
}


