
package strategygame;


public class AttackOption {
    public Attribute attribute;
    public StatEffect effect;
    public double damage;
    public double weight;
    public double param1;
    public double param2;
    
    
    public AttackOption(Attribute attribute, StatEffect effect,double damage, double weight, double param1, double param2){
        this.attribute = attribute;
        this.effect = effect;
        this.damage = damage;
        this.weight = weight;
        this.param1 = param1;
        this.param2 = param2;
    }
}
