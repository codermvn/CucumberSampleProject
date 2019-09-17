/**
 * 
 */
package org.tcs.cobra.parent.cleanup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 238630
 *
 */
public class Reported {

	/**
	 * 
	 */
	public Reported() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)   {
		// TODO Auto-generated method stub
		
		String format = "yyyyMMdd";
		DateFormat dateFormatter = new SimpleDateFormat(format);
		String date= dateFormatter.format(new Date());
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH mm ss");
		String time = dateFormat.format(new Date());
		
		try {
			File destinationFolder = new File("results\\"+date);
			String finalPath = null;
			if(!destinationFolder.exists())
				destinationFolder.mkdir();
			
			 finalPath=destinationFolder.getAbsolutePath()+"\\"+time;
			System.out.println(finalPath);
			
			Files.move(new File("B2BNeo\\target\\cucumber-report-html").toPath(), new File(finalPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
