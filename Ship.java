package ex3;

public class Ship {

    private int xLocationStart;
    private int yLocationStart;
    private int xLocationEnd;
    private int yLocationEnd;
    private int hitsToDown = (xLocationEnd - xLocationStart)*(yLocationEnd - yLocationStart);

    public Ship(int xLocationStart,int xLocationEnd,int yLocationStart,int yLocationEnd) {
        setxLocationEnd(xLocationEnd);
        setxLocationStart(xLocationStart);   // set coordinates
        setyLocationStart(yLocationStart);
        setyLocationEnd(yLocationEnd);
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

    public int getyLocationEnd() {
        return yLocationEnd;
    }

    public void setyLocationEnd(int yLocationEnd) {
        this.yLocationEnd = yLocationEnd;
    }


    public boolean onShot(int xLocation,int yLocation) {
    	if (xLocationStart < xLocation && xLocation < xLocationEnd &&
    			yLocationStart < yLocation && yLocation < yLocationEnd)
    		hitsToDown--;
    		return true;
        return false;
	}
    
    
    public boolean isDown(){
    	if (hitsToDown == 0) {
    		return true;
    	}
    	return false;
    }
}
