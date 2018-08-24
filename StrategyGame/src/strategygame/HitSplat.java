
package strategygame;

import java.awt.Color;
import java.awt.Graphics;


public abstract class HitSplat extends GameObject{
    
    public double num;
    public Agent owner;
    public int timeLeft;
    public int timeOnScreen;
    public double risingSpeed;
    public int redColor;
    public int greenColor;
    public int blueColor;
    
    
    
    
    
    
    @Override
    public void actionOnTick() {
        timeLeft -= fieldPanel.GAME_TICK;
        yPosition -= risingSpeed;
        if (timeLeft <= 0)
            delete();
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(redColor,greenColor,blueColor,(int)(timeLeft*255.0/timeOnScreen)));
        g.setFont(Stuff.hitSplatFont);
        String str = Stuff.getDecimalFormat(num).format(num);
        g.drawString(str, (int)(xPosition-(str.length()*2)), (int) (yPosition));
        
    }

    
}
