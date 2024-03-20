public class TransferTransaction {

    public Account _fromAccount;
    public Account _toAccount;

    public double _amount;

    public DepositTransaction _deposit;
    public WithdrawTransaction _withdraw;

    public boolean _executed;
    public boolean _reversed;

    public TransferTransaction(Account fromAccount, Account toAccount, double amount) {
        this._fromAccount = fromAccount;
        this._toAccount = toAccount;
        this._amount = amount;

        _deposit = new DepositTransaction(toAccount, amount);
        _withdraw = new WithdrawTransaction(fromAccount, amount);

    }

    public boolean Executed() {
        return _executed;
    }

    public boolean Success() {
        return (_deposit.Success() && _withdraw.Success());
    }

    public boolean Reversed() {
        return _reversed;
    }

    public void Print() {
        if (!_executed)
            System.out.println("Tranfer Pending..");
        else if (!Success())
            System.out.println("Transfer Failed!");
        else if (_reversed)
            System.out.println("Transfer Reversed..");
        else if (Success()) {
            System.out.println("Transfer of " + this._amount + " from " + this._fromAccount._name + " to " + this._toAccount._name
                    + " Completed..");
        }
    }

    public void Execute() {
        if (_executed) {
            try {
                throw new Exception("Transition already executed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        _executed = true;
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
    }

    public void Rollback() {
        if (_reversed) {
            try {
                throw new Exception("Transaction already reversed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (!_executed) {
            try {
                throw new Exception("Transfer not Executed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(Success()){
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
        _reversed = true;
    }
}