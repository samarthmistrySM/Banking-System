public class DepositTransaction {

    private Account _account;
    private double _amount;
    private boolean _executed;
    private boolean _success;
    private boolean _reversed;

    public DepositTransaction(Account account, double amount) {
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
            System.out.println("Deposit Pending..");
        else if (!_success)
            System.out.println("Something went wrong!");
        else if (_reversed)
            System.out.println("Deposit Reversed..");
        else if (_success) {
            System.out.println("Deposit of " + this._amount + " to " + this._account.getName() + " Completed..");
        }
    }

    public void Execute(){
        if(_executed && _success){
            try{
                throw new Exception("Deposit already executed!");
            }catch(Exception e){
                System.out.println(e);
            }
        }
        _executed = true;
        _success = _account.Deposit(_amount);
        // Print();
        if(!_success) System.out.println("Something went Wrong!");
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
        _reversed = _account.Withdraw(_amount);

        if(!_reversed){
            try {
                throw new Exception("Rollback failed!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
