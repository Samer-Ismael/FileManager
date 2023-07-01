import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        System.out.println("Enter the path for the folder you want to sort");
        String filePath = new Scanner(System.in).nextLine();

        FileSorting sort = new FileSorting(filePath);
        sort.RunTheApp();

    }
}
