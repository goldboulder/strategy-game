package strategygame;

import java.awt.Graphics;

public abstract class Item extends GameObject{
    
    public double size;
    
    public void actionWhenCollected(Player p){
        delete();
    }
    
}
