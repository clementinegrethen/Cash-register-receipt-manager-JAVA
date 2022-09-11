
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants; 


public class PageBase {

	protected int largeurPage;
	protected int hauteurPage;
	protected String titrePage;
	protected JFrame frame;
	protected JPanel panel;
	protected Historique mesTickets;
	
	public PageBase(int largeur, int hauteur, String titre) {
		this.largeurPage = largeur;
		this.hauteurPage = hauteur;
		this.titrePage = titre;
		this.panel = new JPanel();   
		setUpPage();
	}
	
	public PageBase(int largeur, int hauteur, String titre, Historique historique) {
		this.largeurPage = largeur;
		this.hauteurPage = hauteur;
		this.titrePage = titre;
		this.mesTickets = historique;
		this.panel = new JPanel();   
		setUpPage();
	}
	
	private void setUpPage() {
		
		this.frame = new JFrame(this.titrePage);
		this.frame.setSize(this.largeurPage, this.hauteurPage);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.add(this.panel);
		//this.frame.add(new JLabel(logo));

		this.panel.setLayout(null);
		//setUplogo();
		setUpTitre();
		if (this.titrePage.contentEquals("PagePrincipale")) {
			setUpDeconnexion();
		} else if (this.titrePage.contentEquals("Tricket")) {	
			;
		} else if ((this.titrePage.contentEquals("PageConnexion")) | (this.titrePage.contentEquals("PageInscription")) ) {
			setUpRetourInit();
		}else {
			setUpRetour();
		}
		setUpParamatre();
	}
	private void setUplogo() {
		String imageParametre = "/home/cgrethen/tob/MN-1/doc/logo3.png";
        	BufferedImage buffIm;
		try {
			buffIm = ImageIO.read(new File(imageParametre));
			JLabel image = new JLabel(new ImageIcon(buffIm));
			image.setBounds(this.largeurPage - 5 - 50, 15, 60, 60);
			panel.add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setUpTitre() {
		JLabel titre = new JLabel(this.titrePage);
		titre.setBounds(this.largeurPage/2 - 250, 20, 500, 60);
		titre.setFont(new Font("Rubik", Font.BOLD, 40));
		titre.setForeground(Color.BLUE);
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon logo= new ImageIcon("/home/cgrethen/tob/doc/logo.png");

		this.panel.add(titre);
	}
	
	private void setUpRetour() {
		String imageRetour = "/home/cgrethen/tob/MN-1/src/retour.png";
        	BufferedImage buffIm;
		try {
			buffIm = ImageIO.read(new File(imageRetour));
			JLabel image = new JLabel(new ImageIcon(buffIm));
			image.setBounds(47, 15, 60, 60);
			panel.add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 JButton boutonRetour = new JButton("Retour");
		 boutonRetour.setBounds(20, 85, 115, 32);
		 boutonRetour.setBackground(Color.GRAY);
		 boutonRetour.setForeground(Color.WHITE);
		 boutonRetour.setFocusPainted(false);
		 boutonRetour.setFont(new Font("Rubik", Font.BOLD, 16));
		 this.panel.add(boutonRetour);
		 
		 boutonRetour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ev) {
			    	cacherPage();
			    	PagePrincipale2 page = new PagePrincipale2(1000, 500 ,"Page Principale", mesTickets);
			    	page.afficherPage();
			    }
		 });
	}
	
	private void setUpRetourInit() {
		String imageRetour = "/home/cgrethen/tob/MN-1/src/retour.png";
        	BufferedImage buffIm;
		try {
			buffIm = ImageIO.read(new File(imageRetour));
			JLabel image = new JLabel(new ImageIcon(buffIm));
			image.setBounds(47, 15, 60, 60);
			panel.add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 JButton boutonRetour = new JButton("Retour");
		 boutonRetour.setBounds(20, 85, 115, 32);
		 boutonRetour.setBackground(Color.GRAY);
		 boutonRetour.setForeground(Color.WHITE);
		 boutonRetour.setFocusPainted(false);
		 boutonRetour.setFont(new Font("Rubik", Font.BOLD, 16));
		 this.panel.add(boutonRetour);
		 
		 boutonRetour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ev) {
			    	cacherPage();
			    	InterfacePrincipale page = new InterfacePrincipale();
			    }
		 });
	}
	
	private void setUpDeconnexion() {
		String imageRetour = "/home/cgrethen/tob/MN-1/src/retour.png";
        	BufferedImage buffIm;
		try {
			buffIm = ImageIO.read(new File(imageRetour));
			JLabel image = new JLabel(new ImageIcon(buffIm));
			image.setBounds(47, 15, 60, 60);
			panel.add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 JButton boutonRetour = new JButton("Deconnexion");
		 boutonRetour.setBounds(20, 85, 115, 32);
		 boutonRetour.setBackground(Color.GRAY);
		 boutonRetour.setForeground(Color.WHITE);
		 boutonRetour.setFocusPainted(false);
		 boutonRetour.setFont(new Font("Rubik", Font.BOLD, 16));
		 this.panel.add(boutonRetour);
		 
		 boutonRetour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ev) {
			    	cacherPage();
			    	InterfacePrincipale page = new InterfacePrincipale();
			    }
		 });
	}
	
	private void setUpParamatre() {
		String imageParametre = "/home/cgrethen/tob/MN-1/src/parametre.png";
        	BufferedImage buffIm;
		try {
			buffIm = ImageIO.read(new File(imageParametre));
			JLabel image = new JLabel(new ImageIcon(buffIm));
			image.setBounds(this.largeurPage - 72 - 50, 15, 60, 60);
			panel.add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 JButton boutonParametre = new JButton("Parametres");
		 boutonParametre.setBounds(this.largeurPage - 115 - 35, 85, 115, 32);
		 boutonParametre.setBackground(Color.GRAY);
		 boutonParametre.setForeground(Color.WHITE);
		 boutonParametre.setFocusPainted(false);
		 boutonParametre.setFont(new Font("Rubik", Font.BOLD, 15));
		 this.panel.add(boutonParametre);
		 
		 boutonParametre.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent ev) {
			    	cacherPage();
			    	PagePrincipale2 page = new PagePrincipale2(1000, 500 ,"Page Principale");
			    	page.afficherPage();
			    }
		 });
	}
	
	
	public void afficherPage() {
        	this.frame.setVisible(true);
	}
	
	public void cacherPage() {
		this.frame.setVisible(false);
	}
}

