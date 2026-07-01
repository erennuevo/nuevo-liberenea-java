package payment.processing.system;

public class Main {
	
	public static void main(String[] args) {
		PaymentSystem[] payments = {
				new CreditCard(123, 1000),
				new PayPal(456, 2000),
				new BankTransfer(789, 3000)
		};
		
		for (PaymentSystem payment : payments) {
			System.out.println(payment.checkBalance());
			payment.payAmount(500);
		}
	}
}
