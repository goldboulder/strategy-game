package strategygame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MenuPanel extends JPanel implements ActionListener{
    public StrategyGame frame;
    
    public int xSize = 1800;
    public int ySize = 1000;
    
    public JPanel titlePanel = new JPanel();
    public JPanel optionPanel = new JPanel();
    public JPanel builderPanel = new JPanel();
    public JPanel makePanel = new JPanel();
    public JPanel playPanel = new JPanel();
    public JPanel testPanel = new JPanel();
    public JLabel titleLabel = new JLabel("Strategy Game");
    public JLabel builderLabel = new JLabel("Select an option");
    public JLabel makeLabel = new JLabel("Make");
    public JTextField makeTextField = new JTextField();
    public JButton makeButton = new JButton("Go");
    public JLabel playLabel = new JLabel("Play");
    public JTextField playTextField = new JTextField();
    public JButton playButton = new JButton("Go");
    public JButton testButton = new JButton("Test");
    
    
    public MenuPanel(StrategyGame frame){
        this.frame = frame;
        
        testPanel.add(testButton);
        playPanel.add(playLabel);
        playPanel.add(playTextField);
        playPanel.add(playButton);
        makePanel.add(makeLabel);
        makePanel.add(makeTextField);
        makePanel.add(makeButton);
        builderPanel.add(builderLabel);
        optionPanel.add(builderPanel);
        optionPanel.add(makePanel);
        optionPanel.add(playPanel);
        optionPanel.add(testPanel);
        titlePanel.add(titleLabel);
        add(titlePanel);
        add(optionPanel);
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        optionPanel.setLayout(new BoxLayout(optionPanel,BoxLayout.Y_AXIS));
        makePanel.setLayout(new BoxLayout(makePanel,BoxLayout.X_AXIS));
        playPanel.setLayout(new BoxLayout(playPanel,BoxLayout.X_AXIS));
        makeTextField.setMaximumSize(new Dimension(100,16)); 
        makeTextField.setColumns(16);
        playTextField.setMaximumSize(new Dimension(100,16)); 
        playTextField.setColumns(16);
        
        testButton.addActionListener(this);
        testButton.setActionCommand("test");
        
        setVisible(true);
    }
    
    
    
    
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("test")){
            GamePanel gamePanel = new GamePanel(frame);
            gamePanel.fieldPanel.loadTestLevel();
            frame.setContentPane(gamePanel);
            frame.getContentPane().revalidate();
            frame.pack();
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(xSize,ySize);
    }
    
    
}
