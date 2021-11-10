import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {
    static Scanner in = new Scanner(System.in);
    static int random = (int) (Math.random()* (1000000 - 100000)) + 100000;
    static String id = Integer.toString(random);

    private static void tambahAkun(ArrayList<String> data) throws IOException {
        AtmCard card = new AtmCard();
        
        FileWriter fileWriter = new FileWriter("Database.DATA", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(id + "," + card.getPin() + "," + card.getBalance() + "," + data.get(0) + "," + data.get(2) + "," + data.get(1));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void tambahAkun(ArrayList<String> data, int saldoAwal) throws IOException {
        AtmCard card = new AtmCard(); 
        String id = Integer.toString(random);

        FileWriter fileWriter = new FileWriter("Database.DATA", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(id + "," + card.getPin() + "," + saldoAwal + "," + data.get(0) + "," + data.get(2) + "," + data.get(1));
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
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
                // Data collection
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
                System.out.println("Terima kasih telah menggunakan jasa bank kami!!");
                break;
            
            default:
                System.err.println("Inputan anda salah!!");
                break;
        }
    }
}