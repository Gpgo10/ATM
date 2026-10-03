import java.util.ArrayList;

public class BankService {
    private ArrayList<User> users = new ArrayList<>();


    public BankService(ArrayList<User> users) {
        this.users = users;
    }
    public User findUserByCardNumber(String cardNumber){

        for(User user:users){
            if (user.getCardNumber().equals(cardNumber)){
                return user;
            }
        }
        return null;
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public User authorize(String cardNumber, String pinCode)
            throws AccountNotFoundException, InvalidPinException {

        User user = findUserByCardNumber(cardNumber);

        if (user == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (!user.getPinCode().equals(pinCode)) {
            throw new InvalidPinException("Invalid PIN");
        }

        return user;
    }
}
