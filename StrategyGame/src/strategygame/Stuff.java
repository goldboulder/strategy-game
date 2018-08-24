
package strategygame;

import java.awt.Font;
import java.text.DecimalFormat;

public class Stuff{
    
    public static DecimalFormat decFormat = new DecimalFormat("##.##");
    public static DecimalFormat intFormat = new DecimalFormat("###,###,###,###");
    public static Font hitSplatFont = new Font("Arial",Font.BOLD,13);
    public static Font hpBarFont = new Font("Arial",Font.BOLD,9);
    public static DecimalFormat getDecimalFormat(double number){
        if (Math.abs(number) < 1)
            return decFormat;
        else
            return intFormat;
    }
    
    public static double round(double num, double roundee){
        return Math.rint(num/roundee) * roundee;
    }
    
}

