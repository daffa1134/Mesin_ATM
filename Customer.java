public class Customer extends AtmCard
{
    private int id, pin, balance;

    /* 
    method constrkator
    */
    public Customer(int id)
    {
        super();
        this.id = id;
        this.pin = getPinAwal();
        this.balance = getBalanceAwal();
    }
    /*
    other method/feature of customer
    */
    public int getId()
    {
        return this.id;
    }
    public int getPin()
    {
        return this.pin;
    }
    public void setPin(int pin)
    {
        this.pin = pin;
    }
    public int getBalance()
    {
        return this.balance;
    }
    //method tarik saldo
    public void setWithdrawBalance(int nominal)
    {
        this.balance -= nominal;
    }
    // method setor saldo
    public void setDepositBalance(int nominal)
    {
        this.balance += nominal;
    }
}