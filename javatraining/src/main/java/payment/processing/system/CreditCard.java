package payment.processing.system;

public final class CreditCard extends PhysicalPayment 
	implements PaymentSystem {
	
	private final int cardNumber;
	private int balance;
	
	public CreditCard(int cardNumber, int balance) {
		this.cardNumber = cardNumber;
		this.balance = balance; 
	}
	
	@Override
	public String checkBalance() {
		return "Credit Card balance: " + balance;
	}
	
	@Override
	public void payAmount(int amount) {
		balance -= amount;
		System.out.println("Credit Card balance deducted by " + amount + ". New balance is: " + balance);
	}

}
