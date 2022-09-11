import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 

public class Inscription {
    JFrame frame;
    public  Inscription(JFrame frame) {
    	this.frame = frame;
        // Creating instance of JFrame
        JFrame frameinscription = new JFrame("s'inscrire");
        // Setting the width and height of frame
        frameinscription.setSize(350, 450);
        frameinscription.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frameinscription.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        placeComponents(panel,frame,frameinscription);

        // Setting the frame visibility to true
        frameinscription.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame, JFrame frameinscription) {

        /* We will discuss about layouts in the later sections
         * of this tutorial. For now we are setting the layout 
         * to null
         */
        panel.setLayout(null);
        
        JLabel bienvenueLabel = new JLabel("s'inscrire");
        bienvenueLabel.setBounds(10,20,80,25);
        panel.add(bienvenueLabel);
        
        // Creating JLabel
        JLabel nomLabel = new JLabel("nom");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        nomLabel.setBounds(10,50,200,25);
        panel.add(nomLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField nomText = new JTextField(20);
        nomText.setBounds(140,50,200,25);
        panel.add(nomText);
        String nom = nomText.getText();

        // Same process for password label and text field.
        JLabel prenomLabel = new JLabel("prenom");
        prenomLabel.setBounds(10,80,200,25);
        panel.add(prenomLabel);


        JTextField prenomText = new JTextField(20);
        prenomText.setBounds(140,80,200,25);
        panel.add(prenomText);
        String prenom = prenomText.getText();
        
        // Same process for password label and text field.
        //JLabel ageLabel = new JLabel("age");
        //ageLabel.setBounds(10,110,200,25);
        //panel.add(ageLabel);


        //JTextField ageText = new JTextField(20);
        //ageText.setBounds(140,110,200,25);
        //panel.add(ageText);
        //String age = ageText.getText();
        
        // Same process for password label and text field.
        JLabel pseudoLabel = new JLabel("pseudo");
        pseudoLabel.setBounds(10,140,200,25);
        panel.add(pseudoLabel);
        

        JTextField pseudoText = new JTextField(20);
        pseudoText.setBounds(140,140,200,25);
        panel.add(pseudoText);
        String pseudo = pseudoText.getText();
        
        // Same process for password label and text field.
        JLabel mdpLabel = new JLabel("mot de passe");
        mdpLabel.setBounds(10,170,200,25);
        panel.add(mdpLabel);

        JPasswordField mdpText = new JPasswordField(20);
        mdpText.setBounds(140,170,200,25);
        panel.add(mdpText);
        String mdp = mdpText.getText();
        
        // Same process for password label and text field.
        JLabel sexeLabel = new JLabel("sexe");
        sexeLabel.setBounds(10,200,200,25);
        panel.add(sexeLabel);


        JTextField sexeText = new JTextField(20);
        sexeText.setBounds(140,200,200,25);
        panel.add(sexeText);
        String sexe = sexeText.getText();
        
        // Same process for password label and text field.
        JLabel villeLabel = new JLabel("ville");
        villeLabel.setBounds(10,230,200,25);
        panel.add(villeLabel);


        JTextField villeText = new JTextField(20);
        villeText.setBounds(140,230,200,25);
        panel.add(villeText);
        String ville = villeText.getText();
        
        // Same process for password label and text field.
        JLabel magasinLabel = new JLabel("magasin favori");
        magasinLabel.setBounds(10,260,200,25);
        panel.add(magasinLabel);


        JTextField magasinText = new JTextField(20);
        magasinText.setBounds(140,260,200,25);
        panel.add(magasinText);
        String magasin = magasinText.getText();
        
        // Same process for password label and text field.
        JLabel mailLabel = new JLabel("mail");
        mailLabel.setBounds(10,290,200,25);
        panel.add(mailLabel);


        JTextField mailText = new JTextField(20);
        mailText.setBounds(140,290,200,25);
        panel.add(mailText);
        String mail = mailText.getText();
        
        // Same process for password label and text field.
        JLabel numeroLabel = new JLabel("numero");
        numeroLabel.setBounds(10,320,200,25);
        panel.add(numeroLabel);


        JTextField numeroText = new JTextField(20);
        numeroText.setBounds(140,320,200,25);
        panel.add(numeroText);
        String numero = numeroText.getText();
        
        JButton retourButton = new JButton("retour");
        retourButton.setBounds(10, 360, 80, 25);
        panel.add(retourButton);
        retourButton.addActionListener(new Actionretour2(frame,frameinscription));

        // Creating login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(90, 360, 80, 25);
        panel.add(loginButton);
    }
}

class Actionretour2 implements ActionListener {
	JFrame frame;
	JFrame frameinscription;
	public Actionretour2(JFrame frame,JFrame frameinscription) {
		this.frame = frame;
		this.frameinscription = frameinscription;
	}
		
	public void actionPerformed(ActionEvent ev) {
		frameinscription.setVisible(false);
		frame.setVisible(true);
	}
}