package strategygame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FieldPanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    public StrategyGame frame;
    public GamePanel parent;
    
    public int xSize = 1500;
    public int ySize = 1000;
    
    
    public static final int GAME_TICK = 20;
    public Timer clock;
    
    
    public boolean upArrowPressed;
    public boolean leftArrowPressed;
    public boolean rightArrowPressed;
    public boolean downArrowPressed;
    public boolean mousePressed;
    
    public int mouseXPosition;
    public int mouseYPosition;
    
    public ArrayList<Enemy> enemyList;
    public ArrayList<Player> playerList;
    public ArrayList<HitSplat> hitSplatList;
    public ArrayList<Item> itemList;
    public ArrayList<Projectile> projectileList;
    public ArrayList<Block> blockList;
    
    public double gXPosition = 0;
    public double gYPosition = 0;
    public BufferedImage background;
    
    public FieldPanel(StrategyGame frame,GamePanel parent){
        setFocusable(true);
        requestFocusInWindow();
        
        this.frame = frame;
        this.parent = (GamePanel) parent;
        
        clock = new Timer(GAME_TICK,this);
        clock.setActionCommand("tick");
        clock.start();
        
        enemyList = new ArrayList<>();
        playerList = new ArrayList<>();
        hitSplatList = new ArrayList<>();
        itemList = new ArrayList<>();
        projectileList = new ArrayList<>();
        blockList = new ArrayList<>();
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    public void loadLevel(String levelName){
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.clearRect(-1000000, -1000000,2000000,2000000);
        centerCameraOnPlayer(g);
        drawBackground(g);
        
        
        
        for (int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).paint(g);
        }
        for (int i = 0; i < playerList.size(); i++){
            playerList.get(i).paint(g);
        }
        for (int i = 0; i < hitSplatList.size(); i++){
            hitSplatList.get(i).paint(g);
        }
        for (int i = 0; i < itemList.size(); i++){
            itemList.get(i).paint(g);
        }
        for (int i = 0; i < projectileList.size(); i++){
            projectileList.get(i).paint(g);
        }
        for (int i = 0; i < blockList.size(); i++){
            blockList.get(i).paint(g);
        }
    }
    
    private void centerCameraOnPlayer(Graphics g){
        if (parent.focusPlayer != null){
            gXPosition = parent.focusPlayer.xPosition - xSize/2;
            gYPosition = parent.focusPlayer.yPosition - ySize/2;
        }
        else{
            gXPosition = 0;
            gYPosition = 0;
        }
        g.translate((int) -gXPosition, (int) -gYPosition);
    }
    
    public void drawBackground(Graphics g){
        if (background != null){
            drawTiledBackground(g);
        }
        else
            drawGridBackground(g);
    }
    
    public void drawTiledBackground(Graphics g){
        
    }
    
    public void drawGridBackground(Graphics g){
        g.setColor(new Color(0,0,0,40));
        
        
        
        //draw horizontal lines
        int y = (int)gYPosition;
        y = (int) Stuff.round(y,100);
        while (y < gYPosition + ySize){
            g.drawLine((int)gXPosition, y, (int) (gXPosition + xSize), y);
            y += 100;
        }
        
        
        
        //draw vertical lines
        int x = (int)gXPosition;
        x = (int) Stuff.round(x,100);
        while (x < gXPosition + xSize){
            g.drawLine(x, (int)gYPosition, x, (int) (gYPosition + ySize));
            x += 100;
        }
        
    }
    
    private int worldXToScreenX(){
        return 0;
    }
    
    private int worldYToScreenY(){
        return 0;
    }
    
    private int screenXToWorldX(){
        return 0;
    }
    
    private int screenYToWorldY(){
        return 0;
    }
    
    public void addGameObject(GameObject g){
        if (g instanceof Player){
            playerList.add((Player) g);
        }
        else if (g instanceof Enemy){
            enemyList.add((Enemy) g);
        }
        else if (g instanceof HitSplat){
            hitSplatList.add((HitSplat) g);
        }
        else if (g instanceof Item){
            itemList.add((Item) g);
        }
        else if (g instanceof Projectile){
            projectileList.add((Projectile) g);
        }
    }
    
    public void loadTestLevel(){
        Player p = new Player(this,500,500);
        addGameObject(p);
        p.requestFocus();
        
        addGameObject(new Enemy("Test Monster",this,600,500));
        addGameObject(new Enemy("Test Monster",this,500,400));
        addGameObject(new Enemy("Test Monster",this,400,400));
        addGameObject(new Enemy("Test Monster",this,300,400));
        addGameObject(new Enemy("Test Monster",this,200,400));
        addGameObject(new Enemy("Test Monster",this,100,400));
        addGameObject(new Enemy("Test Monster",this,0,400));
    }
    
    public void delete(GameObject g){
        if (g instanceof Player){
            playerList.remove((Player) g);
        }
        else if (g instanceof Enemy){
            enemyList.remove((Enemy) g);
        }
        else if (g instanceof HitSplat){
            hitSplatList.remove((HitSplat) g);
        }
        else if (g instanceof Item){
            itemList.remove((Item) g);
        }
        else if (g instanceof Projectile){
            projectileList.remove((Projectile) g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("tick")){
            tick();
            repaint();
        }
    }
    
    public void tick(){
        for (int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).actionOnTick();
        }
        for (int i = 0; i < playerList.size(); i++){
            playerList.get(i).actionOnTick();
        }
        for (int i = 0; i < hitSplatList.size(); i++){
            hitSplatList.get(i).actionOnTick();
        }
        for (int i = 0; i < itemList.size(); i++){
            itemList.get(i).actionOnTick();
        }
        for (int i = 0; i < projectileList.size(); i++){
            projectileList.get(i).actionOnTick();
        }
        for (int i = 0; i < blockList.size(); i++){
            blockList.get(i).actionOnTick();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            upArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            leftArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            rightArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            downArrowPressed = true;
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            upArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            leftArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            rightArrowPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            downArrowPressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
