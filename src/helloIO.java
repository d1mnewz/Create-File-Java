import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class helloIO {

	Scanner user_input = new Scanner( System.in ); // class to read input from keyboard
	String FileName = new String(); // initializing new object of String
	public helloIO() 
	{
		System.out.println("Project started");
		System.out.println();
		LocalDateTime obj = LocalDateTime.now(); // date + time of start project
		System.out.println(obj); 
		System.out.println();
		
	}
	
	public void CreateFile() throws IOException
	{	
		//String fileSeparator = System.getProperty("file.separator"); 
		// not required for current example but useful thing while using full-path fileName
		
		
		FileName = this.user_input.nextLine(); // reading filename from keyboard
		if(!this.FileName.isEmpty()) // if input is empty go to row 51 and tell it to user
		{
			if(!this.FileName.endsWith(".txt")) // if user entered filename without .txt
			{
				this.FileName += ".txt"; // then add txt
			} 
			File file = new File(this.FileName); // create an object of File class
												//with our filename as an argument to constructor
			if(file.createNewFile()) // file.createNewFile() returns boolean value 
	        {
	            System.out.println(this.FileName +  " created in Project root directory.");
	        }
			else System.out.println(this.FileName +  " is already defined in root directory."); // if false
		}
		else System.out.println("Empty input!");
		
        
         
        
    }
		
	

	public static void main(String [] args)
	{
		
		helloIO obj = new helloIO();
		try {
			obj.CreateFile();
		} catch (IOException e)  // handling exception that obj.CreateFile() throws (row 24)
		{
			
			JOptionPane.showMessageDialog(null, "Invalid input",
					"InfoBox: " + "Error!", JOptionPane.INFORMATION_MESSAGE); // MessageBox.Show() analogue in java
		}		
	}

}
