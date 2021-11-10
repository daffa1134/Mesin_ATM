import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Customer
{
    private String nama;
    private String id;
    private AtmCard card;
    
    // method constrkator
    public Customer(String id) throws IOException
    {
        this.id = id;
        nama = getNama();
        // Composition
        card = new AtmCard(id);
    }

    // Mendapatkan ID customer
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

    // Mendapatkan nama customer
    public String getNama() throws IOException {
        FileReader fileReader = new FileReader("Database.DATA");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String data = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        while (data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            stringTokenizer.nextToken();
            // Ditemukan saldo berdasarkan ID
            if (data.contains(id)) {
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                this.nama = stringTokenizer.nextToken();
                break;
            } else {
                // Baca baris berikutnya
                data = bufferedReader.readLine();
            }            
        }
        bufferedReader.close();


        return this.nama;
    }
}