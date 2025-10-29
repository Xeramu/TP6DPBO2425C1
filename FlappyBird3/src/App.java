import javax.swing.*;

public class App {
    public static void main (String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        frame.add(tampilan);
        frame.pack();
        frame.setVisible(true);

        tampilan.requestFocus();
    }
}
