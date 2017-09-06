/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Sitemap
{
	private ArrayList<String> sitemapHyperlink;
	private Webpage sitemap;
	
	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Sitemap(String url) throws FileNotFoundException, IOException
	{
		sitemapHyperlink = new ArrayList<String>();
		sitemap = new Webpage(url);
		Database.setInitialSitemapURL(new URL(url));
	}
	
	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Sitemap(URL url) throws FileNotFoundException, IOException
	{
		sitemapHyperlink = new ArrayList<String>();
		sitemap = new Webpage(url);
		Database.setInitialSitemapURL(url);
	}
	
	public void addHyperlink(String hyperlink)
	{
		sitemapHyperlink.add(hyperlink);
	}

	/**
	 * @return the sitemapHyperlink
	 */
	public ArrayList<String> getSitemapHyperlink()
	{
		return sitemapHyperlink;
	}

	/**
	 * @return the sitemap
	 */
	public Webpage getSitemap()
	{
		return sitemap;
	}

	/**
	 * @param sitemapHyperlink the sitemapHyperlink to set
	 */
	public void setSitemapHyperlink(ArrayList<String> sitemapHyperlink)
	{
		this.sitemapHyperlink = sitemapHyperlink;
	}

	/**
	 * @param sitemap the sitemap to set
	 */
	public void setSitemap(Webpage sitemap)
	{
		this.sitemap = sitemap;
	}
}
