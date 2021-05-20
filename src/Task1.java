import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Task1 {
    private static Vector<String> check (Vector<String> list, String password){
        if(password.contains("a")) {
            if (list.contains(password.replace('a', '@'))) {

            } else {
                list.add(password.replace('a', '@'));
            }
        }
        if(password.contains("i")){
            if (list.contains(password.replace('i', '!'))) {

            } else {
                list.add(password.replace('i', '!'));
            }
        }
        if(password.contains("1")){
            if (list.contains(password.replace('1', '!'))) {

            } else {
                list.add(password.replace('1', '!'));
            }
        }
        if(password.contains("t")){
            if (list.contains(password.replace('t', '7'))) {

            } else {
                list.add(password.replace('t', '7'));
            }
        }
        if(password.contains("e")){
            if (list.contains(password.replace('e', '3'))) {

            } else {
                list.add(password.replace('e', '3'));
            }
        }
        if(password.contains("o")){
            if (list.contains(password.replace('o', '0'))) {

            } else {
                list.add(password.replace('o', '0'));
            }
        }
        if(password.contains("S")){
            if (list.contains(password.replace('S', '$'))) {

            } else {
                list.add(password.replace('S', '$'));
            }

            if (list.contains(password.replace('S', '5'))) {

            } else {
                list.add(password.replace('S', '5'));
            }
        }
        if(password.contains("s")){
            if (list.contains(password.replace('s', '$'))) {

            } else {
                list.add(password.replace('s', '$'));
            }

            if (list.contains(password.replace('s', '5'))) {

            } else {
                list.add(password.replace('s', '5'));
            }
        }
        return list;
    }

    private static Vector<String> replaceCharacter(String password, Vector<String> list){
        //check initial password to get initial variation
        list.add(password);
        list = check(list,password);

        //loop through the list to get variations of the variations adding more to the list as it goes
        int i = 0;
        while(i<list.size()){
            list = check(list,list.get(i));
            i++;
        }
        return list;
    }

    private static Vector<String> addSpecial(String password, Vector<String> list){
        list.add(password+'!');
        list.add(password+'@');
        list.add(password+'#');
        list.add(password+'$');
        list.add(password+'%');
        list.add(password+'&');
        return list;
    }

    private static Vector<String> capitalize(String password, Vector<String> list){
        list.add(password.substring(0,1).toUpperCase()+password.substring(1));
        return list;
    }

    private static Vector<String> addDigit(String password, Vector<String> list){
        for(int i = 0; i < 10; i++){
            list.add(password.substring(0,1) + i + password.substring(1));
        }
        return list;
    }

    public static Vector<String> assembleList (Vector<String> passList){
        Vector<String> list = new Vector<String>(0);

        for(String pass : passList){
            list = replaceCharacter(pass, list);
            list = capitalize(pass, list);
            list = addSpecial(pass, list);
            list = addDigit(pass, list);

        }
        return list;
    }

    private static Vector<String> assembleList (String pass){
        Vector<String> list = new Vector<String>(0);

        list = replaceCharacter(pass, list);
        list = capitalize(pass, list);
        list = addSpecial(pass, list);
        list = addDigit(pass, list);

        return list;
    }

    public static Vector<String> ECLP (){
        Vector<String> list = new Vector<String>(0);
        try {
            File file = new File ("Dic.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                list.add(sc.nextLine());
            }
        } catch(Exception e) {
            System.out.println("file not found!");
        }
        return assembleList(list);
    }

    private static void write(Vector<String> list){
        try{
            PrintWriter out = new PrintWriter(new FileWriter("ELCP.txt"));
            out.print(list);
            out.flush();
            out.close();
        } catch (Exception e){
            System.out.println("File not Found");
        }
    }

    public static void main(String[] args) {
//        assembleList("password");
        write(ECLP());
    }
}
