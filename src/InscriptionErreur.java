import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;

public class InscriptionErreur extends PageBase {
	JLabel prenomLabel;
	JLabel nomLabel;
	JTextField nomText;
	JTextField prenomText;
	
	public InscriptionErreur(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		setUpPage(super.frame,super.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		JLabel erreur = new JLabel("veuillez renseigner de nouveau vos informations : un champs etait vide");
		erreur.setBounds(250, 50, 600, 60);
		erreur.setFont(new Font("Rubik", Font.BOLD, 10));
		erreur.setForeground(Color.RED);
		erreur.setHorizontalAlignment(SwingConstants.CENTER);
		erreur.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(erreur);
		JLabel nomLabel = new JLabel("nom");
		nomLabel.setBounds(350,100,200,25);
        panel.add(nomLabel);
        JTextField nomText = new JTextField(20);
        nomText.setBounds(450,100,200,25);
        panel.add(nomText);

        
        JLabel prenomLabel = new JLabel("prenom");
        prenomLabel.setBounds(350,130,200,25);
        panel.add(prenomLabel);
        JTextField prenomText = new JTextField(20);
        prenomText.setBounds(450,130,200,25);
        panel.add(prenomText);
        //String prenom = prenomText.getText();
        //JLabel ageLabel = new JLabel("age");
        //ageLabel.setBounds(350,160,200,25);
        //panel.add(ageLabel);
        //JTextField ageText = new JTextField(20);
        //ageText.setBounds(450,160,200,25);
        //panel.add(ageText);
        //String age = ageText.getText();
        JLabel pseudoLabel = new JLabel("pseudo");
        pseudoLabel.setBounds(350,190,200,25);
        panel.add(pseudoLabel);
        

        JTextField pseudoText = new JTextField(20);
        pseudoText.setBounds(450,190,200,25);
        panel.add(pseudoText);
        String pseudo = pseudoText.getText();
        
        // Same process for password label and text field.
        JLabel mdpLabel = new JLabel("mot de passe");
        mdpLabel.setBounds(350,220,200,25);
        panel.add(mdpLabel);

        JPasswordField mdpText = new JPasswordField(20);
        mdpText.setBounds(450,220,200,25);
        panel.add(mdpText);
        String mdp = mdpText.getText();
        
        // Same process for password label and text field.
        JLabel sexeLabel = new JLabel("sexe");
        sexeLabel.setBounds(350,250,200,25);
        panel.add(sexeLabel);


        JTextField sexeText = new JTextField(20);
        sexeText.setBounds(450,250,200,25);
        panel.add(sexeText);
        String sexe = sexeText.getText();
        
        // Same process for password label and text field.
        JLabel villeLabel = new JLabel("ville");
        villeLabel.setBounds(350,280,200,25);
        panel.add(villeLabel);

        
        JTextField villeText = new JTextField(20);
        villeText.setBounds(450,280,200,25);
        panel.add(villeText);
        String ville = villeText.getText();
        
        // Same process for password label and text field.
        JLabel magasinLabel = new JLabel("magasin favori");
        magasinLabel.setBounds(350,310,200,25);
        panel.add(magasinLabel);


        JTextField magasinText = new JTextField(20);
        magasinText.setBounds(450,310,200,25);
        panel.add(magasinText);
        String magasin = magasinText.getText();
        
        // Same process for password label and text field.
        JLabel mailLabel = new JLabel("mail");
        mailLabel.setBounds(350,340,200,25);
        panel.add(mailLabel);


        JTextField mailText = new JTextField(20);
        mailText.setBounds(450,340,200,25);
        panel.add(mailText);
        String mail = mailText.getText();
        
        // Same process for password label and text field.
        JLabel numeroLabel = new JLabel("numero");
        numeroLabel.setBounds(350,370,200,25);
        panel.add(numeroLabel);


        JTextField numeroText = new JTextField(20);
        numeroText.setBounds(450,370,200,25);
        panel.add(numeroText);
        String numero = numeroText.getText();
        
        JButton loginButton = new JButton("login");
        loginButton.setBounds(450, 420, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionIns(frame,nomText,prenomText,pseudoText,mdpText,sexeText,villeText,magasinText,mailText,numeroText));
	}
}

