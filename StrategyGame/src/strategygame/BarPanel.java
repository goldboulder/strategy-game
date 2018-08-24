package strategygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;


public class BarPanel extends JPanel{
    
    public GamePanel parent;
    public Player player;
    public int xSize;
    public int ySize;
    public double numCurrent;
    public double numMax;
    public Color fillColor;
    public Color emptyColor;
    public Color textColor;
    public Font textFont;
    
    
    public BarPanel(GamePanel parent, Player player, int xSize, int ySize, double numCurrent, double numMax, Color fillColor, Color emptyColor, Color textColor, Font textFont){
        this.parent = parent;
        this.player = player;
        this.xSize = xSize;
        this.ySize = ySize;
        this.numCurrent = numCurrent;
        this.numMax = numMax;
        this.fillColor = fillColor;
        this.emptyColor = emptyColor;
        this.textColor = textColor;
        this.textFont = textFont;
    }
    
    @Override
    public void paintComponent(Graphics g){
        if (player != null){
            g.setColor(emptyColor);
            g.fillRect(1,1,xSize,ySize);

            g.setColor(fillColor);
            g.fillRect(0, 0, (int)((numCurrent/numMax)*(xSize)), ySize);

            g.setColor(Color.BLACK);
            g.drawRect(0, 0, xSize, ySize);

            g.setFont(textFont);
            String str = Stuff.getDecimalFormat(numCurrent).format(numCurrent) + " / " + Stuff.getDecimalFormat(numMax).format(numMax);
            g.drawString(str, xSize/2 - str.length()*2, ySize/2 +6);
        }
    }
    
    public void update(double current, double max){
        numCurrent = current;
        numMax = max;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(xSize,ySize);
    }
    
    
    
}
