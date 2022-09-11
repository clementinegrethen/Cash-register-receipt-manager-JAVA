import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ChartPanel extends JPanel {
	
	
	private double[] values;
	
	private String[] names;
	
	
	public ChartPanel(Historique historique) {
		double[] tab= new double[15];
		names=new String[15];
		names[1]="boisson";
		names[2]="produit sucré";
		names[3]="viande et poisson";
		
		names[4]="hygiène";
		names[5]="entretien";
		names[6]="alcool";
		names[7]="alimentes protéinés";
		names[8]="céréales et légumineux";
		names[9]="légume";
		names[10]="fruit";
		names[11]="produit laitier";
		names[12]="condiment";
		names[13]="almt protéinés";
		names[14]="bureau";
		for (int i=1;i<=historique.getNbValeurs()-1;i++){
			tab=somme(tab,historique.getValeur(i).repartitionAchat());
			
		}
		values=tab;
		
	}
	
	
	
	public double[] somme(double[] t1,double[] t2) {
		for (int i=1;i<=14;i++) {
			t1[i]+=t2[i];
		}
		return t1;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	    if (values == null || values.length == 0)
	      return;

	    double minValue = 0;
	    double maxValue = 0;

	    for (int i = 0; i < values.length; i++) {
	      if (minValue > values[i])
	    	  
	        minValue = values[i];

	      if (maxValue < values[i])
	        maxValue = values[i];
	    }

	    Dimension d = getSize();
	    int clientWidth = d.width;
	    int clientHeight = d.height;
	    int barWidth = clientWidth / values.length;

	    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
	    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
	    Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
	    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

	    int titleWidth = titleFontMetrics.stringWidth("Répartition des achats du mois de mai");
	    int y = titleFontMetrics.getAscent();
	    int x = (clientWidth - titleWidth) / 2;
	    g.setFont(titleFont);
	    g.drawString("Répartition des achats du mois de mai", x, y);

	    int top = titleFontMetrics.getHeight();
	    int bottom = labelFontMetrics.getHeight();

	    if (maxValue == minValue)
	      return;
	    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
	    y = clientHeight - labelFontMetrics.getDescent();
	    g.setFont(labelFont);

	    for (int i = 1; i <values.length; i++) {
	      int valueX = i * barWidth + 1;
	      int valueY = top;
	      int height = (int) (values[i] * scale);

	      if (values[i] >= 0)
	        valueY += (int) ((maxValue - values[i]) * scale);
	      else {
	        valueY += (int) (maxValue * scale);
	        height = -height;
	      }

	      g.setColor(Color.red);
	      g.fillRect(valueX, valueY, barWidth - 2, height);
	      g.setColor(Color.black);
	      g.drawRect(valueX, valueY, barWidth - 2, height);

	      int labelWidth = labelFontMetrics.stringWidth(names[i]);
	      x = i * barWidth + (barWidth - labelWidth) / 2;
	      g.drawString(names[i], x, y);
	      double prix=(double) (Math.round(values[i]*100.0)/100.0);
	      g.setColor(Color.black);
	      g.drawString(String.valueOf(prix)+"€",x,y-40);
	    }
	  }
	
	public static void main(String[] args) {
		Date date1=new Date(5,14,2021,8,35);
		Date date2=new Date (18,12,2022,9,9);
		Date date3=new Date (9,12,2021,10,9);
		String ExcelPath=args[0];
		Workbook wb;
		Sheet sh;
	
		try(InputStream is = new FileInputStream(new File(ExcelPath))) {
			
			WindowListener wndCloser = new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        System.exit(0);
			      }
			    };
			
			
			wb=WorkbookFactory.create(is);

			sh=wb.getSheetAt(0);
			TicketCaisse ticket1=new TicketCaisse (sh,date1);
			TicketCaisse ticket2=new TicketCaisse(sh,date2);
			TicketCaisse ticket3=new TicketCaisse(sh,date3);

			Historique historique_test= new Historique();

			historique_test.enregistrer(ticket1);
			historique_test.enregistrer(ticket2);
			historique_test.enregistrer(ticket3);
	        //System.out.println(historique_test.getValeur(2).repartitionAchat()[6]);

			JFrame f = new JFrame();
			f.setSize(900, 700);
			f.getContentPane().add(new ChartPanel(historique_test));
			f.addWindowListener(wndCloser);
		    f.setVisible(true);
			

			    
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		


	    
	
	
	}
	

}
