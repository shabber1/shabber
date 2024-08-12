package genericutilities;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * This class contains reusable methods to perform java related operations
 * @author shabber
 */

public class javaUtility {
	
		/**
		 * 
		 * @param limit
		 * @return
		 */
		public int generateRandomNum(int limit) {
			Random random =new Random();
			return random.nextInt(limit);
		}
		public void waiting(long time) {
			try {
				Thread.sleep(time);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		/**
		 * this metod return system data and time in string format
		 * @return String
		 */
		
		public String getCurrentTime() {
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
			return sdf.format(date);
			
		}
		/**
		 * this method convert string to any data type
		 * @param data
		 * @paramdatatype
		 * @return
		 */
		public Object convertStringToAntDataType(String data,String Datatype) {
			Object obj=null;
			switch(Datatype) {
			case "int":
			obj=Integer.parseInt(Datatype);
			break;
			case "float":
				obj=Float.parseFloat(Datatype);
				break;
			case "double":
				obj=Double.parseDouble(Datatype);
				break;
			case "long":
				obj=Long.parseLong(Datatype);
				break;
			default:
				System.out.println("invalid data type");
			}
			return obj;
		}
		public Object convertStringToAnyDataType(String data, DataType datatype) {
			Object obj = null;

			if (datatype.toString().equalsIgnoreCase("int"))
				obj = Integer.parseInt(data);
			else if (datatype.toString().equalsIgnoreCase("float"))
				obj = Float.parseFloat(data);
			else if (datatype.toString().equalsIgnoreCase("double"))
				obj = Double.parseDouble(data);
			else if (datatype.toString().equalsIgnoreCase("long"))
				obj = Long.parseLong(data);
			else
				System.out.println("Invalid Data Type");

			return obj;
		}
		/**
		 * this method converts month name to integer
		 * @param month
		 * @return
		 */
		public int convertMonthToInt(String month) {
			return DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH).parse(month).get(ChronoField.MONTH_OF_YEAR);
		}
		/**
		 * this method splits a string based on the split strategy provided
		 * @param str
		 * @param splitStrategy
		 * @return
		 */
		public String[] splitString(String str,String splitStrategy) {
			return str.split(splitStrategy);
		}
}
