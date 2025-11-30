import java.util.Scanner;
/*
1) Simulate a bank (account creation, deposit, withdrawal, interest calculation)
2) Ask user for name and PIN
3) Require login for deposit, withdrawl, or interest calculation
4) Output accurate amount after compounded interest
*/
public class Bank{
    
    static double balance = 0;
    static boolean loggedIn = false;

    static Scanner sc = new Scanner(System.in);

    public static int createPin(){
        boolean validInput = false; // Conditional that checks if user entered a valid pin (4 digits)
        int pin = 0; // Temporary value
        while(!validInput){ // Run while validInput is false
            System.out.println("Please enter a 4-digit pin");
            if(sc.hasNextInt()){    // Passes one checkpoint
                int candidate = sc.nextInt();   // Input is a candidate for a valid pin
                if(getPinLength(candidate)!=4){ // If candidate is not 4 digits long, error! Repeat
                    System.out.println("Error, pin must be 4 digits long.");
                    sc.next();  // Eats invalid token
                }
                else{   // Passes second checkpoint, everything is good
                    pin = candidate;
                    validInput = true;  // Breaks out of the loop
                }
            }
            else{   // If user inputs anything other than an int
                System.out.println("Invalid input, enter a 4-digit pin (int)");
                sc.next();
            }
        }
        return pin; // Returns the user's pin
    }

    public static int getPinLength(int p){  // Used to check if pin is of valid length in createPin
        return String.valueOf(p).length();
    }

    public static void login(int p){    // Take in the pin (p)
        boolean validLogin = false; // Assumes the login is false at the start
        while(!validLogin){
            System.out.println("Enter your pin");   // Query for pin
            if(sc.hasNextInt()){
                int candidate = sc.nextInt();   // Potential pin
                if(candidate==p){   // Correct pin!
                    System.out.println("Login successful");
                    validLogin = true;  // Breaks out of loop
                }
                else{
                    System.out.println("Wrong pin");    // Wrong pin
                }
            }
            else{
                System.out.println("Please enter a 4-digit pin");   // If user inputs a non-int pin
                sc.next();
            }
        }
        loggedIn = true;
    }
    
    public static void showBalance(boolean loggedIn){   // Only works if user is logged in
        if(loggedIn==false){ 
            System.out.println("You must be logged in to view your balance");
        }
        else{
            System.out.println("Your balance is: "+balance);    // Simply displays the balance
        } 
    }

    public static double deposit(double dp){    // Takes in desired deposit amount
        balance+=dp;    // Add the deposit value to the balance
        return balance; // Return the new balance
    }

    public static double withdraw(double wd){   // Takes in desired withdrawal amount
        if(wd>balance){ // If the withdrawal is higher than the balance, error
            System.out.println("Cannot withdraw more than you have!");
        }
        else{
            balance-=wd;    // Subtract the withdrawal value from the balance
        }
        return balance; // Return the new balance
    }

    public static void calculateInterest() {
    System.out.println("How many years will you leave the money invested?");
    if(sc.hasNextInt()) {
        int years = sc.nextInt();
        double rate = 0.05; // 5% Interest Rate (Econ 001 dreams!)
        
        // A = P(1 + r)^t
        double futureValue = balance * Math.pow((1 + rate), years);
        
        System.out.printf("At 5%% interest, in %d years you will have: $%.2f\n", years, futureValue);
    } else {
        sc.next(); // Clear bad input
    }
}

    public static void main(String[]args){   
        boolean using = true;
        
        System.out.println("\nWelcome to Temulen's Bank!\n");
        int pin = createPin();
        login(pin);
        showBalance(loggedIn);
        while(using){
            System.out.println("Would you like to deposit or withdraw? (1-Deposit, 2-Withdraw, 3-Calculate Interest)");
            int decision = sc.nextInt();
            if(decision==1){
                System.out.println("How much would you like to deposit?");
                double dpamount = sc.nextDouble();
                deposit(dpamount);
            }
            else if(decision==2){
                System.out.println("How much would you like to withdraw?");
                double wdamount = sc.nextDouble();
                withdraw(wdamount);
            }
            else if(decision==3){
                calculateInterest();
            }
            System.out.println("You now have "+balance);
            System.out.println("Would you like to continue? (1-Yes, 2-No)");
            int decision2 = sc.nextInt();
            if(decision2==1){
                using = true;
            }
            if(decision2==2){
                System.out.println("Thank you for using Temulen's Bank!");
                using = false;
            }
        }
    sc.close(); // Close the scanner
    }
}