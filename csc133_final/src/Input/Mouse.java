package Input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Data.Click;

public class Mouse implements MouseListener {
	
	//Fields
	private boolean isReady;
	private Click lastClick;
	
	//Constructor
	public Mouse() {
		isReady = false;
		lastClick = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//We are using this
		lastClick = new Click(arg0.getX(), arg0.getY(), arg0.getButton());
		isReady = true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Click pollClick() {
		if (!isReady)
			return null;
		isReady = false;
		return lastClick;
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public static Point getMouseCoords() {
		return MouseInfo.getPointerInfo().getLocation();
	}
}
