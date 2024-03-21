import java.util.*;

public class Bank {

    private ArrayList<Account> _accounts = new ArrayList<>();
    private ArrayList<Transaction> _transactions = new ArrayList<>();

    public Scanner sc =  new Scanner(System.in);

    public List<Transaction> geTransactions (){
        return _transactions;
    }

    public Bank(){}

    public void AddAccount(Account account){
        this._accounts.add(account);
    }

    public Account GetAccount(String Name){
        Account account = null;
        for(Account acc : _accounts){
            if(acc.getName().equals(Name)){
                account = acc;
            }
        }
        return account;
    }

    public boolean FindAccount(String Name){
        for (Account account : _accounts) {
            if(account.getName().equals(Name)){
                return true;
            }
        }
        return false;
    }    
    
    public void Execute(Transaction transaction){
        _transactions.add(transaction);
        try {
            transaction.Execute();
        } catch (Exception e) {
            TerminalColor.printRed(e + " Try Again..");
        }
    }

    public void Rollback(Transaction transaction){
        try{
            transaction.Rollback();
        }catch(Exception e){
            TerminalColor.printRed(e + " Try Again..");
        }
    }

    public String TransactionType(Transaction transaction){
        switch (transaction.getClass().toString()) {
            case "class TransferTransaction":
                return "Transfer";

            case "class WithdrawTransaction":
                return "Withdraw";
            
            case "class DepositTransaction":
                return "Deposit";
            }
            return "Unknown";
    }

    public void PrintTranscationHistory(){
        for (Transaction transaction : _transactions) {
            System.out.println(
                TransactionType(transaction) + " with " + transaction.HistoryHelper()
            );
        }
    }
}
