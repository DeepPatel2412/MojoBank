package Part2;
import java.time.LocalDate;
import java.util.Random;

public class AccountDetailsP2
{
    static CustomArraylistP2<String> AccountName = new CustomArraylistP2<>();
    static CustomArraylistP2<String> HolderAddress = new CustomArraylistP2<>();
    static CustomArraylistP2<Double> Balance = new CustomArraylistP2<>();
    static CustomArraylistP2<Integer> AccountNumber = new CustomArraylistP2<>();
    static CustomArraylistP2<LocalDate> OpeningDate = new CustomArraylistP2<>();
    static CustomArraylistP2<InteractiveTransactionP2> TransactionDetails = new CustomArraylistP2<>();

    AccountDetailsP2(String Name, String Address)
    {
        AccountName.add(Name);
        HolderAddress.add(Address);
        Balance.add(0.0);
        OpeningDate.add(LocalDate.now());
        TransactionDetails.add(new InteractiveTransactionP2());

        Random random = new Random();
        int NewAccNumber = random.nextInt(10000000,99999999);
        while(AccountNumber.contains(NewAccNumber))
        {
            NewAccNumber = random.nextInt(10000000,99999999);
        }
        AccountNumber.add(NewAccNumber);
    }

    public static void Deposit(int AccIndex, double Amount)     // Method to Deposit Money to user's Account.
    {
        Balance.set(AccIndex,Balance.get(AccIndex) + Amount);
        TransactionDetails.get(AccIndex).UpdateTransaction("Deposit",Amount);
    }

    public static void Withdraw(int AccIndex, double Amount)     // Method to Withdraw Money from user's Account.
    {
        Balance.set(AccIndex, Balance.get(AccIndex) - Amount);
        TransactionDetails.get(AccIndex).UpdateTransaction("Withdraw",Amount);
    }

    public static void ViewAccountDetails(int AccIndex)     // Method to Print Account General Details at given AccIndex
    {
        System.out.println();
        System.out.println("** Account General Details **");
        System.out.printf("%-23s: %d\n","Account Number",AccountNumber.get(AccIndex));
        System.out.printf("%-23s: %s\n","Account Holder Name",AccountName.get(AccIndex));
        System.out.printf("%-23s: %s\n","Account Holder Address",HolderAddress.get(AccIndex));
        System.out.printf("%-23s: "+OpeningDate.get(AccIndex)+"\n","Opening Date");
        System.out.printf("%-23s: %.2f\n","Current Balance",Balance.get(AccIndex));
    }

    public static void ViewRecentTransaction(int AccIndex)      // Method to Print Account Transaction History at given AccIndex.
    {
        System.out.println();
        TransactionDetails.get(AccIndex).SearchAndPrint();
    }

    public static void DeleteAccount(int AccIndex)     // Method to remove/delete account from ArrayLists at given AccIndex.
    {
        AccountNumber.remove(AccIndex);
        AccountName.remove(AccIndex);
        HolderAddress.remove(AccIndex);
        OpeningDate.remove(AccIndex);
        Balance.remove(AccIndex);
        TransactionDetails.remove(AccIndex);
    }

}


