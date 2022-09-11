import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PagePrincipale2 extends PageBase {
	
	protected Historique mesTickets;
	private String excel; 
	public PagePrincipale2(int largeur, int hauteur, String titre) {
		super(largeur,hauteur,titre);
		this.excel = "/home/cgrethen/tob/MN-1/doc/bdd-tob.xlsx";
		this.mesTickets = createurHistorique();
		setUpPage(super.frame,super.panel);
	}
	
	public PagePrincipale2(int largeur, int hauteur, String titre, Historique historique) {
		super(largeur,hauteur,titre);
		this.excel = "/home/cgrethen/tob/MN-1/doc/bdd-tob.xlsx";
		this.mesTickets = historique;
		setUpPage(super.frame,super.panel);
	}
	
	private void setUpPage(JFrame frame,JPanel panel) {
		panel.setLayout(null);
		
		JButton ticketsButton = new JButton("Mes Tickets");
		ticketsButton.setFocusPainted(false);
        ticketsButton.setBounds(200, 150, 600, 140);
        ticketsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		cacherPage();
        		PageHistorique PageHist = new PageHistorique(800, 600, "Mes tickets", mesTickets);
        		PageHist.afficherPage();
        	}
        });
        panel.add(ticketsButton);
        
//        JButton supprimerButton = new JButton("supprimer un ticket");
//        supprimerButton.setBounds(0, 300, 190, 50);
//        panel.add(supprimerButton);
        JLabel confirmation = new JLabel("Ticket ajouté.", JLabel.CENTER);
        confirmation.setBounds(200, 370, 190, 50);
        JButton ajouterButton = new JButton("Ajouter un ticket");
        ajouterButton.setFocusPainted(false);
        ajouterButton.setBounds(200, 300, 190, 50);
        ajouterButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		String ExcelPath = excel;
        		Workbook wb;
        		Sheet sh; 
        		try(InputStream is = new FileInputStream(new File(ExcelPath))) {

        			wb=WorkbookFactory.create(is);
        			sh=wb.getSheetAt(0);
        			mesTickets.enregistrer(new TicketCaisse(sh, randomDate()));
        		} catch (Exception e) {

        			e.printStackTrace();
        		}
        		panel.add(confirmation);
        		panel.revalidate();
        		panel.repaint();
        		//try {
					//wait(1000);
				//} catch (InterruptedException e) {
				//}
        		//panel.remove(confirmation);
        		//panel.revalidate();
        		//panel.repaint();
        	}
        });
        panel.add(ajouterButton);
		
        JButton nutriButton = new JButton("Mes bilans");
        nutriButton.setFocusPainted(false);
        nutriButton.setBounds(610, 300, 190, 50);
        nutriButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		Workbook wb;
        		Sheet sh;
        		String ExcelPath = excel;

        		try(InputStream is = new FileInputStream(new File(ExcelPath))) {
        			
        			WindowListener wndCloser = new WindowAdapter() {
        			      public void windowClosing(WindowEvent e) {
        			        System.exit(0);
        			      }
        			    };
        			
        			
        			wb=WorkbookFactory.create(is);

        			sh=wb.getSheetAt(0);
      

        			JFrame f = new JFrame();
        			f.setSize(900, 700);
        			f.getContentPane().add(new ChartPanel(mesTickets));
        			f.addWindowListener(wndCloser);
        		    f.setVisible(true);
        			

        			    
        			
        		} catch (Exception e) {

        			e.printStackTrace();
        		}
        		
        	}
        });
        panel.add(nutriButton);
        
	
	}
	
	public Historique createurHistorique() {
		String ExcelPath=this.excel;
		Workbook wb;
		Sheet sh; 
		Historique historique = new Historique();
		int nbTickets = 20;
		
		try(InputStream is = new FileInputStream(new File(ExcelPath))) {

			wb=WorkbookFactory.create(is);
			sh=wb.getSheetAt(0);
			
			for (int i = 0; i < nbTickets; i++) {
				historique.enregistrer(new TicketCaisse(sh, randomDate()));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return historique;
	}
	
	public static Date randomDate() {
		Random rn = new Random();
		return new Date(rn.nextInt(11) + 1, rn.nextInt(27) + 1, 2021, rn.nextInt(22) + 1, rn.nextInt(58) + 1);
	}
}