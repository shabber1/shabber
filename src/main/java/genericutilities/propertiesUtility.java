package genericutilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * This class contains reusable methods to read data from properties file
 */
public class propertiesUtility {
	private Properties property;
	/**
	 * This method initializes properties file
	 * @param filePath
	 */
	public void PropertiesInit(String filePath) {
		FileInputStream fis = null;
		try {
			fis=new FileInputStream(filePath);
			}
		catch(FileNotFoundException e)
			{
			e.printStackTrace();
			}
			property=new Properties();
		try {
			property.load(fis);
			}
			catch(IOException e)
			{
			e.printStackTrace();
			}
	}
	/**
	 * This method fetches values of the key specified from properties file
	 * @param key
	 * @return String
	 */
	public String readfromperperties(String key) {
		return property.getProperty(key);
	}
}