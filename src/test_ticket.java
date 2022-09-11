

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Random;


public class test_ticket {
	
	public static void main(String[] args) {
		
		Date date1=new Date(5,14,2021,8,35);
		System.out.println(date1.ToString());
		String ExcelPath=args[0];
		Workbook wb;
		Sheet sh;
		

		try(InputStream is = new FileInputStream(new File(ExcelPath))) {

			wb=WorkbookFactory.create(is);

			sh=wb.getSheetAt(0);
			TicketCaisse ticket=new TicketCaisse (sh,date1);
			ticket.afficher();
			
			ticket.ticketMin(sh);
		}catch (Exception e) {

			e.printStackTrace();
		}
		

;	}
	

}
