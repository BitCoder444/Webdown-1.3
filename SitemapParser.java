/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SitemapParser
{
	
	/**
	 * @param sitemap
	 */
	public void extractHyperlinksFromSitemap(HTTPSWebpage sitemap)
	{
		int locIndex;
		int closingLocIndex;
		String loc;
		String closingLoc;
		StringBuilder buffer;
		String hyperlink;

		buffer = bufferSitemap(sitemap);
		loc = "<loc>";
		closingLoc = "</loc>";
		locIndex = buffer.indexOf(loc);
		closingLocIndex = buffer.indexOf(closingLoc);
		hyperlink = buffer.substring(locIndex, closingLocIndex);

		if (!containsLocTags(new String(buffer)))
		{
			System.out.println("Sitemap is void of valid hyperlinks");
			return;
		} else
		{
			while (hyperlink != null)
			{
				//Database.addHyperlink(hyperlink);
				locIndex = buffer.indexOf(loc, closingLocIndex);
				closingLocIndex = buffer.indexOf(closingLoc, closingLocIndex +
						closingLoc.length());
				if (closingLocIndex > 0 && locIndex > 0)
				{
					if (closingLocIndex + closingLoc.length() < buffer.length())
					{
						hyperlink = buffer.substring(locIndex + loc.length(), closingLocIndex);
						Database.addHyperlink(hyperlink);
						System.out.println(hyperlink);
					}			
				} else
				{
					hyperlink = null;
				}
			}
		}
	}
	
	/**
	 * @param sitemap
	 * @return
	 */
	private StringBuilder bufferSitemap(HTTPSWebpage sitemap)
	{
		StringBuilder buffer;
		String input;
		buffer = null;
		input = null;
		try
		{
			sitemap.getWebpageFile().reopen();;
			buffer = new StringBuilder();
			input = sitemap.getWebpageFile().readLine();
			while (input != null)
			{
				buffer.append(input);
				input = sitemap.getWebpageFile().readLine();
			}
			sitemap.getWebpageFile().close();;
			return buffer;
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO
			e.printStackTrace();
		}
		return buffer;
	}
	
	
	/**
	 * @param input
	 * @return
	 */
	private boolean containsLocTags(String input)
	{
		if (StringClass.contains("<loc>", input) || StringClass.contains("</loc>", input))
		{
			return true;
		} else
		{
			return false;
		}
	}
}
