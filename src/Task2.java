import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Vector;

class Person {
    String first;
    String last;
    String DOB;
    String day;
    String month;
    String year;
    String halfYear;
    String phone;
    String street;
    String streetNum;
    String apt;
    String city;
    String state;
    String zip;
    String email;
    String loginUser;
    String loginPass;

    Person(String name, String DOB, String phone, String email, String address, String apt, String city, String state, String zip){
        //split name
        String[] splitName = name.split("\\s+");
        this.first = splitName[0];
        this.last = splitName[1];

        //split DOB
        String[] splitDOB = DOB.split("\\/+");
        this.day = splitDOB[0];
        this.month = splitDOB[1];
        this.year = splitDOB[2];
        this.halfYear = year.substring(2);

        //split street number and name
        String[] splitAddress = address.split(" ");
        this.streetNum = splitAddress[0];
        this.street = splitAddress[1];

        this.apt = apt;
        this.city = city;
        this.state = state;
        this.zip = zip;

        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
    }
}

public class Task2 {
    public static Person samplePerson(){
        String name = "John Doe";
        String DOB = "1/2/3456";
        String phone = "123 456-7890";
        String email = "John.Doe_123@gmail.com";
        String street = "123 main st.";
        String apt = "4";
        String city = "Logan";
        String state = "Utah";
        String zip = "84321";
        return new Person(name,DOB,phone,email,street,apt,city,state,zip);
    }
    private static Vector<String> parseEmail (Person person){
        Vector<String> list = new Vector<String>(0);
        String email = person.email;

        //split email after @
        String[] splitEmail = email.split("@+");
        String first = splitEmail[0];

        //split email email by . or _
        String[] partsOfEmail = first.split("[._]+");

        //add it to a list and return it
        Collections.addAll(list, partsOfEmail);

        return list;
    }
    private static Vector<String> parsePhone (Person person){
        Vector<String> list = new Vector<String>(0);
        String temp = person.phone.substring(0,7);
        list.add(temp.replace(" ",""));
        list.add(person.phone.substring(8));
        return list;
    }
    private static Vector<String> basicList (Person person){
        Vector<String> list = new Vector<String>(0);

        //add all basic items
        list.add(person.first);
        list.add(person.last);
        list.add(person.DOB);
        list.add(person.day);
        list.add(person.month);
        list.add(person.year);
        list.add(person.halfYear);
        list.add(person.phone);
        list.add(person.streetNum);
        list.add(person.street);
        list.add(person.apt);
        list.add(person.city);
        list.add(person.state);
        list.add(person.zip);

        //add to list from functions
        list.addAll(parseEmail(person));
        list.addAll(parsePhone(person));

        return list;
    }
    public static  Vector<String> ECLP2 (Person person){
        return Task1.assembleList(basicList(person));
    }


    public static void main(String[] args) {
        Person person = samplePerson();
        try {
            PrintWriter out = new PrintWriter(new FileWriter("ELCP 2.0.txt"));
            out.print(ECLP2(person));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("File not Found");
        }
    }
}