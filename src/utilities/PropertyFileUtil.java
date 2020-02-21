package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {

	
	public  static String getValueForKey(String Key) throws Exception{
		
		Properties p=new Properties();
		FileInputStream fis=new FileInputStream("D:\\New folder (2)\\tanya\\StockAccounting_hybrid\\PropertiesFile\\Environment.properties");
				p.load(fis);
		return p.getProperty(Key);
	}
}
