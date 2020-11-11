package gamble_api;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
//https://www.briggsoft.com/docs/pmavens/Technical_Interface.htm#AccountsList
public class gamble_get_commands {
	
	public static String pokerUrl = "http://51.222.10.99:8087/";
	public static String pokerApiPassword = "Stats12";
	static String urlBuilder = "";
	public void setUrlBuilder() {
		urlBuilder = pokerUrl + "api?" + "Password=" + pokerApiPassword + "&Command=";
	}
	public void resetUrlBuilder() {
		urlBuilder = "";
	}
	public String getAccountInformation(String pokerPlayerName,String argument) throws IOException {
		setUrlBuilder();
		urlBuilder = urlBuilder + "AccountsGet&Player="+ pokerPlayerName;
		URL url = new URL(urlBuilder);
		Scanner sc = new Scanner(url.openStream());
		while(sc.hasNext()) {
			String line = sc.next();
			if(line.contains(argument+"=")) {
				String[] splitReader = line.split("=");
				return splitReader[1];
			}
		}
		resetUrlBuilder();
		return "";
	}
	
	public boolean apiAccountChecker(String AccountName) throws IOException {
		AccountName = AccountName.toLowerCase();
		setUrlBuilder();
		urlBuilder = urlBuilder + "AccountsList&Fields=Player";
		URL url = new URL(urlBuilder);
		Scanner sc = new Scanner(url.openStream());
		while(sc.hasNext()) {
			String line = sc.next();
			String[] splitReader = line.split("=");
			splitReader[1] = splitReader[1].toLowerCase();
			if(splitReader[1].equals(AccountName)) {
				return true;
			}
		}
		return false;
	}
	public void apiDeposit(String PlayerName, int amount) throws IOException {
		if(apiAccountChecker(PlayerName)==true) {
			PlayerName = PlayerName.toLowerCase();
			setUrlBuilder();
			urlBuilder = urlBuilder + "AccountsIncBalance&Player="+PlayerName+"&Amount="+amount;
			URL url = new URL(urlBuilder);
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext()) {
				String line = sc.next();
			}
		}else {
			System.out.println("false");
		}
	}
	public void apiWithDraw(String PlayerName, int amount) throws IOException {
		setUrlBuilder();
		System.out.println("[pass 1]");
		if(apiAccountChecker(PlayerName)==true) {
			setUrlBuilder();
			System.out.println("[pass 2]");
			PlayerName = PlayerName.toLowerCase();
			String line =getAccountInformation(PlayerName, "Balance");
			setUrlBuilder();
			double bal = Double.parseDouble(line);
			if(bal>amount) {
				System.out.println("[pass 3]");
				urlBuilder = urlBuilder + "AccountsDecBalance&Player="+PlayerName+"&Amount="+amount;
				System.out.println(urlBuilder);
				URL url = new URL(urlBuilder);
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					String line2 = sc.next();
					System.out.println(line2);
				}
			}
		}
	}
	
	public void errorMessages(int a) {
		switch(a) {
		
		case 0:;
		}
	}
	
	
	
	public void apiControler(String PlayerName,int a, int amount) {
		switch(a) {
		//gets the players balance
		case 0:;
		//deposits to the players account
		case 1:;
		//Withdraws from the players account
		case 2:;
		//checks to see if the player has an account.
		case 3:;
		//creates players account
		case 4:;
		}
	}
	
	   public static void main(String args[]) throws IOException {
		   gamble_get_commands object = new gamble_get_commands();
		  // object.getAccountInformation("cade","Balance");
		   //object.apiAccountChecker("caade");
		   //object.apiDeposit("adfds", 2147010000);
		   object.apiWithDraw("cade", 217680984);
		  
	     
	   }
	
}
