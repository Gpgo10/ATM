import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        scanner.useLocale(Locale.US);


        StorageManager storageManager=new StorageManager();
        ArrayList<User> users= storageManager.loadUsers();
        BankService bankService = new BankService(users);
        storageManager.saveUsers(bankService.getUsers());
        User authorizedUser=null;



        for (int chance = 1;chance<= 3;chance++) {
            System.out.println("Enter your Card:");
            String cardNumber = scanner.nextLine();


            User user = bankService.findUserByCardNumber(cardNumber);
            if (user == null) {
                System.out.println("User don't find");
                System.out.println("chance:"+chance+"\t"+"from 3");
                continue;
            }

            System.out.println("enter your pin code:");
            String pinCode = scanner.nextLine();

            if (user.getPinCode().equals(pinCode)) {
                System.out.println("Pin code right ");
                authorizedUser=user;
                break;
            } else {
                System.out.println("pin code wrong");
                System.out.println("Chance"+chance+"\t"+"from 3");

            }
        }
        if (authorizedUser == null){
            System.out.println("chance missed");
            return;
        }
        System.out.println("hello");
        boolean running=true;
        while (running) {
            System.out.println();
            System.out.println("=== ATM MENU ===");
            System.out.println("1. Проверить баланс");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять наличные");
            System.out.println("4. Выйти");

            System.out.println("Select your action:");

            int choice;
            try{
                choice=scanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("Enter number");
                scanner.nextLine();
                continue;
            }

            switch (choice){
                case 1:
                    System.out.println("your balance"+authorizedUser.getBalance());
                    break;
                case 2:
                    System.out.println("add balance:");
                    double amount;

                    try{
                        amount=scanner.nextDouble();

                    }catch (InputMismatchException e){
                        System.out.println("enter number!");
                        scanner.nextLine();
                        break;
                    }
                    try{
                        authorizedUser.deposit(amount);

                      storageManager.saveUsers(bankService.getUsers());
                    System.out.println("add balance:"+authorizedUser.getBalance());}
                    catch (InvalidAmountException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("get balance");
                    double withdrawAmount ;
                    try {
                        withdrawAmount=scanner.nextDouble();
                    }catch (InputMismatchException e){
                        System.out.println("enter numbers!");
                        scanner.nextLine();
                        break;
                    }
                    try{authorizedUser.withdraw(withdrawAmount);
                    storageManager.saveUsers(bankService.getUsers());
                    System.out.println(
                            "Balance after getting:"+authorizedUser.getBalance());}
                    catch (InvalidAmountException e){
                        System.out.println(e.getMessage());
                    }
                    catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("don't have this");
            }
        }
    }

}
