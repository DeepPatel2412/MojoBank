package Part2;
import java.time.LocalDate;      // Import LocalDate
public class InteractiveTransactionP2        // Start InteractiveTransactionP2 Class.
{
    String[] tranType = new String[6];        // String[] tranType - Array to Store transaction type.(Initialization)
    double[]tranAmount = new double[6];       // double[] tranAmount - Array to store transaction amount.(Initialization)
    LocalDate[] tranDate = new LocalDate[6];  // LocalDate[] tranDate - Array to store transaction date.(Initialization)
    int index = 0;                // int index - Store index of user's account.(Initialization)
    int countTransaction =0;      // int countTransaction - Transaction Counter.(Initialization)
    public void UpdateTransaction(String type,double amount)     // Method to add Transactions.
    {
        tranType[index] = type;                  // Add transaction type to respective array.(Assignment)
        tranAmount[index] = amount;              // Add transaction amount to respective array.(Assignment)
        tranDate[index] = LocalDate.now();       // Add transaction date to respective array.(Assignment)
        index++;                                 // Increment by 1.(Operation)
        if (index>5)                             // if index>5 execute the given code.
        {
            index=0;                             // Make index 0.(Reset index)
        }
    }   //End UpdateTransaction() method.

    //Creating sorting/searching algorithm without using java collection.
    public void SearchAndPrint()            // Method to Search and Print Transaction.
    {
        double[] copiedArray = tranAmount.clone();                         // Clone tranAmount Array.
        boolean printLayout = false;

        for (int i =0;i<6;i++)            // for loop to iterate it 6 times.
        {
            double max = copiedArray[0];                     // Set max initial value to copiedArray[0].
            int indexSorted = 0;                             // int indexSorted - Store index of sorted elements.

            for (int j = 0; j < copiedArray.length ; j++)    // for loop to iterate it 6 times.
            {
                if ( max < copiedArray[j] )                  // if max<copiedArray[j] execute given code.
                {
                    max= copiedArray[j];                     // Set max to copiedArray[j].
                    indexSorted = j;                         // Set indexSorted to j.
                }
            }
            if (copiedArray[indexSorted] != 0)      // if tranAmount[indexSorted] != 0 it will execute block
            {
                // Display Transaction History.
                if (!printLayout)
                {
                    System.out.println("** Transaction History ** ");
                    System.out.println();
                    System.out.printf("%-15s%-15s%-13s\n","Type","Amount","Date");
                    System.out.println("----------------------------------------");
                    printLayout = true;
                }
                System.out.printf("%-15s%-15.2f"+tranDate[indexSorted]+"\n",
                        tranType[indexSorted],tranAmount[indexSorted]/*,tranDate[indexSorted]*/);
                countTransaction++;     // Increment by 1.
            }
            copiedArray[indexSorted] = 0;     // Set copiedArray[indexSorted] to 0 So it won't be repeated.
        }    // End for loop
        if(countTransaction == 0)             // if no transaction execute given code.
        {
            System.out.println("** No recent Transactions :) **");      // Display no transaction MSG.
        }

    }   // End SearchAndPrint() method.

}   // End TranDetailsP2 class.

