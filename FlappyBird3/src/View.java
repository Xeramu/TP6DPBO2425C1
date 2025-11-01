import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;//buat ukuran panelnya
    int height = 640;//buat ukuran panelnya

    private Logic logic;
    private JLabel scoreLabel;
    private Image backgroundImage;

    //constructor
    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));//buat ukuran panelnya
        setBackground(Color.cyan);//klo image gagal ngeload, ntar cyan aja bgnya

        backgroundImage = new ImageIcon(getClass().getResource("assets/bgFB2.jpeg")).getImage();

        //biar panel meneripma input dr keyboard
        setFocusable(true);
        addKeyListener(logic);

        //buat label skor
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Press Start 2P", Font.BOLD, 30));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setSize(200, 50);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setLocation((width - scoreLabel.getWidth()) / 2, 20); // tengah atas

        setLayout(null);
        add(scoreLabel);

        //requestFocusInWindow();
    }

    //ngegambar ulang smua komponen dilayar
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //biar gambar background di paling belakang
        if (backgroundImage != null){
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        }
        else{
            g.setColor(Color.cyan);
            g.fillRect(0, 0, width, height);
        }

        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        //gambar smua pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        //update text score
        scoreLabel.setText( "" + logic.getScore());

        //klo game over, tampilkan teks di tengah
        if (logic.isGameOver()) {
            g.setFont(new Font("Press Start 2P", Font.BOLD, 20));
            g.setColor(Color.red);
            String msg = "GAME OVER";

            //buat ngitung posisi biar teks di tengah layar
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(msg);
            int textHeight = fm.getHeight();

            int x = (width - textWidth) / 2;
            int y = (height - textHeight) / 2;

            //biar ada efek shadow biar kelihatan di background
            g.setColor(Color.black);
            g.drawString(msg, x + 3, y + 3);
            g.setColor(Color.red);
            g.drawString(msg, x, y);

            //buat message press R to restart
            g.setFont(new Font("Press Start 2P", Font.PLAIN, 12));
            String restartMsg = "Press R to restart";
            FontMetrics fm2 = g.getFontMetrics();
            int restartWidth = fm2.stringWidth(restartMsg);
            int restartX = (width - restartWidth) / 2;
            int restartY = y + 40; // posisi di bawah teks "GAME OVER"

            g.setColor(Color.black);
            g.drawString(restartMsg, restartX, restartY);
        }
    }
}