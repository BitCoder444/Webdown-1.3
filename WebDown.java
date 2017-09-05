/**
 * @author OnlineBuilder
 * Copyright (c) OnlineBuilder 2017. 
 */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;

public class WebDown
{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Database.setWebdownDirectory(new AdvancedDirectory("/media/codeseeker508/Extern/WebDown"));
		Database.setInitialSitemapURL(Database.querySitemapURL());
		Database.createDatabase(Database.getInitialSitemapURL());
		Database.createSitemap(Database.getInitialSitemapURL());
		Database.writeToSitemap();
		Database.parseSitemap();
		Database.downloadWebpages();
	}
	
	public void println(Object input)
	{
		System.out.println(input);
	}

}
