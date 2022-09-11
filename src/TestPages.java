import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestPages {
	
	public static void main(String[] args) {
//		PageBase maPage = new PageBase(800, 600, "Mes tickets");
//		maPage.afficherPage();
		Date date1=new Date(5,14,2021,8,35);
		System.out.println(date1.ToString());
		String ExcelPath=args[0];
		Workbook wb;
		Sheet sh; 
		Historique mesTickets = new Historique();
		int nbTickets = 20;
		
		try(InputStream is = new FileInputStream(new File(ExcelPath))) {

			wb=WorkbookFactory.create(is);
			sh=wb.getSheetAt(0);
			
			for (int i = 0; i < nbTickets; i++) {
				mesTickets.enregistrer(new TicketCaisse(sh, randomDate()));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		
		PageHistorique Page = new PageHistorique(800, 600, "Mes tickets", mesTickets);
		Page.afficherPage();
		
//		PageTicket testTicket = new PageTicket(mesTickets.getValeur(0));
//		 testTicket.afficherPage();
	}
	
	public static Date randomDate() {
		Random rn = new Random();
		return new Date(rn.nextInt(27) + 1, rn.nextInt(11) + 1, 2021, rn.nextInt(22) + 1, rn.nextInt(58) + 1);
	}
	
}

