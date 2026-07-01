package payment.processing.system;

/* I was able to demonstrate inheritance by creating a class hierarchy.
 * PayPal is a concrete child class of OnlinePayment, inheriting the balance field. */
public final class PayPal extends OnlinePayment
	implements PaymentSystem {
	
	private int paypalNo;
	
	public PayPal(int paypalNo, int balance) {
		this.paypalNo = paypalNo;
		super(balance);
	}
	
	@Override
	public String checkBalance() {
		return "PayPal balance: " + balance;
	}

	@Override
	public void payAmount(int amount) {
		if (balance > amount) {
			balance -= amount;
			System.out.println("PayPal balance deducted by " + amount + ". New balance is: " + balance);
		} else {
			System.out.println("PayPal balance insufficient.");
		}
	}
}