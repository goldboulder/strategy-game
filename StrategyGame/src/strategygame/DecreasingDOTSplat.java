package strategygame;

import java.awt.Color;
import java.awt.Graphics;


public class DecreasingDOTSplat  extends HitSplat{
    
    public DecreasingDOTSplat(FieldPanel fieldPanel, double num, Agent a){
        this.fieldPanel = fieldPanel;
        this.num = num;
        this.owner = a;
        
        timeOnScreen = 1300;
        timeLeft = timeOnScreen;
        xPosition = (Math.random() * a.size*2) + a.xPosition - a.size;
        yPosition = (Math.random() * a.size*2) + a.yPosition - a.size;
        risingSpeed = 0.2;
        redColor = 150;
        greenColor = 0;
        blueColor = 0;
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(150,0,0,(int)(timeLeft*255.0/timeOnScreen)));
        g.setFont(Stuff.hitSplatFont);
        g.drawString(Stuff.decFormat.format(num), (int)(xPosition-10), (int) (yPosition));
    }
    
    @Override
    public boolean isActive() {
        return fieldPanel.hitSplatList.contains(this);
    }
}
