public class Account {
    private double _balance;
    private String _name;

    public String getName(){
        return this._name;
    }

    public Account(String name, double balance){
        this._name = name;
        this._balance = balance;
    }

    public boolean Deposit(double amount){
        if(amount < 1){
            System.out.println("Deposit amount should be greater than 1.0");
            return false;
        }
        this._balance += amount;
        // System.out.println(amount + " deposited to account " + this._name + " and balance is " + this._balance);
        return true;
    }

    public boolean Withdraw(double amount){
        if(amount < 1){
            
            System.out.println("Withdraw amount should be greater thamn 1.0");
            return false;
        }
        else if(amount > this._balance){
            System.out.println("Insufficient Balance");
            return false;
        }
        this._balance -= amount;
        // System.out.println(amount + " withdrawn from account " + this._name + " and balance is " + this._balance);
        return true;
    }

    public void Print(){
        System.out.println("Name: " + this._name + "\nBalance: " + this._balance);
    }
}
