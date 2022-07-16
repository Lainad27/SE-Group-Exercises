package gameLogic;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameInputs.MouseListener;




public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1300; // Board and Tile Size
	public static final int HEIGHT = WIDTH * 11/20;
	public static final int HEADER = WIDTH * 1/20;
	public static final int XTILES = 10;
	public static final int YTILES = 10;
	public static final int TILE_WIDTH = (WIDTH/XTILES)/2;
	public static final int TILE_HEIGHT = (HEIGHT-HEADER)/YTILES;
	public static final BufferedImage backgroundImage = loadImages(1); // Load Images
	public static final BufferedImage battleShipImage = loadImages(2);
	public static final BufferedImage XmarkImage = loadImages(3);
	public static final BufferedImage explosionImage = loadImages(4);
	public static final BufferedImage battleShipRotatedImage = loadImages(5);
	public static final BufferedImage youWinImage = loadImages(6);
	public static final BufferedImage youLoseImage = loadImages(7);
	public boolean running = false; 
	private Thread gameThread;
	public static GAME_STATE state = GAME_STATE.START_SCREEN;
	private HumanPlayer human = new HumanPlayer();
	private ComputerPlayer computer = new ComputerPlayer();
	private boolean computerTurn = false;
	private boolean computerPickedLocations = false;
	private boolean humanWon;

	public static BufferedImage loadImages(int index) { // function that loads images into static final variables
		try {
			switch (index) {
			case 1:
				return ImageIO.read(new File("src/image.jpg"));
			case 2:
				return ImageIO.read(new File("src/BattleShip.PNG"));
			case 3:
				return ImageIO.read(new File("src/Xmark.png"));
			case 4:
				return ImageIO.read(new File("src/explosion.png"));
			case 5:
				return ImageIO.read(new File("src/BattleShipRotated.png"));
			case 6:
				return ImageIO.read(new File("src/youWin.jpg"));
			case 7:
				return ImageIO.read(new File("src/youLose.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 

		
	}



	
	public Game() { // init game
		canvasSetup();
		new Window("BattleShips", this);
		this.addMouseListener(new MouseListener(this));
	}
	public void canvasSetup() {	// init canvas
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
	}

	public void locationPressed (int Xclick, int Yclick, int clickType) { // this function is called when a player clicks on the screen
		switch (state) {
		case START_SCREEN:
			state = GAME_STATE.SHIP_CHOICE;
			break;
		case SHIP_CHOICE:
			if (clickType==3) {
				human.setChosenShipRotated(!human.getChosenShipRotated());
				return;
			}
			human.pickShipLocation(Xclick, Yclick);
			if (Xclick > 4*Game.WIDTH/6 && Yclick > Game.HEIGHT-2*Game.HEADER && human.shipsInPosition())
				state = GAME_STATE.GAME;
			break;
		case GAME:
			if (computerTurn)
				return;
			int[] hitCords = human.hit(Xclick, Yclick);
			if (hitCords==null)
				return;
			boolean didHit = computer.gotShot(hitCords[0], hitCords[1]);
			human.registerHit(hitCords[0], hitCords[1], didHit);
			if (computer.didLose()) {
				state = GAME_STATE.AFTER_GAME;
				humanWon = true;
			}
			computerTurn=true;
			break;
		case AFTER_GAME:
			break;
		}
	}
	
	
	public void start() { // game start
		gameThread = new Thread(this);
		gameThread.start();
		running = true;
	}
	
	
	public void stop() { // game stop
		try {
			gameThread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() { // classic run fucntion that updates, draws and displays FPS
		long lastTime = System.nanoTime();
		double amountOFTicks = 60.0;
		double ns = 1000000000 / amountOFTicks;
		double delta = 0;
		long timer = System.currentTimeMillis(); 
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				delta--;
			}
			if(running)
				draw();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("State: " + state);
				frames = 0;
			}
		}
		stop();
	}
	
	
	
	
	public void draw() { //draw graphics
		// Initialize drawing tools
		BufferStrategy buffer = this.getBufferStrategy();
		
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		switch(state) {
		case START_SCREEN: //Start Screen Draw
			g2d.drawImage(backgroundImage, 0, 0, null);
			g2d.setColor(Color.white);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, Game.WIDTH/20)); 
			g2d.drawString("CLICK ANYWHERE TO START", Game.WIDTH/6, Game.HEIGHT/2);
			break;
		case SHIP_CHOICE: // Screen where you place ships
			g2d.drawImage(backgroundImage, 0, 0, null);
			human.drawShipsAndHits(g);
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(7));
			g2d.drawLine(Game.WIDTH/2,  0, Game.WIDTH/2, Game.HEIGHT);
			g2d.setColor(Color.white);
			
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, Game.WIDTH/35)); 
			g2d.drawString("CLICK HERE", 4*Game.WIDTH/6, Game.HEIGHT-Game.HEADER - Game.HEADER/10);
			g2d.drawString("TO START BATTLE", 4*Game.WIDTH/6, Game.HEIGHT-Game.HEADER/2 - Game.HEADER/10);
			g2d.drawString("(After placing ships)", 4*Game.WIDTH/6, Game.HEIGHT-Game.HEADER/10);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, Game.WIDTH/28)); 
			g2d.drawString("Your Board", 0, 2*Game.HEADER/3);
			g2d.drawString("Place Your Ships", Game.WIDTH/2, 2*Game.HEADER/3);
			g2d.drawString("(Click Ship and place on board)", Game.WIDTH/2, 4*Game.HEADER/3);
			g2d.drawString("(Right Click To Rotate)", Game.WIDTH/2, 6*Game.HEADER/3);
			
			g2d.drawLine(4*Game.WIDTH/6,  Game.HEIGHT-2*Game.HEADER, 4*Game.WIDTH/6, Game.HEIGHT);
			g2d.drawLine(4*Game.WIDTH/6,  Game.HEIGHT-2*Game.HEADER, Game.WIDTH, Game.HEIGHT-2*Game.HEADER);
			break;
		case GAME: // Game Screen
			g2d.drawImage(backgroundImage, 0, 0, null);
			human.drawShipsAndHits(g);
			g2d.setColor(Color.black);
			g2d.setStroke(new BasicStroke(7));
			g2d.drawLine(Game.WIDTH/2,  0, Game.WIDTH/2, Game.HEIGHT);
			g2d.setColor(Color.white);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, Game.WIDTH/25)); 
			g2d.drawString("Your Board", 0, 2*Game.HEADER/3);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, Game.WIDTH/40));
			g2d.drawString("Enemy Board (Click To Attack)", Game.WIDTH/2, 2*Game.HEADER/3);
			break;
		case AFTER_GAME: // Win/Lose Screen
			if (humanWon)
				g2d.drawImage(youWinImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			else
				g2d.drawImage(youLoseImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			break;
		}
		
		// dispose
		g.dispose();
		buffer.show();
	}
	
	public void update() { // updates - auto computer turnes
		if (state == GAME_STATE.SHIP_CHOICE && !computerPickedLocations) {
			computer.pickShipLocation(1,1);
			computerPickedLocations=true;
		}
		if (state == GAME_STATE.GAME && computerTurn) {
			int[] hitCords = computer.hit(0, 0);
			if (hitCords==null)
				return;
			boolean didHit = human.gotShot(hitCords[0], hitCords[1]);
			computer.registerHit(hitCords[0], hitCords[1], didHit);
			if (human.didLose()) {
				state = GAME_STATE.AFTER_GAME;
				humanWon = false;
			}
			computerTurn=false;
		}
	}
	
	public static void main(String[] args) { //main function
		new Game();
	}
}
