package orcFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	Boolean isPaused = false;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addPauseListener(new PauseListener());
		view.addMouseListener(new ClickListener());

	}
		
	public static void main(String [] args) {
		Controller c = new Controller();
		c.start();
	}     
	
        //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++)
		{
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect());
		
		}
	}

	//this is the class for the pause button
	class PauseListener implements ActionListener{
		int XIncr;
		int YIncr;
		Direction d;
		
		@Override
		public void actionPerformed(ActionEvent e) {
				if(!isPaused) {
					XIncr = model.getXIncr();
					YIncr = model.getYIncr();
					d = model.getDirect();
					model.setXIncr(0);
					model.setYIncr(0);
				
				}else {
					model.setXIncr(XIncr);
					model.setYIncr(YIncr);
				}
				isPaused = !isPaused;
		}
		
	}
	
	class ClickListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
		
			int modelCenterX= model.getX()+(view.getImageWidth()/2);
			int modelCenterY=model.getY()+(view.getImageHeight()/2);
			int targetX = 0;
			int targetY = 0;
			// A new target is selected
			if (e.getClickCount()==1) {
				targetX = e.getX();
				targetY = e.getY();
				
				
			    //System.out.println(model.getX() +" and "+ model.getY());
			    //System.out.println(modelCenterX + " and " + modelCenterY);
			    //System.out.println(e.getX() +" and "+ e.getY());
			}

			// Player is not standing on the target
			if ((targetX != modelCenterX) || targetY != modelCenterY) {

			    // Get the vector between the player and the target
			    int pathX = targetX - modelCenterX;
			    int pathY = targetY - modelCenterY;

			    // Calculate the unit vector of the path
			    double distance = Math.sqrt((pathX * pathX) + (pathY * pathY));
			    double directionX = pathX / distance;
			    double directionY = pathY / distance;
			    // Calculate the actual walk amount

			    double movementX = (directionX*8) ;
			    double movementY = (directionY * 8) ;

			    // Move the player
			    model.setXIncr((int)movementX);
			    model.setYIncr((int)movementY);
			   
			}
		}

	}
	
	
//	//this is the redirect button 
//	class RedirectListener implements ActionListener{
//		int XIncr;
//		int YIncr;
//		Direction d;
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			XIncr = model.getXIncr();
//			YIncr = model.getYIncr();
//			model.setXIncr(XIncr*-1);
//			model.setYIncr(YIncr*-1);
//			
//		}
//		
//	}
}
