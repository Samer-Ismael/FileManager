import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        String filePath = new Scanner(System.in).nextLine();
        //String filePath = "C:\\Users\\Samer\\Documents\\TestFile";
        FileSorting sort = new FileSorting(filePath);
        sort.RunTheApp();

    }
}
