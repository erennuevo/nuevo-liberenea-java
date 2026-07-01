package payment.processing.system;

public final class PayPal extends OnlinePayment
	implements PaymentSystem {
	
	private int paypalNo;
	private int balance;
	
	public PayPal(int paypalNo, int balance) {
		this.paypalNo = paypalNo;
		this.balance = balance; 
	}
	
	@Override
	public String checkBalance() {
		return "PayPal balance: " + balance;
	}

	@Override
	public void payAmount(int amount) {
		balance -= amount;
		System.out.println("PayPal balance deducted by " + amount + ". New balance is: " + balance);
	}
}