public class WithdrawTransaction extends Transaction {

    private Account _account;

    public WithdrawTransaction(Account account, double amount) {
        super(amount);
        this._account = account;
    }

    @Override
    public void Print() {
        if (!Executed())
            System.out.println("Withdraw Pending..");
        else if (!Success())
            System.out.println("Insufficient Balance..");
        else if (Reversed())
            System.out.println("Withdraw Reversed..");
        else if (Success()) {
            System.out.println("Withdraw of " + this._amount + " from " + this._account.getName() + " Completed..");
        }
    }

    @Override
    public void Execute() {
        super.Execute();
        _success = _account.Withdraw(_amount);


        if (!_success)
            System.out.println("Insufficient balance!");
        Print();
    }




    
    @Override
    public void Rollback() {
        super.Rollback();
        boolean _completed = _account.Deposit(_amount);
        
        if (!_completed) {
            try {
                throw new Exception("Rollback failed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        super.SetReversed(true);
    }
}