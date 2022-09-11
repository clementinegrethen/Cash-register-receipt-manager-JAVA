import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class InterfacePrincipale {
	public static void main(String[] args) {
		PageBase pagePrincipale = new PageBase(1000,500,"Tricket");
		pagePrincipale.afficherPage();
		setUpPage(pagePrincipale.frame,pagePrincipale.panel);
	}
	
	public InterfacePrincipale() {
		PageBase pagePrincipale = new PageBase(1000,500,"Tricket");
		pagePrincipale.afficherPage();
		setUpPage(pagePrincipale.frame,pagePrincipale.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		panel.setLayout(null);
		
		//JLabel bienvenueLabel = new JLabel("bienvenue sur tickets");
		//bienvenueLabel.setBounds(350,390,200,55);
        //panel.add(bienvenueLabel);
		
		//JLabel userLabel = new JLabel("se connecter");
		//userLabel.setBounds(350,200,200,55);
        //panel.add(userLabel);
        
        JButton loginButton = new JButton("se connecter");
        loginButton.setBounds(400, 200, 200, 55);
        panel.add(loginButton);
        loginButton.addActionListener(new Actionlogin(frame));
        
		panel.setLayout(null);
		//JLabel inscriptionLabel = new JLabel("s'inscrire");
		//inscriptionLabel.setBounds(350,270,200,55);
        //panel.add(inscriptionLabel);
        
        JButton inscriptionButton = new JButton("s'inscrire");
        inscriptionButton.setBounds(400, 270, 200, 55);
        panel.add(inscriptionButton);
        inscriptionButton.addActionListener(new Actioninscription(frame));
	}
}

class Actionlogin implements ActionListener {
	JFrame frame;
	public Actionlogin(JFrame frame) {
		this.frame = frame;
	}
	public void actionPerformed(ActionEvent ev) {
		//connexion conn = new connexion(frame);
		Connexion2 conn2 = new Connexion2(1000,500,"PageConnexion");
		conn2.afficherPage();
		frame.setVisible(false);
	}
}

class Actioninscription implements ActionListener {
	JFrame frame;
	public Actioninscription(JFrame frame) {
		this.frame = frame;
	}
		
	public void actionPerformed(ActionEvent ev) {
		Inscription2 ins = new Inscription2(1000,500,"PageInscription");
		ins.afficherPage();
		frame.setVisible(false);
	}
	
	
}