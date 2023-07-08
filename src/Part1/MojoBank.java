package Part1;
import java.util.Scanner;                // Importing Scanner

public class MojoBank                      // Creating a class that will run all our program and its user(console) interface.
{
    public static void main(String[] args)    // Main() method where program will start from.(Start Point)
    {
        start();     // Call start() method.
    }
    public static void start()          // Method to Run the Start Page.
    {
        // Displaying 4 Different Options to User.
        System.out.println();
        System.out.println("   --> Welcome to MojoBank! <--   ");
        System.out.println("1) Register        2) User Login");
        System.out.println("3) Admin Login     4) Exit");
        System.out.println();
        System.out.print("--> Enter A Number : ");

        Scanner InputReader = new Scanner(System.in);
        boolean ValidChoice = false;
        int AccIndex = 0;

        // Using do while loop to check and validate user's InputReader.
        do
        {
            if (InputReader.hasNextInt())
            {
                int startChoice = InputReader.nextInt();
                if (startChoice == 1)
                {
                    RegisterAccount();
                    AccIndex = AccountDetails.AccountName.size()-1;
                    ValidChoice = true;
                }
                else if (startChoice == 2)
                {
                    AccIndex = UserLogin();
                    ValidChoice = true;
                }
                else if (startChoice == 3)
                {
                    AdminLogin();
                    AdminFunctions();
                    ValidChoice = true;
                }
                else if (startChoice == 4)
                {
                    exit();
                }
                else if (startChoice == 0)
                {
                    start();
                }
                else
                {
                    System.out.print("Enter 1-4 | 0 to Start Again : ");
                }
            }   // End if statement
            else
            {
                System.out.print("Enter 1-4 | 0 to Start Again : ");
                InputReader.next();
            }
        } while (!ValidChoice);

        BankingFunctions(AccIndex);
    }   // End start() method.

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
                    AccountDetails NewUser = new AccountDetails(name, address);
                    System.out.println();
                    System.out.println("--> Account Created <--");
                    int AccIndex = AccountDetails.Balance.size() - 1;
                    AccountDetails.ViewAccountDetails(AccIndex);
                }
                else
                {
                    System.out.println("--> Age is not Valid ( Must be >=18 ) <--");
                    System.out.println("   ** Returning to Start Menu **  ");
                    start();
                }
                ValidateAge=true;
            }
            else
            {
                System.out.print("Enter Integer Only : ");
                InputReader.next();
            }
        }
    }   // End RegisterAccount() method.

    public static int UserLogin()
    {
        int AccIndex=0;
        Scanner InputReader = new Scanner(System.in);
        System.out.print("Enter Account Number : ");
        while(!InputReader.hasNextInt())
        {
            System.out.print("Enter Integers Only || Return Start [0] : ");
            InputReader.next();
            InputReader.hasNextInt();
        }
        int ValidateAccountNumber = InputReader.nextInt();
        if(AccountDetails.AccountNumber.contains(ValidateAccountNumber))
        {
            AccIndex = AccountDetails.AccountNumber.indexOf(ValidateAccountNumber);
            System.out.println("--> Login Successful <--");
        }
        else
        {
            System.out.println("--> Login Failed , Account Not Found <--");
            System.out.println(" Returning to Start Menu");
            start();
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
            }   // End for loop
            if (!login)
            {
                if(AttemptsLeft <= 0)
                {
                    start();
                }
                System.out.println();
                System.out.println("--> Login Failed,Try again");
                System.out.print("Username : ");
                ValidateUsername = InputReader.nextLine();
                System.out.print("Password : ");
                ValidatePassword = InputReader.nextLine();
                AttemptsLeft --;
            }
        }   // End while loop
    }   //End AdminLogin() method

    public static void exit()
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.print("Confirm Your Exit (Enter Y | N ) : ");
        String confirmExit = InputReader.nextLine().toUpperCase();
        while (!(confirmExit.equals("Y") || confirmExit.equals("N")))
        {
            System.out.print("Enter only Y | N : ");
            confirmExit = InputReader.nextLine().toUpperCase();
        }
        if (confirmExit.equals("Y"))
        {
            System.out.println("--> Thank you for using choosing MojoBank <--");
            System.exit(0);
        }
        else
        {
            System.out.println("--> Returning to Start Menu ");
            start();
        }
    }

    public static void BankingFunctions(int AccIndex)
    {
        Scanner InputReader = new Scanner(System.in);
        System.out.println();
        System.out.println("      --> Banking Functions <--     ");
        System.out.println("1) Deposit               2) Withdraw");
        System.out.println("2) Transaction Details   4) Delete Account");
        System.out.println("5) Log Out");
        System.out.println();
        System.out.print("--> Enter A Number : ");

        boolean ValidChoice = false;
        while (!ValidChoice)
        {
            if(InputReader.hasNextInt())
            {
                int bankingChoice = InputReader.nextInt();

                if (bankingChoice == 1)
                {
                    Deposit(AccIndex);
                    ValidChoice = true;
                }
                else if (bankingChoice == 2)
                {
                    Withdraw(AccIndex);
                    ValidChoice = true;
                }
                else if (bankingChoice == 3)
                {
                    AccountDetails.ViewRecentTransaction(AccIndex);
                    ValidChoice = true;
                }
                else if (bankingChoice == 4)
                {
                    DeleteAccount(AccIndex);
                    ValidChoice = true;
                }
                else if (bankingChoice == 5)
                {
                    System.out.println("** Logged Out **");
                    start();
                }
                else if (bankingChoice == 0)
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
        System.out.println("1) View Accounts Details ");
        System.out.println("2) Delete Account ");
        System.out.println("3) Log Out");
        System.out.print("--> Enter A Number : ");

        Scanner InputReader = new Scanner(System.in);
        boolean ValidChoice= false;
        while (!ValidChoice)
        {
            if (InputReader.hasNextInt())
            {
                int adminChoice = InputReader.nextInt();
                if (adminChoice == 1)
                {
                    ViewAllAccounts();
                    AdminFunctions();
                    ValidChoice = true;
                }
                if (adminChoice == 2)
                {
                    DeleteUserAccount();
                    AdminFunctions();
                    ValidChoice = true;
                }
                if (adminChoice == 3)
                {
                    start();
                    ValidChoice = true;
                }
                if (adminChoice == 0)
                {
                    AdminFunctions();
                }
            }
            else
            {
                System.out.print("Enter 1-3 | 0 to Admin Menu : ");
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
            System.out.print("Enter Integer Only | 0 for Banking Menu : ");
            InputReader.nextLine();
            ValidateAmount = InputReader.hasNextDouble();
        }   // End while loop
        double Amount = InputReader.nextDouble();
        while (Amount<0)
        {
            System.out.print("Enter Integer Only | 0 for Banking Menu : ");
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
            AccountDetails.Deposit(AccIndex, Amount);
            System.out.println();
            System.out.printf("Deposited       : %.2f \n", Amount);
            System.out.printf("Current Balance : %.2f \n" , AccountDetails.Balance.get(AccIndex));
        }
    }

    public static void Withdraw(int AccIndex)
    {
        Scanner InputReader = new Scanner(System.in);
        double CurrentBalance = AccountDetails.Balance.get(AccIndex);
        if(CurrentBalance == 0)
        {
            System.out.println("Balance : 0 , Can't Withdraw Money");
            BankingFunctions(AccIndex);
        }

        System.out.print("Withdrawal Amount : ");
        boolean ValidateAmount = InputReader.hasNextDouble();
        while(!ValidateAmount)
        {
            System.out.print("Enter Valid Amount (> 0) | 0 for Banking Menu : ");
            InputReader.nextLine();
            ValidateAmount = InputReader.hasNextDouble();
        }   // End while loop
        double DepositAmount = InputReader.nextDouble();
        while (DepositAmount > CurrentBalance || DepositAmount<0)
        {
            System.out.print("Enter Valid Amount (> 0 & <= CurrentBalance) | 0 for Banking Menu : ");
            InputReader.nextLine();
            if(InputReader.hasNextDouble())
            {
                DepositAmount = InputReader.nextDouble();
            }
        }   // End while loop
        if (DepositAmount == 0)
        {
            BankingFunctions(AccIndex);
        }
        AccountDetails.Withdraw(AccIndex,DepositAmount);
        System.out.println();
        System.out.printf("Withdrawn       : %.2f \n",DepositAmount);
        System.out.printf("Current Balance : %.2f \n",AccountDetails.Balance.get(AccIndex));
    }   // End Deposit() method

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
            AccountDetails.DeleteAccount(AccIndex);
            System.out.println("--> Account Deleted <--");
            start();
        }
        else
        {
            BankingFunctions(AccIndex);
        }
    }

    public static void ViewAllAccounts()
    {
        int numOfAcc = AccountDetails.AccountName.size();
        if (numOfAcc!=0)
        {
            for (int i = 0; i < numOfAcc; i++)
            {
                AccountDetails.ViewAccountDetails(i);
                System.out.println();
            }
        }
        else
        {
            System.out.println("No Accounts Exist in the system.");
        }
    }   // End ViewAllAccounts() method.

    public static void DeleteUserAccount()
    {
        int numOfAcc = AccountDetails.AccountName.size();
        if (numOfAcc!=0)
        {
            int AccIndex;
            Scanner InputReader = new Scanner(System.in);
            System.out.print("Enter Account Number(to be deleted) : ");
            while (!InputReader.hasNextInt())
            {
                System.out.println("Wrong Input! Try again : ");
                InputReader.next();
                InputReader.hasNextInt();
            }
            
            int ValidateAccountNumber = InputReader.nextInt();
            InputReader.nextLine();
            if (AccountDetails.AccountNumber.contains(ValidateAccountNumber))
            {
                AccIndex = AccountDetails.AccountNumber.indexOf(ValidateAccountNumber);
                AccountDetails.ViewAccountDetails(AccIndex);

                System.out.print("Confirm Account Details [Y | N] : ");
                String confirmDelete = InputReader.nextLine().toUpperCase();

                while (!(confirmDelete.equals("Y") || confirmDelete.equals("N")))
                {
                    System.out.print("Enter only Y or N : ");
                    confirmDelete = InputReader.nextLine().toUpperCase();
                }   //End while loop

                if (confirmDelete.equals("Y"))
                {
                    AccountDetails.DeleteAccount(AccIndex);
                    System.out.println("-- Account Deleted --");
                    AdminFunctions();
                }
                else  // Else execute following code.
                {
                    System.out.println("Account Delete was Stopped");
                    AdminFunctions();
                }
            }
            else  // Else execute following code.
            {
                System.out.println("--> Account Doesn't Exists.");
                AdminFunctions();
            }
        }     // End if statement
        else  // Else execute following code.
        {
            System.out.println("No Account Exist in the system");
        }
    }

}

