import java.util.Scanner;
public class Executable
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an option:");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");

        int option = sc.nextInt();

        switch (option)
        {
            case 1:
                System.out.println("Admin selected.");
                Admin.admin();
                break;

            case 2:
                System.out.println("Option 2 selected.");
                User.user();
                break;

            case 3:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid option selected.");
                break;
        }

        sc.close();
    }
}
