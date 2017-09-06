/**
 * @author OnlineBuilder
 * Copyright (c) <2017> <OnlineBuilder>
 * No rights or licenses from any copyright holder or contributor is granted,
 * whether expressly, by implication, estoppel or otherwise. 
 * */
package onlinebuilder.webdown;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WebpageDownloader
{
	private URL url;
	private HttpsURLConnection httpsURLConnection;
	private HttpURLConnection httpURLConnection;
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	
	public WebpageDownloader(Webpage webpage) throws IOException
	{
		url = webpage.getUrl();
		connect(this.url);
	}
	
	private void connect(URL url) throws IOException
	{
		if (isHTTPS(url))
		{
			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			inputStream = httpsURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
		} else if (isHTTP(url))
		{
			httpURLConnection = (HttpURLConnection) url.openConnection();
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
		} else
		{
			StringClass.print("Website connection protocol is unsupported");
		}
	}
	
	public void download(AdvancedFile inputFile) throws IOException
	{
		String code;
		code = bufferedReader.readLine();
		while (code != null)
		{
			if (inputFile == null)
			    break;
			try
			{
				inputFile.writeLine(code);
				code = bufferedReader.readLine();
			} catch (IOException e)
			{
				try
				{
					inputFile.close();
				} catch (IOException e1)
				{
					StringClass.println("Error closing file " + inputFile.getFile().getName());
					e.printStackTrace();
					break;
				}
				StringClass.println("Error writing to file " + inputFile.getFile().getName());
				e.printStackTrace();
				break;
			}
		}
		inputFile.close();

	}
	
	private boolean isHTTPS(URL url)
	{
		return url.getProtocol().equals("https");
	}
	
	private boolean isHTTP(URL url)
	{
		return url.getProtocol().equals("http");
	}
	
}
