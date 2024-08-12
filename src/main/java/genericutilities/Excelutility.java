package genericutilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * this class contains reusable methods to read/write data from excel
 */
public class Excelutility {
		private Workbook wb;
		private DataFormatter df;
		/**
		 * this method initilizes excel
		 * @param excelPath
		 */
		public void excelInit(String excelPath) {
			FileInputStream fis = null;
			try {
				fis=new FileInputStream(excelPath);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				wb=WorkbookFactory.create(fis);
			}catch(EncryptedDocumentException | IOException e) {
				e.printStackTrace();
			}
			df=new DataFormatter();
		}
		/**
		 * this method fetches data from specified cell
		 * @param sheetname
		 * @param rownum
		 * @param cellnum
		 * @return String
		 */
		public String readfromexcel(String sheetName, int rowNum, int cellNum) {
			return df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		}
		/**
		 * this method fetches data required for specified test case
		 * @param sheetName
		 * @param expectedTestName
		 * @return
		 */
		public Map<String, String> readfromexcel(String sheetName, String expectedTestName) {
			Map<String, String>map=new HashMap<>();
			Sheet sheet =wb.getSheet(sheetName);
			int requiredRowNum =0;
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				requiredRowNum =i;
				if(df.formatCellValue(sheet.getRow(i).getCell(1)).equalsIgnoreCase(expectedTestName))
					break;
			}
			for (int i=requiredRowNum; i<=sheet.getLastRowNum();i++) {
				if(df.formatCellValue(sheet.getRow(i).getCell(2)).equals("####"))
					break;
				map.put(df.formatCellValue(sheet.getRow(i).getCell(2)),df.formatCellValue(sheet.getRow(i).getCell(3)));
			}
			return map;
		}
		/**
		 * this method writes to excel
		 * @param sheetName
		 * @param expectedTestName
		 */
		public void writetoexcel(String sheetName, String expectedTestName, String status) {
			Sheet sheet = wb.getSheet(sheetName);
			for(int i=0; i<=sheet.getLastRowNum();i++) {
				if(df.formatCellValue(sheet.getRow(i).getCell(2)).equalsIgnoreCase(expectedTestName)) {
					sheet.getRow(i).createCell(4).setCellValue(status);
				break;
				}
			}
		}
		/**
		 * this method save the excel
		 * @param excelPath
		 */
	public void saveexcel(String excelPath) {
		FileOutputStream fos = null;
		try {
			fos=new FileOutputStream(excelPath);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method closes the workbook
	 */
	public void closeexcel() {
		try {
			wb.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}