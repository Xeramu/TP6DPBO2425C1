import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    int PlayerStartPosX = frameWidth / 2;
    int PlayerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameloop;
    Timer pipesCooldown;
    int gravity = 1;

    int pipeVelocityX = -2;

    int score = 0;

    public Logic() {
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(PlayerStartPosX, PlayerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameloop = new Timer(1000 / 60, this);
        gameloop.start();
        System.out.println("Game loop dimulai...");
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerpipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerpipe);
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }
    }

    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                pipe.getWidth(), pipe.getHeight());

        return playerRect.intersects(pipeRect);
    }

    public int getScore() {
        return score;
    }

    public void gameOver() {
        System.out.println("GAME OVER");
        gameloop.stop();
        pipesCooldown.stop();

        // 🧩 tampilkan popup di EDT setelah UI update
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "GAME OVER!\nPress R to restart the game");
        });
    }

    public void resetGame() {
        System.out.println("Restart Game");

        // reset posisi player
        player.setPosX(PlayerStartPosX);
        player.setPosY(PlayerStartPosY);
        player.setVelocityY(0);

        for (Pipe p : pipes) {
            p.passed = false;
        }
        pipes.clear();
        score = 0;

        // mulai ulang timer
        gameloop.start();
        pipesCooldown.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();

        // Repaint dulu biar tampilan update tiap frame
        if (view != null) {
            view.repaint();
        }

        // Cek kalau burung jatuh ke bawah layar
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
            return;
        }

        // Cek tabrakan dengan setiap pipa
        for (Pipe pipe : pipes) {
            if (checkCollision(player, pipe)) {
                System.out.println("Nabrak bang");
                gameOver();
                return;
            }

            // Tambah skor hanya untuk pipa bawah (supaya nggak double count)
            if (pipe.getImage() == lowerPipeImage && !pipe.passed && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.passed = true;
                score++;
                System.out.println("Score: " + score);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        } else if (code == KeyEvent.VK_R) {
            resetGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}