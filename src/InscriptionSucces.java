import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;

public class InscriptionSucces extends PageBase {
	JLabel prenomLabel;
	JLabel nomLabel;
	JTextField nomText;
	JTextField prenomText;
	
	public InscriptionSucces(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		setUpPage(super.frame,super.panel);
	}
	
	static void setUpPage(JFrame frame,JPanel panel) {
		JLabel succes = new JLabel("vous etes bien inscrit : "
				+ "vous pouvez maintenant vous connecter ");
		succes.setBounds(250, 150, 600, 60);
		succes.setFont(new Font("Rubik", Font.BOLD, 15));
		succes.setForeground(Color.RED);
		succes.setHorizontalAlignment(SwingConstants.CENTER);
		succes.setVerticalAlignment(SwingConstants.CENTER);
		panel.add(succes);
	}
}
