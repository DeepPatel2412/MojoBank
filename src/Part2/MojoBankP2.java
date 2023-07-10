package Part2;
import java.util.Scanner;

public class MojoBankP2
{
    public static void main(String[] args)
    {
        StartPage();
    }
    public static void StartPage()          // Method to Run the Start Page.
    {
        System.out.println();
        System.out.println("   --> Welcome to MojoBank! <--   ");
        System.out.println("1) Register        2) User Login");
        System.out.println("3) Admin Login     4) Exit");
        System.out.println();
        System.out.print("Enter A Number : ");

        Scanner InputReader = new Scanner(System.in);
        boolean ValidChoice = false;
        int AccIndex = 0;

        do
        {
            if (InputReader.hasNextInt())
            {
                int StartInputChoice = InputReader.nextInt();
                if (StartInputChoice == 1)
                {
                    RegisterAccount();
                    AccIndex = AccountDetailsP2.AccountName.size()-1;
                    ValidChoice = true;
                }
                else if (StartInputChoice == 2)
                {
                    AccIndex = UserLogin();
                    ValidChoice = true;
                }
                else if (StartInputChoice == 3)
                {
                    AdminLogin();
                    AdminFunctions();
                    ValidChoice = true;
                }
                else if (StartInputChoice == 4)
                {
                    Quit();
                }
                else if (StartInputChoice == 0)
                {
                    StartPage();
                }
                else
                {
                    System.out.print("Enter 1-4 | 0 to Start Again : ");
                }
            }
            else
            {
                System.out.print("Enter 1-4 | 0 to Start Again : ");
                InputReader.next();
            }
        } while (!ValidChoice);

        BankingFunctions(AccIndex);
    }

    public static void RegisterAccount()
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.println();
        System.out.println("--> Enter Following Details <--");
        System.out.print("First Name : ");
        String FirstName = InputReader.nextLine();
        System.out.print("Surname    : ");
        String Surname = InputReader.nextLine();
        String name = FirstName +" "+ Surname;
        System.out.print("Address    : ");
        String address = InputReader.nextLine();
        System.out.print("Age        : ");

