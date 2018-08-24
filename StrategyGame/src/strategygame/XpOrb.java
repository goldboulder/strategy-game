package strategygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class XpOrb extends Item{
    
    public double xp;
    
    public BufferedImage picture;
    
    public XpOrb(FieldPanel fieldPanel, double xp,double x,double y){
        this.fieldPanel = fieldPanel;
        this.xp = xp;
        xPosition = x;
        yPosition = y;
        size = 10;
        
        try {
            picture = ImageIO.read(new File("pictures/Xp Orb.png"));
        } catch (IOException ex) {
            System.out.println("Could not fine Xp Orb picture");
        }
        
        
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(picture,(int)(xPosition - size),(int)(yPosition - size),(int) size*2,(int) size*2, null);
    }
    
    @Override
    public void actionOnTick() {
        
    }

    @Override
    public void actionWhenCollected(Player p) {
        p.addXp(xp);
        p.makeHitSplat(xp, HitSplatType.XP);
        if (p.hasFocus())
            fieldPanel.parent.update(p);
        super.actionWhenCollected(p);
    }
    
    @Override
    public boolean isActive() {
        return fieldPanel.itemList.contains(this);
    }
    

    
}
