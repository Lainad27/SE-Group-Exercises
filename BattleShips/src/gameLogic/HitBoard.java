package gameLogic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HitBoard {
    private int width;
    private int length;

    private HIT_STATE[][] board;

    public HitBoard(int width,int length) {
        setWidth(width);
        setLength(length);
        board = new HIT_STATE[this.width][this.length];
        for(int index2 = 0; index2 < board.length; index2++) {
            for(int index = 0; index < board[index2].length; index++) {
            	board[index2][index] = HIT_STATE.UNTOUCHED;
            }
        }
    }

    public HitBoard() {
        setWidth(10);
        setLength(10);
        board = new HIT_STATE[this.width][this.length];
        for(int index2 = 0; index2 < board.length; index2++) {
            for(int index = 0; index < board[index2].length; index++) {
            	board[index2][index] = HIT_STATE.UNTOUCHED;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void newHit(int xLoacation,int yLoacation, HIT_STATE state){
        board[xLoacation][yLoacation] = state;
    }
    public boolean isHit(int xLoacation,int yLoacation){
        return board[xLoacation][yLoacation] != HIT_STATE.UNTOUCHED;
    }

    public void draw(Graphics g, int offset){ //draw the board
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(5));
		for(int x=0;x<Game.XTILES;x++) {
			for(int y=0;y<Game.YTILES;y++) {
				g2d.drawLine(offset + x*Game.TILE_WIDTH, Game.HEADER +  (y)*Game.TILE_HEIGHT, offset + (x+1)*Game.TILE_WIDTH, Game.HEADER + (y)*Game.TILE_HEIGHT);
				g2d.drawLine(offset + x*Game.TILE_WIDTH, Game.HEADER + (y)*Game.TILE_HEIGHT, offset + (x)*Game.TILE_WIDTH, Game.HEADER + (y+1)*Game.TILE_HEIGHT);
				switch (board[x][y]) {
				case MISSED:
					g2d.drawImage(Game.XmarkImage, offset + x*Game.TILE_WIDTH, Game.HEADER +  y*Game.TILE_HEIGHT, Game.TILE_WIDTH, Game.TILE_HEIGHT, null);
					break;
				case HIT:
				g2d.drawImage(Game.explosionImage, offset + x*Game.TILE_WIDTH, Game.HEADER +  y*Game.TILE_HEIGHT, Game.TILE_WIDTH, Game.TILE_HEIGHT, null);
					break;
				default:
					break;
				}
			}
		}
    }



}
