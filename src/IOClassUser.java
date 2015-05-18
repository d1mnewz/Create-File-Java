import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class IOClassUser {

	Scanner user_input = new Scanner( System.in ); // class to read input from keyboard
	String FileName = new String(); // initializing new object of String
	File UserFile = null; // null`ing UserFile because we dont have a filename.
	public IOClassUser() 
	{
		System.out.println("Project started");
		System.out.println();
		LocalDateTime obj = LocalDateTime.now(); // date + time of start project
		System.out.println(obj); // show date + time
		System.out.println();
		
	}
	public void DeleteLastCreatedFile() // delete() doesnt throw IOException ! notice it!
	{
		this.UserFile = new File(this.FileName);
		if(this.UserFile.exists()) // if file exists
		{
			if(this.UserFile.delete())
			{
				System.out.println(this.UserFile + " deleted from the root directory.");
				this.FileName = null;
				this.UserFile = null;
			} 
			else 
			{
				System.out.println("Unable to delete this file!"); // if this.UserFile.delete() returns false
			}
		}
		else 
		{
			System.out.println("File doesnt exist"); // if this.UserFile.exists() returns false)
		}
	}
	
	public boolean DeleteFileByName() // delete() doesnt throw IOException ! notice it!
	{
		String FileForDeleteName = new String();
		FileForDeleteName = this.user_input.nextLine();
		if(!FileForDeleteName.endsWith(".txt")) // if user entered filename without .txt
		{
			FileForDeleteName += ".txt"; // then add .txt
		} 
		
		File FileForDelete = new File(FileForDeleteName); // initializing fileForDelete
		return FileForDelete.delete(); // return boolean result of function fileForDelete.delete();
		
	}
	public void CreateFile() throws IOException
	{	
		//String fileSeparator = System.getProperty("file.separator"); 
		// not required for current example but useful thing while using full-path fileName
		// to make our programm platform independent we need to this file separators from class File
		// --------------------------------------------------------------------------------------
		// 												UNIX-like systems
		//				File.separator = /
		//				File.separatorChar = /
		//				File.pathSeparator = :
		//				File.pathSeparatorChar = :
		// ---------------------------------------------------------------------------------------
		// 												Windows-like systems
		//				File.separator = \
		//				File.separatorChar = \
		//				File.pathSeparator = ;
		//				File.pathSeparatorChar = ;
		// ---------------------------------------------------------------------------------------
		if(!this.FileName.isEmpty()) // if input is empty go to row 51 and tell it to user
		{
			if(!this.FileName.endsWith(".txt")) // if user entered filename without .txt
			{
				this.FileName += ".txt"; // then add .txt
			} 
			this.UserFile = new File(this.FileName); // create an object of File class
												//with our filename as an argument to constructor
			if(UserFile.createNewFile()) // file.createNewFile() returns boolean value 
	        {
	            System.out.println(this.UserFile +  " created in Project root directory.");
	        }
			else System.out.println(this.UserFile +  " is already defined in root directory."); // if false
		}
		else System.out.println("Empty input!");
		
        
         
        
    }
	public void ReadFileName()
	{
		this.FileName = this.user_input.nextLine(); // reading filename from keyboard
	}
		
	

	public static void main(String [] args)
	{
		
		IOClassUser obj = new IOClassUser();
		
		try {
			obj.ReadFileName();
			obj.CreateFile();
		} catch (IOException e)  // handling exception that obj.CreateFile() throws (row 24)
		{
			
			JOptionPane.showMessageDialog(null, "Invalid input",
					"InfoBox: " + "Error!", JOptionPane.INFORMATION_MESSAGE); // MessageBox.Show() analogue in java
		}		

		obj.DeleteLastCreatedFile();
		System.out.println("Result of deleting file by name - " + obj.DeleteFileByName());
		
		
	}

}
