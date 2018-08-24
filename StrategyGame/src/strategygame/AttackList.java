
package strategygame;

import java.util.ArrayList;


public class AttackList {
    public ArrayList<AttackOption> choices;
    
    public AttackList(ArrayList<AttackOption> choices){
        this.choices = choices;
    }
    
    public AttackList(){
        choices = new ArrayList<>();
    }
    
    public void add(AttackOption a){
        choices.add(a);
    }
    
    public void delete(AttackOption a){
        choices.remove(a);
    }
    public void empty(){
        choices.clear();
    }
    public int size(){
        return choices.size();
    }
    
    public AttackOption selectAttack(){
        if (choices.isEmpty()) return null;
        
        double totalWeight = 0;
        for (int i = 0; i < choices.size(); i++){
            totalWeight += choices.get(i).weight;
        }
        double randomWeight = Math.random() * totalWeight;
        int index = -1;
        while (randomWeight > 0){
            index ++;
            randomWeight -= choices.get(index).weight;
        }
        
        return choices.get(index);
    }
    
    
}
