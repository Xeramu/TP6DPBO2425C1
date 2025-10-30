import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {

    //ukuran freame utama
    int frameWidth = 360;
    int frameHeight = 640;

    //data buat player
    int PlayerStartPosX = frameWidth / 2;
    int PlayerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    //data buat pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    //refrensi ke view buat update tampilan
    View view;

    //objek gambar sama entitas game
    Image birdImage;
    Player player;
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    //timer buat game loop sama spawn pipa
    Timer gameloop;
    Timer pipesCooldown;

    //variable buat gravity sama kecepatan pipa
    int gravity = 1;
    int pipeVelocityX = -2;

    //score awal pemain
    int score = 0;

    //contsurctor
    public Logic() {
        //buat ngeload gambar pipa sama burung dari foler asset
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(PlayerStartPosX, PlayerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        //timer spawn pipa. Jdnya pipa spawn setiap 1.5 detiks
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa"); //buat debug di terminal pipanya muncul gaks
                placePipes();
            }
        });
        pipesCooldown.start();

        //game lopp berjalan 60 fps
        gameloop = new Timer(1000 / 60, this);
        gameloop.start();
        System.out.println("Game loop dimulai"); //buat debug jugaks
    }

    //setter buat ngehubugin logic sama view
    public void setView(View view) {
        this.view = view;
    }

    //getter buat player sama pipa
    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    //buat sepasang pipa
    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;//buat jarak antara kita (pipa)

        //pipa atas
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        //pipa bawah
        Pipe lowerpipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerpipe);
    }

    //buat update posisi player dan pipa setiap frame
    public void move() {
        //burung jatuh karena gravitasi
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));//biar engga kluar layar atas

        //pipa geraknya kekiri
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }
    }

    //buat ngecek burung nabrak pipa
    public boolean checkCollision(Player player, Pipe pipe) {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight());
        Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                pipe.getWidth(), pipe.getHeight());

        return playerRect.intersects(pipeRect);
    }

    //getter buat score
    public int getScore() {
        return score;
    }

    //pas game over
    public void gameOver() {
        System.out.println("GAME OVER");//buat debug jugaks
        gameloop.stop();
        pipesCooldown.stop();
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "GAME OVER!\nPress R to restart the game");//pas kalah ntar nampilin message window
        });
    }

    //buat reset smua data biar game bisa direstart
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

        gameloop.start();
        pipesCooldown.start();
    }

    //buat event loop (tiap frame)
    @Override
    public void actionPerformed(ActionEvent e) {
        move();

        //refresh tampilan
        if (view != null) {
            view.repaint();
        }

        //buat ngecek kalau burung jatuh ke bawah layar
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
            return;
        }

        //buat ngecek si burung nabrak pipa sama skor
        for (Pipe pipe : pipes) {
            if (checkCollision(player, pipe)) {
                System.out.println("Nabrak bang");
                gameOver();
                return;
            }

            //ambah skor sebiji klo ngelewatin SEPASANG pipa atas sama bawah
            //sesungguhnya kita diciptakan berpasangan
            if (pipe.getImage() == lowerPipeImage && !pipe.passed && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.passed = true;
                score++;
                System.out.println("Score: " + score);
            }
        }
    }

    //buat control keyboard
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