
package strategygame;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Player extends Agent{
    
    private double diagonalMovementSpeed;
    
    public double generalResistance;
    public double stabAttack;
    public double slashAttack;
    public double crushAttack;
    public double fireAttack;
    public double windAttack;
    public double iceAttack;
    public double electricAttack;
    public double holyAttack;
    public double unholyAttack;
    public double energyAttack;
    public double magicAttack;
    public double explosionAttack;
    public double waterAttack;
    public double acidAttack;
    
    public XpHandler xpHandler;
    public int levelPointsUsed;
    
    
    public Player(FieldPanel fieldPanel, double x, double y){
        this.fieldPanel = fieldPanel;
        this.xPosition = x;
        this.yPosition = y;
        //picture = ImageIO.read(new File("enemies/" + name + "/Picture.png"));
            this.name = "Player";
            this.health = 100;
            this.maxHealth = health;
            this.healRate = 0.001;
            this.movementSpeed = 1;
            this.diagonalMovementSpeed = movementSpeed / Math.sqrt(2);
            this.attackCoolDown = 2;
            this.size = 20;
            this.attackRange = 50;
            this.attacksPerCycle = 1;
            this.stabResistance = 1;
            this.slashResistance = 1;
            this.crushResistance = 1;
            this.fireResistance = 1;
            this.windResistance = 1;
            this.iceResistance = 1;
            this.electricResistance = 1;
            this.holyResistance = 1;
            this.unholyResistance = 1;
            this.energyResistance = 1;
            this.magicResistance = 1;
            this.explosionResistance = 1;
            this.waterResistance = 1;
            this.acidResistance = 1;
            this.decreasingDOTResistance = 1;
            this.constantDOTResistance = 1;
            this.moveSpeedDownResistance = 1;
            this.moveSpeedDownPercentageRecoverRate = 1;
            this.attackDelayResistance = 1;
            this.vampiricResistance = 1;
            this.defenceDownResistance = 1;
            this.defenceDownPercentageRecoverRate = 1;
            this.attackDownResistance = 1;
            this.attackDownPercentageRecoverRate = 1;
            this.attackRangeDownResistance = 1;
            this.attackRangeDownPercentageRecoverRate = 1;
            this.attackSpeedDownResistance = 1;
            this.attackSpeedDownPercentageRecoverRate = 1;
            this.confusionResistance = 1;
            moveSpeedDownPercentage = 1;
            defenceDownPercentage = 1;
            attackDownPercentage = 1;
            attackRangeDownPercentage = 1;
            attackSpeedDownPercentage = 1;
            defenceMult = 1;
            generalResistance = 1;
            
            DDOTTick = 1000;
            CDOTTick = 1000;
            DDOTTime = 1000;
            CDOTTime = 500;
            constantDOTTime = 0;
            
            stabAttack = 1;
            slashAttack = 1;
            crushAttack = 1;
            fireAttack = 1;
            windAttack = 1;
            iceAttack = 1;
            electricAttack = 1;
            holyAttack = 1;
            unholyAttack = 1;
            energyAttack = 1;
            magicAttack = 1;
            explosionAttack = 1;
            waterAttack = 1;
            acidAttack = 1;
            
            
            
            xpHandler = new XpHandler();
            levelPointsUsed = 0;
        
    }
    
    
    

    @Override
    public LinkedList<Agent> getAgentsInAttackRange() {
        LinkedList<Agent> list = new LinkedList<>();
        for (int i = 0; i < fieldPanel.enemyList.size(); i++){
            if (isInRange(fieldPanel.enemyList.get(i))){
                list.add(fieldPanel.enemyList.get(i));
            }
        }
        
        if (confuseTime > 0){
            for (int i = 0; i < fieldPanel.playerList.size(); i++){
                if (fieldPanel.playerList.get(i) != this && isInRange(fieldPanel.playerList.get(i))){
                    list.add(fieldPanel.playerList.get(i));
                }
            }
        }
        
        return list;
    }
    
    @Override
    public LinkedList<Agent> getAlliesInAttackRange() {
        LinkedList<Agent> list = new LinkedList<>();
        for (int i = 0; i < fieldPanel.playerList.size(); i++){
            if (isInRange(fieldPanel.playerList.get(i)) && fieldPanel.playerList.get(i) != this){
                list.add(fieldPanel.playerList.get(i));
            }
        }
        return list;
    }
    
    //@Override
    //public void paint(Graphics g) {
        
    //}
    
    @Override
    public void actionOnTick() {
        super.actionOnTick();
        move();
        collectItems();
        fieldPanel.parent.update(this);
    }
    
    public void collectItems(){
        for (int i = 0; i < fieldPanel.itemList.size(); i++){
            if (isTouching(fieldPanel.itemList.get(i))){
                fieldPanel.itemList.get(i).actionWhenCollected(this);
            }
        }
    }
    
    public boolean isTouching(Item i){
        return (size + i.size > Math.hypot(xPosition - i.xPosition, yPosition - i.yPosition));
    }
    
    @Override
    public void attack(Agent a){
        updateAttackInput();
        super.attack(a);
    }
    
    public void updateAttackInput(){
        attackList.empty();
        
        if (fieldPanel.parent.stabAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.STAB,StatEffect.NONE,stabAttack,1,0.5,10));
        if (fieldPanel.parent.slashAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.SLASH,StatEffect.NONE,slashAttack,1,0,0));
        if (fieldPanel.parent.crushAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.CRUSH,StatEffect.NONE,crushAttack,1,0,0));
        if (fieldPanel.parent.fireAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.FIRE,StatEffect.NONE,fireAttack,1,0,0));
        if (fieldPanel.parent.windAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.WIND,StatEffect.NONE,windAttack,1,0,0));
        if (fieldPanel.parent.iceAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.ICE,StatEffect.NONE,iceAttack,1,0,0));
        if (fieldPanel.parent.electricAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.ELECTRIC,StatEffect.NONE,electricAttack,1,0,0));
        if (fieldPanel.parent.holyAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.HOLY,StatEffect.NONE,holyAttack,1,0,0));
        if (fieldPanel.parent.unholyAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.UNHOLY,StatEffect.NONE,unholyAttack,1,0,0));
        if (fieldPanel.parent.energyAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.ENERGY,StatEffect.NONE,energyAttack,1,0,0));
        if (fieldPanel.parent.magicAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.MAGIC,StatEffect.NONE,magicAttack,1,0,0));
        if (fieldPanel.parent.explosionAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.EXPLOSION,StatEffect.NONE,explosionAttack,1,0,0));
        if (fieldPanel.parent.waterAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.WATER,StatEffect.NONE,waterAttack,1,0,0));
        if (fieldPanel.parent.acidAttackRadioButton.isSelected())
            attackList.add(new AttackOption(Attribute.ACID,StatEffect.NONE,acidAttack,1,0,0));
        
    }
    
    
    public void move(){
        boolean up = fieldPanel.upArrowPressed;
        boolean right = fieldPanel.rightArrowPressed;
        boolean left = fieldPanel.leftArrowPressed;
        boolean down = fieldPanel.downArrowPressed;
        
        boolean upDown = (up && down) || (!up && !down);
        boolean rightLeft = (right && left) || (!right && !left);

        double ds;
        if (upDown && rightLeft)
            ds = diagonalMovementSpeed;
        else
            ds = movementSpeed;
        
        if (up)
            yPosition -= ds;
        if (down)
            yPosition += ds;
        if (right)
            xPosition += ds;
        if (left)
            xPosition -= ds;
    }
    
    @Override
    public void takeBasicDamage(double damage, HitSplatType type){// type: constant,decreasing DOT, or basic hit
        super.takeBasicDamage(damage*generalResistance, type);
        if (hasFocus()){
            fieldPanel.parent.update(this);
            
        }
    }
    
    @Override
    public boolean canSee(Agent a){
        for (int i = 0; i < fieldPanel.blockList.size(); i++){
            if (!clearLineOfSight(target,fieldPanel.blockList.get(i))){
                return false;
            }
        }
        
        
        return true;
    }

    @Override
    public void delete() {
       
    }

    @Override
    public void deathAction() {
        if (isActive()){
            //gameOver 
        }
    }
    
    public void requestFocus(){
        fieldPanel.parent.grantFocus(this);
    }
    
    public boolean hasFocus(){
        return (fieldPanel.parent.HPBarPanel.player == this);
    }

    @Override
    public boolean isActive() {
        return fieldPanel.playerList.contains(this);
    }
    
    public void addXp(double xp){
        xpHandler.addXp(xp);
    }
    
    // what is displayed on the left of the interface's xp bar
    public double currentXPForLevel() {
        return xpHandler.currentXPForLevel();
    }

    // what is displayed on the right of the interface's xp bar
    public double XPForNextLevel() {
        return xpHandler.XPForNextLevel();
    }
    
    public int getLevel(){
        return xpHandler.totalXPToLevel(xpHandler.getXp());
    }
    public double getXp(){
        return xpHandler.getXp();
    }
    
    public int getLevelPointsAvailable(){
        return getLevel() - levelPointsUsed - 1;
    }
    
    public void upgradeMovementSpeed(){
        //upgradeHandler?
    }

    
}
