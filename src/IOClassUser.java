import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;


// CQRS pattern


public class IOClassUser 
{

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

	public void Dispatcher(String choice)
	{ 
		// dispatcher -> handlers
		switch(choice)
		{
		case "1":
			// all of functions return StatusResult value that has method ShowResult() 
			// so we can use it from result of this.CreateFile("").ShowResult();
			try 
			{
				this.CreateFile(this.user_input.nextLine()).ShowResult(); // create file throws IOException
				this.user_input.nextLine();
			}
			
			// this.user_input.nextLine() is kinda "press any key to continue"
			
			catch (IOException e) 
			{
				StatusResult obj = new StatusResult("invalid input of filename in create-branch.");
				obj.ShowResult();
			}
			break;
			
		case "2":
			this.DeleteFileByName(this.user_input.nextLine()).ShowResult(); 
			this.user_input.nextLine();
			break;
			
		case "3":
			this.DeleteLastCreatedFile().ShowResult();
			this.user_input.nextLine();
			break;
			
		case "4":
			String From = this.user_input.nextLine(); // read FromFileName
			String To = this.user_input.nextLine(); // read ToFileName
			this.RenameFile(From, To).ShowResult();
			this.user_input.nextLine();
			break;
			
		case "5":
			this.RenameLastUsedFile(this.user_input.nextLine()).ShowResult();
			this.user_input.nextLine();
			break;
			
		case "0":
			System.exit(0); // exit app without error code
			break;
			
		default:
				StatusResult obj1 = new StatusResult("Invalid input in menu"); 
				obj1.ShowResult(); // if any of cases wasnt used
				this.user_input.nextLine();
				break;
		
		}
		
	}
	
	public  void clearConsole() // clearing console for windows and unix-like systems
	{
	    try
	    {
	    	String[] cls = new String[] {"cmd.exe", "/c", "cls"};
	    	String[] clear = new String [] {"cmd.exe", "/c", "clear"};
	    	Runtime.getRuntime().exec(cls); 
	         String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	        
	        	Runtime.getRuntime().exec(cls); 
	        }
	        else
	        {
	            Runtime.getRuntime().exec(clear);
	        }
	    }
	    catch ( Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
	}
	
	public void Menu()
	{
		String choice = new String();
		while(choice != "0") 
		{
		this.clearConsole(); // clear console before printing menu
		System.out.println("\tJava IO interface\t");
		System.out.println("1. Create new file.");
		System.out.println("2. Delete file by name.");
		System.out.println("3. Delete last created file.");
		System.out.println("4. Rename file by name.");
		System.out.println("5. Rename last created file.");
		System.out.println("0. Exit.");
		choice = this.user_input.nextLine(); // read choice from keyboard
		this.Dispatcher(choice);
		}
	}
	
	public StatusResult RenameLastUsedFile(String filename) // to do
	{
		// if lastCreatedFile exists & filename that we want to assign to old file is not already in use
		if(this.UserFile != null && this.UserFile.exists() && !(new File(filename).exists())) // 
		{
			this.UserFile.renameTo(new File(filename));
			return new StatusResult();
		}
		else return new StatusResult("Last used file isn`t defined.");
		
	}
	
	public StatusResult RenameFile(String filenameFrom, String filenameTo) // to do
	{
		// if file with such name exists in directory &
		// filename that we want to assign to old file is not already in use
		if(new File(filenameFrom).exists() & !(new File(filenameTo).exists()))
		{
			new File(filenameFrom).renameTo(new File(filenameTo));
			return new StatusResult();
		}
		else return new StatusResult("Invalid input.");
	}
	
	public StatusResult DeleteLastCreatedFile() // delete() does not throw IOException ! notice it!
	{
		this.UserFile = new File(this.FileName);
		if(this.UserFile.exists()) // if file exists
		{
			if(this.UserFile.delete()) // if true
			{
				System.out.println(this.UserFile + " deleted from the root directory.");
				return new StatusResult();
			} 
			else 
			{
				// if this.UserFile.delete() returns false
				return new StatusResult("Unable to delete this file!");
			}
		}
		else 
		{
			// if this.UserFile.exists() returns false)
			return new StatusResult("file doesnt exist.");
		}
	}
	
	public StatusResult DeleteFileByName(String filenameToDelete) // delete() does not throw IOException ! notice it!
	{
		File FileForDelete = new File(filenameToDelete); // initializing fileForDelete
		if(FileForDelete.delete()) // if true
		{
			return new StatusResult(); // return boolean result of function fileForDelete.delete();
		}
		else return new StatusResult("cannot delete file by name");
	}
	
	
	public StatusResult CreateFile(String filename) throws IOException
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
		this.FileName = filename;
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
				return new StatusResult();
	        }
			else return new StatusResult(this.UserFile +  " is already defined in root directory."); // if false
		}
		else return new StatusResult("Empty input for create file!");
    }
	
	
		
	

	public static void main(String [] args)
	{
		// create an instance of IOClassUser
		IOClassUser obj = new IOClassUser();
		// and launch program
		obj.Menu(); 
		
	}

}
