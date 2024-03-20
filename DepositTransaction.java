public class DepositTransaction extends Transaction {

    private Account _account;

    public DepositTransaction(Account account, double amount) {
        super(amount);
        this._account = account;
    }

    @Override
    public void Print() {
        if (!Executed())
            System.out.println("Deposit Pending..");
        else if (!Success())
            System.out.println("Something went wrong!");
        else if (Reversed())
            System.out.println("Deposit Reversed..");
        else if (Success()) {
            System.out.println("Deposit of " + this._amount + " to " + this._account.getName() + " Completed..");
        }
    }

    @Override
    public void Execute() {
        super.Execute();
        _success = _account.Deposit(_amount);

        if (!_success)
            System.out.println("Something went Wrong!");
        Print();
    }

    @Override
    public void Rollback() {
        super.Rollback();
        boolean _completed = _account.Withdraw(_amount);

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