package Part2;
import java.util.Scanner;               // Importing Scanner

public class MojoBankP2                      // Creating a class that will run all our program and its user(console) interface.
{
    public static void main(String[] args)
    {
        start();
    }
    public static void start()          // Method that will become program's start/welcome page (suppose it as 1st page of program).
    {
        // Displaying 4 Different Options to User.
        System.out.println();
        System.out.println("** Welcome **");
        System.out.println("1) Create an account");
        System.out.println("2) Login to existing account");
        System.out.println("3) admin Login");
        System.out.println("4) Exit the program");
        System.out.print("-> Enter any of the number to proceed : ");

        Scanner input = new Scanner(System.in); // Scanner input - For taking input from user (Initialization).
        boolean correctChoice = false;          // boolean correctChoice - Will be use for Checking if user has selected correct choice or not (Initialization).
        int userIndex = 0;                      // int userIndex - Will be assigned to every new/existing user So it can act as arraylists index (Initialization).

        // Using do while loop to check and validate user's input.
        do
        {
            if (input.hasNextInt())                 // check if user's next input is int or not,Execute block of code if true.
            {
                int startChoice = input.nextInt();  // int startChoice - Store the next int variable input from user (Initialization).
                if (startChoice == 1)               // if startChoice == 1 is true execute the given code.
                {
                    createAcc();                                  // Call createAcc() method.
                    userIndex = AccountDetailsP2.accHName.size()-1;        // Storing the last index of accHName ArrayList(New user) as userIndex (Assignment).
                    correctChoice = true;                         // Exiting/Breaking out of do while loop.
                }
                else if (startChoice == 2)      // else if startChoice == 2 is true execute the given code.
                {
                    userIndex = userLogin();    // calling userLogin()( method and assigning it to userIndex.
                    correctChoice = true;       // Exiting/Breaking out of do while loop.
                }
                else if (startChoice == 3)      // else if startChoice == 3 is true execute given code.
                {
                    adminLogin();               // Call adminLogin() method.
                    adminOptions();             // Call adminOptions() method.
                    correctChoice = true;       // Exiting/Breaking out of do while loop.
                }
                else if (startChoice == 4)      // else if startChoice == 4 is true execute the given code.
                {
                    exit();                     // Call exit() method.
                }
                else if (startChoice == 0)
                {
                    start();
                }
                else                   // Else execute following code.
                {
                    System.out.print("Enter Only Integer[1-4] || Return to Start[0]: ");    // Displaying Try Again Message.
                }
            }   // End if statement
            else                       // Else execute following code.
            {
                System.out.print("Enter Only Integer[1-4] || Return to Start[0]: ");        // Displaying Try Again Message.
                input.next();          // Clearing any unwanted text that Scanner may have.
            }
        } while (!correctChoice);      // while not correctChoice is true loop continuously.

        bankingOptions(userIndex);     // Call bankingOptions() method.
    }   // End start() method.

