
package strategygame;

import java.awt.Color;
import java.awt.Graphics;


public class XpSplat extends HitSplat{
    
    
    public XpSplat(FieldPanel fieldPanel, double num, Agent a){
        this.fieldPanel = fieldPanel;
        this.num = num;
        this.owner = a;
        
        timeOnScreen = 1300;
        timeLeft = timeOnScreen;
        xPosition = (Math.random() * a.size*2) + a.xPosition - a.size;
        yPosition = (Math.random() * a.size*2) + a.yPosition - a.size;
        risingSpeed = 0.6;
        redColor = 0;
        greenColor = 170;
        blueColor = 170;
    }
    
    
    @Override
    public boolean isActive() {
        return fieldPanel.hitSplatList.contains(this);
    }
    
}


