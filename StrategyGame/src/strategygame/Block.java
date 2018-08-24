package strategygame;

import java.awt.Graphics;


public class Block extends GameObject{
    // official block position at top left
    public double xSize;
    public double ySize;
    
    public Block(double xPosition, double yPosition, double xSize, double ySize){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect((int)xPosition, (int)yPosition, (int)xSize, (int)ySize);
    }

    @Override
    public void actionOnTick() {
        for (int i = 0; i < fieldPanel.enemyList.size(); i++){
                fieldPanel.enemyList.get(i).blockCollide(this);
            }
            for (int i = 0; i < fieldPanel.playerList.size(); i++){
                fieldPanel.playerList.get(i).blockCollide(this);
            }
    }

    @Override
    public boolean isActive() {
        return fieldPanel.blockList.contains(this);
    }

    
    
    
    
    
}
