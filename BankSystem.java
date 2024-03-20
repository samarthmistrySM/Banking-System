import java.util.Scanner;

public class BankSystem {

    public static Scanner sc = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    
    public enum MenuOption{
        ADDACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        PRINT,
        QUIT
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void NewAccount(Bank bank){
        TerminalColor.printBlue("Name of account: ");
        String name = sc.nextLine();
        
        try {
            if(!bank.FindAccount(name)){
                System.out.println("Enter the balance of the account: ");
                int balance = sc.nextInt();
                Account newAccount = new Account(name, balance);
                bank.AddAccount(newAccount);
                TerminalColor.printGreen("Account Added..");
                sc.nextLine();
            }else{
                throw new Exception("Account already exist..");
            }
        } catch (Exception e) {
            System.out.println(e + " Try Again");
        }
        
    }

    public static void DoDeposit(Account account){
        System.out.println("Enter the amount you want to Deposit");
        try {
            double amount = sc.nextDouble();
            account.Deposit(amount);
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(e + " Try Again");
        }
    }

    public static void DoWithdraw( Account account){
        System.out.println("Enter the amount you want to Withdraw");
        try {
            double amount = sc.nextDouble();
            account.Withdraw(amount);
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(e + " Try Again");
        }
    }

    public static void DoTransfer(Account fromAccount, Account toAccount){
        System.out.println("Enter the amount you want ot transfer from " + fromAccount._name + " to " + toAccount._name );
        try{
            double amount = sc.nextDouble();
            TransferTransaction transfer = new TransferTransaction(fromAccount, toAccount, amount);
            transfer.Execute();
            sc.nextLine();
        }catch(Exception e){
            System.out.println(e + " Try again");
        }
    }

    public static void Print(Account account){
        account.Print();
    }
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        boolean isQuit = false;

        Account account1 = new Account("Calm", 10000);
        Account account2 = new Account("Encore", 10000);

        Bank bank = new Bank();

        while (!isQuit) {

            clearScreen();
            TerminalColor.printPurple("======|| Welcome to Bank ||======");
            TerminalColor.printCyan("Enter your Choice: \n1.AddAccount \n2.Deposit \n3.Withdraw \n4.Transfer \n5.Print \n6.QUIT ");

            int n = sc.nextInt();

            MenuOption choice = MenuOption.values()[n-1];

            sc.nextLine();

            switch (choice) {
                case MenuOption.ADDACCOUNT:
                    NewAccount(bank);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.DEPOSIT:
                    DoDeposit(account1);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.WITHDRAW:
                    DoWithdraw(account1);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.TRANSFER:
                    DoTransfer(account1,account2);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.PRINT:
                    Print(account1);
                    Print(account2);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.QUIT:
                    System.out.println("Quiting.....");
                    isQuit = true;
                    sc.close();
                    clearScreen();
                    break;
            
                default:
                    System.out.println("wrong input! try again..");
                    break;
            }
        }
    }
}
