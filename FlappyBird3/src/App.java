import javax.swing.*;

public class App {
    public static void main(String[] args) {
        //ngebuat window utama
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //inisialisasi logic(logikaks game) sama view(tampilan)
        Logic logika = new Logic();
        View tampilan = new View(logika);

        //ngehubungin view sama logic biar saling akses data
        logika.setView(tampilan);

        //nambahin panel tampilan ke frame
        frame.add(tampilan);
        frame.pack();//buat nyesuaiin ukuran frama sama panel
        frame.setLocationRelativeTo(null);//buat nampil di tengah layar
        frame.setVisible(true);

        //buat fokus ke panel supaya input keyboard bisa masuk
        tampilan.requestFocusInWindow();
    }
}