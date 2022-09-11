import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class pagePrincipale {
	public JFrame framePrecedente;
	public JFrame frame;
	public pagePrincipale(JFrame framePrecedente) {
		this.framePrecedente = framePrecedente;
		JFrame frame = new JFrame("page principale");
		frame.setSize(1000, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(); 
        frame.add(panel);
        placerComponents(panel,frame);
        frame.setVisible(true);
	}
	
	private static void placerComponents(JPanel panel, JFrame frame) {
		panel.setLayout(null);
		
		JLabel bienvenueLabel = new JLabel("bienvenue sur tickets");
		bienvenueLabel.setBounds(400,50,200,55);
        panel.add(bienvenueLabel);
		
        
        JButton ticketsButton = new JButton("Mes Tickets");
        ticketsButton.setBounds(200, 150, 600, 140);
        panel.add(ticketsButton);
        
        JButton supprimerButton = new JButton("supprimer un ticket");
        supprimerButton.setBounds(0, 300, 190, 50);
        panel.add(supprimerButton);
        
        JButton ajouterButton = new JButton("Ajouter un ticket");
        ajouterButton.setBounds(200, 300, 190, 50);
        panel.add(ajouterButton);
        
        JButton nutriButton = new JButton("Mes bilans");
        nutriButton.setBounds(400, 300, 190, 50);
        panel.add(nutriButton);
        
        JButton Button4 = new JButton("Nutriscore");
        Button4.setBounds(600, 300, 190, 50);
        panel.add(Button4);
        
        JButton Button5 = new JButton("nutriscore");
        Button5.setBounds(800, 300, 190, 50);
        panel.add(Button5);
	}
}
