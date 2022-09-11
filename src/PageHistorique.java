

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

import org.apache.poi.ss.usermodel.Sheet;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PageHistorique extends PageBase {

	private int nbTicketsAffiches;
	protected boolean ordreTri;
	protected int tri;
	protected Point position;
	
	public PageHistorique(int largeur, int hauteur, String titre, Historique historique) {
		super(largeur, hauteur, titre, historique);
		this.position = new Point(250, 250);
		this.nombreTickets();
		this.ordreTri = true;
		this.tri = 1;
		setUpPage();
	}
	
	public PageHistorique(int largeur, int hauteur, String titre, Historique historique, int tri, boolean croissant, Point position) {
		super(largeur, hauteur, titre, historique);
		this.position = position;
		this.nombreTickets();
		this.ordreTri = croissant;
		this.tri = tri;
		setUpPage();
	}


	private void setUpPage() {
		super.frame.setLocation(this.position);
		setUpSlots();
		setUpBoutton();
	}
	
	private void setUpSlots() {
		ArrayList<Integer> indices_tries = null;
		switch (this.tri) {
			case 1 : indices_tries = this.triPrix();
			break;
			case 2 : indices_tries = this.triDate();
			break;
			case 3 : indices_tries = this.triMagasin();
			break;
		}
		
		if (!this.ordreTri) { indices_tries = triDecroissant(indices_tries);}
		
		int i = 0;
		while (i < this.nbTicketsAffiches && i < super.mesTickets.getNbValeurs()) {
			JLabel slots = new JLabel();
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			slots.setBounds(20, 200 + 30*i, super.largeurPage * 3/4, 30);
			slots.setOpaque(true);
			slots.setBackground(Color.WHITE);
			slots.setBorder(border);
			slots.setFont(new Font("Rubik", Font.BOLD, 14));
			String description = "Prix : " + String.valueOf(super.mesTickets.getValeur(indices_tries.get(i)).getPrix())
			+ "€   --  " + super.mesTickets.getValeur(indices_tries.get(i)).GetMagasin().getNom()
			+ "  --  " + super.mesTickets.getValeur(indices_tries.get(i)).getDate().ToString();
			slots.setText(description);
			final int indice = i;
			final ArrayList<Integer> array_indices = indices_tries;
			slots.addMouseListener((MouseListener) new MouseListener() 
			{
				public void mouseClicked(MouseEvent e) {
					PageTicket page = new PageTicket(mesTickets.getValeur(array_indices.get(indice)));
					page.afficherPage();
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}	
			});
			super.panel.add(slots);
			
			
			BufferedImage buttonIcon = null;
			try {
				buttonIcon = ImageIO.read(new File("/home/cgrethen/tob/MN-1/doc/comparateur30x30.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JButton button = new JButton(new ImageIcon(buttonIcon));
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setContentAreaFilled(false);
			button.setBounds(50 + super.largeurPage * 3/4, 200 + 30*i, 30, 30);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TicketCaisse ticketOpti = new TicketCaisse(mesTickets.getValeur(array_indices.get(indice)).getDate(), mesTickets.getValeur(array_indices.get(indice)).getExcel());
					//System.out.print(mesTickets.getValeur(array_indices.get(indice)).getNb());
					for (int i = 0; i < mesTickets.getValeur(array_indices.get(indice)).getNb(); i++) {
//						System.out.print(mesTickets.getValeur(array_indices.get(indice)).GetMagasin().getNom());
//						System.out.print(mesTickets.getValeur(array_indices.get(indice)).getProduit()[i].ToString());
//						System.out.print(mesTickets.getValeur(array_indices.get(indice)).GetMagasin().getNom());
						ticketOpti = ticketOpti.ajouterProduit(
								mesTickets.getValeur(array_indices.get(indice)).produixMin(
										mesTickets.getValeur(array_indices.get(indice)).getProduit()[i], 
										mesTickets.getValeur(array_indices.get(indice)).GetMagasin().getNom(), 
										mesTickets.getValeur(array_indices.get(indice)).getExcel())
								);
					}
					//System.out.print("\n" + ticketOpti.getNb());
					PageTicket page = new PageTicket(mesTickets.getValeur(array_indices.get(indice)), ticketOpti);
					page.afficherPage();
				}
			});
			super.panel.add(button);
			
			i++;
		}
	}
	
	private void setUpBoutton() {
		JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem menu1 = new JMenuItem("Date");
		menu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raffraichirPage(2, ordreTri);
			}
		});
		popupMenu.add(menu1);
		 
		JMenuItem menu2 = new JMenuItem("Prix");
		menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raffraichirPage(1, ordreTri);
			}
		});
		popupMenu.add(menu2);
		 
