package online.bank.system;
import java.util.Scanner;

// ID is "BA" followed by the order that accounts are added to the array i.e. BA1, BA2, BA3,

public class Project1 {
	
	// Class variable that is a fixed value capping the number of accounts allowed 
	public static final int MAX = 100; 
	
	/**
	 * Main function for the client side logic
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean loopBreaker = false; 
		Account[] allUsers = new Account[MAX];
		// current size always pointing to the open spot 
		int currentSize = 0; 
		while (loopBreaker == false) {
			// step 1: display main menu
			int userInput = displayMainMenu(input); 
			if (userInput == 1) {
				if (currentSize <= 100) {
					Account newAccount = createNewUser(input, currentSize);
					if (newAccount != null) {
						allUsers[currentSize] = newAccount;
						currentSize++; 
					} else {
						System.out.println("New Account exceeds max user count");
					}
				} else {
					System.out.println("Not enough space for new users.");
				}
			} else if (userInput == 2) {
				currentUser(input, allUsers, currentSize); 
			} else {
				loopBreaker = true; 
			}
		}
		input.close(); 
	}
	
	/**
	 * Function to display the main menu
	 * 
	 * @param input scanner instance to allow user input
	 * @return userInput a value that tells the system which part of the menu the user is attempting to use
	 */
	public static int displayMainMenu(Scanner input) {
		System.out.println("Welcome to the bank!");
		System.out.println("1.) New User");
		System.out.println("2.) Returning User");
		System.out.println("0.) Exit");
		int userInput = input.nextInt();
		return userInput; 
	}
	
	/**
	 * Function that handles the logic for creating a new account
	 * First checks to make sure that there is room for a new account
	 * Then gets user input and calls the constructor from the Account class to make the account
	 * 
	 * @param input scanner instance to allow user input
	 * @param currentSize the current size of the array that holds the Account objects 
	 * @return Either the new account object or null if the account was unable to be created 
	 */
	public static Account createNewUser(Scanner input, int currentSize) {
		if (currentSize < MAX) {
			System.out.println("Please enter a name for this new account: ");
			String accountName = input.next(); 
			System.out.println("How much would you like to deposit into this new account?");
			double accountBalance = input.nextDouble(); 
			Account a = new Account("BA" + (currentSize +  1), accountName, accountBalance);
			return a; 
		} else {
			return null; 
		}
		
	}
	
	/**
	 * Function that handles the logic for accounts that already exist
	 * First gets an ID to find the user's account and checks if it can find it
	 * Once it finds the account it displays the account info the user
	 * Then uses a while loop to continuously display a different menu to the user
	 * Depending on the input from the user, the user can: 
	 * 	Deposit to their account
	 * 	Withdraw from their account 
	 * 	Check the balance 
	 * 	Transfer money to a different account
	 * 
	 * @param input Scanner instance that allows user input
	 * @param allUsers Location of the array that holds the account objects 
	 * @param currentSize current size of the array that holds the account objects 
	 */
	public static void currentUser(Scanner input, Account[] allUsers, int currentSize) {
		boolean loopBreaker = false; 
		System.out.println("Please enter your user ID: ");
		System.out.println("The pattern for IDs is 'BA + the number of current users'");
		String ID = input.next(); 
		for (int i = 0; i < currentSize; i++) {
			if (allUsers[i].getID().equals(ID)) {
				Account userAccount = allUsers[i];
				displayAccountInfo(userAccount);
				while (loopBreaker == false) {
					activeUserMenu(); 
					int userInput = input.nextInt(); 
					if (userInput == 1) {
						System.out.println("How much would you like to deposit?");
						double depositAmount = input.nextDouble();
						userAccount.deposit(depositAmount);
					} else if (userInput == 2) {
						System.out.println("How much would you like to withdraw?");
						double withdrawAmount = input.nextDouble(); 
						if ((userAccount.getBalance() - withdrawAmount) < 0) {
							System.out.println("Not enough in the account to withdraw that amount");
						} else {
							userAccount.withdraw(withdrawAmount);
						}
					} else if (userInput == 3) {
						double b = userAccount.getBalance(); 
						System.out.println("The current balance for this account is: " + b);
					} else if (userInput == 4) {
						System.out.println("Enter the ID of the account to transfer to: ");
						String transferAccountID = input.next(); 
						for (int j = 0; j < currentSize; j++) {
							if (allUsers[j].getID().equals(transferAccountID)) {
								Account transferAccount = allUsers[j];
								System.out.println("How much would you like to transfer?");
								double transferAmount = input.nextDouble(); 
								if ((userAccount.getBalance() - transferAmount) < 0) {
									System.out.println("Not enough in the account to withdraw that amount");
								} else {
									boolean success = userAccount.transfer(transferAmount, transferAccount); 
									if (success) {
										System.out.println("Your transfer was successful");
										System.out.println("Your account balance: " + userAccount.getBalance());
									} else {
										System.out.println("Your transfer failed");
									}
								}
							} 
						}
					} else {
						loopBreaker = true; 
					}
				}
			} 
		}
	}
	
	/**
	 * Function that displays menu to the user for active users
	 */
	public static void activeUserMenu() {
		System.out.println("What service would you like to use?");
		System.out.println("1.) Deposit"); 
		System.out.println("2.) Withdraw");
		System.out.println("3.) Check Balance");
		System.out.println("4.) Transfer to another account");
		System.out.println("0.) Return to Main Menu");
	}
	
	/**
	 * Function that displays user information 
	 * 
	 * @param userAccount Account object of current user
	 */
	public static void displayAccountInfo(Account userAccount) {
		System.out.println("<<<<<<<<<<>>>>>>>>>>");
		System.out.println("User ID: " + userAccount.getID());
		System.out.println("User Name: " + userAccount.getName());
		System.out.println("Account Balance: " + userAccount.getBalance());
		System.out.println("<<<<<<<<<<>>>>>>>>>>");
	}

}
