package payment.processing.system;

public final class BankTransfer extends OnlinePayment
	implements PaymentSystem {

	private int bankAccNo;
	private int balance;
	
	public BankTransfer(int bankAccNo, int balance) {
		this.bankAccNo = bankAccNo;
		this.balance = balance; 
	}

	@Override
	public String checkBalance() {
		return "Bank Account balance: " + balance;
	}

	@Override
	public void payAmount(int amount) {
		balance -= amount;
		System.out.println("Bank Account balance deducted by " + amount + ". New balance is: " + balance);
	}
}
