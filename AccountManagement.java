import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AccountManagement {
    static Scanner in = new Scanner(System.in);
    // Mendapatkan ID acak untuk pendaftar baru
    static int random = (int) (Math.random()* (1000000 - 100000)) + 100000;
    static String id = Integer.toString(random);

    private static void tambahAkun(ArrayList<String> data) throws IOException {
        // POLYMORPHISM STATIC
        // Di sini dipanggil method constructor yang tidak memiliki parameter
        AtmCard card = new AtmCard();
        
        FileWriter fileWriter = new FileWriter("Database.DATA", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(id + "," + card.getPin() + "," + card.getBalance() + "," + data.get(0) + "," + data.get(2) + "," + data.get(1));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void tambahAkun(ArrayList<String> data, int saldoAwal) throws IOException {
        // POLYMORPHISM STATIC
        // Di sini dipanggil method constructor yang tidak memiliki parameter
        AtmCard card = new AtmCard(); 
        // Menulis data pada file Database.DATA di akhir file
        FileWriter fileWriter = new FileWriter("Database.DATA", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        // Menulis data pendaftar baru
        bufferedWriter.write(id + "," + card.getPin() + "," + saldoAwal + "," + data.get(0) + "," + data.get(2) + "," + data.get(1));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void hapusAkun(String id) throws IOException {
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
                // Skip
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

    private static boolean cekID(String id) throws IOException{
        boolean adaData = true;
        // Membuka file database
        FileReader fileReader = new FileReader("Database.DATA");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // Proses mencari id dari database
        String data = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        while (data != null) {
            stringTokenizer = new StringTokenizer(data, ",");
            // Ditemukan idnya
            if (id.equals(stringTokenizer.nextToken())) {
                adaData = true;
                break;
            } else {
                adaData = false;
            }
            // Baca baris berikutnya
            data = bufferedReader.readLine();
        }
        bufferedReader.close();
        return adaData;
    }
    public static void main(String[] args) throws IOException {
        String select, confirm;
        
        System.out.println("Selamat datang di Bank-Ku");
        System.out.println("Ada yang bisa kami bantu?");
        System.out.println("1. Membuat akun");
        System.out.println("2. Menghapus akun");
        System.out.print("Menu: ");
        select = in.nextLine();

        switch (select) {
            case "1":
                String nama, alamat, jk;
                // DATA COLLECTION
                ArrayList<String> data = new ArrayList<>();

                System.out.println("Silahkan isi formulir berikut ini!!");
                System.out.print("Nama lengkap: ");
                nama = in.nextLine();
                data.add(nama);
                System.out.print("Alamat: ");
                alamat = in.nextLine();
                data.add(alamat);
                System.out.print("Jenis kelamin: (L/P) ");
                jk = in.nextLine();
                data.add(jk);
                System.out.println("Setoran awal yang wajib adalah Rp. 50000");
                System.out.print("Apakah anda ingin menambah saldo? (y/n) ");
                confirm = in.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    int saldoAwal;
                    System.out.print("Berapa jumlah saldo yang ingin anda setor? ");
                    saldoAwal = in.nextInt();
                    while (saldoAwal < 50000) {
                        System.out.println("Harap mengisi saldo di atas 50000");
                        System.out.print("Berapa jumlah saldo yang ingin anda setor? ");
                        saldoAwal = in.nextInt();
                    }

                    tambahAkun(data, saldoAwal);
                } else {
                    tambahAkun(data);
                }
                System.out.println("ID anda adalah: " + id + " dengan default pin kartu ATM 123456");
                System.out.println("Harap mengingat ID anda baik-baik!!");
                System.out.println("Terima kasih telah mempercayakan uang anda pada bank kami!!");
                break;
            
            case "2":
                System.out.println("Silahkan masukkan ID untuk akun yang ingin di hapus!!");
                System.out.print("ID : ");
                id = in.nextLine();
                System.out.print("Apakah anda yakin akan menghapus akun dengan ID " + id + " ? (y/n) ");
                confirm = in.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    // Di cek ID nya apakah ada atau tidak di database
                    if (cekID(id)) {
                        hapusAkun(id);
                    } else {
                        System.out.println("Maaf, ID tidak ditemukan!! Harap masukkan ID yang terdaftar");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Terima kasih atas kepercayaan anda pada bank kami!!");
                }

                System.out.println("Terima kasih telah menggunakan jasa bank kami!!");
                break;
            
            default:
                System.err.println("Inputan anda salah!!");
                break;
        }
    }
}