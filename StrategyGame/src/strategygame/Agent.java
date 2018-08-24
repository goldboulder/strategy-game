package strategygame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public abstract class Agent extends GameObject{
    public String name;
    public double health;
    public double maxHealth;
    public double healRate;
    public double movementSpeed;
    public double attackCoolDown; // attack rate in seconds per hit
    public double timeUntilAttack;
    public AttackList attackList = new AttackList();
    public double size;
    public double attackRange;
    public BufferedImage picture;
    public Agent target;
    public double attacksPerCycle; // max number of projectiles launched per cycle. decimal means a % chance of hitting another.
    public double defenceMult;
    
    //how effective attributes and effects are against them. 1: does normal damage, 0: does nothing, negative: opposite effect
    public double stabResistance;
    public double slashResistance;
    public double crushResistance;
    public double fireResistance;
    public double windResistance;
    public double iceResistance;
    public double electricResistance;
    public double holyResistance;
    public double unholyResistance;
    public double energyResistance;
    public double magicResistance;
    public double explosionResistance;
    public double waterResistance;
    public double acidResistance;
    
    public int DDOTTick;
    public int DDOTTime;
    public int CDOTTick;
    public int CDOTTime;
    public double constantDOTTime;
    
    public double decreasingDOTResistance;
    public double constantDOTResistance;
    public double moveSpeedDownResistance;
    public double attackDelayResistance;
    public double vampiricResistance;
    public double defenceDownResistance;
    public double attackDownResistance;
    public double attackRangeDownResistance;
    public double attackSpeedDownResistance;
    public double confusionResistance;
    
    
    
    public double decreasingDOTDamage;
    public double constantDOTDamage;
    public double moveSpeedDownPercentage;
    public double moveSpeedDownPercentageRecoverRate;
    public double stunTime; // if > 0, this goes down instead of attackCoolDown
    public double defenceDownPercentage;
    public double defenceDownPercentageRecoverRate;
    public double attackDownPercentage;
    public double attackDownPercentageRecoverRate;
    public double attackRangeDownPercentage;
    public double attackRangeDownPercentageRecoverRate;
    public double attackSpeedDownPercentage;
    public double attackSpeedDownPercentageRecoverRate;
    
    
    
    public double confuseTime; // if > 0, can attack allies
    
    
    
    
    @Override
    public void paint(Graphics g) {
        //draw attackRange circle
        g.setColor(new Color(255,0,0,100));
        double aRange = attackRange * attackRangeDownPercentage;
        g.drawOval((int)(xPosition - aRange),(int)(yPosition - aRange),(int) aRange*2,(int) aRange*2);
        
        if (picture != null)
            g.drawImage(picture,(int)(xPosition - size),(int)(yPosition - size),(int) size*2,(int) size*2, null);
        else{
            g.setColor(Color.BLACK);
            g.drawOval((int)(xPosition - size),(int)(yPosition - size),(int) size*2,(int) size*2);
        }
        
        //if (health < maxHealth)
            paintHPBar(g);
        
        g.setColor(Color.BLACK);
        if (name != null){
            g.drawString(name, (int)(xPosition - 2*name.length()), (int) (yPosition - size - 16));
        }
        else{
            g.drawString("missing name", (int)(xPosition - 2*name.length()), (int) (yPosition - size - 16));
        }
        
    }
    
    @Override
    public void actionOnTick() {
        if (timeUntilAttack <= 0)
            attack();
        //handle internal affairs such as status effect recovery, ect...
        
        
        // auto heal
        health += healRate;
        if (health > maxHealth) health = maxHealth;
        
        
        //DDOTTick
        
        if (DDOTTime > 0)
            DDOTTime -= fieldPanel.GAME_TICK;
        else
            decreacingDOTTick();
        
        //CDOTTick
        
        if (CDOTTime > 0)
            CDOTTime -= fieldPanel.GAME_TICK;
        else
            constantDOTTick();
        
        
        //stun
        if (stunTime > 0)
            stunTime -= fieldPanel.GAME_TICK/1000.0;
        else{
            if (timeUntilAttack > 0)
                timeUntilAttack -= fieldPanel.GAME_TICK/1000.0;
        }
        
        
        //attackSpeed down
        if (attackSpeedDownPercentage < 1){
            attackSpeedDownPercentage += attackSpeedDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (attackSpeedDownPercentage > 1)
                attackSpeedDownPercentage = 1;
        }
        
        //attack down
        if (attackDownPercentage < 1){
            attackDownPercentage += attackDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (attackDownPercentage > 1)
                attackDownPercentage = 1;
        }
        
        //defence down
        if (defenceDownPercentage < 1){
            defenceDownPercentage += defenceDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (defenceDownPercentage > 1)
                defenceDownPercentage = 1;
        }
        
        //attackRange down
        if (attackRangeDownPercentage < 1){
            attackRangeDownPercentage += attackRangeDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (attackRangeDownPercentage > 1)
                attackRangeDownPercentage = 1;
        }
        
        //moveSpeedRange down
        if (moveSpeedDownPercentage < 1){
            moveSpeedDownPercentage += moveSpeedDownPercentageRecoverRate*fieldPanel.GAME_TICK/1000.0;
            if (moveSpeedDownPercentage > 1)
                moveSpeedDownPercentage = 1;
        }
        
        
        // other stat effects needed
        
        
        //confuse
        if (confuseTime > 0)
            confuseTime -= fieldPanel.GAME_TICK/1000.0;
        
        
        //death
        if (health <= 0)
            deathAction();
    }
    
    public void decreacingDOTTick(){
        DDOTTime += DDOTTick;
        if (decreasingDOTDamage == 0)
            return;
        takeBasicDamage(decreasingDOTDamage,HitSplatType.DECREASING_DOT);
        decreasingDOTDamage *= this.decreasingDOTResistance;
        if (decreasingDOTDamage < 0.05)
            decreasingDOTDamage = 0;
    }
    
    public void constantDOTTick(){
        CDOTTime += CDOTTick;
        
        if (constantDOTTime <= 0)
            return;
        constantDOTTime -= CDOTTick / 1000.0;
        takeBasicDamage(constantDOTDamage,HitSplatType.CONSTANT_DOT);
        
    }
    
    //sends out projectile(s) to opponents
    public void attack() {
        if (attacksPerCycle <= 0) return;
        double attacksLeft = attacksPerCycle;
        LinkedList<Agent> attackCandidates = getAgentsInAttackRange();
        
        if (!attackCandidates.isEmpty()){
            timeUntilAttack += attackCoolDown/attackSpeedDownPercentage;
        }
        else{
            return;
        }
        
        if (target != null && attackCandidates.contains(target) && confuseTime <= 0){
            attack(target);
            attacksLeft --;
            attackCandidates.remove(target);
        }
        
        while (attacksLeft >= 1 && !attackCandidates.isEmpty()){
            int index = (int)(Math.random() * attackCandidates.size());
            attack(attackCandidates.get(index));
            attackCandidates.remove(index);
            attacksLeft --;
        }
        
        //chance of hitting additional target
        if (attacksLeft > 0 && Math.random() < attacksLeft && !attackCandidates.isEmpty()){
            int index = (int)(Math.random() * attackCandidates.size());
            attack(attackCandidates.get(index));
            attackCandidates.remove(index);
            attacksLeft --;
        }
        
    }
    
    //sends one projectile to one agent
    public void attack(Agent a){
        AttackOption option = attackList.selectAttack();
        if (option == null) return;
        
        Projectile p = new Projectile(fieldPanel,this,a,option.attribute,option.effect,option.damage*attackDownPercentage,option.param1,option.param2);
        fieldPanel.addGameObject(p);
    }
    
    public abstract LinkedList<Agent> getAgentsInAttackRange();
    public abstract LinkedList<Agent> getAlliesInAttackRange();
    
    public void takeBasicDamage(double damage, HitSplatType type){// type: constant,decreasing DOT, or basic hit
        damage *= defenceMult/defenceDownPercentage;
        if (damage < 0){
            heal(-damage);
            return;
        }
        if (this.health - damage <= 0){
            makeHitSplat(this.health,type);
            health = 0;
            deathAction();
        }
        else{
            health -= damage;
            makeHitSplat(damage,type);
        }
    }
    
    public void heal(double health){
        if (health < 0){
            takeBasicDamage(-health,HitSplatType.BASIC);
            return;
        }
        if (this.health + health > maxHealth){
            makeHitSplat(maxHealth - this.health,HitSplatType.HEAL);
            this.health = maxHealth;
        }
        else{
            makeHitSplat(health,HitSplatType.HEAL);
            this.health += health;
        }
    }
    
    public void makeHitSplat(double number, HitSplatType type){
        if (type == HitSplatType.BASIC)
            fieldPanel.addGameObject(new DamageSplat(fieldPanel,number,this));
        else if (type == HitSplatType.DECREASING_DOT)
            fieldPanel.addGameObject(new DecreasingDOTSplat(fieldPanel,number,this));
        else if (type == HitSplatType.CONSTANT_DOT)
            fieldPanel.addGameObject(new ConstantDOTSplat(fieldPanel,number,this));
        else if (type == HitSplatType.HEAL)
            fieldPanel.addGameObject(new HealSplat(fieldPanel,number,this));
        else if (type == HitSplatType.XP)
            fieldPanel.addGameObject(new XpSplat(fieldPanel,number,this));
    }
    
    public void takeDecreasingDOTDamage(double damage){
        takeBasicDamage(damage,HitSplatType.DECREASING_DOT);
    }
    
    public void takeConstantDOTDamage(double damage){
        takeBasicDamage(damage,HitSplatType.CONSTANT_DOT);
    }
    
    public void takeStabDamage(double damage){
        takeBasicDamage(damage * stabResistance,HitSplatType.BASIC);
    }
    
    public void takeSlashDamage(double damage){
        takeBasicDamage(damage * slashResistance,HitSplatType.BASIC);
    }
    
    public void takeCrushDamage(double damage){
        takeBasicDamage(damage * crushResistance,HitSplatType.BASIC);
    }
    
    public void takeFireDamage(double damage){
        takeBasicDamage(damage * fireResistance,HitSplatType.BASIC);
    }
    
    public void takeWindDamage(double damage){
        takeBasicDamage(damage * windResistance,HitSplatType.BASIC);
    }
    
    public void takeIceDamage(double damage){
        takeBasicDamage(damage * iceResistance,HitSplatType.BASIC);
    }
    
    public void takeElectricDamage(double damage){
        takeBasicDamage(damage * electricResistance,HitSplatType.BASIC);
    }
    
    public void takeHolyDamage(double damage){
        takeBasicDamage(damage * holyResistance,HitSplatType.BASIC);
    }
    
    public void takeUnholyDamage(double damage){
        takeBasicDamage(damage * unholyResistance,HitSplatType.BASIC);
    }
    
    public void takeEnergyDamage(double damage){
        takeBasicDamage(damage * energyResistance,HitSplatType.BASIC);
    }
    
    public void takeMagicDamage(double damage){
        takeBasicDamage(damage * magicResistance,HitSplatType.BASIC);
    }
    
    public void takeExplosionDamage(double damage){
        takeBasicDamage(damage * explosionResistance,HitSplatType.BASIC);
    }
    
    public void takeWaterDamage(double damage){
        takeBasicDamage(damage * waterResistance,HitSplatType.BASIC);
    }
    
    public void takeAcidDamage(double damage){
        takeBasicDamage(damage * acidResistance,HitSplatType.BASIC);
    }
    
    //what the agent does when it dies
    public abstract void deathAction();
    
    //collision detection for blocks
    public void blockCollide(Block b){
        double xMin = b.xPosition;
        double xMax = b.xPosition + b.xSize;
        double yMin = b.yPosition;
        double yMax = b.yPosition + b.ySize;
        
        // if clear of block in a cardinal direction, do nothing
        if (xPosition + size < xMin) return;
        if (xPosition - size > xMax) return;
        if (yPosition + size < yMin) return;
        if (yPosition - size > yMax) return;
        
        
        
        
        int xRange = inRange(xPosition,xMin,xMax);
        int yRange = inRange(yPosition,yMin,yMax);
        // sides
        if (xRange == -1 && yRange == 0){//left
            xPosition = xMin - size;
            return;
        }
        if (xRange == 1 && yRange == 0){//right
            xPosition = xMax + size;
            return;
        }
        if (xRange == 0 && yRange == -1){//top
            yPosition = yMin - size;
            return;
        }
        if (xRange == 0 && yRange == 1){//bottom
            yPosition = yMax + size;
            return;
        }
        
        
        //diagonals
        
        double angle;
        
        if (xRange == -1 && yRange == -1 && Math.hypot(xMin - xPosition,yMin - yPosition) < size){//top left
            angle = Math.atan2(yMin - yPosition,xMin - xPosition);
            xPosition = xMin + size*Math.cos(angle);
            yPosition = yMin + size*Math.sin(angle);
            return;
        }
        
        if (xRange == 1 && yRange == -1 && Math.hypot(xPosition - xMax,yMin - yPosition) < size){//top right
            angle = Math.atan2(yMin - yPosition,xPosition - xMax);
            xPosition = xMin + size*Math.cos(angle);
            yPosition = yMin + size*Math.sin(angle);
            return;
        }
        
        if (xRange == -1 && yRange == 1 && Math.hypot(xMin - xPosition,yPosition - yMax) < size){//bottom left
            angle = Math.atan2(yPosition - yMax,xMin - xPosition);
            xPosition = xMin + size*Math.cos(angle);
            yPosition = yMin + size*Math.sin(angle);
            return;
        }
        
        if (xRange == 1 && yRange == 1 && Math.hypot(xPosition - xMax,yPosition - yMax) < size){//bottom right
            angle = Math.atan2(yPosition - yMax,xPosition - xMax);
            xPosition = xMin + size*Math.cos(angle);
            yPosition = yMin + size*Math.sin(angle);
            return;
        }
        // else inside block
    }
    
    //determines if agent is within attacking distance of another agent
    public boolean isInRange(Agent a){
        double distance = Math.hypot(xPosition - a.xPosition,yPosition - a.yPosition) - a.size;
        return (distance <= attackRange*attackRangeDownPercentage);
    }
    
    public int inRange(double p, double min, double max){
        if (p < min) return -1;
        if (p > max) return 1;
        return 0;
    }
    public abstract boolean canSee(Agent a);
    
    public boolean clearLineOfSight(Agent a, Block b){
        return true;////////////////
    }
    
    
    
    public void paintHPBar(Graphics g){
        int barXSize = 50;
        int barYSize = 10;
        
        g.setColor(Color.BLACK);
        g.fillRect((int)xPosition - barXSize/2,(int) (yPosition - size - barYSize - 3), barXSize, barYSize);
        g.setColor(Color.RED);
        g.fillRect((int)xPosition - barXSize/2 + 1,(int) (yPosition - size - barYSize - 3) + 1, barXSize-2, barYSize-2);
        int numGreenPixels = (int) ((barXSize - 2)*(health/maxHealth));
        g.setColor(Color.GREEN);
        g.fillRect((int)xPosition - barXSize/2 + 1,(int) (yPosition - size - barYSize - 3) + 1, numGreenPixels, barYSize-2);
        
        
        String hp = Stuff.getDecimalFormat(health).format(health) + "/" + Stuff.getDecimalFormat(maxHealth).format(maxHealth);
        g.setColor(Color.BLACK);
        g.setFont(Stuff.hpBarFont);
        g.drawString(hp, (int)(xPosition-(hp.length()*2)), (int) (yPosition - size -5));
        
    }
    
    public void applyEffect(Projectile p){
        double drain;
        switch(p.effect){
            case NONE:
            
            break;
            case DECREASING_DOT:
                decreasingDOTDamage += p.param1;
            break;
            case CONSTANT_DOT:
                
                constantDOTDamage = p.param1* getAttributeResistance(p);// attributeDefence?;
                constantDOTTime = p.param2 * constantDOTResistance;
            
            break;
            case MOVE_SPEED_DOWN:
                drain = moveSpeedDownPercentage*(1-p.param1)*moveSpeedDownResistance;
                //System.out.println(target.moveSpeedDownPercentage + " " + param1 + " " + target.moveSpeedDownResistance + " " + drain);
                moveSpeedDownPercentage -= drain;
                if (moveSpeedDownPercentage < 0)
                    moveSpeedDownPercentage = 0;
            break;
            case ATTACK_DELAY:
                double newStunTime = p.param1*attackDelayResistance;
                if (newStunTime > stunTime)
                    stunTime = newStunTime;
            break;
            case VAMPIRIC:
                p.owner.heal(p.damage * p.param1 * p.target.vampiricResistance);
                //System.out.println(p.damage + " " + p.param1 + " " + p.target.vampiricResistance);
            break;
            case DEFENCE_DOWN:
                drain = defenceDownPercentage*(1-p.param1)*defenceDownResistance;
                //System.out.println(target.defenceDownPercentage + " " + param1 + " " + target.defenceDownResistance + " " + drain);
                defenceDownPercentage -= drain;
                if (defenceDownPercentage < 0)
                    defenceDownPercentage = 0;
            break;
            case ATTACK_DOWN:
                drain = attackDownPercentage*(1-p.param1)*attackDownResistance;
                //System.out.println(target.attackDownPercentage + " " + param1 + " " + target.attackDownResistance + " " + drain);
                attackDownPercentage -= drain;
                if (attackDownPercentage < 0)
                    attackDownPercentage = 0;
            break;
            case ATTACK_RANGE_DOWN:
                drain = attackRangeDownPercentage*(1.0-p.param1)*attackRangeDownResistance;
                    //System.out.println(drain);
                    attackRangeDownPercentage -= drain;
                    if (attackRangeDownPercentage < 0)
                        attackRangeDownPercentage = 0;
            break;
            case ATTACK_SPEED_DOWN:
                drain = attackSpeedDownPercentage*(1.0-p.param1)*attackSpeedDownResistance;
                //System.out.println(target.attackSpeedDownPercentage + " " + param1 + " " + target.attackSpeedDownResistance + " " + drain);
                attackSpeedDownPercentage -= drain;
                if (attackSpeedDownPercentage < 0)
                    attackSpeedDownPercentage = 0;
            break;
            case SIGHT_RANGE_DOWN:
                sightRangeDown(p);
            break;
            case CONFUSION:
                confuseTime += p.param1*confusionResistance;
            break;
            case CHAIN_HIT:
                if (p.param1 <= 0)
                    return;
                LinkedList attackCandidates = getAlliesInAttackRange();
                if (attackCandidates.isEmpty())
                    return;
                int index = (int)(Math.random() * attackCandidates.size());
                Agent a = (Agent)attackCandidates.get(index);
                Projectile pr = new Projectile(fieldPanel,this,a,p.attribute,p.effect,p.damage*a.attackDownPercentage,p.param1-1,p.param2);
                fieldPanel.addGameObject(pr);
                
            break;
        }
    }
    
    public void sightRangeDown(Projectile p){
        
    }
    
    public double getAttributeResistance(Projectile p){
        if (null != p.attribute) switch (p.attribute) {
            case STAB:
                return stabResistance;
            case SLASH:
                return slashResistance;
            case CRUSH:
                return crushResistance;
            case FIRE:
                return fireResistance;
            case WIND:
                return windResistance;
            case ICE:
                return iceResistance;
            case ELECTRIC:
                return electricResistance;
            case HOLY:
                return holyResistance;
            case UNHOLY:
                return unholyResistance;
            case ENERGY:
                return energyResistance;
            case MAGIC:
                return magicResistance;
            case EXPLOSION:
                return explosionResistance;
            case WATER:
                return waterResistance;
            case ACID:
                return acidResistance;
            default:
                System.out.println("Something went wrong in the Agent class's getAttributeResistance method");
        }
        return 1;
    }
    
    public void applyDamageBasedOnAttribute(Projectile p){
        
        if (p.attribute != null) switch (p.attribute) {
            case STAB:
                takeStabDamage(p.damage);
                break;
            case SLASH:
                takeSlashDamage(p.damage);
                break;
            case CRUSH:
                takeCrushDamage(p.damage);
                break;
            case FIRE:
                takeFireDamage(p.damage);
                break;
            case WIND:
                takeWindDamage(p.damage);
                break;
            case ICE:
                takeIceDamage(p.damage);
                break;
            case ELECTRIC:
                takeElectricDamage(p.damage);
                break;
            case HOLY:
                takeHolyDamage(p.damage);
                break;
            case UNHOLY:
                takeUnholyDamage(p.damage);
                break;
            case ENERGY:
                takeEnergyDamage(p.damage);
                break;
            case MAGIC:
                takeMagicDamage(p.damage);
                break;
            case EXPLOSION:
                takeExplosionDamage(p.damage);
                break;
            case WATER:
                takeWaterDamage(p.damage);
                break;
            case ACID:
                takeAcidDamage(p.damage);
                break;
            default:
                break;
        }
    }
    
    
    
}
