import java.util.Scanner;

public class MainATM{
    static private int id;
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

    public static void main(String[] args) throws Exception
    {       
        int trial, menu, nominal, newPin, newPinConfirm;
        String confirm;
        
        // Database
        Customer user = new Customer(id);

        while (true) {
            System.out.print("Masukkan pin anda: ");
            id = input_int.nextInt();
            trial = 1;

            while(id != user.getPin()){
                System.out.print("Pin salah! Silahkan masukkan lagi: ");
                id = input_int.nextInt();
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
                        System.out.print("Masukkan nominal saldo: ");
                        nominal = input_int.nextInt();
                        
                        System.out.println("Konfirmasi: Anda akan melakukan penarikan dengan nominal berikut? y/n Rp. " + nominal);
                        confirm = input_str.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")){
                            System.out.println("Saldo awal anda adalah: Rp. " + user.getBalance());
                        }else break;
                        
                        if (nominal < user.getBalance()){
                            user.setWithdrawBalance(nominal);
                            System.out.println("Penarikan berhasil!");
                            System.out.println("Saldo anda sekarang: Rp. " + user.getBalance());
                        }else{
                            System.out.println("Maaf saldo anda tidak cukup untuk melakukan penarikan!");
                            System.out.println("Silahkan lakukan penambahan saldo");
                        }
                        break;
                    case 3:
                        System.out.print("Masukkan nominal saldo: ");
                        nominal = input_int.nextInt();
                        
                        System.out.print("Konfirmasi: Anda akan melakukan penyetoran dengan nominal berikut? y/n Rp. " + nominal + " ");
                        confirm = input_str.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")){
                            user.setDepositBalance(nominal);
                            System.out.println("Saldo awal anda adalah: Rp. " + user.getBalance());
                        }else break;

                        
                    case 4:
                        System.out.print("Masukkan pin anda: ");
                        id = input_int.nextInt();
                        
                        while(id != user.getPin()){
                            System.out.print("Pin salah! Silahkan masukkan lagi: ");
                            id = input_int.nextInt();
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
                        System.out.println("Terima Kasih :)");
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
            break;
        }
    }
}