import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class AtmCard
{
    private String id;
    private int pin;
    private int balance;

    // Method constructor dengan pin default dan saldo minimal 50k
    public AtmCard()
    {
        this.pin = 123456;
        this.balance = 50000;
    }

    // Method constructor dengan parameter id, sesuai user
    public AtmCard(String id) throws IOException {
        this.id = id;
        // Dapatkan pin dari database
        this.pin = getPin(id);
        // Dapatkan saldo dari database
        this.balance = getBalance(id);
    }   

    // Method Mendapatkan PIN default
    public int getPin() {
        return this.pin;
    }

    // Method Mendapatkan Saldo awal
    public int getBalance() {
        return this.balance;
    }

    // Method mendapatkan PIN di database
    public int getPin(String id) throws IOException
    {
        FileReader fileReader = new FileReader("Database.DATA");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // Membaca 1 baris
        String data = bufferedReader.readLine();
        // Diambil data yang dipisahkan dengan koma(,)
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        // Dibaca sampai akhir dari file
        while (data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Ditemukan pin berdasarkan ID
            if (id.equals(stringTokenizer.nextToken())) {
                // Mendapatkan pin
                this.pin = Integer.parseInt(stringTokenizer.nextToken());
                break;
            } else {
                // Baca baris berikutnya
                data = bufferedReader.readLine();
            }            
        }
        bufferedReader.close();

        return this.pin;
    }

    // Mengubah pin pada kartu dan database
    public void setPin(int pinBaru) throws IOException
    {
        // Buka database original
        File file = new File("Database.DATA");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        // Membuat Database temporary
        File temp = new File("TempDatabase.TEMP");
        FileWriter fileWriter = new FileWriter(temp);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Membaca data
        String data = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        // Dibaca sampai akhir dari file
        while(data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Pindah ke ID
            if(id.equals(stringTokenizer.nextToken())) {
                
                // Majukan cursor ke PIN lama
                stringTokenizer.nextToken();
                // Dapatkan saldo
                String nominal = stringTokenizer.nextToken();
                // Dapatkan nama
                String nama = stringTokenizer.nextToken();
                // Copy ke database sementara
                bufferedWriter.write(id + "," + Integer.toString(pinBaru) + "," + nominal + "," + nama + "\n");
            } else {
                // Copy ke database sementara
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            data = bufferedReader.readLine();
        }
        // Menulis data ke file
        bufferedWriter.flush();
        
        // Jangan lupa tutup
        bufferedReader.close();
        bufferedWriter.close();

        // Delete database lama
        file.delete();
        // Rename database temp ke DATABASE.DATA
        temp.renameTo(file);
    }

    
    public int getBalance(String id) throws IOException
    {
        FileReader fileReader = new FileReader("Database.DATA");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String data = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        while (data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Ditemukan ID
            if (id.equals(stringTokenizer.nextToken())) {
                stringTokenizer.nextToken();
                this.balance = Integer.parseInt(stringTokenizer.nextToken());
                break;
            } else {
                // Baca baris berikutnya
                data = bufferedReader.readLine();
            }            
        }
        bufferedReader.close();

        return this.balance;
    }

    // Method perbarui saldo di database
    public void setBalance(int balanceBaru) throws IOException
    {
        // Buka database original
        File file = new File("Database.DATA");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        // Membuat Database temporary
        File temp = new File("TempDatabase.TEMP");
        FileWriter fileWriter = new FileWriter(temp);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Membaca data
        String data = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

        while(data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Pengecekan ID
            if(id.equals(stringTokenizer.nextToken())) {
                // Majukan cursor ke PIN
                String pin = stringTokenizer.nextToken();
                // Pindah ke saldo lama
                stringTokenizer.nextToken();
                // Dapatkan nama
                String nama = stringTokenizer.nextToken();
                // Copy ke database sementara
                bufferedWriter.write(id + "," + pin + "," + Integer.toString(balanceBaru) + "," + nama);
                bufferedWriter.newLine();
            } else {
                // Copy ke database sementara
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            data = bufferedReader.readLine();
        }
        // Menulis data ke file
        bufferedWriter.flush();
        
        // Jangan lupa tutup
        bufferedReader.close();
        bufferedWriter.close();

        // Delete database lama
        file.delete();
        // Rename database temp ke DATABASE.DATA
        temp.renameTo(file);
    }
}