package Part1;
import java.time.LocalDate;     // Import LocalDate.
import java.util.Arrays;        // Import Arrays.

public class InteractiveTransaction
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

    public void SortNPrint()      // Method to Sort and Print Transaction.
    {
        int SortedIndex;
        double[] CloneArray = TransactionAmount.clone();
        boolean PrintUI = false;

        Arrays.sort(CloneArray);

        for (int i=CloneArray.length-1;i>=0;i--)
        {
            double TranAmount = CloneArray[i];
            for (int j= 0 ; j<TransactionAmount.length ; j++)
            {
                if (TransactionAmount[j] == TranAmount)
                {
                    SortedIndex = j;
                    if (TransactionAmount[SortedIndex] != 0)
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
                        TranAmount =0;
                    }
                }
            }
            CloneArray[i] = 0;
        }
        if(TransactionCounter == 0)
        {
            System.out.println("-- No Transactions Available --");
        }

    }

}

