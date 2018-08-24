
package strategygame;


import javax.swing.JFrame;


public class StrategyGame extends JFrame{

    public StrategyGame(){
        setTitle("Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MenuPanel(this));
        pack();
        //centers the frame on the screen
        setLocationRelativeTo(null);
        setVisible(true);
}

    public static void main(String[] args) {
        new StrategyGame();
    }
    
}
