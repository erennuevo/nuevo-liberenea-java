package payment.processing.system;

public abstract sealed class OnlinePayment
	permits BankTransfer, PayPal {

}
