/**
 * @author OnlineBuilder
 * Copyright (c) OnlineBuilder 2017. 
 */
/**
 * This class is to be used to store common data that will be accessible
 * to all classes that WebDown is composed of.
 * */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Database
{
	private static HTTPSWebpage sitemap;
	private static ArrayList<AdvancedDirectory> databases;
	private static ArrayList<String> sitemapHyperlinks;

	private static AdvancedDirectory webdownDirectory;
	private static AdvancedDirectory workingDirectory;
	private static URL initialSitemapURL;
	
	public static void downloadWebpages()
	{
		for (String link: sitemapHyperlinks)
		{
			HTTPSWebpage httpsWebpage;
			HTTPSWebpageDownloader httpsWebpageDownloader;
			try
			{
				httpsWebpage = new HTTPSWebpage(link);
				httpsWebpageDownloader = new HTTPSWebpageDownloader(httpsWebpage);
				httpsWebpageDownloader.download(httpsWebpage.getWebpageFile());
				
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void parseSitemap()
	{
		sitemapHyperlinks = new ArrayList<String>();
		SitemapParser parser;
		parser = new SitemapParser();
		parser.extractHyperlinksFromSitemap(sitemap);
	}

	/**
	 * @throws IOException
	 * 
	 */
	public static void writeToSitemap() throws IOException
	{
		HTTPSWebpageDownloader httpsWebpageDownloader;
		httpsWebpageDownloader = new HTTPSWebpageDownloader(sitemap);
		httpsWebpageDownloader.download(sitemap.getWebpageFile());
	}

	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createEmptySitemap(String url) throws FileNotFoundException, IOException
	{
		sitemap = new HTTPSWebpage(url);
	}

	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createSitemap(URL url) throws FileNotFoundException, IOException
	{
		sitemap = new HTTPSWebpage(url);
	}

	/**
	 * Creates a new entry in the WebDown directory, a sitemap.xml file, and an
	 * ArrayList of type AdvancedDirectory.
	 * 
	 * @throws FileNotFoundException,
	 *             {@link IOException}
	 */
	public static void createDatabase(URL url) throws FileNotFoundException, IOException
	{
		String authority;

		authority = url.getAuthority();
		authority = StringClass.replaceAll(".", authority, "_");
		workingDirectory = new AdvancedDirectory(webdownDirectory + "/" + authority);
		databases = new ArrayList<AdvancedDirectory>();
		databases.add(workingDirectory);
	}

	/**
	 * @return URL
	 */
	public static URL querySitemapURL()
	{
		Scanner sc;
		sc = new Scanner(System.in);
		println("Please enter the sitemap url.");
		while (true)
		{
			if (sc.hasNext())
			{
				try
				{
					return new URL(sc.next());
				} catch (MalformedURLException e)
				{
					println("The url you entered was invalid." 
				            + " Please enter a valid url.\nEx. "
							+ "https://www.google.com/sitemap.xml");
					continue;
				}
			}
			sc.close();
		}
	}
	
	public static void addHyperlink(String hyperlink)
	{
		sitemapHyperlinks.add(hyperlink);
	}

	/**
	 * @param input
	 */
	public static void println(Object input)
	{
		System.out.println(input);
	}

	/**
	 * @return the sitemapHyperlinks
	 */
	public static ArrayList<String> getSitemapHyperlinks()
	{
		return sitemapHyperlinks;
	}

	/**
	 * @param sitemapHyperlinks
	 *            the sitemapHyperlinks to set
	 */
	public static void setSitemapHyperlinks(ArrayList<String> sitemapHyperlinks)
	{
		Database.sitemapHyperlinks = sitemapHyperlinks;
	}

	/**
	 * @return the webdownDirectory
	 */
	public static AdvancedDirectory getWebdownDirectory()
	{
		return webdownDirectory;
	}

	/**
	 * @return the initialSitemapURL
	 */
	public static URL getInitialSitemapURL()
	{
		return initialSitemapURL;
	}

	/**
	 * @param webdownDirectory
	 *            the webdownDirectory to set
	 */
	public static void setWebdownDirectory(AdvancedDirectory webdownDirectory)
	{
		Database.webdownDirectory = webdownDirectory;
	}

	/**
	 * @param initialSitemapURL
	 *            the initialSitemapURL to set
	 */
	public static void setInitialSitemapURL(URL initialSitemapURL)
	{
		Database.initialSitemapURL = initialSitemapURL;
	}

	/**
	 * @return the databases
	 */
	public static ArrayList<AdvancedDirectory> getDatabases()
	{
		return databases;
	}

	/**
	 * @return the workingDirectory
	 */
	public static AdvancedDirectory getWorkingDirectory()
	{
		return workingDirectory;
	}

	/**
	 * @return the sitemap
	 */
	public static HTTPSWebpage getSitemap()
	{
		return sitemap;
	}

}
