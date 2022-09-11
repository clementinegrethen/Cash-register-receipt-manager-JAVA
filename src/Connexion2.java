import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;

public class Connexion2 extends PageBase {
	
	public Connexion2(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		setUpPage(super.frame,super.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		panel.setLayout(null);
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(350,200,80,25);
        panel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(450,200,165,25);
        panel.add(userText);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(350,270,80,25);
        panel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(450,270,165,25);
        panel.add(passwordText);
        
        // Creating login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(450, 340, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new ActionEntree(frame,userText,passwordText));
	}
}

class ActionEntree implements ActionListener {
	JFrame frame;
	public JTextField pseudo;
	public JTextField mdp;
	public ActionEntree(JFrame frame,JTextField pseudo,JTextField mdp) {
		this.frame = frame;
		this.pseudo = pseudo;
		this.mdp = mdp;
	}
	public void actionPerformed(ActionEvent ev) {
		//connexion conn = new connexion(frame);
		Connexion_backend tab = new Connexion_backend("bdd_utilisateurs.xlsx", "Feuille1");
		try {
			tab.connecterUtilisateur(pseudo.getText(), mdp.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tab.connexion_faite) {
			PagePrincipale2 principale= new PagePrincipale2(1000,500,"PagePrincipale");
			principale.afficherPage();
			frame.setVisible(false);
		} else {
			ConnexionErreur ins = new ConnexionErreur(1000,500,"PageInscription");
			ins.afficherPage();
			frame.setVisible(false);
		}
		
	}
}