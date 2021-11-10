import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Customer
{
    private String nama;
    private String id;
    private AtmCard card;
    
    // method constructor
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

    // Mendapatkan pin
    public int getPin() throws IOException
    {
        return card.getPin();
    }

    // Mendapatkan saldo
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
        // Membaca 1 baris
        String data = bufferedReader.readLine();
        // Diambil data yang dipisahkan dengan koma(,)
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        // Dibaca sampai akhir dari file
        while (data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Pindah ke ID
            stringTokenizer.nextToken();
            // Ditemukan saldo berdasarkan ID
            if (data.contains(id)) {
                // Pindah ke pin
                stringTokenizer.nextToken();
                // Pindah ke saldo
                stringTokenizer.nextToken();
                // Pindah dan dapatkan nama
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