    public static void createAcc()      // Method that will create accounts for new users.
    {
        Scanner input = new Scanner(System.in);     // Scanner input - For taking input from user (Initialization).
        System.out.println();
        System.out.println("-> Please enter following details to create your account.");
        System.out.print("First Name : ");
        String firstName = input.nextLine();        // String firstName - Store user's first name (Initialization).
        System.out.print("Last Name  : ");
        String lastName = input.nextLine();         // String lastName - Store user's last name (Initialization).
        String name = firstName +" "+ lastName;     // String name - combine first and last name and store it.
        System.out.print("Address    : ");
        String address = input.nextLine();          // String address - Store user's address (Initialization).
        System.out.print("Age        : ");

        boolean checkInput =false;                  // boolean checkInput - Will be use for Checking if user's input is correct or not (Initialization).
        while (!checkInput)                         // while not checkInput is true loop continuously
        {
            if(input.hasNextInt())                  // check if user's next input is int or not,Execute given code if true.
            {
                int age = input.nextInt();          // int age - Store the next int variable input from user (Initialization).
                if (age >= 18)                      // if age >= 18 then execute given code (Validating Account Holder's Age)
                {
                    AccountDetailsP2 user = new AccountDetailsP2(name, address);    // Creating instance of Account class named user and passing the arguments required.
                    System.out.println();
                    System.out.println("** Congrats,Your account has been created **");  // Displaying congrats and account creation MSG.
                    int userIndex = AccountDetailsP2.accCBalance.size() - 1;    // Storing the last index of accCBalance ArrayList(New user) as userIndex (Assignment).
                    AccountDetailsP2.printGenDetails(userIndex);    // Printing details of new account at given index (userIndex) by using printGenDetails method from Account class.

                }
                else               // Else execute following code.
                {
                    System.out.println("You're not old enough to open an account,Please Come back when you turn 18.");      // Displaying under-aged MSG
                    System.out.println("                    ** Returning back to Home Page **                    ");  // Displaying retuning to home MSG.
                    System.out.println();
                    start();       // Call start() method { user is Under-Aged  and can not create account So going to Start Page }
                }
                checkInput=true;   // Exiting/Breaking out of while loop.
            }
            else                   // Else execute following code.
            {
                System.out.print("You can only enter Integer : ");      // Displaying Try Again MSG.
                input.next();      // Clearing any unwanted text that Scanner may have.
            }
        }
    }   // End createAcc() method.

    public static int userLogin()       // Method that can let user login to their existing account.
    {
        int userIndex=0;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Account Number : ");
        while(!input.hasNextInt())  // while input is not int loop continuously
        {
            System.out.print("Oops Wrong Input! Try again || Return Start [0]: ");
            input.next();           // Clearing any unwanted text that scanner may have.
            input.hasNextInt();     // Prompting the user to try again.
        }
        int checkAccountNum = input.nextInt();                      // int checkAccountNum - Store the Account number given from user (Initialization).
        if(AccountDetailsP2.accNum.contains(checkAccountNum))                // if accNum (Arraylist) contains checkAccountNum then execute the code.
        {
            userIndex = AccountDetailsP2.accNum.indexOf(checkAccountNum);    // Store the index of ArrayList which contains the checkAccountNum (Assignment).
            System.out.println("** Login Success **");              // Displaying Login Success Msg.
        }
        else                                                        // Else execute following code.
        {
            System.out.println("** Login Failed **");               // Displaying Login Failed Msg.
            System.out.println("No account associated with given number,Returning to Home Page.");
            start();                                                // Call start() method. ( Login failed so returning to start page ) ( Recursive call )
        }
        return userIndex;
    }

    public static void adminLogin()     // Method that will be used for admins to login into system
    {
        String[] adminUser = {"ADMIN"};        // Arrays that stores admin usernames.
        String[] adminPass = {"ADMIN"};      // Arrays that stores admin passwords.
        // Can only make changes to it by changing the code.No method to change admin login details to make it secured.

        Scanner input = new Scanner(System.in);           // Scanner input - For taking input from user (Initialization).

        System.out.println("Please enter your login details.");
        System.out.print("Username : ");                   // Display enter username MSG.
        String checkUser = input.nextLine();              // String checkUser - Store Username input from user (Initialization).
        System.out.print("Password : ");                   // Display enter username MSG.
        String checkPass = input.nextLine();              // String checkPass- Store Password input from user (Initialization).

        boolean login = false;                            // boolean login - Will be use for Checking if user has logged in or not (Initialization).
        int attemptLeft = 3;                              // int attemptLeft - Store attemptsLeft before system automatically returns.

        while (!login)                                    // while not login is true loop continuously.
        {
            for (int i = 0; i < adminUser.length; i++)    // for each element in adminUser array execute the code.
            {
                if (adminUser[i].equals(checkUser))       // if adminUser contains checkUser execute the given code.
                {
                    if (adminPass[i].equals(checkPass))   // if adminPass contains checkPass execute the given code.
                    {
                        System.out.println();
                        System.out.println("** Login Success **");   // Display login success MSG.
                        login = true;                     // Exiting/Breaking out of while loop.
                    }
                }
            }   // End for loop
            if (!login)                                   // if not login is true execute given code.
            {
                if(attemptLeft <= 0)                       // if attemptLeft <= 0 execute given code.
                {
                    start();                              // Call start() method.
                }
                System.out.println();
                System.out.println("Login Failed,Try again. [Attempts left: "+attemptLeft+"]");  // Display login failed MSG.
                System.out.print("Username : ");           // Enter username again.
                checkUser = input.nextLine();             // Store username. (Assignment)
                System.out.print("Password : ");           // Enter password again.
                checkPass = input.nextLine();             // Store password. (Assignment)
                attemptLeft --;                           // Decrease attemptLeft by 1.
            }
        }   // End while loop
    }   //End adminLogin() method

