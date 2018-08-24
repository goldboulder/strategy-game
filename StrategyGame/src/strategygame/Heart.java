package strategygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Heart extends Item{
    
    public double healAmount;
    
    public BufferedImage picture;
    
    public Heart(FieldPanel fieldPanel, double healAmount,double x,double y){
        this.fieldPanel = fieldPanel;
        this.healAmount = healAmount;
        xPosition = x;
        yPosition = y;
        size = 10;
        
        try {
            picture = ImageIO.read(new File("pictures/Heart.png"));
        } catch (IOException ex) {
            System.out.println("Could not fine Heart picture");
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
        p.heal(healAmount);
        super.actionWhenCollected(p);
    }
    
    @Override
    public boolean isActive() {
        return fieldPanel.itemList.contains(this);
    }

    
}
