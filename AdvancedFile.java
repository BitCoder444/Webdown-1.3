/**
 * @author OnlineBuilder
 * Copyright (c) OnlineBuilder 2017. 
 */
/**
 * The AdvacedFile class was created using the Builder design pattern.
 */
package onlinebuilder.webdown;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdvancedFile
{
	/**
	 * Since this field may be null due to the possibility programmer using the
	 * empty constructor, always check to to see if this fields is null before
	 * referencing it. {@link File} {@link BufferedReader} 
	 * {@link BufferedWriter}
	 */
	private File file;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	/**
	 * The filename received by this constructor should have the full path before
	 * it. Ex. filename = /bin/usr/Downloads/Webpage.HTML
	 * 
	 * @throws IOException, FileNotFoundException
	 */
	public AdvancedFile(String fileName) 
			throws IOException, FileNotFoundException
	{
		if (fileName != null)
		{
			file = new File(fileName);
			file.setReadable(true);
			file.setWritable(true);
			
			if (!file.exists())
			{
				if (file.createNewFile())
				{
					System.out.println(file + " was created");
				}
			} else
			{
				System.out.println(file + " already exists");
			}
			
			try
			{
				bufferedReader = new BufferedReader(new FileReader(file));
				bufferedWriter = new BufferedWriter(new FileWriter(file));
			} catch (FileNotFoundException e)
			{
				try
				{
					if (bufferedReader != null)
					{
						bufferedReader.close();
					}
					if (bufferedWriter != null)
					{
						bufferedWriter.close();
					}
				} catch (IOException ex)
				{
					println("Error closing file " + file);
					throw new IOException();
				}
				println("Error: file " + file + " does not exist");
				e.printStackTrace();
				throw new FileNotFoundException();
			} catch (IOException e)
			{
				try
				{
					bufferedReader.close();
					bufferedWriter.close();
				} catch (Exception ex)
				{
					println("Error closing file " + file);
					throw new IOException();
				}
				println("I/O error");
			}
		}

	}

	/**
	 * Writes one line to this advanced file. If the file does not exist, an
	 * exception is thrown.
	 * 
	 * @return {@link Void}
	 * @throws IOException
	 *             , {@link NullPointerException}
	 */
	public void writeLine(String input) throws IOException
	{
		if (input != null)
		{
			bufferedWriter.write(input);
		} else
		{
			throw new NullPointerException();
		}
	}

	/**
	 * Returns one line from the advanced file of this AdvancedFile class. The
	 * reader advances one line.
	 * 
	 * @return {@link String}
	 * @throws FileNotFoundException,
	 *             {@link IOException}
	 */
	public String readLine() throws FileNotFoundException, IOException
	{
		return bufferedReader.readLine();
	}
	
	/**
	 * Reopens the I/O streams. All input is appended to the existing
	 * file.
	 * @throws FileNotFoundException, {@link IOException}
	 * */
	public void reopen() throws FileNotFoundException, IOException
	{
		bufferedReader = new BufferedReader(new FileReader(file));
		bufferedWriter = new BufferedWriter(new FileWriter(file, true));
	}

	/**
	 * Closes all IO streams
	 * 
	 * @return {@link Void}
	 * @throws IOException
	 */
	public void close() throws IOException
	{
		bufferedReader.close();
		bufferedWriter.close();
	}

	private void println(Object input)
	{
		System.out.println(input);
	}

	/**
	 * Overrides the Object parent class toString method
	 * 
	 * @return {@link String}
	 */
	public String toString()
	{
		return file.toString();
	}

	/**
	 * Tests whether the file denoted by this abstract pathname is a directory.
	 * Where it is required to distinguish an I/O exception from the case that the
	 * file is not a directory, or where several attributes of the same file are
	 * required at the same time, then the Files.readAttributes method may be used.
	 * 
	 * @return true if and only if the file denoted by this abstract pathname exists
	 *         and is a directory; false otherwise
	 * @throws SecurityException
	 *             - If a security manager exists and its
	 *             java.lang.SecurityManager.checkRead(java.lang.String) method
	 *             denies read access to the file
	 */
	public boolean isDirectory()
	{
		return file.isDirectory();
	}

	/**
	 * Tests whether the file denoted by this abstract pathname is a normal file. A
	 * file is normal if it is not a directory and, in addition, satisfies other
	 * system-dependent criteria. Any non-directory file created by a Java
	 * application is guaranteed to be a normal file. Where it is required to
	 * distinguish an I/O exception from the case that the file is not a normal
	 * file, or where several attributes of the same file are required at the same
	 * time, then the Files.readAttributes method may be used.
	 * 
	 * @return <code>true</code> if and only if the file denoted by this abstract
	 *         pathname exists and is a normal file; <code>false</code> otherwise
	 * @throws SecurityException
	 *             - If a security manager exists and its
	 *             java.lang.SecurityManager.checkRead(java.lang.String) method
	 *             denies read access to the file
	 */
	public boolean isFile()
	{
		return file.isFile();
	}

	/**
	 * Tests whether the file named by this abstract pathname is a hidden file. The
	 * exact definition of hidden is system-dependent. On UNIX systems, a file is
	 * considered to be hidden if its name begins with a period character ('.'). On
	 * Microsoft Windows systems, a file is considered to be hidden if it has been
	 * marked as such in the filesystem.
	 * 
	 * @returns <code>true</code> if and only if the file denoted by this abstract
	 *          pathname is hidden according to the conventions of the underlying
	 *          platform
	 * @throws SecurityException
	 *             - If a security manager exists and its
	 *             java.lang.SecurityManager.checkRead(java.lang.String) method
	 *             denies read access to the file
	 */
	public boolean isHidden()
	{
		return file.isHidden();
	}

	/**
	 * If the file exists, returns true, otherwise false. This method can be used
	 * without initializing the field File.
	 * 
	 * @return <code>true</code> if the file exiss, <code>false</code> otherwise
	 */
	public static boolean ifFileExists(String filePath) throws SecurityException
	{
		File file = new File(filePath);

		if (file.exists())
		{
			return true;
		} else
		{
			return false;
		}

	}

	/**
	 * If the directory exists, returns true, otherwise false. This method can be
	 * used without initializing the field "file" If the file does not exist, an
	 * exception is thrown.
	 * 
	 * @return <code>true</code> if directory exists, <code>false</code> otherwise
	 */
	public static boolean ifDirectoryExists(String directory)
	{
		File file = new File(directory);

		try
		{
			if (file.exists())
			{
				return true;
			} else
			{
				return false;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return {@link BufferedWriter}
	 */
	public BufferedWriter getBufferedWriter()
	{
		return bufferedWriter;
	}

	/**
	 * @return {@link File}
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * {@link BufferedReader}
	 */
	public BufferedReader getBufferedReader()
	{
		return bufferedReader;
	}
}
