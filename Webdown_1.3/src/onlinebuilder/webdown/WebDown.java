/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class WebDown
{
	private static Scanner sc;
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		sc = new Scanner(System.in);
		Database.setWebdownDirectory(new AdvancedDirectory("/media/codeseeker508/Extern/WebDown"));
		Database.queryValidInitialDirectory(sc);
		Database.setInitialSitemapURL(Database.querySitemapURL(sc));
		Database.createDatabase(Database.getInitialSitemapURL());
		Database.createSitemap(Database.getInitialSitemapURL());
		Database.writeToSitemap();
		Database.parseSitemap();
		Database.downloadWebpages();
		sc.close();
	}
	
	public void println(Object input)
	{
		System.out.println(input);
	}

}
