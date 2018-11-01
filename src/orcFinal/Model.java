
package orcFinal;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model{
	int x;
	int y;
	Direction direct;
	int w;
	int h;
	int iw;
	int ih;
	int thisXIncr;
	int thisYIncr;
	

	public Model(int width, int height, int imageWidth, int imageHeight) {
		x = 0;
		y = 0;
		direct = Direction.SOUTHEAST;
		w = width;
		h = height;
		iw = imageWidth;
		ih = imageHeight;
		thisXIncr = 8;
		thisYIncr = 2;
	}

	public void updateLocationAndDirection() {
		x+=thisXIncr;
		y+=thisYIncr;
    	if(x>(w - iw) || x<-20) {
    		thisXIncr = (-1)*thisXIncr;
    	}
    	if(y>(h - ih - 45) || y<-20) {
    		thisYIncr = (-1)*thisYIncr;
    	}
    	
    	//change direction 
    	if (thisYIncr<0 && thisXIncr==0){
    		direct = Direction.NORTH;
    	}
    	if (thisYIncr>0 && thisXIncr==0){
    		direct = Direction.SOUTH;
    	}
    	if (thisYIncr==0 && thisXIncr>0){
    		direct = Direction.EAST;
    	}
    	if (thisYIncr==0 && thisXIncr<0){
    		direct = Direction.WEST;
    	}
    	if(thisYIncr<0 && thisXIncr>0) {
    		direct = Direction.SOUTHEAST;

    	}
    	if(thisYIncr>0 && thisXIncr<0) {
    		direct = Direction.NORTHWEST;
    
    	}
    	if(thisYIncr<0 && thisXIncr<0) {
    		direct = Direction.SOUTHWEST;
    
    	}
    	if(thisYIncr>0 && thisXIncr>0) {
    		direct = Direction.NORTHEAST;
    		
    	}
   
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirect() {
		return direct;
	}
	
	public void setDirect(Direction e) {
		direct = e;
	}
	
	public void setXIncr(int x) {
		thisXIncr = x;
	}
	
	public void setYIncr(int y) {
		thisYIncr = y;
	}
	
	public int getXIncr() {
		return thisXIncr;
	}
	
	public int getYIncr() {
		return thisYIncr;
	}
	
}