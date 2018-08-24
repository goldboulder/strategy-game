
package strategygame;


public class XpHandler {
    private double xp = 0;
    
    // what is displayed on the left of the interface's xp bar
    public double currentXPForLevel() {
        return xp - levelToMinTotalXP(totalXPToLevel(xp)-1);
    }

    // what is displayed on the right of the interface's xp bar
    public double XPForNextLevel() {
        return 9 + totalXPToLevel(xp);
    }
    
    public int totalXPToLevel(double xp){
        return (int) (Math.sqrt(90.25+2*xp) - 8.5);
    }
    
    private double levelToMinTotalXP(int level){
        return level * (level + 19) / 2;
    }
    
    public void addXp(double xp){
        this.xp += xp;
    }

    
    public double getXp() {
        return xp;
    }

    
    public void setXp(double xp) {
        this.xp = xp;
    }
}
