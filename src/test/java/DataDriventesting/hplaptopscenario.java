package DataDriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class hplaptopscenario {

	public static void main(String[] args) throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hp.com/in-en/shop/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("search")).sendKeys("laptops");
		driver.findElement(By.xpath("//a[@title='Search']")).submit();
		List<WebElement> laptops=driver.findElements(By.xpath("//li[@class='item product product-item g-col-4 g-col-xl-4 g-col-lg-6']"));
		
		FileInputStream fis=new FileInputStream("./src/test/resources/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
//		
		for(int i=0;i<laptops.size();i++) {
			
			System.out.println(laptops);}
//			String price=driver.findElement(By.xpath("//div[text()='"+name+"']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']")).getText();
//			wb.getSheet("Sheet3").createRow(i).createCell(0).setCellValue(name);
//			wb.getSheet("Sheet3").getRow(i).createCell(1).setCellValue(price);
//		}
//		FileOutputStream fos=new FileOutputStream("./src/test/resources/testdata.xlsx");
//		wb.write(fos);
//		wb.close();
	driver.quit();

	}

}
