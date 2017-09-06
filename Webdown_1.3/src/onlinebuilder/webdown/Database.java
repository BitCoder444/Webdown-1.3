/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
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
	private final static char[] validDirectoryChars =
	{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	private static Webpage sitemap;
	private static ArrayList<AdvancedDirectory> databases;
	private static ArrayList<String> sitemapHyperlinks;

	private static AdvancedDirectory webdownDirectory;
	private static AdvancedDirectory workingDirectory;
	private static URL initialSitemapURL;

	/**
	 * Downloads the webpages
	 */
	public static void downloadWebpages()
	{
		for (String link : sitemapHyperlinks)
		{
			Webpage httpsWebpage;
			WebpageDownloader httpsWebpageDownloader;
			try
			{
				httpsWebpage = new Webpage(link);
				httpsWebpageDownloader = new WebpageDownloader(httpsWebpage);
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
		WebpageDownloader httpsWebpageDownloader;
		httpsWebpageDownloader = new WebpageDownloader(sitemap);
		httpsWebpageDownloader.download(sitemap.getWebpageFile());
	}

	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createEmptySitemap(String url)
			throws FileNotFoundException, IOException
	{
		sitemap = new Webpage(url);
	}

	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createSitemap(URL url)
			throws FileNotFoundException, IOException
	{
		sitemap = new Webpage(url);
	}

	/**
	 * Creates a new entry in the WebDown directory, a sitemap.xml file, and an
	 * ArrayList of type AdvancedDirectory.
	 * 
	 * @throws FileNotFoundException,
	 *             {@link IOException}
	 */
	public static void createDatabase(URL url) 
			throws FileNotFoundException, IOException
	{
		String authority;

		authority = url.getAuthority();
		authority = StringClass.replaceAll(".", authority, "_");
		workingDirectory = new AdvancedDirectory(webdownDirectory 
				+ "/" + authority);
		databases = new ArrayList<AdvancedDirectory>();
		databases.add(workingDirectory);
	}

	/**
	 * @return URL
	 */
	public static URL querySitemapURL(Scanner sc)
	{
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

	/**
	 * @param sc
	 */
	public static void queryValidInitialDirectory(Scanner sc)
	{
		String answer;
		while (true)
		{
			String potentialDirectory;
			println("Download the website to the default directory " 
		            + "/media/codeseeker508/Extern/WebDown\n"
					+ " or use a new directory? (Y/n)");
			if (sc.hasNext())
			{
				answer = sc.next();
				if (answer.equals("Y"))
				{
					println("Please type the full directory with no spaces");
					if (sc.hasNext())
					{
						
						char[] inputArray;
						potentialDirectory = sc.next();
						inputArray = potentialDirectory.toCharArray();
						for (char character : inputArray)
						{
							if (isValidDirectoryCharacter(character))
							{
								continue;
							} else
							{
								println("The path that you entered was invalid."
										+ " Either enter a valid path or simply "
										+ " \npress enter");
								break;
							}
						}
						webdownDirectory =
								new AdvancedDirectory(potentialDirectory);
						break;
					}
				}
				else if (answer.equals("n"))
				{
					break;
				}
			}		
		}
	}

	/**
	 * @param input
	 * @return
	 */
	private static boolean isValidDirectoryCharacter(char input)
	{
		for (char character : validDirectoryChars)
		{
			if (input == character)
			{
				return true;
			}
		}
		return false;
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
	public static void setSitemapHyperlinks(
			ArrayList<String> sitemapHyperlinks)
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
	public static Webpage getSitemap()
	{
		return sitemap;
	}

	/**
	 * @return the validdirectorychars
	 */
	public static char[] getValiddirectorychars()
	{
		return validDirectoryChars;
	}

}
