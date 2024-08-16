import main.Main;

public class App {
    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
}
