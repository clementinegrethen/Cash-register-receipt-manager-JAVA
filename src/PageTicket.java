import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PageTicket {

	protected int largeurPage;
	protected int hauteurPage;
	protected String titrePage;
	protected JFrame frame;
	protected JPanel panel;
	protected TicketCaisse ticket;
	
	// Constructeur ticket 
	public PageTicket(TicketCaisse ticket) {
		this.largeurPage = 700;
		this.hauteurPage = 700;
		String titre = ticket.getDate().ToString();
		titre = titre.replaceAll("date:", "Ticket du ");
		this.titrePage = titre;
		this.ticket = ticket;
		this.panel = new JPanel();
		setUpTitre();
		setUpPage1();
		setUpInfos();
	}

	// Constructeur ticket prix minimum
	public PageTicket(TicketCaisse ticket, TicketCaisse ticketOpti) {
		this.largeurPage = 700;
		this.hauteurPage = 700;
		String titre = ticket.getDate().ToString();
		titre = titre.replaceAll("date:", "Ticket du ");
		this.titrePage = titre;
		this.ticket = ticketOpti;
		this.panel = new JPanel();
		setUpTitre();
		setUpPage2();
		setUpInfosOpti(ticket);
	}
	
	private void setUpTitre() {
		JLabel titre = new JLabel(this.titrePage);
		titre.setBounds((this.largeurPage - 300) / 2, 20, 300, 60);
		titre.setFont(new Font("Rubik", Font.BOLD, 25));
		titre.setForeground(Color.BLUE);
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setVerticalAlignment(SwingConstants.CENTER);
		this.panel.add(titre);
	}
	
	private void setUpPage1() {
		
		this.frame = new JFrame(this.titrePage);

		this.frame.setSize(this.largeurPage, this.hauteurPage);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.frame.add(this.panel);
		panel.setBackground(Color.LIGHT_GRAY);
		this.panel.setLayout(null);
		
		String ticket = "   ";
		int taille = this.ticket.getProduit().length;
        for (int i = 0; i <= taille - 1; i++) {
            ticket += this.ticket.getProduit()[i].ToString() + "€\n   ";
        }
        ticket = ticket.replaceAll("€", "");
        ticket += "Prix total : " + Math.round(this.ticket.getPrix() * 100.0) / 100.0 + "€\n   ";
        
		JTextArea texte = new JTextArea(ticket);
		texte.setFont(new Font("Rubik", Font.BOLD, 14));
		texte.setForeground(Color.BLACK);
		texte.setBackground(Color.LIGHT_GRAY);
		texte.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(
                texte,                          //Le contenu du JScrollPane
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,    //La barre verticale toujours visible
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //La barre horizontale toujours visible
		scrollPane.setBounds(20, 80, this.largeurPage - 50, this.hauteurPage - 200);
		this.panel.add(scrollPane);
	}
private void setUpPage2() {
		
		this.frame = new JFrame(this.titrePage);

		this.frame.setSize(this.largeurPage, this.hauteurPage);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.frame.add(this.panel);
		panel.setBackground(Color.LIGHT_GRAY);
		this.panel.setLayout(null);
		
		String ticket = "   ";
		int taille = this.ticket.getProduit().length;
        for (int i = 0; i <= taille - 1; i++) {
            ticket += this.ticket.getProduit()[i].ToString2() + "€\n   ";
        }
        ticket = ticket.replaceAll("€", "");
        ticket += "Prix total avec les nouveaux produits: " + Math.round(this.ticket.getPrix() * 100.0) / 100.0 + "€\n   ";

		JTextArea texte = new JTextArea(ticket);
		texte.setFont(new Font("Rubik", Font.BOLD, 14));
		texte.setForeground(Color.BLACK);
		texte.setBackground(Color.LIGHT_GRAY);
		texte.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(
                texte,                          //Le contenu du JScrollPane
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,    //La barre verticale toujours visible
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //La barre horizontale toujours visible
		scrollPane.setBounds(20, 80, this.largeurPage - 50, this.hauteurPage - 200);
		this.panel.add(scrollPane);
	}
	private void setUpInfos() {
		String description = "Prix : " + String.valueOf(this.ticket.getPrix())
		+ "€   --  " + this.ticket.GetMagasin().getNom()
		+ "  --  " + this.ticket.getDate().ToString();
		JLabel infos = new JLabel(description);
		infos.setBounds(20, 500, 600, 60);
		infos.setFont(new Font("Rubik", Font.BOLD, 20));
		infos.setForeground(Color.BLACK);
		infos.setHorizontalAlignment(SwingConstants.LEFT);
		infos.setVerticalAlignment(SwingConstants.CENTER);
		this.panel.add(infos);
		
		BufferedImage buffIm;
		String imageParametre = "";
		switch (this.ticket.NutriScoreMoyen()) {
			case "A" :				
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreA.png";
				break;
			case "B" :
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreA.png";
				break;
			case "C":
				 imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreC.png";
				break;
			case "D":
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreD.png";
				break;
			case "E":
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreE.png";
				break;
			default:
		}
		try {
			buffIm = ImageIO.read(new File(imageParametre));
			JLabel image1 = new JLabel(new ImageIcon(buffIm));
			image1.setBounds(20, 560, 150, 81);
			this.panel.add(image1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch (this.ticket.ecoScoreMoyen()) {
		case "A" :				
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreA.png";
			break;
		case "B" :
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreB.png";
			break;
		case "C":
			 imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreC.png";
			break;
		case "D":
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreD.png";
			break;
		case "E":
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreE.png";
			break;
		default:
	}
	try {
		buffIm = ImageIO.read(new File(imageParametre));
		JLabel image2 = new JLabel(new ImageIcon(buffIm));
		image2.setBounds(480, 560, 300, 83);
		this.panel.add(image2);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	private void setUpInfosOpti(TicketCaisse ticket) {
		double prixOpti = Math.round(100.0 * this.ticket.getPrix()) / 100.0;
		double economie = ticket.getPrix() - prixOpti;
		String description = "Prix : " + String.valueOf(prixOpti)
		+ "€   --  " +  this.ticket.getDate().ToString()
		+ "  --> -" + economie + "€";
		JLabel infos = new JLabel(description);
		infos.setBounds(20, 500, 600, 60);
		infos.setFont(new Font("Rubik", Font.BOLD, 20));
		infos.setForeground(Color.BLACK);
		infos.setHorizontalAlignment(SwingConstants.LEFT);
		infos.setVerticalAlignment(SwingConstants.CENTER);
		this.panel.add(infos);
		
		BufferedImage buffIm;
		String imageParametre = "";
		switch (ticket.NutriScoreMoyen()) {
			case "A" :				
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreA.png";
				break;
			case "B" :
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreA.png";
				break;
			case "C":
				 imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreC.png";
				break;
			case "D":
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreD.png";
				break;
			case "E":
				imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\nutriscoreE.png";
				break;
			default:
		}
		try {
			buffIm = ImageIO.read(new File(imageParametre));
			JLabel image1 = new JLabel(new ImageIcon(buffIm));
			image1.setBounds(20, 560, 150, 81);
			this.panel.add(image1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch (ticket.ecoScoreMoyen()) {
		case "A" :				
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreA.png";
			break;
		case "B" :
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreB.png";
			break;
		case "C":
			 imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreC.png";
			break;
		case "D":
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreD.png";
			break;
		case "E":
			imageParametre = "C:\\Users\\33766\\eclipse-workspace\\Ticketsv2\\src\\EcoscoreE.png";
			break;
		default:
	}
	try {
		buffIm = ImageIO.read(new File(imageParametre));
		JLabel image2 = new JLabel(new ImageIcon(buffIm));
		image2.setBounds(480, 560, 300, 83);
		this.panel.add(image2);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public void afficherPage() {
    	this.frame.setVisible(true);
	}
	
	public void cacherPage() {
		this.frame.setVisible(false);
}
}
