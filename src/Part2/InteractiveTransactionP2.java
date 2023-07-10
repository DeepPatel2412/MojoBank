package Part2;
import java.time.LocalDate;     // Import LocalDate.
public class InteractiveTransactionP2        // Start InteractiveTransactionP2 Class.
{
    String[] TransactionType = new String[6];
    double[]TransactionAmount = new double[6];
    LocalDate[] TransactionDate = new LocalDate[6];
    int index = 0;
    int TransactionCounter = 0;
    public void UpdateTransaction(String Type,double Amount)     // Method to add Transactions.
    {
        TransactionType[index] = Type;
        TransactionAmount[index] = Amount;
        TransactionDate[index] = LocalDate.now();
        index++;
        if (index>5)
        {
            index=0;
        }
    }

    public void SearchAndPrint()      // Method to Search and Print Transaction.
    {
        double[] CloneArray = TransactionAmount.clone();
        boolean PrintUI = false;

        for (int i =0;i<6;i++)
        {
            double MaxAmount = CloneArray[0];
            int SortedIndex = 0;

            for (int j= 0; j< CloneArray.length ; j++)
            {
                if ( MaxAmount < CloneArray[j] )
                {
                    MaxAmount= CloneArray[j];
                    SortedIndex = j;
                }
            }
            if (CloneArray[SortedIndex] != 0)      // if TransactionAmount[SortedIndex] != 0 it will execute block
            {
                if (!PrintUI)
                {
                    System.out.println("--> Transaction Details <-- ");
                    System.out.println();
                    System.out.printf("%-15s%-15s%-13s\n","Type","Amount","Date");
                    System.out.println("----------------------------------------");
                    PrintUI = true;
                }
                System.out.printf("%-15s%-15.2f"+TransactionDate[SortedIndex]+"\n",
                        TransactionType[SortedIndex],TransactionAmount[SortedIndex]);
                TransactionCounter++;
            }
            CloneArray[SortedIndex] = 0;
        }
        if(TransactionCounter == 0)
        {
            System.out.println("-- No Transactions Available --");
        }

    }

}

