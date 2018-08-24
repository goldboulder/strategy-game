package strategygame;


import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;


public class Projectile extends GameObject{
    public Agent owner;
    public Agent target;
    
    public Attribute attribute;
    public StatEffect effect;
    public double param1;
    public double param2;
    
    public double damage;
    
    public double xStart;
    public double yStart;
    public double percentToTarget = 0;
    public double percentSpeed = 0.02;
    
    private Color fillColor;
    private Color outlineColor;
    private double outlineSize = 7;
    private double fillSize = 5;
    
    public Projectile(FieldPanel fieldPanel,Agent owner, Agent target, Attribute attribute, StatEffect effect, double damage, double param1, double param2){
        this.fieldPanel = fieldPanel;
        this.owner = owner;
        this.target = target;
        this.attribute = attribute;
        this.effect = effect;
        this.damage = damage;
        this.param1 = param1;
        this.param2 = param2;
        xStart = owner.xPosition;
        yStart = owner.yPosition;
        
        
        // color
        if (this.attribute == Attribute.STAB){
            fillColor = new Color(255,255,255);
            outlineColor = new Color(0,0,0);
        } 
        else if (this.attribute == Attribute.SLASH){
            fillColor = new Color(150,150,150);
            outlineColor = new Color(0,0,0);
        }
        else if (this.attribute == Attribute.CRUSH){
            fillColor = new Color(200,150,100);
            outlineColor = new Color(0,0,0);
        }
        else if (this.attribute == Attribute.FIRE){
            fillColor = new Color(255,0,0);
            outlineColor = new Color(255,128,0);
        }
        else if (this.attribute == Attribute.WIND){
            fillColor = new Color(170,170,170);
            outlineColor = new Color(200,200,200);
        }
        else if (this.attribute == Attribute.ICE){
            fillColor = new Color(0,255,255);
            outlineColor = new Color(200,200,200);
        }
        else if (this.attribute == Attribute.ELECTRIC){
            fillColor = new Color(255,255,0);
            outlineColor = new Color(128,128,0);
        }
        else if (this.attribute == Attribute.HOLY){
            fillColor = new Color(255,255,200);
            outlineColor = new Color(255,255,0);
        }
        else if (this.attribute == Attribute.UNHOLY){
            fillColor = new Color(0,0,0);
            outlineColor = new Color(110,0,50);
        }
        else if (this.attribute == Attribute.ENERGY){
            fillColor = new Color(0,200,200);
            outlineColor = new Color(200,200,0);
        }
        else if (this.attribute == Attribute.MAGIC){
            fillColor = new Color(140,0,128);
            outlineColor = new Color(0,0,200);
        }
        else if (this.attribute == Attribute.EXPLOSION){
            fillColor = new Color(255,255,0);
            outlineColor = new Color(255,128,0);
        }
        else if (this.attribute == Attribute.WATER){
            fillColor = new Color(50,50,255);
            outlineColor = new Color(0,0,200);
        }
        else if (this.attribute == Attribute.ACID){
            fillColor = new Color(0,255,0);
            outlineColor = new Color(0,128,0);
        }
        
    }
    
    
    @Override
    public void paint(Graphics g) {
        g.setColor(outlineColor);
        g.fillOval((int)(xPosition - outlineSize),(int)(yPosition - outlineSize), (int)outlineSize*2, (int)outlineSize*2);
        
        g.setColor(fillColor);
        g.fillOval((int)(xPosition - fillSize),(int)(yPosition - fillSize), (int)fillSize*2, (int)fillSize*2);
    }
    
    
    
    
    
    
    
    @Override
    public void actionOnTick() {
        if (target != null){
            percentToTarget += percentSpeed;
            xPosition = xStart + (target.xPosition - xStart)*percentToTarget;
            yPosition = yStart + (target.yPosition - yStart)*percentToTarget;
            if (percentToTarget >= 1){
                if (target.isActive()){
                    target.applyDamageBasedOnAttribute(this);
                    target.applyEffect(this);
                }
                delete();
            }
            
        }
        else{
            delete();
        }
    }
    
    @Override
    public boolean isActive() {
        return fieldPanel.projectileList.contains(this);
    }

    
    
}
