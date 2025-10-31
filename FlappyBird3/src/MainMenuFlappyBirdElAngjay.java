import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFlappyBirdElAngjay {
    //atribut form gui
    private JPanel panel1;
    private JButton playButton;
    private JButton exitButton;
    private JLabel TitlePanel;

    //buat ukuran window gui formnya
    int width = 360;
    int height = 640;

    public MainMenuFlappyBirdElAngjay() {

        //buat set ukuran window sama warna backrgoudnhya
        panel1.setPreferredSize(new Dimension(width, height));
        panel1.setBackground(Color.cyan);

        //buat styleny text di jpanel title
        if (TitlePanel != null) {
            TitlePanel.setFont(new Font("Arial", Font.BOLD, 20)); // nama font, gaya, ukuran
            TitlePanel.setForeground(Color.white); // ubah warna teks juga kalau mau
        }

        //action listener buat button exit
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup aplikasi
                System.exit(0);
            }
        });
    }

    //buat nampilin window gui formnya
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu Flappy Bird");
        frame.setContentPane(new MainMenuFlappyBirdElAngjay().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
