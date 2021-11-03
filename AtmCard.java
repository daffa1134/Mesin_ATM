public class AtmCard 
{
    private int defaultPin;
    private int defaultBalance;

    public AtmCard(int defaultPin, int defaultBalance)
    {
        this.defaultPin = defaultPin;
        this.defaultBalance = defaultBalance;
    }
    public AtmCard()
    {
        this.defaultPin = 1234;
        this.defaultBalance = 10000;
    }

    public int getPinAwal()
    {
        return this.defaultPin;
    }
    public int getBalanceAwal()
    {
        return this.defaultBalance;
    }
}