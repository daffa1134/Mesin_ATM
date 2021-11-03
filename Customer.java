public class Customer
{
    private int id;
    private AtmCard card;

    /* 
    method constrkator
    */
    public Customer(int id)
    {
        this.id = id;
        // Composition
        card = new AtmCard();
    }
    /*
    other method/feature of customer
    */
    public int getId()
    {
        return this.id;
    }
    
    // Mengubah pin customer, dan juga merubahnya pada class AtmCard
    public void setPin(int pin)
    {
        card.setPin(pin);
    }

    public int getPin()
    {
        return card.getPin();
    }

    public int getBalance()
    {
        return card.getBalance();
    }

    //method tarik saldo
    public void setWithdrawBalance(int nominal)
    {
        // Mengupdate saldo pada class AtmCard
        card.setBalance(card.getBalance() - nominal);
    }
    // method setor saldo
    public void setDepositBalance(int nominal)
    {
        // Mengupdate saldo pada class AtmCard
        card.setBalance(card.getBalance() + nominal);
    }
}