package gameLogic;

import java.awt.Graphics;

public class HumanPlayer extends Player{
    private String name;
    private int shipChosenIndex = 0;
    private boolean chosenShipRotated = false;
    public HumanPlayer(String name) {
        setName(name);
    }
    
    public HumanPlayer() {
        setName("Human");
    }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setChosenShipRotated(boolean rotated) {
        this.chosenShipRotated = rotated;
    }
    public boolean getChosenShipRotated() {
    	return this.chosenShipRotated;
    }

    public void drawShipsAndHits(Graphics g){ // draws ships and board on screen
    	for (int shipIndex=0; shipIndex<ships.size(); shipIndex++) {
			ships.get(shipIndex).draw(g);
		}
    	switch (Game.state) {
    	case SHIP_CHOICE:
        	defenceBoard.draw(g, 0);
        	break;
    	case GAME:
    		attackBoard.draw(g, Game.WIDTH/2);
        	defenceBoard.draw(g, 0);
        	break;
		default:
			break;
    	}
    }

	@Override
	public void pickShipLocation(int Xclick, int Yclick) { // code resposible for placing a ship when clicked
		for (int shipIndex=0; shipIndex<ships.size(); shipIndex++) {
			Ship currentShip = ships.get(shipIndex);
			if (Xclick > currentShip.getxLocationStart()*Game.TILE_WIDTH
				&& Xclick <(currentShip.getxLocationEnd() + 1)*Game.TILE_WIDTH
				&& Yclick > currentShip.getyLocationStart()*Game.TILE_HEIGHT + Game.HEADER
				&& Yclick < (currentShip.getyLocationEnd() + 1)*Game.TILE_HEIGHT + Game.HEADER
				) {
				shipChosenIndex = shipIndex;
				return;
			}
		}
		int XclickCords = (int) Xclick/Game.TILE_WIDTH;
		int YclickCords = (int) (Yclick-Game.HEADER)/Game.TILE_HEIGHT;
		Ship chosenShip = ships.get(shipChosenIndex);
		
		int sWidth = chosenShip.getWidth();
		int sHeight = chosenShip.getHeight();
		if (chosenShipRotated) {
			chosenShipRotated = false;
			if (sWidth>sHeight) {
				chosenShip.setyLocationEnd(chosenShip.getyLocationStart() + sWidth);
				chosenShip.setxLocationEnd(chosenShip.getxLocationStart());
			}
			else {
				chosenShip.setxLocationEnd(chosenShip.getxLocationStart() + sHeight);
				chosenShip.setyLocationEnd(chosenShip.getyLocationStart());
			}
		} //code responsible for nudging the ship's top left corner to an available location
		if (XclickCords<Game.XTILES && YclickCords<Game.YTILES) {
			while (XclickCords + chosenShip.getWidth()>=Game.XTILES) {
				XclickCords--;
			}
			while (YclickCords + chosenShip.getHeight()>=Game.YTILES) {
				YclickCords--;
			}
			boolean nudged = true;
			int XoriginalCord = XclickCords;
			int YoriginalCord = YclickCords;
			while (nudged) {			
			nudged = false;

			for (int shipIndex=0; shipIndex<ships.size(); shipIndex++) { // check if ship collides with any other ship
				Ship otherShip = ships.get(shipIndex);
				if (shipIndex!=shipChosenIndex &&
						XclickCords <= otherShip.getxLocationEnd() && 
						otherShip.getxLocationStart() <= XclickCords + chosenShip.getWidth() &&
						YclickCords <= otherShip.getyLocationEnd() &&
						otherShip.getyLocationStart() <= YclickCords + chosenShip.getHeight()
						) {
					nudged = true;
					if (chosenShip.getWidth()>chosenShip.getHeight())
						XclickCords--;
					else
						YclickCords--;
					if (YclickCords< 0 || XclickCords < 0 || XclickCords + chosenShip.getWidth() < XoriginalCord 
						|| YclickCords + chosenShip.getHeight() < YoriginalCord)
						return;
				}
			}
			}
			chosenShip.setxLocationEnd(XclickCords + chosenShip.getWidth());
			chosenShip.setxLocationStart(XclickCords);
			chosenShip.setyLocationEnd(YclickCords + chosenShip.getHeight());
			chosenShip.setyLocationStart(YclickCords);
		}
	}



	@Override
	public int[] hit(int Xclick, int Yclick) {
		int XclickCords = (int) (Xclick-Game.WIDTH/2)/Game.TILE_WIDTH;
		int YclickCords = (int) (Yclick-Game.HEADER)/Game.TILE_HEIGHT;
		if (XclickCords<0 || attackBoard.isHit(XclickCords, YclickCords))
			return null;
		return new int[]{XclickCords,YclickCords};
	}
}
