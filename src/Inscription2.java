import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.awt.event.*;
import java.io.IOException;

public class Inscription2 extends PageBase {
	JLabel prenomLabel;
	JLabel nomLabel;
	JTextField nomText;
	JTextField prenomText;
	
	public Inscription2(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		setUpPage(super.frame,super.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		JLabel nomLabel = new JLabel("nom");
		nomLabel.setBounds(350,80,200,25);
        panel.add(nomLabel);
        JTextField nomText = new JTextField(20);
        nomText.setBounds(520,80,200,25);
        panel.add(nomText);

        
        JLabel prenomLabel = new JLabel("prenom");
        prenomLabel.setBounds(350,110,200,25);
        panel.add(prenomLabel);
        JTextField prenomText = new JTextField(20);
        prenomText.setBounds(520,110,200,25);
        panel.add(prenomText);
        //String prenom = prenomText.getText();
        //JLabel ageLabel = new JLabel("age");
        //ageLabel.setBounds(350,140,200,25);
        //panel.add(ageLabel);
        //JTextField ageText = new JTextField(20);
        //ageText.setBounds(520,140,200,25);
        //panel.add(ageText);
        //String age = ageText.getText();
        JLabel pseudoLabel = new JLabel("pseudo");
        pseudoLabel.setBounds(350,170,200,25);
        panel.add(pseudoLabel);
        

        JTextField pseudoText = new JTextField(20);
        pseudoText.setBounds(520,170,200,25);
        panel.add(pseudoText);
        String pseudo = pseudoText.getText();
        
        // Same process for password label and text field.
        JLabel mdpLabel = new JLabel("mot de passe");
        mdpLabel.setBounds(350,200,200,25);
        panel.add(mdpLabel);

        JPasswordField mdpText = new JPasswordField(20);
        mdpText.setBounds(520,200,200,25);
        panel.add(mdpText);
        String mdp = mdpText.getText();
        
        // Same process for password label and text field.
        JLabel sexeLabel = new JLabel("sexe (M ou F)");
        sexeLabel.setBounds(350,230,200,25);
        panel.add(sexeLabel);


        JTextField sexeText = new JTextField(20);
        sexeText.setBounds(520,230,200,25);
        panel.add(sexeText);
        String sexe = sexeText.getText();
        
        // Same process for password label and text field.
        JLabel villeLabel = new JLabel("ville");
        villeLabel.setBounds(350,260,200,25);
        panel.add(villeLabel);


        JTextField villeText = new JTextField(20);
        villeText.setBounds(520,260,200,25);
        panel.add(villeText);
        String ville = villeText.getText();
        
        // Same process for password label and text field.
        JLabel magasinLabel = new JLabel("magasin favori");
        magasinLabel.setBounds(350,290,200,25);
        panel.add(magasinLabel);


        JTextField magasinText = new JTextField(20);
        magasinText.setBounds(520,290,200,25);
        panel.add(magasinText);
        String magasin = magasinText.getText();
        
        // Same process for password label and text field.
        JLabel mailLabel = new JLabel("mail");
        mailLabel.setBounds(350,320,200,25);
        panel.add(mailLabel);


        JTextField mailText = new JTextField(20);
        mailText.setBounds(520,320,200,25);
        panel.add(mailText);
        String mail = mailText.getText();
        
        // Same process for password label and text field.
        JLabel numeroLabel = new JLabel("numero(XX.XX.XX.XX.XX)");
        numeroLabel.setBounds(350,350,200,25);
        panel.add(numeroLabel);


        JTextField numeroText = new JTextField(20);
        numeroText.setBounds(540,350,200,25);
        panel.add(numeroText);
        String numero = numeroText.getText();
        
        JButton loginButton = new JButton("login");
        loginButton.setBounds(450, 400, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionIns(frame,nomText,prenomText,pseudoText,mdpText,sexeText,villeText,magasinText,mailText,numeroText));
	}
}

class ActionIns implements ActionListener {
	public JTextField nom;
	public JTextField prenom;
	
	public JTextField pseudo;
	public JTextField mdp;
	public JTextField sexe;
	public JTextField ville;
	public JTextField magasin;
	public JTextField mail;
	public JTextField numero;
	public JFrame frame;
	public ActionIns(JFrame frame,JTextField nom,JTextField prenom,JTextField pseudo,JTextField mdp,JTextField sexe,JTextField ville,JTextField magasin,JTextField mail,JTextField numero) {
		this.nom=nom;
		this.frame =frame;
		this.prenom = prenom;
		
		this.pseudo = pseudo;
		this.mdp = mdp;
		this.sexe = sexe;
		this.ville = ville;
		this.magasin = magasin;
		this.mail = mail;
		this.numero = numero;
	}
		
	public void actionPerformed(ActionEvent ev) {
		
		
		Inscription_backend ins = new Inscription_backend("bdd_utilisateurs.xlsx","Feuille1");
		System.out.println(this.nom.getText());
		
			try {
				//boolean isNumeric =true;
				//boolean isNumeric =  this.age.getText().matches("[+-]?\\d*(\\.\\d+)?");
				boolean numero_bon =true;
				int i=0;
				while(numero_bon && i<=this.numero.getText().length()-1) {
				//for (int i=0;i<this.numero.getText().length()-1;i++) {
					switch(i) {
					case 0:
					case 1:
					case 3:
					case 4:
					case 6:
					case 7:
					case 9:
					case 10:
					case 12:
					case 13:
						numero_bon = Character.isDigit(this.numero.getText().charAt(i));
						i++;
						break;
					case 2:
					case 5:
					case 8:
					case 11:
						numero_bon = this.numero.getText().charAt(i) =='.';
						i++;
						break;					
					
					default :
						numero_bon = false;
					}
				}
				if ( numero_bon) {
					ins.ajouterUtilisateur(this.nom.getText(), this.prenom.getText(), this.pseudo.getText(), this.mdp.getText(), this.sexe.getText(), this.ville.getText(), this.magasin.getText(), this.mail.getText(), this.numero.getText());
					InscriptionSucces succes= new InscriptionSucces(1000,300,"Inscription avec succes");
					succes.afficherPage();
					frame.setVisible(false);
				} else {
					InscriptionErreur err = new InscriptionErreur(1000,500,"PageInscription");
					err.afficherPage();
					frame.setVisible(false);
				}
				
			} catch (InvalidFormatException | ArrayIndexOutOfBoundsException e) {
				InscriptionErreur err = new InscriptionErreur(1000,500,"PageInscription");
				err.afficherPage();
				frame.setVisible(false);
				 
			} catch (IOException e) {
				InscriptionErreur err = new InscriptionErreur(1000,500,"PageInscripton");
				err.afficherPage();
				frame.setVisible(false);
			}
			
		
		
		
	}
}

	
