import java.util.Scanner;
public class MainATM{
    private static int id;
    private static Customer atm;

    public MainATM()
    {

    }
    public static void main(String[] args) 
    {       
        int trial, menu, nominal, newPin, newPinConfirm;
        String confirm;
        Scanner input_int = new Scanner(System.in);
        Scanner input_str = new Scanner(System.in);
        
        atm = new Customer(id);

        while (true) {
            System.out.print("Masukkan pin anda: ");
            id = input_int.nextInt();
            trial = 1;

            while(id != atm.getPin()){
                System.out.print("Pin salah! Silahkan masukkan lagi: ");
                id = input_int.nextInt();
                trial ++;
            }
            
            if (trial == 3){
                System.out.println("Error! Silahkan ambil kartu dan coba lagi..");
                System.exit(0);
            }
            
            System.out.println("Selamat Datang Di ATM-KU..");
            
            do{
                System.out.println("\tMenu :");
                System.out.println("\t1. Cek Saldo");
                System.out.println("\t2. Tarik");
                System.out.println("\t3. Setor");
                System.out.println("\t4. Ganti Pin");
                System.out.println("\t5. Keluar");

                System.out.print("Pilih menu: ");
                menu = input_int.nextInt();
                
                switch (menu){
                    case 1:
                        System.out.println("Saldo anda sekarang: Rp. " + atm.getBalance());
                        break;
                    case 2:
                        System.out.print("Masukkan nominal saldo: ");
                        nominal = input_int.nextInt();
                        
                        System.out.println("Konfirmasi: Anda akan melakukan penarikan dengan nominal berikut? y/n Rp. " + nominal);
                        confirm = input_str.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")){
                            System.out.println("Saldo awal anda adalah: Rp. " + atm.getBalance());
                        }else break;
                        
                        if (nominal < atm.getBalance()){
                            atm.setWithdrawBalance(nominal);
                            System.out.println("Penarikan berhasil!");
                            System.out.println("Saldo anda sekarang: Rp. " + atm.getBalance());
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
                            atm.setDepositBalance(nominal);
                            System.out.println("Saldo awal anda adalah: Rp. " + atm.getBalance());
                        }else break;
                        break;
                    case 4:
                        System.out.print("Masukkan pin anda: ");
                        id = input_int.nextInt();
                        
                        while(id != atm.getPin()){
                            System.out.print("Pin salah! Silahkan masukkan lagi: ");
                            id = input_int.nextInt();
                        }
                        
                        System.out.print("Silahkan masukkan pin baru: ");
                        newPin = input_int.nextInt();
                        
                        System.out.print("Konfirmasi pin baru: ");
                        newPinConfirm = input_int.nextInt();
                        
                        if(newPin == newPinConfirm){
                            atm.setPin(newPin);
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
                System.out.println("Lanjutkan transaksi? (y/n)");
                confirm = input_str.nextLine();
            } while(confirm.equalsIgnoreCase("y"));

            input_int.close();
            input_str.close();
        }
    }
}
