import java.util.Scanner;

public class Input implements IInput{
    private Scanner scanner;

    public Input(){
        this.scanner = new Scanner(System.in);
    }

    public void closeScanner(){
        this.scanner.close();
    }

    public int InputInteger(){
        int input = 0;
        while(true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e) {
                continue;
            }
        }
        return input;
    }

    public String InputString(){
       String str = scanner.nextLine();
        return str;
    }

}
