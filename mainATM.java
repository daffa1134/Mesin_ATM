public class mainATM{
    public static void main(String[] args) {

        Customer daffa = new Customer(12);

        System.out.println(daffa.getBalance());
        daffa.setDepositBalance(2000);
        System.out.println(daffa.getBalance());
    }
}
