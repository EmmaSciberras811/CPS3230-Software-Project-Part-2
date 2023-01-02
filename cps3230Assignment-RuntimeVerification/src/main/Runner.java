package main;

import java.util.Random;

// Logging in Runner 
public class Runner{
	
	public static void main(String[] args) {
		final Runner m = new Runner();
		final User user = m.new User(false, false, 0);
		m.run(user);
	}
	
	// similar to tutorial example - bad login system 
	public void badLogin() {
		System.out.println("Bad login at: " + System.currentTimeMillis());
	}
	public void goodLogin() {
		System.out.println(" login at: " + System.currentTimeMillis());
	}
	
	public void run(final User user) {
		final Random rand = new Random();
		
		while(true){
			final int randomNumber = rand.nextInt(10);
			
			if (randomNumber < 7){
				this.badLogin();
				user.setBadLogins(user.getInvalidLogins() + 1);
				if(user.getInvalidLogins() >= 3){
					user.inLoginPage(true);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					user.setBadLogins(0);
					user.inLoginPage(true);
				}
			} else {
				this.goodLogin();
				user.setBadLogins(0);
				user.inAlertPage(true);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class User{
		//stores number of invalidLogins
		private Integer badLogin;
		//boolean values of screens 
		private boolean alertScr;
		private boolean loginScr;
		
		public User(final boolean alertScr, final boolean loginScr, final Integer badLogin) {
			super();
			this.alertScr = alertScr;
			this.loginScr = loginScr;
			this.badLogin = badLogin;
		}

		// Methods to set the page 
		public void inAlertPage(boolean alertScr) {
			if(alertScr)
				System.out.println("@ Alert Page");
			 else 
				System.out.println("@ Login Page!");
			
			this.alertScr = alertScr;
		}

		public void inLoginPage(boolean loginScr) {
			if(loginScr)
				System.out.println("@ Login Page!");
			 else 
				System.out.println("@ Alert Page!");
			
			this.loginScr = loginScr;
		}

		//Method to retrieve the number of bad logins 
		public Integer getInvalidLogins() {
			return badLogin;
		}

		public void setBadLogins(Integer badLogin) {
			this.badLogin = badLogin;
		}
	}
}