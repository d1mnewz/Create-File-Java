
public class StatusResult 
{
	public boolean Result;
	public String ErrorCode;
	public StatusResult() 
	{
		this.Result = true;
		this.ErrorCode = null;
	}
	public StatusResult(String code)
	{
		this.Result = false;
		if(!code.isEmpty())
		{
			this.ErrorCode = code;
		}
	}
	public void ShowResult()
	{
		System.out.println("Result of an operation is " + this.Result);
		if(this.Result == false)
		{
			System.out.println("Error Message : " + this.ErrorCode);
		}
	}
	

}
