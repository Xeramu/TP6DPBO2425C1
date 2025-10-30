import java.awt.*;

public class Pipe {
    //atribut buat pipa kayak posisi sama ukuran
    private int posX;
    private int posY;
    private int width;
    private int height;

    //gambar pipa
    private Image image;

    //kecepatan gerakan pipa
    private int velocityX;

    //buat penanda pipanya udh dilewatin burung blm
    boolean passed;

    //costructor
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = 0;
        this.passed = false;
    }

    //get set smua atributs
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getPosY() {
        return posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityX() {
        return velocityX;
    }
}
