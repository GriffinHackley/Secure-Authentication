import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Task3 {
    private static void login(Person person) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Username");
        String user = scan.nextLine();

        System.out.println("Enter password");
        String password = scan.nextLine();

        int failed = 0;
        while(!(user.equals(person.loginUser) && password.equals(person.loginPass))){
            failed++;
            if(failed%3 == 0){
                System.out.println("You have attempted to log in too many times");
                System.out.println("You have been locked out for " + Math.pow(2,(Math.floorDiv(failed,3)-1)) + " Minutes");
                TimeUnit.MINUTES.sleep((long) Math.pow(2,Math.floorDiv(failed,3)));
            }
            System.out.println("You have put in the wrong information, please try again");

            System.out.println("Enter Username");
            user = scan.nextLine();

            System.out.println("Enter password");
            password = scan.nextLine();
        }
        failed = 0;
        System.out.println("You are logged in");
    }
    private static Person register(Person person){
        Vector<String> ECLP1 = Task1.ECLP();
        Vector<String> ECLP2 = Task2.ECLP2(person);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Username");
        String user = scan.nextLine();

        System.out.println("Enter password");
        String password = scan.nextLine();

        boolean registrationComplete = false;
        while(!registrationComplete){
            if(ECLP1.contains(password)){
                System.out.println("Your password is a very common password (or a variant of one)");
                System.out.println("This could make your password vulnerable to a dictionary attack");
                System.out.println("Please create a new password");
                password = scan.nextLine();
            } else if(ECLP2.contains(password)){
                System.out.println("Your password contains some personal information (or a variant of it)");
                System.out.println("This could make your password vulnerable to a targeted guessing attack");
                System.out.println("Please create a new password");
                password = scan.nextLine();
            } else {
                System.out.println("Your password has been accepted");
                System.out.println("Registration Complete!");
                registrationComplete = true;
            }
        }
        person.loginUser = user;
        person.loginPass = password;
        return person;
    }

    public static void main(String[] args) throws InterruptedException {
        Person person = Task2.samplePerson();

        person = register(person);

        login(person);
    }
}
