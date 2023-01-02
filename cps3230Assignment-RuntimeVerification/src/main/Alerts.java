package main;


public class Alerts {

	public static int sid = 0;
	
	public int id;
	
	public AlertSystem location;
	
	
	public Alerts(AlertSystem location)
	{ 
		id = ++sid;
		this.location = location;
	}
	
	public Alerts(int id)
	{
		this.id = id;
	}
	
	public void menu()
	{
	}
	
}
