package strategygame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Enemy extends Agent{
    public double sightRange;
    public double healthDrops;
    public double xpDrops;
    public double sightDownResistance;
    public double sightDownPercentageRecoverRate;
    public double sightDownPercentage;
    
    public Enemy(String name, FieldPanel fieldPanel, double x, double y){
        try{
            picture = ImageIO.read(new File("enemies/" + name + "/Picture.png"));
            this.name = name;
            
            Scanner sc = new Scanner(new File("enemies/" + name + "/Stats.txt"));
            this.health = sc.nextDouble();
            this.maxHealth = health;
            sc.nextLine();
            this.healRate = sc.nextDouble();
            sc.nextLine();
            this.movementSpeed = sc.nextDouble();
            sc.nextLine();
            this.attackCoolDown = sc.nextDouble();
            sc.nextLine();
            this.size = sc.nextDouble();
            sc.nextLine();
            this.attackRange = sc.nextDouble();
            sc.nextLine();
            this.sightRange = sc.nextDouble();
            sc.nextLine();
            this.healthDrops = sc.nextDouble();
            sc.nextLine();
            this.xpDrops = sc.nextDouble();
            sc.nextLine();
            this.attacksPerCycle = sc.nextDouble();
            sc.nextLine();
            this.stabResistance = sc.nextDouble();
            sc.nextLine();
            this.slashResistance = sc.nextDouble();
            sc.nextLine();
            this.crushResistance = sc.nextDouble();
            sc.nextLine();
            this.fireResistance = sc.nextDouble();
            sc.nextLine();
            this.windResistance = sc.nextDouble();
            sc.nextLine();
            this.iceResistance = sc.nextDouble();
            sc.nextLine();
            this.electricResistance = sc.nextDouble();
            sc.nextLine();
            this.holyResistance = sc.nextDouble();
            sc.nextLine();
            this.unholyResistance = sc.nextDouble();
            sc.nextLine();
            this.energyResistance = sc.nextDouble();
            sc.nextLine();
            this.magicResistance = sc.nextDouble();
            sc.nextLine();
            this.explosionResistance = sc.nextDouble();
            sc.nextLine();
            this.waterResistance = sc.nextDouble();
            sc.nextLine();
            this.acidResistance = sc.nextDouble();
            sc.nextLine();
            this.decreasingDOTResistance = sc.nextDouble();
            sc.nextLine();
            this.constantDOTResistance = sc.nextDouble();
            sc.nextLine();
            this.moveSpeedDownResistance = sc.nextDouble();
            this.moveSpeedDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.attackDelayResistance = sc.nextDouble();
            sc.nextLine();
            this.vampiricResistance = sc.nextDouble();
            sc.nextLine();
            this.defenceDownResistance = sc.nextDouble();
            this.defenceDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.attackDownResistance = sc.nextDouble();
            this.attackDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.attackRangeDownResistance = sc.nextDouble();
            this.attackRangeDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.attackSpeedDownResistance = sc.nextDouble();
            this.attackSpeedDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.sightDownResistance = sc.nextDouble();
            this.sightDownPercentageRecoverRate = sc.nextDouble();
            sc.nextLine();
            this.confusionResistance = sc.nextDouble();
            sc.nextLine();
            
            sc.nextLine();
            
            Attribute a;
            StatEffect e;
            double d;
            double w;
            double p1;
            double p2;
            while(sc.hasNext()){
                switch(sc.next()){
                    case "stab": a = Attribute.STAB; break;
                    case "slash": a = Attribute.SLASH; break;
                    case "crush": a = Attribute.CRUSH; break;
                    case "fire": a = Attribute.FIRE; break;
                    case "wind": a = Attribute.WIND; break;
                    case "ice": a = Attribute.ICE; break;
                    case "electric": a = Attribute.ELECTRIC; break;
                    case "holy": a = Attribute.HOLY; break;
                    case "unholy": a = Attribute.UNHOLY; break;
                    case "energy": a = Attribute.ENERGY; break;
                    case "magic": a = Attribute.MAGIC; break;
                    case "explosion": a = Attribute.EXPLOSION; break;
                    case "water": a = Attribute.WATER; break;
                    case "acid": a = Attribute.ACID; break;
                    default: throw new RuntimeException("Wrong attribute type in file");
                }
                
                switch(sc.next()){
                    case "none": e = StatEffect.NONE; break;
                    case "ddot": e = StatEffect.DECREASING_DOT; break;
                    case "cdot": e = StatEffect.CONSTANT_DOT; break;
                    case "movedown": e = StatEffect.MOVE_SPEED_DOWN; break;
                    case "attackdelay": e = StatEffect.ATTACK_DELAY; break;
                    case "vampiric": e = StatEffect.VAMPIRIC; break;
                    case "defdown": e = StatEffect.DEFENCE_DOWN; break;
                    case "attdown": e = StatEffect.ATTACK_DOWN; break;
                    case "attrangedown": e = StatEffect.ATTACK_RANGE_DOWN; break;
                    case "attspeeddown": e = StatEffect.ATTACK_SPEED_DOWN; break;
                    case "sightdown": e = StatEffect.SIGHT_RANGE_DOWN; break;
                    case "confusion": e = StatEffect.CONFUSION; break;
                    default: throw new RuntimeException("Wrong stat effect type in file");
                }
                
                
                
                d = sc.nextDouble();
                w = sc.nextDouble();
                p1 = sc.nextDouble();
                p2 = sc.nextDouble();
                
                this.attackList.add(new AttackOption(a,e,d,w,p1,p2));
                sc.nextLine();
            }
            
            this.defenceMult = 1;
            this.fieldPanel = fieldPanel;
            this.xPosition = x;
            this.yPosition = y;
            
            moveSpeedDownPercentage = 1;
            defenceDownPercentage = 1;
            attackDownPercentage = 1;
            attackRangeDownPercentage = 1;
            attackSpeedDownPercentage = 1;
            sightDownPercentage = 1;
            
            DDOTTick = 1000;
            CDOTTick = 1000;
            DDOTTime = 1000;
            CDOTTime = 500;
            constantDOTTime = 0;
            
        }
        catch(FileNotFoundException e){
            System.out.println("Error reading file");
        }
        catch (IOException ex) {
            System.out.println("Could not find file");
        }
        
        
        
    }
    
    @Override
    public void sightRangeDown(Projectile p){
        
        double drain = sightDownPercentage*(1.0-p.param1)*sightDownResistance;
        //System.out.println(drain);
        sightDownPercentage -= drain;
        if (sightDownPercentage < 0)
            sightDownPercentage = 0;
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(0,0,255,100));
        double sRange = sightRange * sightDownPercentage;
        g.drawOval((int)(xPosition - sRange),(int)(yPosition - sRange),(int) sRange*2,(int) sRange*2);
        super.paint(g);
    }
    

    @Override
    public LinkedList<Agent> getAgentsInAttackRange() {
        LinkedList<Agent> list = new LinkedList<>();
        for (int i = 0; i < fieldPanel.playerList.size(); i++){
            if (isInRange(fieldPanel.playerList.get(i))){
                list.add(fieldPanel.playerList.get(i));
            }
        }
        
        if (confuseTime > 0){
            for (int i = 0; i < fieldPanel.enemyList.size(); i++){
                if (fieldPanel.enemyList.get(i) != this && isInRange(fieldPanel.enemyList.get(i))){
                    list.add(fieldPanel.enemyList.get(i));
                }
            }
        }
        
        return list;
    }
    
    @Override
    public LinkedList<Agent> getAlliesInAttackRange() {
        LinkedList<Agent> list = new LinkedList<>();
        for (int i = 0; i < fieldPanel.enemyList.size(); i++){
            if (isInRange(fieldPanel.enemyList.get(i)) && fieldPanel.enemyList.get(i) != this){
                list.add(fieldPanel.enemyList.get(i));
            }
        }
        return list;
    }
    
    
    
    @Override
    public void actionOnTick() {
        super.actionOnTick();
        findTarget();
        move();
        if (sightDownPercentage < 1){
            sightDownPercentage += sightDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (sightDownPercentage > 1)
                sightDownPercentage = 1;
        }
        
        
        
        
    }
    
    public void findTarget(){
        
        if (target != null){
            if (canSee(target))
                return;
            else
                target = null;
        }
        
        else{
            
            for (int i = 0; i < fieldPanel.playerList.size(); i++){
                if (canSee(fieldPanel.playerList.get(i)))
                    target = fieldPanel.playerList.get(i);
            }
        }
        
    }
    
    public void move(){
        if (target != null && canSee(target) && !isInRange(target)){
            double angle = Math.atan2(target.yPosition - yPosition, target.xPosition - xPosition);
            xPosition += (movementSpeed * moveSpeedDownPercentage * Math.cos(angle));
            yPosition += (movementSpeed * moveSpeedDownPercentage * Math.sin(angle));
        }
    }
    
    @Override
    public boolean canSee(Agent a){
        double distance = Math.hypot(xPosition - a.xPosition,yPosition - a.yPosition) - a.size;
        if (distance > sightRange*sightDownPercentage){
            return false;
        }
        
        for (int i = 0; i < fieldPanel.blockList.size(); i++){
            if (!clearLineOfSight(target,fieldPanel.blockList.get(i))){
                return false;
            }
        }
        
        
        return true;
    }
    
    public void dropHearts(double amount){
        if (healthDrops > 0){
            double x = (Math.random() * size*2) + xPosition - size;
            double y = (Math.random() * size*2) + yPosition - size;
            fieldPanel.addGameObject(new Heart(fieldPanel,healthDrops,x,y));
        }
        
    }
    
    public void dropXP(double amount){
        if (xpDrops > 0){
            double x = (Math.random() * size*2) + xPosition - size;
            double y = (Math.random() * size*2) + yPosition - size;
            fieldPanel.addGameObject(new XpOrb(fieldPanel,xpDrops,x,y));
        }
    }
    
    
    @Override
    public void deathAction(){
        if (isActive()){
            dropHearts(healthDrops);
            dropXP(xpDrops);
            super.delete();
        }
    }

    @Override
    public boolean isActive() {
        return fieldPanel.enemyList.contains(this);
    }

    

    
    
}