        boolean ValidateAge =false;
        while (!ValidateAge)
        {
            if(InputReader.hasNextInt())
            {
                int age = InputReader.nextInt();
                if (age >= 18)
                {
                    AccountDetailsP2 NewUser = new AccountDetailsP2(name, address);
                    System.out.println();
                    System.out.println("--> Account Created <--");
                    int AccIndex = AccountDetailsP2.Balance.size() - 1;
                    AccountDetailsP2.ViewAccountDetails(AccIndex);
                }
                else
                {
                    System.out.println("--> Age is not Valid ( Must be >=18 ) <--");
                    System.out.println("   ** Returning to Start Menu **  ");
                    StartPage();
                }
                ValidateAge=true;
            }
            else
            {
                System.out.print("Enter Integer Only : ");
                InputReader.next();
            }
        }
    }

    public static int UserLogin()
    {
        int AccIndex=0;
        Scanner InputReader = new Scanner(System.in);
        System.out.print("Enter Account Number : ");
        while(!InputReader.hasNextInt())
        {
            System.out.print("Enter Integers Only | Return Start [0] : ");
            InputReader.next();
            InputReader.hasNextInt();
        }
        int ValidateAccountNumber = InputReader.nextInt();
        if(AccountDetailsP2.AccountNumber.contains(ValidateAccountNumber))
        {
            AccIndex = AccountDetailsP2.AccountNumber.indexOf(ValidateAccountNumber);
            System.out.println("--> Login Successful <--");
        }
        else
        {
            System.out.println("--> Login Failed , Account Not Found <--");
            System.out.println(" Returning to Start Menu");
            StartPage();
        }
        return AccIndex;
    }

    public static void AdminLogin()
    {
        String[] AdminUsername = {"HAMZA"};
        String[] AdminPassword = {"HAMZA"};

        Scanner InputReader = new Scanner(System.in);

        System.out.println("--> Enter Login Details <--");
        System.out.print("Username : ");
        String ValidateUsername = InputReader.nextLine();
        System.out.print("Password : ");
        String ValidatePassword = InputReader.nextLine();

        boolean login = false;
        int AttemptsLeft = 3;

        while (!login)
        {
            for (int i = 0; i < AdminUsername.length; i++)
            {
                if (AdminUsername[i].equals(ValidateUsername))
                {
                    if (AdminPassword[i].equals(ValidatePassword))
                    {
                        System.out.println("--> Login Success <--");
                        login = true;
                    }
                }
            }
            if (!login)
            {
                if(AttemptsLeft <= 0)
                {
                    StartPage();
                }
                System.out.println();
                System.out.println("--> Login Failed,Attempts Left :"+ AttemptsLeft);
                System.out.print("Username : ");
                ValidateUsername = InputReader.nextLine();
                System.out.print("Password : ");
                ValidatePassword = InputReader.nextLine();
                AttemptsLeft --;
            }
        }   // End while loop
    }   //End AdminLogin() method

    public static void Quit()
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.print("Confirm Your Exit (Enter Y | N ) : ");
        String ConfirmExit = InputReader.nextLine().toUpperCase();
        while (!(ConfirmExit.equals("Y") || ConfirmExit.equals("N")))
        {
            System.out.print("Enter Only Y | N : ");
            ConfirmExit = InputReader.nextLine().toUpperCase();
        }
        if (ConfirmExit.equals("Y"))
        {
            System.out.println("--> Thank you for using choosing MojoBank <--");
            System.exit(0);
        }
        else
        {
            System.out.println("--> Returning to Start Menu ");
            StartPage();
        }
    }

    public static void BankingFunctions(int AccIndex)
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.println();
        System.out.println("      --> Banking Functions <--     ");
        System.out.println("1) Deposit               2) Withdraw");
        System.out.println("3) Transaction Details   4) Delete Account");
        System.out.println("5) Log Out");
        System.out.println();
        System.out.print("Enter A Number : ");

        boolean ValidChoice = false;
        while (!ValidChoice)
        {
            if(InputReader.hasNextInt())
            {
                int BankingInputChoice = InputReader.nextInt();

                if (BankingInputChoice == 1)
                {
                    Deposit(AccIndex);
                    ValidChoice = true;
                }
                else if (BankingInputChoice == 2)
                {
                    Withdraw(AccIndex);
                    ValidChoice = true;
                }
                else if (BankingInputChoice == 3)
                {
                    AccountDetailsP2.ViewRecentTransaction(AccIndex);
                    ValidChoice = true;
                }
                else if (BankingInputChoice == 4)
                {
                    DeleteAccount(AccIndex);
                    ValidChoice = true;
                }
                else if (BankingInputChoice == 5)
                {
                    System.out.println("--> Logged Out <--");
                    StartPage();
                }
                else if (BankingInputChoice == 0)
                {
                    BankingFunctions(AccIndex);
                }
                else
                {
                    System.out.print("Enter 1-5 | 0 for Banking Menu : ");
                }
            }
            else
            {
                System.out.print("Enter 1-5 | 0 for Banking Menu : ");
                InputReader.next();
            }
        }
        BankingFunctions(AccIndex);
    }

    public static void AdminFunctions()
    {
        System.out.println();
        System.out.println("--> Admin Options <--");
        System.out.println("1) View All Accounts");
        System.out.println("2) Delete Account");
        System.out.println("3) Log Out");
        System.out.print("Enter A Number : ");

        Scanner InputReader = new Scanner(System.in);
        boolean ValidChoice= false;
        while (!ValidChoice)
        {
            if (InputReader.hasNextInt())
            {
                int AdminInputChoice = InputReader.nextInt();
                if (AdminInputChoice == 1)
                {
                    ViewAllAccounts();
                    AdminFunctions();
                    ValidChoice = true;
                }
                if (AdminInputChoice == 2)
                {
                    DeleteUserAccount();
                    AdminFunctions();
                    ValidChoice = true;
                }
                if (AdminInputChoice == 3)
                {
                    StartPage();
                    ValidChoice = true;
                }
                if (AdminInputChoice == 0)
                {
                    AdminFunctions();
                }
            }
            else
            {
                System.out.print("Enter 1-3 | Admin Menu [0] : ");
            }
        }
    }

    public static void Deposit(int AccIndex)
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.print("Deposit Amount : ");
        boolean ValidateAmount = InputReader.hasNextDouble();
        while(!ValidateAmount)
        {
            System.out.print("Enter Integer Only | Banking Menu [0] : ");
            InputReader.nextLine();
            ValidateAmount = InputReader.hasNextDouble();
        }
        double Amount = InputReader.nextDouble();
        while (Amount<0)
        {
            System.out.print("Enter Integer Only | Banking Menu [0] : ");
            InputReader.nextLine();
            if(InputReader.hasNextDouble())
            {
                Amount = InputReader.nextDouble();
            }
        }   // End while loop

        if (Amount == 0)
        {
            BankingFunctions(AccIndex);
        }
        else
        {
            AccountDetailsP2.Deposit(AccIndex, Amount);
            System.out.println();
            System.out.printf("Deposited       : %.2f \n", Amount);
            System.out.printf("Current Balance : %.2f \n" , AccountDetailsP2.Balance.get(AccIndex));
        }
    }

    public static void Withdraw(int AccIndex)
    {
        Scanner InputReader = new Scanner(System.in);
        double CurrentBalance = AccountDetailsP2.Balance.get(AccIndex);
        if(CurrentBalance == 0)
        {
            System.out.println("Balance : 0 , Can't Withdraw Money");
            BankingFunctions(AccIndex);
        }

        System.out.print("Withdrawal Amount : ");
        boolean ValidateAmount = InputReader.hasNextDouble();
        while(!ValidateAmount)
        {
            System.out.print("Enter Valid Amount (> 0) | Banking Menu [0] : ");
            InputReader.nextLine();
            ValidateAmount = InputReader.hasNextDouble();
        }   // End while loop
        double DepositAmount = InputReader.nextDouble();
        while (DepositAmount > CurrentBalance || DepositAmount<0)
        {
            System.out.print("Enter Valid Amount (> 0 & <= CurrentBalance) | Banking Menu [0] : : ");
            InputReader.nextLine();
            if(InputReader.hasNextDouble())
            {
                DepositAmount = InputReader.nextDouble();
            }
        }
        if (DepositAmount == 0)
        {
            BankingFunctions(AccIndex);
        }
        AccountDetailsP2.Withdraw(AccIndex,DepositAmount);
        System.out.println();
        System.out.printf("Withdrawn       : %.2f \n",DepositAmount);
        System.out.printf("Current Balance : %.2f \n",AccountDetailsP2.Balance.get(AccIndex));
    }   // End Deposit() method

    public static void ViewAllAccounts()
    {
        int numOfAcc = AccountDetailsP2.AccountName.size();
        if (numOfAcc!=0)
        {
            for (int i = 0; i < numOfAcc; i++)
            {
                AccountDetailsP2.ViewAccountDetails(i);
                System.out.println();
            }
        }
        else
        {
            System.out.println("No Accounts Exist in the system.");
        }
    }   // End ViewAllAccounts() method.

    public static void DeleteAccount(int AccIndex)
    {
        Scanner InputReader = new Scanner (System.in);
        System.out.print("Confirm Delete (Y | N) : ");
        String confirmDelete = InputReader.nextLine().toUpperCase();
        while (!(confirmDelete.equals("Y") || confirmDelete.equals("N")))
        {
            System.out.print("Enter only Y | N : ");
            confirmDelete = InputReader.nextLine().toUpperCase();
        }
        if (confirmDelete.equals("Y"))
        {
            AccountDetailsP2.DeleteAccount(AccIndex);
            System.out.println("--> Account Deleted <--");
            StartPage();
        }
        else
        {
            BankingFunctions(AccIndex);
        }
    }

    public static void DeleteUserAccount()
    {
        int numOfAcc = AccountDetailsP2.AccountName.size();
        if (numOfAcc!=0)
        {
            int AccIndex;
            Scanner InputReader = new Scanner(System.in);
            System.out.print("Enter Account Number(to be deleted) : ");
            while (!InputReader.hasNextInt())
            {
                System.out.println("Enter Integer Only : ");
                InputReader.next();
                InputReader.hasNextInt();
            }
            
            int ValidateAccountNumber = InputReader.nextInt();
            InputReader.nextLine();
            if (AccountDetailsP2.AccountNumber.contains(ValidateAccountNumber))
            {
                AccIndex = AccountDetailsP2.AccountNumber.indexOf(ValidateAccountNumber);
                AccountDetailsP2.ViewAccountDetails(AccIndex);

                System.out.print("Confirm Account Details [Y | N] : ");
                String confirmDelete = InputReader.nextLine().toUpperCase();

                while (!(confirmDelete.equals("Y") || confirmDelete.equals("N")))
                {
                    System.out.print("Enter only Y or N : ");
                    confirmDelete = InputReader.nextLine().toUpperCase();
                }

                if (confirmDelete.equals("Y"))
                {
                    AccountDetailsP2.DeleteAccount(AccIndex);
                    System.out.println("-- Account Deleted --");
                    AdminFunctions();
                }
                else  // Else execute following code.
                {
                    System.out.println("Account Delete was Stopped");
                    AdminFunctions();
                }
            }
            else
            {
                System.out.println("--> Account Doesn't Exists.");
                AdminFunctions();
            }
        }
        else
        {
            System.out.println("No Account Exist in the system");
        }
    }

}

