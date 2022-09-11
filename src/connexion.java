import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 

public class connexion {
    JFrame frame;
    JFrame frameConnexion;
    public  connexion(JFrame frame) {
    	//super(350,200,"pageConnexion");
    	this.frame =frame;
        // Creating instance of JFrame
        JFrame frameConnexion = new JFrame("My First Swing Example");
        // Setting the width and height of frame
        frameConnexion.setSize(350, 200);
        frameConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frameConnexion.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
        placeComponents(panel,frame,frameConnexion);

        // Setting the frame visibility to true,frame
        frameConnexion.setVisible(true);
    	//this.frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, JFrame frame, JFrame frameconnexion) {

        /* We will discuss about layouts in the later sections
         * of this tutorial. For now we are setting the layout 
         * to null
         */
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        /* This method specifies the location and size
         * of component. setBounds(x, y, width, height)
         * here (x,y) are cordinates from the top left 
         * corner and remaining two arguments are the width
         * and height of the component.
         */
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        // Same process for password label and text field.
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /*This is similar to text field but it hides the user
         * entered data and displays dots instead to protect
         * the password like we normally see on login screens.
         */
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);


        
        // Creating login button
        JButton retourButton = new JButton("retour");
        retourButton.setBounds(10, 80, 80, 25);
        panel.add(retourButton);
        retourButton.addActionListener(new Actionretour(frame,frameconnexion));
        
        // Creating login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(90, 80, 80, 25);
        panel.add(loginButton);
    }    
}

class Actionretour implements ActionListener {
	JFrame frame;
	JFrame frameconnexion;
	public Actionretour(JFrame frame,JFrame frameconnexion) {
		this.frame = frame;
		this.frameconnexion = frameconnexion;
	}
		
	public void actionPerformed(ActionEvent ev) {
		frameconnexion.setVisible(false);
		frame.setVisible(true);
	}
}