//		JMenuItem menu3 = new JMenuItem("Magasin");
//		menu3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				raffraichirPage(3, ordreTri);
//			}
//		});
//		popupMenu.add(menu3);
		
		JButton dropDownButton = new JButton("Trier par :");
		dropDownButton.setBounds(20, 150, 115, 30);  //super.largeurPage * 3/8 - 105 / 2 (milieu)
		dropDownButton.setForeground(Color.BLACK);
		dropDownButton.setFocusPainted(false);
		dropDownButton.setFont(new Font("Rubik", Font.BOLD, 14));
		dropDownButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	popupMenu.show(dropDownButton, 0, 30);
		    }
		});
		super.panel.add(dropDownButton);
		
		JButton BouttonOrdre = new JButton("changer ordre");
		BouttonOrdre.setBounds(super.largeurPage - 170, 150, 140, 30);
		BouttonOrdre.setBackground(Color.LIGHT_GRAY);
		BouttonOrdre.setForeground(Color.BLACK);
		BouttonOrdre.setFocusPainted(false);
		BouttonOrdre.setFont(new Font("Rubik", Font.BOLD, 14));
		BouttonOrdre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	raffraichirPage(1, !ordreTri);
		    }
		});
		super.panel.add(BouttonOrdre);
	}
	
	private void nombreTickets() {
		int i = 1;
		while (200 + 30*i < super.hauteurPage - 30) {
			i++;
		}
		this.nbTicketsAffiches = i;
	}
	
	private ArrayList<Integer> triPrix() {
		
		ArrayList<Integer> indices_tries = new ArrayList<Integer>();
		
		for (int j = 0; j < super.mesTickets.getNbValeurs(); j++) {
			int indice_min = 0;
			double min = 10000f;
			for (int i = 0; i < super.mesTickets.getNbValeurs(); i++) {
				double prix = super.mesTickets.getValeur(i).getPrix();
				if (prix < min && !indices_tries.contains(i)) {
					min = prix;
					indice_min = i;
					//System.out.print(prix + "\n");
					//System.out.print(indice_min + "\n");
				}
			}
			indices_tries.add(indice_min);
			//System.out.print("ticket moins cher : " + indice_min + "\n");
		}
		return indices_tries;
	}
	
	private ArrayList<Integer> triDate() {
		ArrayList<Integer> indices_tries = new ArrayList<Integer>();
		
		for (int j = 0; j < super.mesTickets.getNbValeurs(); j++) {
			int indice_min = 0;
			Date dateMin = new Date(1, 1, 1789, 1, 1);
			for (int i = 0; i < super.mesTickets.getNbValeurs(); i++) {
				Date date = super.mesTickets.getValeur(i).getDate();
				if (date.egalDate(date.triDate(dateMin, date),date) && !indices_tries.contains(i)) {
					indice_min = i;
					dateMin = date;
					//System.out.print(indice_min + "\n");
					}
				}
			indices_tries.add(indice_min);
		}
		return indices_tries;
	}
	
	private ArrayList<Integer> triMagasin() {
		return null;
	}
	
	private ArrayList<Integer> triDecroissant(ArrayList<Integer> indices_croissants) {
		ArrayList<Integer> indices_decroissants = new ArrayList<Integer>();
		for (int i = 1; i <= super.mesTickets.getNbValeurs(); i++) {
			int j = super.mesTickets.getNbValeurs() - i;
			indices_decroissants.add(indices_croissants.get(j));
		}
		return indices_decroissants;
	}
	
	private void raffraichirPage(int tri, boolean croissant) {
		Point dernierePosition = super.frame.getLocationOnScreen();
		super.frame.dispose();
		PageHistorique Page = new PageHistorique(super.largeurPage, super.hauteurPage, 
				super.titrePage, super.mesTickets, tri, croissant, dernierePosition);
		Page.afficherPage();
	}
}

