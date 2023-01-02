package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AlertSystem {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	ArrayList<Alerts> users = new ArrayList<Alerts>();
	
	public void addAlert()
	{
		users.add(new Alerts(this));
	}
	
	public void deleteAllAlerts()
	{
		users.clear(); // remove all from list 
	}
	public void process()
	{}
	
	public static int read()
	{
		try{
		return Integer.parseInt(br.readLine());
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		return -1;
	}
	
	public static void write(String text)
	{
		System.out.println(text);
	}
	
	public String show()
	{
		String s = "";
		for (Alerts a:users)
			s += a.id + ", ";
		return s;
	}
	
	public void menu()
	{
		boolean run = true;
		while (run)
		{
			System.out.println("****Alerts page****");
			System.out.println("Alerts: "+show());
			System.out.println("1. add alert");
			System.out.println("2. delete alerts");
			System.out.println("3. Log out ");
			switch(read())
			{
			case 1:addAlert();break;
			case 2:deleteAllAlerts();break;
			case 3:run = false;break;
			}
		}
	}
	
	public static void main(String[] args) {
		try{
		System.out.println(System.currentTimeMillis());
		new AlertSystem().menu();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
