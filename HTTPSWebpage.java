/**
 * @author OnlineBuilder
 * Copyright (c) OnlineBuilder 2017. 
 */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPSWebpage
{
	private URL url;
	private AdvancedFile webpageFile;
	
	/**
	 * @param url
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * */
	public HTTPSWebpage(String url) throws FileNotFoundException, IOException
	{
        setUrl(url);
        String HTMLFileName;
        HTMLFileName = Database.getWorkingDirectory().getDirectory().getAbsolutePath()
        		+ "/" + StringClass.replaceAll("/", this.url.getPath(), "_").substring(1);
        if (!StringClass.contains(".", HTMLFileName))
        {
        	HTMLFileName = HTMLFileName + ".html";
        	webpageFile = new AdvancedFile(HTMLFileName);
        }
        else
        {
            webpageFile = new AdvancedFile(HTMLFileName);
        }
	}

	/**
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public HTTPSWebpage(URL url) throws FileNotFoundException, IOException
	{
        setUrl(url);
        String HTMLFileName;
        HTMLFileName = Database.getWorkingDirectory().getDirectory().getAbsolutePath()
        		+ this.url.getPath();
        if (!StringClass.contains(".", HTMLFileName))
        {
        	HTMLFileName = HTMLFileName + ".html";
        	webpageFile = new AdvancedFile(HTMLFileName);
        }
        else
            webpageFile = new AdvancedFile(HTMLFileName);
	}

	/**
	 * @return the url
	 */
	public URL getUrl()
	{
		return url;
	}

	/**
	 * @return the webpageFile
	 */
	public AdvancedFile getWebpageFile()
	{
		return webpageFile;
	}

	/**
	 * @param url the url to set
	 * @throws MalformedURLException 
	 */
	public void setUrl(String url) throws MalformedURLException
	{
		this.url = new URL(url);
	}
	
	public void setUrl(URL url)
	{
		this.url = url;
	}

	/**
	 * @param webpageFile the webpageFile to set
	 */
	public void setWebpageFile(AdvancedFile webpageFile)
	{
		this.webpageFile = webpageFile;
	}
	

}
