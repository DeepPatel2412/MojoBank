package Part1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class AccountDetails
{
    static ArrayList<String> AccountName = new ArrayList<>();
    static ArrayList<String> HolderAddress = new ArrayList<>();
    static ArrayList<Double> Balance = new ArrayList<>();
    static ArrayList<Integer> AccountNumber = new ArrayList<>();
    static ArrayList<LocalDate> OpeningDate = new ArrayList<>();
    static ArrayList<InteractiveTransaction> TransactionDetails = new ArrayList<>();

    AccountDetails(String Name, String Address)
    {
        AccountName.add(Name);
        HolderAddress.add(Address);
        Balance.add(0.0);
        OpeningDate.add(LocalDate.now());
        TransactionDetails.add(new InteractiveTransaction());

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

    public static void ViewRecentTransaction(int AccIndex)      // Method to Print Account Transaction History at given AccIndex.
    {
        System.out.println();
        TransactionDetails.get(AccIndex).SortNPrint();
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

    public static void DeleteAccount(int AccIndex)
    {
        AccountNumber.remove(AccIndex);
        AccountName.remove(AccIndex);
        HolderAddress.remove(AccIndex);
        OpeningDate.remove(AccIndex);
        Balance.remove(AccIndex);
        TransactionDetails.remove(AccIndex);
    }

}

