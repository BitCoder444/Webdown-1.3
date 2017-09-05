/**
 * @author OnlineBuilder
 * Copyright (c) OnlineBuilder 2017. 
 */

package onlinebuilder.webdown;

import java.io.File;

public class AdvancedDirectory
{
	private File directory;

	/**
	 * Creates a directory if it does not already exist
	 * @throws NullPointerException
	 * */
	public AdvancedDirectory(String directory)
	{
		this.directory = new File(directory);
        if (!this.directory.exists())
        {	
        	if (this.directory.mkdirs())
        	{
        		System.out.println(directory + " was created");
        	} else
        	{
        		System.out.println(directory + " was not created");
        	}
        }
        else
        {
        	System.out.println(directory+ " already exists");
        }
	}

	/**
	 * @return the directory
	 */
	public File getDirectory()
	{
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(File directory)
	{
		this.directory = directory;
	}
	
	public String toString()
	{
		return directory.toString();
	}
}
