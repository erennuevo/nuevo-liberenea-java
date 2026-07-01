package payment.processing.system;

/* Polymorphism is demonstrated here because I was able to instantiate
 * three different objects belonging to the same class, PaymentSystem. 
 * The same methods are called, but the behavior differs as observed 
 * in the objects' outputs. */
public class Main {
	
	public static void main(String[] args) {
		PaymentSystem[] payments = {
				new CreditCard(123, 1000),
				new PayPal(456, 2000),
				new BankTransfer(789, 3000)
		};
		
		for (PaymentSystem payment : payments) {
			System.out.println(payment.checkBalance());
			payment.payAmount(1500);
		}
	}
}
