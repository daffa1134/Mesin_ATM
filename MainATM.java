import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class MainATM{
    static private int pin;
    static private Customer user;
    static Scanner input_int = new Scanner(System.in);
    static Scanner input_str = new Scanner(System.in);
    
    public MainATM()
    {

    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex){
            System.err.println("Tidak bisa clear screen");
        }
    }
    
    public void struk() throws IOException
    {
        System.out.println("===========================================================");
        System.out.println("Resi tercetak otomatis saat anda keluar!\nHarap simpan tanda terima ini sebagai bukti transaksi anda.\n");
        double random = 100000 + Math.random() * (1000000-100000);
        int resi = (int) random;
        System.out.println("No. Resi\t: " + resi);
        Date tanggal = new Date();
        System.out.println("Pemilik\t\t: " + user.getNama());
        System.out.println("Tanggal\t\t: " + tanggal);
        System.out.println("Saldo akhir\t: Rp. " + user.getBalance() + "\n");
        System.out.println("===========================================================");
        System.out.println("Terima kasih telah menggunakan ATM-KU");

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
    
    public static void main(String[] args) throws Exception
    {       
        int trial, menu, nominal, newPin, newPinConfirm;
        String confirm, id;
        System.out.print("Masukkan ID anda: ");
        id = input_str.nextLine();
        // Cek di database
        if (cekID(id)) {
            user = new Customer(id);
        } else {
            System.out.println("ID tidak ditemukan!!");
            System.out.println("Silahkan mendaftar di bank!!");
            System.exit(0);
        }
        //struk
        MainATM strk = new MainATM();

        while (true) {
            System.out.print("Masukkan pin anda: ");
            pin = input_int.nextInt();
            
            // Cek PIN dari database
            
            trial = 1;

            while(pin != user.getPin()){
                System.out.print("Pin salah! Silahkan masukkan lagi: ");
                pin = input_int.nextInt();
                trial ++;
                if (trial == 3) {
                    System.out.println("Error! Silahkan ambil kartu dan coba lagi..");
                    System.exit(0);
                }
            }
            
            do{
                clearScreen();
                System.out.println("Selamat Datang Di ATM-KU..");
                System.out.println("Menu :");
                System.out.println("1. Cek Saldo");
                System.out.println("2. Tarik");
                System.out.println("3. Setor");
                System.out.println("4. Ganti Pin");
                System.out.println("5. Keluar");

                System.out.print("Pilih menu: ");
                menu = input_int.nextInt();
                
                switch (menu){
                    case 1:
                        System.out.println("Saldo anda sekarang: Rp. " + user.getBalance());
                        break;
                    case 2:
                        System.out.println("ATM ini hanya melayani penarikan dengan pecahan 100000!");
                        System.out.print("Masukkan nominal saldo: ");
                        nominal = input_int.nextInt();
                        
                        System.out.println("Konfirmasi: Anda akan melakukan penarikan dengan nominal berikut? y/n Rp. " + nominal);
                        confirm = input_str.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")){
                            System.out.println("Saldo awal anda adalah: Rp. " + user.getBalance());
                        } else break;
                        
                        if (nominal <= user.getBalance() && (nominal % 100000) == 0){
                            user.setWithdrawBalance(nominal);
                            System.out.println("Penarikan berhasil!");
                            System.out.println("Saldo anda sekarang: Rp. " + user.getBalance());
                        } 
                        else if (nominal % 100000 != 0){
                            System.out.println("Error!ATM ini hanya melayani penarikan dengan pecahan 100000!");
                        }
                        else{
                            System.out.println("Maaf saldo anda tidak cukup untuk melakukan penarikan!");
                            System.out.println("Silahkan lakukan penambahan saldo");
                        }
                        break;
                    case 3:
                        System.out.print("Masukkan nominal saldo: ");
                        nominal = input_int.nextInt();
                        
                        System.out.println("Konfirmasi: Anda akan melakukan penyetoran dengan nominal berikut? y/n Rp. " + nominal + " ");
                        confirm = input_str.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")){
                            System.out.println("Saldo awal anda adalah: Rp. " + user.getBalance());
                            user.setDepositBalance(nominal);
                            System.out.println("Penyetoran berhasil!");
                            System.out.println("Saldo anda sekarang: Rp. " + user.getBalance());
                        }else break;
                        break;
            
                    case 4:
                        System.out.print("Masukkan pin anda: ");
                        pin = input_int.nextInt();
                        
                        while(pin != user.getPin()){
                            System.out.print("Pin salah! Silahkan masukkan lagi: ");
                            pin = input_int.nextInt();
                        }
                        
                        System.out.print("Silahkan masukkan pin baru: ");
                        newPin = input_int.nextInt();
                        
                        System.out.print("Konfirmasi pin baru: ");
                        newPinConfirm = input_int.nextInt();
                        
                        if(newPin == newPinConfirm){
                            user.setPin(newPin);
                            System.out.println("Pin berhasil diganti!");
                        }else{
                            System.out.println("Pin gagal dikonfirmasi! Pastikan pin baru dan konfirmasi pin baru sama");
                        }
                        break;
                    case 5:
                        strk.struk();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Anda salah memilih menu.");
                        System.exit(0);
                        break;
                }
                System.out.print("Lanjutkan transaksi? y/n ");
                confirm = input_str.nextLine();
            } while(confirm.equalsIgnoreCase("y"));
            clearScreen();
            strk.struk();
            break;
        }
    }
}
