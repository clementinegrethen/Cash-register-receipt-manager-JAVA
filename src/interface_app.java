import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class interface_app {
	public static void main(String[] args) {
		JFrame frame = new JFrame("ouverture");
		frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(); 
        frame.add(panel);
        placerComponents(panel,frame);
        frame.setVisible(true);
	}
	
	private static void placerComponents(JPanel panel, JFrame frame) {
		panel.setLayout(null);
		
		JLabel bienvenueLabel = new JLabel("bienvenue sur tickets");
		bienvenueLabel.setBounds(180,10,200,55);
        panel.add(bienvenueLabel);
		
		JLabel userLabel = new JLabel("se connecter");
		userLabel.setBounds(10,50,200,55);
        panel.add(userLabel);
        
        JButton loginButton = new JButton("se connecter");
        loginButton.setBounds(150, 50, 200, 55);
        panel.add(loginButton);
        loginButton.addActionListener(new Actionlogin(frame));
        
		panel.setLayout(null);
		JLabel inscriptionLabel = new JLabel("s'inscrire");
		inscriptionLabel.setBounds(10,140,200,55);
        panel.add(inscriptionLabel);
        
        JButton inscriptionButton = new JButton("s'inscrire");
        inscriptionButton.setBounds(150, 140, 200, 55);
        panel.add(inscriptionButton);
        inscriptionButton.addActionListener(new Actioninscription(frame));
	}
}

