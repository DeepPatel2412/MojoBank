package Part2;
import java.time.LocalDate;
import java.util.Random;

public class AccountDetailsP2
{
    static CustomArraylistP2<Integer> accNum = new CustomArraylistP2<>();
    static CustomArraylistP2<String> accHName = new CustomArraylistP2<>();
    static CustomArraylistP2<String> accHAddress = new CustomArraylistP2<>();
    static CustomArraylistP2<LocalDate> accODate = new CustomArraylistP2<>();
    static CustomArraylistP2<Double> accCBalance = new CustomArraylistP2<>();
    static CustomArraylistP2<InteractiveTransactionP2> InteractiveTransactionP2 = new CustomArraylistP2<>();

    AccountDetailsP2(String Name, String Address)
    {
        Random random = new Random();
        int NewAccNumber = random.nextInt(10000000,99999999);
        while(accNum.contains(NewAccNumber))
        {
            NewAccNumber = random.nextInt(10000000,99999999);
        }
        accNum.add(NewAccNumber);
        accHName.add(Name);
        accHAddress.add(Address);
        accODate.add(LocalDate.now());
        accCBalance.add(0.0);
        InteractiveTransactionP2.add(new InteractiveTransactionP2());
    }
    public static void printGenDetails(int index)
    {
        System.out.println();
        System.out.println("** Account General Details **");
        System.out.printf("%-23s: %d\n","Account Number",accNum.get(index));
        System.out.printf("%-23s: %s\n","Account Holder Name",accHName.get(index));
        System.out.printf("%-23s: %s\n","Account Holder Address",accHAddress.get(index));
        System.out.printf("%-23s: "+accODate.get(index)+"\n","Opening Date");
        System.out.printf("%-23s: %.2f\n","Current Balance",accCBalance.get(index));
    }

    public static void deposit(int index, double amount)     // Method to deposit Money to user's Account.
    {
        double balance = accCBalance.get(index);
        double updateBalance = balance+amount;
        accCBalance.set(index, updateBalance);
        InteractiveTransactionP2.get(index).addTran("Deposit",amount);
    }

    public static void withdraw(int index, double amount)     // Method to withdraw Money from user's Account.
    {
        double balance = accCBalance.get(index);
        double updateBalance = balance-amount;
        accCBalance.set(index, updateBalance);
        InteractiveTransactionP2.get(index).addTran("Withdraw",amount);
    }
    public static void printTranHistory(int index)      // Method to Print Account Transaction History at given index
    {

        System.out.println();
        InteractiveTransactionP2.get(index).SearchAndPrint();
    }

    public static void removeAcc(int index)      // Method to remove/delete account from ArrayLists at given index.
    {
        // Removes account details from ArrayList.
        AccountDetailsP2.accNum.remove(index);
        AccountDetailsP2.accHName.remove(index);
        AccountDetailsP2.accHAddress.remove(index);
        AccountDetailsP2.accODate.remove(index);
        AccountDetailsP2.accCBalance.remove(index);
        InteractiveTransactionP2.remove(index);
    }

}


