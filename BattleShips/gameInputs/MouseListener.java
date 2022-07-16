package gameInputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameLogic.Game;

public class MouseListener extends MouseAdapter{
	
	private Game game;
	public MouseListener(Game game) {
		
		this.game = game;
		
		
	}
	
	public void mousePressed(MouseEvent e) { //triggers when the mouse is clicked
		game.locationPressed(e.getX(), e.getY(), e.getButton());
	}


}
