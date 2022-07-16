package gameLogic;

import java.util.Random;

public class ComputerPlayer extends Player{

	@Override
	public void pickShipLocation(int Xclick, int Yclick) { // the computer player randomly places ships
		Random random = new Random();
		for (int i=0; i<ships.size(); i++) {// check if it collides with another ship. if it does, re roll ship location
			Ship currentShip = ships.get(i);
			boolean flag = false;
			while (!flag) {
				flag = true;
				int length = random.nextInt(4) + 3;
				if (random.nextInt(2) == 0) {
					int xStartCord = random.nextInt(Game.XTILES);
					int yStartCord = random.nextInt(Game.YTILES - length - 2) + 2;
					currentShip.setxLocationStart(xStartCord);currentShip.setxLocationEnd(xStartCord);
					currentShip.setyLocationStart(yStartCord);currentShip.setyLocationEnd(yStartCord+length);
				}
				else {
					int xStartCord = random.nextInt(Game.XTILES - length - 2) + 2;
					int yStartCord = random.nextInt(Game.YTILES);
					currentShip.setxLocationStart(xStartCord);currentShip.setxLocationEnd(xStartCord+length);
					currentShip.setyLocationStart(yStartCord);currentShip.setyLocationEnd(yStartCord);
				}
				for (int j=0; j<i; j++) {
					if (ships.get(j).overLaps(currentShip)) {
						flag = false;
					}
				}
			}
			currentShip.resetHitsToDown();
		}
		
	}


	@Override
	public int[] hit(int Xclick, int Yclick) { // computer shooting algorithm is shooting a random location that hasnt been hit
		Random random = new Random();
		int Xcords = random.nextInt(Game.XTILES);
		int Ycords = random.nextInt(Game.YTILES);
		while (attackBoard.isHit(Xcords,Ycords)) {
			Xcords = random.nextInt(Game.XTILES);
			Ycords = random.nextInt(Game.YTILES);
		}
		return new int[]{Xcords,Ycords};
	}

}