    public static void exit()           // Method to exit the whole program.
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Are you sure you want to exit (Y | N) : ");     // Displaying confirm MSG.
        input.nextLine();           // Clearing any unwanted text that scanner may have.
        String confirmExit = input.nextLine().toUpperCase();             // String confirmExit - Store user's exit decision and convert it to upperCase (Initialization).
        while (!(confirmExit.equals("Y") || confirmExit.equals("N")))    // while confirmExit is not "Y" or "N" loop continuously
        {
            System.out.print("Enter only Y | N : ");                      // Displaying Try Again Message.
            confirmExit = input.nextLine().toUpperCase();                // Prompting the user to try again (Assignment).
        }
        if (confirmExit.equals("Y"))                                     // if confirmExit == "Y" is true execute given code.
        {
            System.out.println("** Thank you for using our Program **"); // Displaying Thank You Message.
            System.exit(0);                                        // Terminate/Exit the program.
        }
        else                                                             // Else execute following code.
        {
            System.out.println("Exit stopped,returning to HomePage");    // Display returning to home Message.
            start();                                                     // Call start() method. ( Recursive call )
        }
    }

    public static void bankingOptions(int userIndex)  // Method that will be used for managing user's account ( suppose it as 2nd page of program).
    {
        Scanner input = new Scanner(System.in);     // Scanner input - For taking input from user (Initialization).
        System.out.println();
        System.out.println("** Banking Options **");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Transaction History");
        System.out.println("4) Close Account");
        System.out.println("5) Log Out");
        System.out.print("-> Please enter any of the number to proceed: ");

        boolean correctChoice = false;                // boolean correctChoice - Will be use for Checking if user has selected correct choice or not (Initialization).
        while (!correctChoice)                        // while not correctChoice is true loop continuously
        {
            if(input.hasNextInt())                    // check if user's next input is int or not,Execute block of code if true.
            {
                int bankingChoice = input.nextInt();  // int bankingChoice - Store the next int variable input from user (Initialization).

                if (bankingChoice == 1)               // if bankingChoice == 1 is true execute the given code.
                {
                    deposit(userIndex);               // Call deposit() method.
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                else if (bankingChoice == 2)          // else if bankingChoice == 2 is true execute the given code.
                {
                    withdraw(userIndex);              // Call withdraw() method.
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                else if (bankingChoice == 3)          // else if bankingChoice == 3 is true execute the given code.
                {
                    AccountDetailsP2.printTranHistory(userIndex);      // Printing transaction history of the account at index (userIndex) by calling printTranDetails() method from Account class.
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                else if (bankingChoice == 4)          // else if bankingChoice == 4 is true execute the given code.
                {
                    deleteAccount(userIndex);         // Call withdraw() method.
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                else if (bankingChoice == 5)          // else if bankingChoice == 5 is true execute the given code.
                {
                    System.out.println("** Logged Out **");   // Displaying Logged out MSG.
                    start();                          // Call start() method { Returning to start page after logging out }.
                }
                else if (bankingChoice == 0)
                {
                    bankingOptions(userIndex);
                }
                else                                  // Else execute following code.
                {
                    System.out.print("Enter Only Integer (1-5) || Menu [0]: ");                // Displaying try again MSG.
                }
            }
            else                                      // Else execute following code.
            {
                System.out.print("Enter Only Integer (1-5) || Menu [0]: "); // Displaying try again MSG.
                input.next();                         // Clearing any unwanted text that Scanner may have.
            }
        }   // End while loop
        bankingOptions(userIndex);                    // Call bankingOptions() method. ( Recursive call )
    }   // End bankingOptions() method

    public static void deposit(int userIndex)         // Method that will deposit money into User's Account.
    {
        Scanner input = new Scanner(System.in);       // Scanner input - For taking input from user (Initialization).
        System.out.print("Deposit Amount : ");         // Displaying enter deposit amount MSG.
        boolean checkAmount = input.hasNextDouble();  // boolean checkAmount - Will be use for Checking if user has entered correct amount or not (Initialization).
        while(!checkAmount)                           // while not checkAmount is true loop continuously.
        {
            System.out.print("Only integer value (> 0) || Menu [0]: ");   // Displaying Try Again Message.
            input.nextLine();                         // Clearing any unwanted text that Scanner may have.
            checkAmount = input.hasNextDouble();      // Prompting the user to try again (Assignment).
        }   // End while loop
        double amount = input.nextDouble();           // double amount - Store the deposit amount given from user (Initialization).
        while (amount<0)                             // loop until amount<0 is true.
        {
            System.out.print("Only enter value (> 0) || Menu [0] : ");  // Displaying Try Again Message.
            input.nextLine();                         // Clearing any unwanted text that scanner may have.
            if(input.hasNextDouble())                 // check if user's next input is double or not,Execute block of code if true.
            {
                amount = input.nextDouble();          // Prompting the user to try again (Assignment).
            }
        }   // End while loop

        if (amount == 0)     // if amount == 0 execute given code.
        {
            bankingOptions(userIndex);      // Call bankingOptions() method to return to menu.
        }
        else
        {
            AccountDetailsP2.deposit(userIndex, amount);            // Depositing given amount to user's account by using deposit() method from Account class.
            System.out.println();
            System.out.printf("Deposited       :  %.2f \n", amount);      // Displaying deposited amount.
            double cBalance = AccountDetailsP2.accCBalance.get(userIndex);      // double cBalance - Store Current Balance of User's Account.(Initialization)
            System.out.printf("Current Balance :  %.2f \n",cBalance);       // Displaying current balance.
        }
    }   // End deposit() method

    public static void withdraw(int userIndex)        // Method that will withdraw money from User's Account.
    {
        Scanner input = new Scanner(System.in);       // Scanner input - For taking input from user (Initialization).
        double cBalance = AccountDetailsP2.accCBalance.get(userIndex);      // double cBalance - Store Current Balance of User's Account.(Initialization)
        if(cBalance == 0)                             // if cBalance == 0 is true execute the given code.
        {
            System.out.println("Sorry your account balance is 0 , you can't withdraw money right now.");   // Display you cant withdraw money MSG.
            bankingOptions(userIndex);                // Call bankingOptions() method.
        }

        System.out.print("Withdrawal Amount : ");       // Displaying enter withdrawal amount MSG.
        boolean checkAmount = input.hasNextDouble();  // boolean checkAmount - Will be use for Checking if user has entered correct amount or not (Initialization).
        while(!checkAmount)                           // while not checkAmount is true loop continuously.
        {
            System.out.print("Enter only integer value (> 0) || Menu [0] : ");   // Displaying Try Again Message.
            input.nextLine();                         // Clearing any unwanted text that Scanner may have.
            checkAmount = input.hasNextDouble();      // Prompting the user to try again (Assignment).
        }   // End while loop
        double depAmount = input.nextDouble();        // double depAmount - Store the withdrawal amount given from user (Initialization).
        while (depAmount > cBalance || depAmount<0)  // loop until depAmount <0  || depAmount > cBalance is true.
        {
            System.out.print("Only enter value (> 0 and <= Remaining balance) || Menu [0] : ");   // Displaying Try Again Message.
            input.nextLine();                         // Clearing any unwanted text that scanner may have.
            if(input.hasNextDouble())                 // check if user's next input is double or not,Execute block of code if true.
            {
                depAmount = input.nextDouble();       // Prompting the user to try again (Assignment).
            }
        }   // End while loop
        if (depAmount == 0)      // if depAmount == 0 execute given code.
        {
            bankingOptions(userIndex);     // Call bankingOptions() method to return to menu.
        }
        AccountDetailsP2.withdraw(userIndex,depAmount);        // Withdrawing given amount from user's account by using withdraw() method from Account class.
        System.out.println();
        System.out.printf("Withdrawn       :  %.2f \n",depAmount);    // Displaying withdrawn amount.
        cBalance = AccountDetailsP2.accCBalance.get(userIndex);              // Store updated current balance.
        System.out.printf("Current Balance :  %.2f \n",cBalance);      // Displaying current balance.
    }   // End deposit() method

    public static void deleteAccount(int userIndex)   // Method that will delete User's Account from ArrayList.
    {
        Scanner input = new Scanner (System.in);                             // Scanner input - For taking input from user (Initialization).
        System.out.print("Are you sure you want to delete (Y | N) : ");       // Displaying confirm MSG.
        String confirmDelete = input.nextLine().toUpperCase();               // String confirmDelete - Store user's Delete decision and convert it to upperCase (Initialization).
        while (!(confirmDelete.equals("Y") || confirmDelete.equals("N")))    // while confirmExit is not "Y" or "N" loop continuously
        {
            System.out.print("Enter only Y | N : ");                          // Displaying Try Again Message.
            confirmDelete = input.nextLine().toUpperCase();                  // Prompting the user to try again (Assignment).
        }   //End while loop
        if (confirmDelete.equals("Y"))                                       // if confirmDelete == "Y" is true execute given code.
        {
            AccountDetailsP2.removeAcc(userIndex);                                    // Deleting user's account at index (userIndex) by using removeAcc() method from Account class.
            System.out.println("** Your account has been Deleted **");       // Displaying Deleted Account Message.
            start();                                                         // Call start() method.
        }
        else                                                                 // Else execute following code.
        {
            bankingOptions(userIndex);                                       // Call bankingOptions() method.
        }
    }   // End deleteAccount() method

    public static void adminOptions()                 // Method that will be used by admin to manipulate system data( suppose it as 3rd page of program).
    {
        // Display options to be used by admin.
        System.out.println();
        System.out.println("** Admin Options **");
        System.out.println("1) Display All Account");
        System.out.println("2) Delete User Account");
        System.out.println("3) Log Out");
        System.out.print("-> Please enter any of the number to proceed: ");

        Scanner input = new Scanner(System.in);       // Scanner input - For taking input from user (Initialization).
        boolean correctChoice= false;                 // boolean correctChoice - Will be use for Checking if user has selected correct choice or not (Initialization).
        while (!correctChoice)                        // Loop until not correctChoice is true.
        {
            if (input.hasNextInt())                   // check if user's next input is int or not,Execute block of code if true.
            {
                int adminChoice = input.nextInt();    // int adminChoice - Store the next int variable input from user (Initialization).
                if (adminChoice == 1)                 // if adminChoice == 1 is true execute the given code.
                {
                    displayAllAcc();                  // Call displayAllAcc() method.
                    adminOptions();                   // Call adminOptions() method (after displaying all acc details).
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                if (adminChoice == 2)                 // if adminChoice == 2 is true execute the given code.
                {
                    delUserAcc();                    // Call delAccAdmin() method.
                }
                if (adminChoice == 3)                 // if adminChoice == 3 is true execute the given code.
                {
                    start();                          // Call start() method to logout and go back to start Page.
                    correctChoice = true;             // Exiting/Breaking out of while loop.
                }
                if (adminChoice == 0)
                {
                    adminOptions();
                }
            }
            else                                      // Else execute following code.
            {
                System.out.print("Enter only integer (1-3) || Menu [0] : ");  // Display invalid input MSG.
            }
        }   // End while Loop
    }   // End adminOptions() method

    public static void displayAllAcc()                // Method that will print general details of all accounts that exists in system.
    {
        int numOfAcc = AccountDetailsP2.accHName.size();       // int numOfAcc - Store the number of accounts that exist in system/Arraylist (Initialization).
        if (numOfAcc!=0)                              // if numOfAcc != 0 is true execute the given code.
        {
            for (int i = 0; i < numOfAcc; i++)        // for each account that exists in system execute
            {
                AccountDetailsP2.printGenDetails(i);           // Print Account General Details by using printGenDetails() method from Account class.
                System.out.println();
            }
        }
        else                                          // Else execute following code (if 0 account in system).
        {
            System.out.println("No Accounts Exist in the system.");   // Display no account in system MSG.
        }
    }   // End displayAllAcc() method.

    public static void delUserAcc()                    // Method to Delete user Account on giving account number
    {
        int numOfAcc = AccountDetailsP2.accHName.size();      // int numOfAcc - Store the number of accounts that exist in system/Arraylist (Initialization).
        if (numOfAcc!=0)                               // if numOfAcc != 0 is true execute the given code.
        {
            int userIndex;                             // int userIndex Declared. (Declaration)
            Scanner input = new Scanner(System.in);    // Scanner input - For taking input from user (Initialization).
            System.out.print("Enter Account Number(to be deleted) : ");   // Displaying enter account number MSG.
            while (!input.hasNextInt())                // Loop until input is not int.
            {
                System.out.println("Oops Wrong Input! Try again : ");   // Display try again MSG.
                input.next();                          // Clearing any unwanted text that Scanner may have.
                input.hasNextInt();                    // Prompting the user to try again.
            }   //End while loop
            int checkAccountNum = input.nextInt();     // int checkAccountNum - Store the Account number given from user (Initialization).
            input.nextLine();                          // Clearing any unwanted text that Scanner may have.
            if (AccountDetailsP2.accNum.contains(checkAccountNum))                // if accNum (Arraylist) contains checkAccountNum then execute the code.
            {
                userIndex = AccountDetailsP2.accNum.indexOf(checkAccountNum);     // Store the index of ArrayList which contains the checkAccountNum (Assignment).
                AccountDetailsP2.printGenDetails(userIndex);  // Print Account General Details by using printGenDetails() method from Account class.

                System.out.print("Verify if this is the account you want to delete[Y | N] : ");   // Displaying confirm MSG.
                String confirmDelete = input.nextLine().toUpperCase();     // String confirmDelete - Store user's Delete decision and convert it to upperCase (Initialization).

                while (!(confirmDelete.equals("Y") || confirmDelete.equals("N")))    // while confirmExit is not "Y" or "N" loop continuously
                {
                    System.out.print("Enter only Y or N : ");           // Displaying Try Again Message.
                    confirmDelete = input.nextLine().toUpperCase();    // Prompting the user to try again (Assignment).
                }   //End while loop

                if (confirmDelete.equals("Y"))         // if confirmDelete == "Y" is true execute given code.
                {
                    AccountDetailsP2.removeAcc(userIndex);    // Deleting user's account at index (userIndex) by using removeAcc() method from Account class.
                    System.out.println("** Account Deleted **");   // Displaying Deleted Account Message.
                    adminOptions();                    // Call adminOptions() method.
                }
                else  // Else execute following code.
                {
                    System.out.println("Account Delete was Stopped");   // Displaying account delete stopped Message.
                    adminOptions();                    // Call adminOptions() method.
                }
            }
            else  // Else execute following code.
            {
                System.out.println("There is no account associated with that number.");   // Display No account found that matches given Acc Number MSG.
                adminOptions();                        // Call adminOptions() method.
            }
        }     // End if statement
        else  // Else execute following code.
        {
            System.out.println("No Account Exist in the system");   // Display No Account in System MSG.
        }
    }   // End delUserAcc() method.

}   // End mainP2 class.

