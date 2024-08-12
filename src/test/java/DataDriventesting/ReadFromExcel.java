package DataDriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//1.convert physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/testdata.xlsx");
		
		//2.open workbook
		Workbook wb=WorkbookFactory.create(fis);
		
		//get control to the sheet
		Sheet sheet=wb.getSheet("Sheet1");
		
		//get control on row
		Row row=sheet.getRow(0);
		
		//get control on cell
		Cell cell=row.getCell(1);
		
		//fetch the value
		System.out.println(cell.getStringCellValue());
		
		//direct method using MethodChaining
		String data=wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		System.out.println(data);
	}

}
