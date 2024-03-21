import java.time.LocalDateTime;

public class Transaction {
    protected double _amount;
    protected boolean _success;

    private boolean _executed;
    private boolean _reversed;
    private LocalDateTime _dateStamp;

    public boolean Success() {
        return this._success;
    }

    public boolean Executed() {
        return this._executed;
    }

    public boolean Reversed() {
        return this._reversed;
    }

    public LocalDateTime DateTime() {
        return this._dateStamp;
    }

    public void SetReversed(boolean bool) {
        this._reversed = bool;
    }

    public Transaction(double amount) {
        this._amount = amount;
    }

    public void Print() {
        System.out.println("Transaction Amount: " + this._amount + " Time: " + DateTime());
    }

    public String HistoryHelper() {
        return ("Transaction Amount: " + this._amount + " and Time: " + DateTime());
    }

    public void Execute() {
        try {
            if (_executed && _success) {
                throw new Exception("Transaction previously executed!!");
            }

            _dateStamp = LocalDateTime.now();
            _executed = true;
        } catch (Exception e) {
            TerminalColor.printRed(e + "Try again..");
        }
    }

    public void Rollback() {
        try {
            if (_reversed) {
                throw new Exception("Transaction already reversed..");
            } else if (!_success) {
                throw new Exception("Nothing to Rollback..");
            }

            _dateStamp = LocalDateTime.now();
            _reversed = true;

        } catch (Exception e) {
            TerminalColor.printRed(e + " Try again..");
        }
    }
}