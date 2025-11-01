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
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS)); // vertikal

        //buat styleny text di jpanel title
        TitlePanel.setText("Welcome To El Flappy");
        TitlePanel.setFont(new Font( "Press Start 2P", Font.BOLD, 15));
        TitlePanel.setForeground(Color.black);
        TitlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        TitlePanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 60, 0)); // jarak atas-bawah

        //buat ukuran tombol
        Dimension buttonSize = new Dimension(200, 50);

        //style tombol play
        playButton.setText("Play");
        playButton.setFont(new Font("Press Start 2P", Font.BOLD, 13));
        playButton.setBackground(Color.gray);
        playButton.setForeground(Color.BLACK);
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setMaximumSize(buttonSize);

        //style tombol exit
        exitButton.setText("Exit");
        exitButton.setFont(new Font("Press Start 2P", Font.BOLD, 13));
        exitButton.setBackground(Color.gray);
        exitButton.setForeground(Color.BLACK);
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(buttonSize);

        //buat nambahin ke panel form
        panel1.removeAll();
        panel1.add(TitlePanel);
        panel1.add(Box.createRigidArea(new Dimension(0, 30)));//buat jarak antara tombol
        panel1.add(playButton);
        panel1.add(Box.createRigidArea(new Dimension(0, 15)));
        panel1.add(exitButton);

        //action listener buat button play
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buat nutup window main menu
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                currentFrame.dispose();

                //jalanin gamnya. diambil dr App.java
                JFrame frame = new JFrame("Flappy Bird");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);

                Logic logika = new Logic();
                View tampilan = new View(logika);
                logika.setView(tampilan);

                frame.add(tampilan);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                tampilan.requestFocusInWindow();
            }
        });

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
