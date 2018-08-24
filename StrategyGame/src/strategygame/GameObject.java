package strategygame;

import java.awt.Graphics;


public abstract class GameObject {
    public FieldPanel fieldPanel;
    public double xPosition;
    public double yPosition;
    
    public abstract void paint(Graphics g);
    public abstract void actionOnTick();
    public abstract boolean isActive();
    public void delete(){
        fieldPanel.delete(this);
    }
    
}
