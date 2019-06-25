package Game;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame{
    Board panel;

    public Application() {
        panel = initUI();
    }

    private Board initUI() {
        Board panel = (Board) add(new Board());
        setTitle("Game.Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setUndecorated(true);
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        return panel;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}
