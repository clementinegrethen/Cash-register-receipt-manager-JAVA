import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;

public class ConnexionErreur extends PageBase {
	
	public ConnexionErreur(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		setUpPage(super.frame,super.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		JLabel erreur = new JLabel("cet utilisateur n'existe pas veuillez réessayer");
		erreur.setBounds(250, 50, 600, 60);
		erreur.setFont(new Font("Rubik", Font.BOLD, 20));
		erreur.setForeground(Color.RED);
		erreur.setHorizontalAlignment(SwingConstants.CENTER);
		erreur.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(erreur);
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

