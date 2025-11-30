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

    public static void login(int p){
        boolean validLogin = false;
        while(!validLogin){
            System.out.println("Enter your pin");
            if(sc.hasNextInt()){
                int candidate = sc.nextInt();
                if(candidate==p){
                    System.out.println("Login successful");
                    validLogin = true;
                }
                else{
                    System.out.println("Wrong pin");
                }
            }
            else{
                System.out.println("Please enter a 4-digit pin");
                sc.next();
            }
        }
        loggedIn = true;
    }
    
    public static void showBalance(boolean loggedIn){
        if(loggedIn==false){
            System.out.println("You must be logged in to view your balance");
        }
        else{
            System.out.println("Your balance is: "+balance);
        } 
    }

    public static double deposit(double dp){
        balance+=dp;
        return balance;
    }

    public static void main(String[]args){   
        System.out.println("\nWelcome to Temulen's Bank!\n");
        int pin = createPin();
        login(pin);
        showBalance(loggedIn);
        
    }
}