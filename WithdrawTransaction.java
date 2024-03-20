public class WithdrawTransaction {

    public Account _account;
    public double _amount;
    public boolean _executed;
    public boolean _success;
    public boolean _reversed;

    public WithdrawTransaction(Account account, double amount) {
        this._account = account;
        this._amount = amount;
    }

    public boolean Executed() {
        return _executed;
    }

    public boolean Success(){
        return _success;
    }

    public boolean Reversed(){
        return _reversed;
    }

    public void Print() {
        if (!_executed)
            System.out.println("Withdraw Pending..");
        else if (!_success)
            System.out.println("Insufficient Balance..");
        else if (_reversed)
            System.out.println("Withdraw Reversed..");
        else if (_success) {
            System.out.println("Withdraw of " + this._amount + " from " + this._account.getName() + " Completed..");
        }
    }

    public void Execute(){
        if(_executed && _success){
            try{
                throw new Exception("Withdraw already executed!");
            }catch(Exception e){
                System.out.println(e);
            }
        }
        _executed = true;
        _success = _account.Withdraw(_amount);
        // Print();

        if(!_success) System.out.println("Insufficient balance!");
    }

    public void Rollback(){
        if(_reversed){
            try {
                throw new Exception("Transaction already reversed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(!_success){
            try {
                throw new Exception("Nothing to Rollback!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        _reversed = _account.Deposit(_amount);

        if(!_reversed){
            try {
                throw new Exception("Rollback failed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
