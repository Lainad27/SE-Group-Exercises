package gameLogic;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ship {

    private int xLocationStart;
    private int yLocationStart;
    private int xLocationEnd;
    private int yLocationEnd;
    private int hitsToDown; // = (xLocationEnd - xLocationStart)*(yLocationEnd - yLocationStart)

    public Ship(int xLocationStart,int yLocationStart,int xLocationEnd,int yLocationEnd) {
        setxLocationEnd(xLocationEnd);
        setxLocationStart(xLocationStart);   // set coordinates
        setyLocationStart(yLocationStart);
        setyLocationEnd(yLocationEnd);
        hitsToDown = (xLocationEnd - xLocationStart + 1)*(yLocationEnd - yLocationStart + 1); //the amount of hits to down is length of ship
    }

    public Ship(){
        setxLocationEnd(1);
        setxLocationStart(0);
        setyLocationStart(0);
        setyLocationEnd(1);
    }

    public int getxLocationStart() {
        return xLocationStart;
    }

    public void setxLocationStart(int xLocationStart) {
        this.xLocationStart = xLocationStart;
    }

    public int getyLocationStart() {
        return yLocationStart;
    }

    public void setyLocationStart(int yLocationStart) {
        this.yLocationStart = yLocationStart;
    }

    public int getxLocationEnd() {
        return xLocationEnd;
    }

    public void setxLocationEnd(int xLocationEnd) {
        this.xLocationEnd = xLocationEnd;
    }
    public void resetHitsToDown() {
    	hitsToDown = (xLocationEnd - xLocationStart + 1)*(yLocationEnd - yLocationStart + 1);
    }

    public int getyLocationEnd() {
        return yLocationEnd;
    }

    public void setyLocationEnd(int yLocationEnd) {
        this.yLocationEnd = yLocationEnd;
    }
    
    public int getWidth() {
    	return this.xLocationEnd - this.xLocationStart;
    }
    public int getHeight() {
    	return this.yLocationEnd - this.yLocationStart;
    }


    public boolean onShot(int xLocation,int yLocation) { // if the ship is shot, it is one less shot from being down
    	if (xLocationStart <= xLocation && xLocation <= xLocationEnd &&
    			yLocationStart <= yLocation && yLocation <= yLocationEnd) {
    		hitsToDown--;
    		return true;
    	}
        return false;
	}
    public boolean inPosition() { //check if the ship is on the board
    	return xLocationEnd<Game.XTILES && yLocationEnd<Game.YTILES && yLocationStart>=0 && xLocationStart >= 0;
    }
    
    public boolean isDown(){//check if the ship is down
    	return hitsToDown <= 0;
    }
    public boolean overLaps(Ship otherShip){//check if the ship overlaps with another ship
    	return (xLocationStart <= otherShip.getxLocationEnd() && otherShip.getxLocationStart() <= xLocationEnd && yLocationStart <= otherShip.getyLocationEnd() && otherShip.getyLocationStart() <= yLocationEnd);
    }
    
    public void draw (Graphics g) { //draw the ship
    	Graphics2D g2d = (Graphics2D)g;
		int shipWidth = 1 + getWidth();
		int shipHeight = 1 + getHeight();
		if (shipWidth>shipHeight)
			g2d.drawImage(Game.battleShipImage, this.xLocationStart*Game.TILE_WIDTH, Game.HEADER +  this.yLocationStart*Game.TILE_HEIGHT, shipWidth*Game.TILE_WIDTH, shipHeight*Game.TILE_HEIGHT, null);
		else
			g2d.drawImage(Game.battleShipRotatedImage, this.xLocationStart*Game.TILE_WIDTH, Game.HEADER +  this.yLocationStart*Game.TILE_HEIGHT, shipWidth*Game.TILE_WIDTH, shipHeight*Game.TILE_HEIGHT, null);
    }
}