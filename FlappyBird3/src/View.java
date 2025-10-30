import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private JLabel scoreLabel; // ✅ dipindah ke sini

    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        setFocusable(true);
        addKeyListener(logic);

        // 🔥 Setup label skor
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBounds(10, 10, 200, 50);
        setLayout(null);
        add(scoreLabel);

        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // 🔥 Update text skor
        scoreLabel.setText("Score: " + logic.getScore());
    }
}