public class TransferTransaction extends Transaction {

    private Account _fromAccount;
    private Account _toAccount;

    private DepositTransaction _deposit;
    private WithdrawTransaction _withdraw;

    

    public TransferTransaction(Account fromAccount, Account toAccount, double amount) {
        super(amount);

        this._fromAccount = fromAccount;
        this._toAccount = toAccount;

        _deposit = new DepositTransaction(toAccount, amount);
        _withdraw = new WithdrawTransaction(fromAccount, amount);

    }

    public boolean Success() {
        return (_deposit.Success() && _withdraw.Success());
    }

    public void Print() {

        if (!Executed())
            System.out.println("Tranfer Pending..");
        else if (!Success())
            System.out.println("Transfer Failed!");
        else if (Reversed())
            System.out.println("Transfer Reversed..");
        else if (Success()) {
            System.out.println("Transfer of " + this._amount + " from " + this._fromAccount.getName() + " to " + this._toAccount.getName()
                    + " Completed..");
        }
    }

    public void Execute() {
        super.Execute();
        try {
            _withdraw.Execute();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (_withdraw.Success()) {
            try {
                _deposit.Execute();
            } catch (Exception e) {
                System.out.println("Transfer Failed: " + e);
                try {
                    Rollback();
                } catch (Exception er) {
                    System.out.println("Withdraw not reversed " + er);
                }
            }
        }
        Print();
        _success = true;
    }

    public void Rollback() {
        super.Rollback();
        if(this.Success()){
            try {
                _deposit.Rollback();
            } catch (Exception e) {
                System.out.println("Deposite rollback failed!");
            }

            try {
                _withdraw.Rollback();
            } catch (Exception e) {
                System.out.println("Withdraw rollback failed!");
                return;
            }
        }
        super.SetReversed(true);
    }
}