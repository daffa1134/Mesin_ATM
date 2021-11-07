import java.io.IOException;

public class Customer
{
    // private String nama;
    private String id;
    private AtmCard card;
    
    // method constrkator
    public Customer(String id) throws IOException
    {
        this.id = id;
        // Composition
        card = new AtmCard(id);
    }
    /*
    other method/feature of customer
    */
    public String getId()
    {
        return this.id;
    }
    
    // Mengubah pin customer, dan juga merubahnya pada class AtmCard
    public void setPin(int pin) throws IOException
    {
        card.setPin(pin);
    }

    public int getPin() throws IOException
    {
        return card.getPin();
    }

    public int getBalance() throws IOException
    {
        return card.getBalance();
    }

    //method tarik saldo
    public void setWithdrawBalance(int nominal) throws IOException
    {
        // Mengupdate saldo pada class AtmCard
        card.setBalance(card.getBalance() - nominal);
    }
    // method setor saldo
    public void setDepositBalance(int nominal) throws IOException
    {
        // Mengupdate saldo pada class AtmCard
        card.setBalance(card.getBalance() + nominal);
    }
}