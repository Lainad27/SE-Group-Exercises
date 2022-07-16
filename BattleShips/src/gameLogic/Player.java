package gameLogic;

import java.util.ArrayList;

public abstract class Player { //abstract class - player and computer will inherit from this
	protected ArrayList<Ship> ships = new ArrayList<Ship>();
	protected HitBoard attackBoard = new HitBoard();
	protected HitBoard defenceBoard = new HitBoard();
	
	protected int amountOfShips;
	protected int boardLength;
	protected int boardWidth;
	
	
	public Player(ArrayList<Ship> ships, HitBoard attackBoard, HitBoard defenceBoard, int amountOfShips, int boardLength, int boardWidth) {
		setShips(ships);
		setAttackBoard(attackBoard);
		setDefenceBoard(defenceBoard);
		setAmountOfShips(amountOfShips);
		setBoardLength(boardLength);
		setBoardWidth(boardWidth);
	}
	
	public boolean shipsInPosition() { // check if a ship is on the board
		for (int shipIndex=0; shipIndex<ships.size(); shipIndex++) {
			if (!ships.get(shipIndex).inPosition())
				return false;
		}
		return true;
	}
	
	public Player() {// These are our game's default ships
		Ship ship1 = new Ship(Game.XTILES,1,Game.XTILES + 5,1);
		ships.add(ship1);
		Ship ship2 = new Ship(Game.XTILES,4,Game.XTILES + 4,4);
		ships.add(ship2);
		Ship ship3 = new Ship(Game.XTILES,7,Game.XTILES + 3,7);
		ships.add(ship3);
		Ship ship4 = new Ship(Game.XTILES,9,Game.XTILES + 2,9);
		ships.add(ship4);
		setAmountOfShips(5);
		setBoardLength(10);
		setBoardWidth(10);
	}
	
	public ArrayList<Ship> getShips() {
		return ships;
	}
	public void setShips(ArrayList<Ship> ships) {
		this.ships = ships;
	}
	public HitBoard getAttackBoard() {
		return attackBoard;
	}
	public void setAttackBoard(HitBoard attackBoard) {
		this.attackBoard = attackBoard;
	}
	public HitBoard getDefenceBoard() {
		return defenceBoard;
	}
	public void setDefenceBoard(HitBoard defenceBoard) {
		this.defenceBoard = defenceBoard;
	}
	public int getAmountOfShips() {
		return amountOfShips;
	}
	public void setAmountOfShips(int amountOfShips) {
		this.amountOfShips = amountOfShips;
	}
	public int getBoardLength() {
		return boardLength;
	}
	public void setBoardLength(int boardLength) {
		this.boardLength = boardLength;
	}
	public int getBoardWidth() {
		return boardWidth;
	}
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public abstract void pickShipLocation(int Xclick, int Yclick); // a player needs to place their ships
	
	public Boolean gotShot(int shotX, int shotY) { // handles when the player board is shot
		boolean didHit = false;
		for (int shipIndex=0; shipIndex<ships.size() && !didHit; shipIndex++) {
			didHit = ships.get(shipIndex).onShot(shotX, shotY);
		}
		if (didHit)
			defenceBoard.newHit(shotX, shotY, HIT_STATE.HIT);
		else
			defenceBoard.newHit(shotX, shotY, HIT_STATE.MISSED);
		return didHit;
	}
	
	public void registerHit(int shotX, int shotY, Boolean didHit) { // register if one of your shots hit or not
		if (didHit)
			attackBoard.newHit(shotX, shotY, HIT_STATE.HIT);
		else
			attackBoard.newHit(shotX, shotY, HIT_STATE.MISSED);
	}
	
	public Boolean didLose () {
		for (int shipIndex=0; shipIndex<ships.size(); shipIndex++) 
			if (!ships.get(shipIndex).isDown()) 
				return false;
		return true;
	};
	
	public abstract int[] hit (int Xclick, int Yclick);
}
