public class User {
    private String cardNumber;
    private String pinCode;
    private double balance;
    public User(String cardNumber,String pinCode,double balance){
        this.balance=balance;
        this.cardNumber=cardNumber;
        this.pinCode=pinCode;
    }
    public String getCardNumber(){
        return cardNumber;
    }
    public String getPinCode(){
        return pinCode;
    }
    public Double getBalance(){
        return balance;
    }
    public void deposit(double amount) throws InvalidAmountException{
        if(amount>0){
            balance = balance+amount;
        }
        if (amount <= 0){
            throw new InvalidAmountException("Summa more than 0");
        }
    }

    public  void withdraw(double amount)
    throws InvalidAmountException,InsufficientFundsException{
        if (amount <= 0) {
            throw new InvalidAmountException("Сумма должна быть больше нуля");
        }
        if (amount>balance){
            throw new InsufficientFundsException("no money");
        }
        balance -= amount;
    }
}

