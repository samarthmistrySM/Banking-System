import java.util.Scanner;

public class BankSystem {

    public static Scanner sc = new Scanner(System.in);
    
    public enum MenuOption{
        ADDACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        ROLLBACK,
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

    public static void DoDeposit(Bank bank){
        System.out.println("Enter the name of your account: ");
        String name = sc.nextLine();
        try {
            if(!bank.FindAccount(name)){
                throw new Exception("The account does not exitst!");
            }
            Account account = bank.GetAccount(name);
            System.out.println("Enter the amount you want to deposit: ");
            double amount = sc.nextDouble();
            DepositTransaction deposit = new DepositTransaction(account, amount);
            bank.Execute(deposit);
            sc.nextLine();
        } catch (Exception e) {
            TerminalColor.printRed(e + " Try again..");
        }
    }

    public static void DoWithdraw(Bank bank){
        System.out.println("Enter the name of your account: ");
        String name = sc.nextLine();
        try {
            if(!bank.FindAccount(name)){
                throw new Exception("The account does not exitst!");
            }
            Account account = bank.GetAccount(name);
            System.out.println("Enter the amount you want to withdraw: ");
            double amount = sc.nextDouble();
            WithdrawTransaction withdraw = new WithdrawTransaction(account, amount);
            bank.Execute(withdraw);
            sc.nextLine();
        } catch (Exception e) {
            TerminalColor.printRed(e + " Try again..");
        }
    }

    public static void DoTransfer(Bank bank){
        System.out.println("Enter the name of the account you want to transfer from: ");
        String fromName = sc.nextLine();
        System.out.println("Enter the name of the account you want to transfer to: ");
        String toName = sc.nextLine();

        try {
            if(!bank.FindAccount(fromName) || !bank.FindAccount(toName)){
                throw new Exception("Account not found..");
            }
            Account fromAccount = bank.GetAccount(fromName);
            Account toAccount = bank.GetAccount(toName);
            System.out.println("Enter the amount you want to trafsfer from " + fromAccount.getName() + " account to " + toAccount.getName() + " account");
            double amount = sc.nextDouble();

            TransferTransaction transfer = new TransferTransaction(fromAccount, toAccount, amount);
            bank.Execute(transfer);
            sc.nextLine();
        } catch (Exception e) {
            TerminalColor.printRed(e + " Try again..");
        }
    }

    public static void DoRollback(Bank bank){
        bank.PrintTranscationHistory();
        System.out.println();
        System.out.println("Enter transaction # to rollback (0 for no rollback) " +  0 + " from " + bank.geTransactions().size());
        int result = sc.nextInt();

        if (result == 0)
            return;

        bank.Rollback(bank.geTransactions().get(result - 1));
        TerminalColor.printGreen("Rollback Completed..");
        sc.nextLine();
    }

    public static void Print(Bank bank){
        System.out.println("Enter the name of the account for which you want to print details: ");
        String name = sc.nextLine();

        try{
            if(!bank.FindAccount(name)){
                throw new Exception("Account not found...");
            }
            Account account = bank.GetAccount(name);
            account.Print();
        }catch (Exception e) {
            TerminalColor.printRed(e + " Try again..");
        }
    }
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        boolean isQuit = false;
        int count = 0;
        Bank bank = new Bank();

        while (!isQuit) {

            clearScreen();
            TerminalColor.printPurple("======|| Welcome to Bank ||======");
            TerminalColor.printCyan("Enter your Choice: \n1.AddAccount \n2.Deposit \n3.Withdraw \n4.Transfer \n5.Rollback \n6.Print \n7.QUIT ");

            if(count < 2){
                TerminalColor.printRed("⚠️ First you have to add two Account ⚠️");
            }

            int n = sc.nextInt();

            MenuOption choice = MenuOption.values()[n-1];

            sc.nextLine();

            switch (choice) {
                case MenuOption.ADDACCOUNT:
                    NewAccount(bank);
                    count++;
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.DEPOSIT:
                    DoDeposit(bank);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.WITHDRAW:
                    DoWithdraw(bank);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.TRANSFER:
                    DoTransfer(bank);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.ROLLBACK:
                    DoRollback(bank);
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;

                case MenuOption.PRINT:
                    Print(bank);
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
