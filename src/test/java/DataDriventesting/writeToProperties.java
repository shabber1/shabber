package DataDriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hpsf.Property;

public class writeToProperties {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		prop.put("subject","selenium");
		FileOutputStream fos=new FileOutputStream("./src/test/resources/data.properties");
		prop.store(fos, "updated successfully");

	}

}
