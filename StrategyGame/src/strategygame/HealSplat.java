
package strategygame;

import java.awt.Color;
import java.awt.Graphics;


public class HealSplat  extends HitSplat{
    
    public HealSplat(FieldPanel fieldPanel, double num, Agent a){
        this.fieldPanel = fieldPanel;
        this.num = num;
        this.owner = a;
        
        timeOnScreen = 1300;
        timeLeft = timeOnScreen;
        xPosition = (Math.random() * a.size*2) + a.xPosition - a.size;
        yPosition = (Math.random() * a.size*2) + a.yPosition - a.size;
        risingSpeed = 0.5;
        redColor = 200;
        greenColor = 0;
        blueColor = 240;
    }
    
    
    
    @Override
    public boolean isActive() {
        return fieldPanel.hitSplatList.contains(this);
    }
    
}
