package online.bank.system;

/**
 * Class for the Account object
 */
public class Account {
	// Part 1: attributes
	private String ID;
	private String name; 
	private double balance; 
	
	// Part 2: Constructor
	/**
	 * Main constructor 
	 * 
	 * @param ID string value for the ID attribute
	 * @param name string value for the name attribute
	 * @param balance double value for the balance
	 */
	public Account(String ID, String name, double balance) {
		this.ID = ID; 
		this.name = name; 
		this.balance = balance; 
	}
	
	/**
	 * Constructor for accounts that aren't depositing money when made
	 * 
	 * @param ID string value for the ID attribute 
	 * @param name string value for the name attribute 
	 */
	public Account(String ID, String name) {
		this(ID, name, 0);
	}
	
	/**
	 * Default constructor 
	 */
	public Account() {
		this("NA", "NA", 0);
	}
	
	// Part 3: methods
	/**
	 * Getter function for the ID attribute
	 * @return the string value of the ID attribute
	 */
	public String getID() {
		return this.ID; 
	}
	/**
	 * Getter function for the name attribute 
	 * @return string value for the name attribute 
	 */
	public String getName() {
		return this.name; 
	}
	/**
	 * Setter function for the name attribute 
	 * @param newName string value of a name to replace the name attribute with 
	 */
	public void setName(String newName) {
		this.name = newName; 
	}
	
	// get balance
	/**
	 * Getter function for the balance attribute 
	 * @return double value of the balance attribute 
	 */
	public double getBalance() {
		return this.balance; 
	}
	/**
	 * Deposit function 
	 * Adds input to the balance
	 * @param income double value for the amount to add to the balance
	 * @return the new balance
	 */
	public double deposit(double income) {
		this.balance += income; 
		return this.balance; 
	}
	/**
	 * Withdraw function 
	 * Subtracts the amount from the balance
	 * @param amount double value for the amount to subtract from the balance 
	 * @return the new balance 
	 */
	public double withdraw(double amount) {
		this.balance -= amount; 
		return this.balance; 
	}
	/**
	 * Transfer function 
	 * Withdraws money from one account and deposits it into another account 
	 * @param money double value for the amount to transfer 
	 * @param another the other account that money is being sent to 
	 * @return boolean value to show if the transfer succeeded 
	 */
	public boolean transfer(double money, Account another) {
		if (this.balance < money) {
			return false; 
		} else {
			this.balance -= money; 
			another.deposit(money);
			return true; 
		}
	}
	
}
