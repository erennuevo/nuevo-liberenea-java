package payment.processing.system;

/* I was able to demonstrate inheritance by creating a class hierarchy.
 * BankTransfer is a concrete child class of OnlinePayment, inheriting the balance field. */
public final class BankTransfer extends OnlinePayment
	implements PaymentSystem {

	private int bankAccNo;
	
	public BankTransfer(int bankAccNo, int balance) {
		this.bankAccNo = bankAccNo;
		super(balance);
	}

	@Override
	public String checkBalance() {
		return "Bank Account balance: " + balance;
	}

	@Override
	public void payAmount(int amount) {
		if (balance > amount) {
			balance -= amount;
			System.out.println("Bank Account balance deducted by " + amount + ". New balance is: " + balance);
		} else {
			System.out.println("Bank Account balance insufficient.");
		}
	}
}
