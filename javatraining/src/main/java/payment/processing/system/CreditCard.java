package payment.processing.system;

/* I was able to demonstrate inheritance by creating a class hierarchy.
 * CreditCard is a concrete hild class of PhysicalPayment, inheriting the balance field. */
public final class CreditCard extends PhysicalPayment 
	implements PaymentSystem {
	
	private final int cardNumber;
	
	public CreditCard(int cardNumber, int balance) {
		this.cardNumber = cardNumber;
		super(balance);
	}
	
	@Override
	public String checkBalance() {
		return "Credit Card balance: " + balance;
	}
	
	@Override
	public void payAmount(int amount) {
		if (balance > amount) {
			balance -= amount;
			System.out.println("Credit Card balance deducted by " + amount + ". New balance is: " + balance);
		} else {
			System.out.println("Credit Card balance insufficient.");
		}
	}

}
