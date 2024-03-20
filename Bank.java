import java.util.*;

public class Bank {

    public ArrayList<Account> _accounts = new ArrayList<>();
    public Scanner sc =  new Scanner(System.in);

    public Bank(){}

    public void AddAccount(Account account){
        this._accounts.add(account);
    }

    public Account GetAccount(String Name){
        Account account = null;
        for(Account acc : _accounts){
            if(acc._name.equals(Name)){
                account = acc;
            }
        }
        return account;
    }

    public boolean FindAccount(String Name){
        for (Account account : _accounts) {
            if(account._name.equals(Name)){
                return true;
            }
        }
        return false;
    }    
    
    public void ExecuteTransaction(WithdrawTransaction transaction){
        try {
            transaction.Execute();
            System.out.println("Do you want to Rollback? (Y/N)");
            String Rollback =  sc.nextLine();
            if(Rollback.toLowerCase() == "y"){
                transaction.Rollback();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ExecuteTransaction(DepositTransaction transaction){
        try {
            transaction.Execute();
            System.out.println("Do you want to Rollback? (Y/N)");
            String Rollback =  sc.nextLine().toLowerCase();
            if(Rollback == "y"){
                transaction.Rollback();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ExecuteTransaction(TransferTransaction transaction){
        try {
            transaction.Execute();
            System.out.println("Do you want to Rollback? (Y/N)");
            String Rollback =  sc.nextLine().toLowerCase();
            if(Rollback == "y"){
                transaction.Rollback();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
