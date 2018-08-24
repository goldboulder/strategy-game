package strategygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class GamePanel extends JPanel implements ActionListener{
    public StrategyGame frame;
    
    public Player focusPlayer;
    
    public int xSize = 1800;
    public int ySize = 1000;
    public int statPanelXSize = 350;
    public int basicStatPanelYSize = 200;
    public int HPBarXSize = 150;
    public int HPBarYSize = 20;
    public int XPBarXSize = 150;
    public int XPBarYSize = 20;
    
    public double HPIncreace = 10;
    public double moveSpeedIncreace = 1;
    public double hitMultipleIncreace = 0.04;
    public double healRateIncreace = 0.01;
    public double attackSpeedIncreace = 0.04;
    public double generalDefenceIncreace = 0.04;
    public double attributeAttackIncreace = 0.1;
    public double attributeDefenceIncreace = 0.1;
    
    
    public JPanel statPanel = new JPanel();
    public FieldPanel fieldPanel = new FieldPanel(frame,this);
    
    public JPanel basicStatPanel = new JPanel();
    public JPanel specialStatPanel = new JPanel();
    
    public JPanel HPPanel = new JPanel();
    public JPanel XPPanel = new JPanel();
    public JPanel moveSpeedAttackRangePanel = new JPanel();
    public JPanel hitMultipleHealRatePanel = new JPanel();
    public JPanel attackTitlePanel = new JPanel();
    public JPanel defenceTitlePanel = new JPanel();
    public JPanel attackSpeedPanel = new JPanel();
    public JPanel generalDefencePanel = new JPanel();
    
    public JPanel stabAttackPanel = new JPanel();
    public JPanel slashAttackPanel = new JPanel();
    public JPanel crushAttackPanel = new JPanel();
    public JPanel fireAttackPanel = new JPanel();
    public JPanel windAttackPanel = new JPanel();
    public JPanel iceAttackPanel = new JPanel();
    public JPanel electricAttackPanel = new JPanel();
    public JPanel holyAttackPanel = new JPanel();
    public JPanel unholyAttackPanel = new JPanel();
    public JPanel energyAttackPanel = new JPanel();
    public JPanel magicAttackPanel = new JPanel();
    public JPanel explosionAttackPanel = new JPanel();
    public JPanel waterAttackPanel = new JPanel();
    public JPanel acidAttackPanel = new JPanel();
    
    public JPanel stabDefencePanel = new JPanel();
    public JPanel slashDefencePanel = new JPanel();
    public JPanel crushDefencePanel = new JPanel();
    public JPanel fireDefencePanel = new JPanel();
    public JPanel windDefencePanel = new JPanel();
    public JPanel iceDefencePanel = new JPanel();
    public JPanel electricDefencePanel = new JPanel();
    public JPanel holyDefencePanel = new JPanel();
    public JPanel unholyDefencePanel = new JPanel();
    public JPanel energyDefencePanel = new JPanel();
    public JPanel magicDefencePanel = new JPanel();
    public JPanel explosionDefencePanel = new JPanel();
    public JPanel waterDefencePanel = new JPanel();
    public JPanel acidDefencePanel = new JPanel();
    
    public JLabel HPLabel = new JLabel("HP");
    public BarPanel HPBarPanel = new BarPanel(this,focusPlayer,HPBarXSize,HPBarYSize,0,0,new Color(0,200,0),new Color(255,0,0),Color.WHITE,new Font("Arial",Font.BOLD,15));
    public JButton HPButton = new JButton("+ " + HPIncreace);
    public JLabel levelLabel = new JLabel("Level: " + "1  " + "XP");
    public BarPanel XPBarPanel = new BarPanel(this,focusPlayer,XPBarXSize,XPBarYSize,0,0,new Color(0,200,200),new Color(180,180,180),Color.BLACK,new Font("Arial",Font.BOLD,15));
    public JLabel levelPointsLabel = new JLabel("Points: " + "0");
    public JLabel moveSpeedLabel = new JLabel("Movement Speed: " + "0");
    public JButton moveSpeedButton = new JButton("+ " + moveSpeedIncreace);
    public JLabel attackRangeLabel = new JLabel("Attack Range: " + "0");
    public JButton attackRangeButton = new JButton("+ " + moveSpeedIncreace);
    public JLabel hitMultipleLabel = new JLabel("Hit Multiple: " + "0");
    public JButton hitMultipleButton = new JButton("+ " + hitMultipleIncreace);
    public JLabel healRateLabel = new JLabel("Heal Rate: " + "0");
    public JButton healRateButton = new JButton("+ " + healRateIncreace);
    
    public JLabel attackTitleLabel = new JLabel("Attack");
    public JLabel defenceTitleLabel = new JLabel("Defence");
    public JLabel attackSpeedLabel = new JLabel ("Attack Speed: " + "0");
    public JButton attackSpeedButton = new JButton("+ " + attackSpeedIncreace);
    public JLabel generalDefenceLabel = new JLabel ("Gen. Def.: " + "0");
    public JButton generalDefenceButton = new JButton("+ " + generalDefenceIncreace);
    
    public JLabel stabAttackLabel = new JLabel ("Stab: " + "0");
    public JRadioButton stabAttackRadioButton = new JRadioButton();
    public JButton stabAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel stabDefenceLabel = new JLabel("Stab: " + "0");
    public JButton stabDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel slashAttackLabel = new JLabel ("Slash: " + "0");
    public JRadioButton slashAttackRadioButton = new JRadioButton();
    public JButton slashAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel slashDefenceLabel = new JLabel("Slash: " + "0");
    public JButton slashDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel crushAttackLabel = new JLabel ("Crush: " + "0");
    public JRadioButton crushAttackRadioButton = new JRadioButton();
    public JButton crushAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel crushDefenceLabel = new JLabel("Crush: " + "0");
    public JButton crushDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel fireAttackLabel = new JLabel ("Fire: " + "0");
    public JRadioButton fireAttackRadioButton = new JRadioButton();
    public JButton fireAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel fireDefenceLabel = new JLabel("Fire: " + "0");
    public JButton fireDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel windAttackLabel = new JLabel ("Wind: " + "0");
    public JRadioButton windAttackRadioButton = new JRadioButton();
    public JButton windAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel windDefenceLabel = new JLabel("Wind: " + "0");
    public JButton windDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel iceAttackLabel = new JLabel ("Ice: " + "0");
    public JRadioButton iceAttackRadioButton = new JRadioButton();
    public JButton iceAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel iceDefenceLabel = new JLabel("Ice: " + "0");
    public JButton iceDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel electricAttackLabel = new JLabel ("Electric: " + "0");
    public JRadioButton electricAttackRadioButton = new JRadioButton();
    public JButton electricAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel electricDefenceLabel = new JLabel("Electric: " + "0");
    public JButton electricDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel holyAttackLabel = new JLabel ("Holy: " + "0");
    public JRadioButton holyAttackRadioButton = new JRadioButton();
    public JButton holyAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel holyDefenceLabel = new JLabel("Holy: " + "0");
    public JButton holyDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel unholyAttackLabel = new JLabel ("Unholy: " + "0");
    public JRadioButton unholyAttackRadioButton = new JRadioButton();
    public JButton unholyAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel unholyDefenceLabel = new JLabel("Unholy: " + "0");
    public JButton unholyDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel energyAttackLabel = new JLabel ("Energy: " + "0");
    public JRadioButton energyAttackRadioButton = new JRadioButton();
    public JButton energyAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel energyDefenceLabel = new JLabel("Energy: " + "0");
    public JButton energyDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel magicAttackLabel = new JLabel ("Magic: " + "0");
    public JRadioButton magicAttackRadioButton = new JRadioButton();
    public JButton magicAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel magicDefenceLabel = new JLabel("Magic: " + "0");
    public JButton magicDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel explosionAttackLabel = new JLabel ("Explosion: " + "0");
    public JRadioButton explosionAttackRadioButton = new JRadioButton();
    public JButton explosionAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel explosionDefenceLabel = new JLabel("Explosion: " + "0");
    public JButton explosionDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel waterAttackLabel = new JLabel ("Water: " + "0");
    public JRadioButton waterAttackRadioButton = new JRadioButton();
    public JButton waterAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel waterDefenceLabel = new JLabel("Water: " + "0");
    public JButton waterDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    public JLabel acidAttackLabel = new JLabel ("Acid: " + "0");
    public JRadioButton acidAttackRadioButton = new JRadioButton();
    public JButton acidAttackButton = new JButton("+ " + attributeAttackIncreace);
    public JLabel acidDefenceLabel = new JLabel("Acid: " + "0");
    public JButton acidDefenceButton = new JButton("+ " + attributeDefenceIncreace);
    
    ButtonGroup attackButtonGroup = new ButtonGroup();
    
    
    
    //radioButtons!
    public GamePanel(StrategyGame frame){
        this.frame = frame;
        
        attackButtonGroup.add(stabAttackRadioButton);
        attackButtonGroup.add(slashAttackRadioButton);
        attackButtonGroup.add(crushAttackRadioButton);
        attackButtonGroup.add(fireAttackRadioButton);
        attackButtonGroup.add(windAttackRadioButton);
        attackButtonGroup.add(iceAttackRadioButton);
        attackButtonGroup.add(electricAttackRadioButton);
        attackButtonGroup.add(holyAttackRadioButton);
        attackButtonGroup.add(unholyAttackRadioButton);
        attackButtonGroup.add(energyAttackRadioButton);
        attackButtonGroup.add(magicAttackRadioButton);
        attackButtonGroup.add(explosionAttackRadioButton);
        attackButtonGroup.add(waterAttackRadioButton);
        attackButtonGroup.add(acidAttackRadioButton);
        
        
        HPPanel.add(HPLabel);
        HPPanel.add(HPBarPanel);
        HPPanel.add(HPButton);
        XPPanel.add(levelLabel);
        XPPanel.add(XPBarPanel);
        XPPanel.add(levelPointsLabel);
        moveSpeedAttackRangePanel.add(moveSpeedLabel);
        moveSpeedAttackRangePanel.add(moveSpeedButton);
        moveSpeedAttackRangePanel.add(attackRangeLabel);
        moveSpeedAttackRangePanel.add(attackRangeButton);
        hitMultipleHealRatePanel.add(hitMultipleLabel);
        hitMultipleHealRatePanel.add(hitMultipleButton);
        hitMultipleHealRatePanel.add(healRateLabel);
        hitMultipleHealRatePanel.add(healRateButton);
        
        attackTitlePanel.add(attackTitleLabel);
        defenceTitlePanel.add(defenceTitleLabel);
        attackSpeedPanel.add(attackSpeedLabel);
        attackSpeedPanel.add(attackSpeedButton);
        generalDefencePanel.add(generalDefenceLabel);
        generalDefencePanel.add(generalDefenceButton);
        
        stabAttackPanel.add(stabAttackLabel);
        stabAttackPanel.add(stabAttackRadioButton);
        stabAttackPanel.add(stabAttackButton);
        stabDefencePanel.add(stabDefenceLabel);
        stabDefencePanel.add(stabDefenceButton);
        
        slashAttackPanel.add(slashAttackLabel);
        slashAttackPanel.add(slashAttackRadioButton);
        slashAttackPanel.add(slashAttackButton);
        slashDefencePanel.add(slashDefenceLabel);
        slashDefencePanel.add(slashDefenceButton);
        
        crushAttackPanel.add(crushAttackLabel);
        crushAttackPanel.add(crushAttackRadioButton);
        crushAttackPanel.add(crushAttackButton);
        crushDefencePanel.add(crushDefenceLabel);
        crushDefencePanel.add(crushDefenceButton);
        
        fireAttackPanel.add(fireAttackLabel);
        fireAttackPanel.add(fireAttackRadioButton);
        fireAttackPanel.add(fireAttackButton);
        fireDefencePanel.add(fireDefenceLabel);
        fireDefencePanel.add(fireDefenceButton);
        
        windAttackPanel.add(windAttackLabel);
        windAttackPanel.add(windAttackRadioButton);
        windAttackPanel.add(windAttackButton);
        windDefencePanel.add(windDefenceLabel);
        windDefencePanel.add(windDefenceButton);
        
        iceAttackPanel.add(iceAttackLabel);
        iceAttackPanel.add(iceAttackRadioButton);
        iceAttackPanel.add(iceAttackButton);
        iceDefencePanel.add(iceDefenceLabel);
        iceDefencePanel.add(iceDefenceButton);
        
        electricAttackPanel.add(electricAttackLabel);
        electricAttackPanel.add(electricAttackRadioButton);
        electricAttackPanel.add(electricAttackButton);
        electricDefencePanel.add(electricDefenceLabel);
        electricDefencePanel.add(electricDefenceButton);
        
        holyAttackPanel.add(holyAttackLabel);
        holyAttackPanel.add(holyAttackRadioButton);
        holyAttackPanel.add(holyAttackButton);
        holyDefencePanel.add(holyDefenceLabel);
        holyDefencePanel.add(holyDefenceButton);
        
        unholyAttackPanel.add(unholyAttackLabel);
        unholyAttackPanel.add(unholyAttackRadioButton);
        unholyAttackPanel.add(unholyAttackButton);
        unholyDefencePanel.add(unholyDefenceLabel);
        unholyDefencePanel.add(unholyDefenceButton);
        
        energyAttackPanel.add(energyAttackLabel);
        energyAttackPanel.add(energyAttackRadioButton);
        energyAttackPanel.add(energyAttackButton);
        energyDefencePanel.add(energyDefenceLabel);
        energyDefencePanel.add(energyDefenceButton);
        
        magicAttackPanel.add(magicAttackLabel);
        magicAttackPanel.add(magicAttackRadioButton);
        magicAttackPanel.add(magicAttackButton);
        magicDefencePanel.add(magicDefenceLabel);
        magicDefencePanel.add(magicDefenceButton);
        
        explosionAttackPanel.add(explosionAttackLabel);
        explosionAttackPanel.add(explosionAttackRadioButton);
        explosionAttackPanel.add(explosionAttackButton);
        explosionDefencePanel.add(explosionDefenceLabel);
        explosionDefencePanel.add(explosionDefenceButton);
        
        waterAttackPanel.add(waterAttackLabel);
        waterAttackPanel.add(waterAttackRadioButton);
        waterAttackPanel.add(waterAttackButton);
        waterDefencePanel.add(waterDefenceLabel);
        waterDefencePanel.add(waterDefenceButton);
        
        acidAttackPanel.add(acidAttackLabel);
        acidAttackPanel.add(acidAttackRadioButton);
        acidAttackPanel.add(acidAttackButton);
        acidDefencePanel.add(acidDefenceLabel);
        acidDefencePanel.add(acidDefenceButton);
        
        
        basicStatPanel.add(HPPanel);
        basicStatPanel.add(XPPanel);
        basicStatPanel.add(moveSpeedAttackRangePanel);
        basicStatPanel.add(hitMultipleHealRatePanel);
        
        specialStatPanel.add(attackTitlePanel);
        specialStatPanel.add(defenceTitlePanel);
        specialStatPanel.add(attackSpeedPanel);
        specialStatPanel.add(generalDefencePanel);
        
        specialStatPanel.add(stabAttackPanel);
        specialStatPanel.add(stabDefencePanel);
        specialStatPanel.add(slashAttackPanel);
        specialStatPanel.add(slashDefencePanel);
        specialStatPanel.add(crushAttackPanel);
        specialStatPanel.add(crushDefencePanel);
        specialStatPanel.add(fireAttackPanel);
        specialStatPanel.add(fireDefencePanel);
        specialStatPanel.add(windAttackPanel);
        specialStatPanel.add(windDefencePanel);
        specialStatPanel.add(iceAttackPanel);
        specialStatPanel.add(iceDefencePanel);
        specialStatPanel.add(electricAttackPanel);
        specialStatPanel.add(electricDefencePanel);
        specialStatPanel.add(holyAttackPanel);
        specialStatPanel.add(holyDefencePanel);
        specialStatPanel.add(unholyAttackPanel);
        specialStatPanel.add(unholyDefencePanel);
        specialStatPanel.add(energyAttackPanel);
        specialStatPanel.add(energyDefencePanel);
        specialStatPanel.add(magicAttackPanel);
        specialStatPanel.add(magicDefencePanel);
        specialStatPanel.add(explosionAttackPanel);
        specialStatPanel.add(explosionDefencePanel);
        specialStatPanel.add(waterAttackPanel);
        specialStatPanel.add(waterDefencePanel);
        specialStatPanel.add(acidAttackPanel);
        specialStatPanel.add(acidDefencePanel);
        
        statPanel.add(basicStatPanel);
        statPanel.add(specialStatPanel);
        
        add(statPanel);
        add(fieldPanel);
        
        //format
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        statPanel.setLayout(new BoxLayout(statPanel,BoxLayout.Y_AXIS));
        basicStatPanel.setLayout(new BoxLayout(basicStatPanel,BoxLayout.Y_AXIS));
        specialStatPanel.setLayout(new GridLayout(16,2));
        statPanel.setPreferredSize(new Dimension(statPanelXSize,ySize));
        statPanel.setMaximumSize(new Dimension(statPanelXSize,ySize));
        basicStatPanel.setPreferredSize(new Dimension(statPanelXSize,basicStatPanelYSize));
        basicStatPanel.setMaximumSize(new Dimension(statPanelXSize,basicStatPanelYSize));
        HPBarPanel.setPreferredSize(new Dimension(HPBarXSize,HPBarYSize));
        HPBarPanel.setMaximumSize(new Dimension(HPBarXSize,HPBarYSize));
        XPBarPanel.setPreferredSize(new Dimension(XPBarXSize,XPBarYSize));
        XPBarPanel.setMaximumSize(new Dimension(XPBarXSize,XPBarYSize));
        stabAttackRadioButton.doClick();
        HPBarPanel.setBackground(new Color(80,80,80));
        XPBarPanel.setBackground(new Color(80,80,80));
        HPBarPanel.setForeground(Color.WHITE);
        XPBarPanel.setForeground(Color.WHITE);
        
        
        stabAttackRadioButton.addActionListener(this);
        stabAttackRadioButton.setActionCommand("stab");
        slashAttackRadioButton.addActionListener(this);
        slashAttackRadioButton.setActionCommand("slash");
        crushAttackRadioButton.addActionListener(this);
        crushAttackRadioButton.setActionCommand("crush");
        fireAttackRadioButton.addActionListener(this);
        fireAttackRadioButton.setActionCommand("fire");
        windAttackRadioButton.addActionListener(this);
        windAttackRadioButton.setActionCommand("wind");
        iceAttackRadioButton.addActionListener(this);
        iceAttackRadioButton.setActionCommand("ice");
        electricAttackRadioButton.addActionListener(this);
        electricAttackRadioButton.setActionCommand("electric");
        holyAttackRadioButton.addActionListener(this);
        holyAttackRadioButton.setActionCommand("holy");
        unholyAttackRadioButton.addActionListener(this);
        unholyAttackRadioButton.setActionCommand("unholy");
        energyAttackRadioButton.addActionListener(this);
        energyAttackRadioButton.setActionCommand("energy");
        magicAttackRadioButton.addActionListener(this);
        magicAttackRadioButton.setActionCommand("magic");
        explosionAttackRadioButton.addActionListener(this);
        explosionAttackRadioButton.setActionCommand("explosion");
        waterAttackRadioButton.addActionListener(this);
        waterAttackRadioButton.setActionCommand("water");
        acidAttackRadioButton.addActionListener(this);
        acidAttackRadioButton.setActionCommand("acid");
        
        setVisible(true);
    }
    
    
    
    
    public void update(Player p){
        
        if (focusPlayer == p){
            HPBarPanel.update(p.health,p.maxHealth);
            XPBarPanel.update(p.currentXPForLevel(),p.XPForNextLevel());
            levelLabel.setText("Level: " + p.getLevel());
            levelPointsLabel.setText("Points: " + (p.getLevelPointsAvailable()));
            repaint();
            frame.getContentPane().revalidate();
        }
        
    }
    
    public void grantFocus(Player p){
        focusPlayer = p;
        HPBarPanel.player = p;
        XPBarPanel.player = p;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.HPBarPanel.paintComponent(g);
        this.XPBarPanel.paintComponent(g);
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(xSize,ySize);
    }
    
}
