/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Webpage
{
	private URL url;
	private AdvancedFile webpageFile;
	
	/**
	 * @param url
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * */
	public Webpage(String url) throws FileNotFoundException, IOException
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
	public Webpage(URL url) throws FileNotFoundException, IOException
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
