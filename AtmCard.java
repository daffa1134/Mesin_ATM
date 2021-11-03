public class AtmCard 
{
    private int pin;
    private int balance;

    // Method constructor dengan pin default dan saldo minimal 50k
    public AtmCard()
    {
        this.pin = 123456;
        this.balance = 50000;
    }

    public int getPin()
    {
        return this.pin;
    }

    // Mengubah pin pada kartu
    public void setPin(int pinBaru)
    {
        this.pin = pinBaru;
    }
    
    public int getBalance()
    {
        return this.balance;
    }

    public void setBalance(int balanceBaru)
    {
        this.balance = balanceBaru;
    }
    
}