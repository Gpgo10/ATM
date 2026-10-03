import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageManager {
    public ArrayList<User> loadUsers(){
        ArrayList<User> users = new ArrayList<>();
        try {
            File file = new File("users.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] data = line.split(";");

                String cardNumber = data[0];
                String pinCode = data[1];
                double balance = Double.parseDouble(data[2]);

                User user = new User(cardNumber,
                        pinCode,
                        balance);
                users.add(user);
            }
            fileScanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("users.txt dont found");
        }
        return users;
    }


    public void saveUsers(ArrayList<User> users){
        try{
            FileWriter writer=new FileWriter("users.txt");
            for (User user:users){
                writer.write(user.getCardNumber()+";"+
                        user.getPinCode()+";"+
                        user.getBalance()+"\n"
                );
            }
            writer.close();
        }catch (IOException e){
            System.out.println("Error in safe");
        }
    }

}
