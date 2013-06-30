package de.ur.ts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import de.ur.ts.interfaces.ControllerListener;
import de.ur.ts.interfaces.MapViewListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class MapView extends JPanel implements MouseListener, ControllerListener{
	
	private static final int DEFAULT_WINDOW_HEIGHT = 500;
	private static final int DEFAULT_WINDOW_WIDTH = 500;
	
	private static final Color START_COLOR = new Color(0,255,0);
	private static final Color GOAL_COLOR = new Color(255,0,0);
	private static final Color OCCUPIED_COLOR = new Color(0,0,0);
	private static final Color IN_USE_COLOR = new Color(0,255,255);
	private static final Color ACTIVE_COLOR = new Color(255,255,0);
	private static final Color DEFAULT_COLOR = new Color(125,125,125);
	
	private int fieldHeight, fieldWidth;
	
	private Map map;
	private boolean mapSet = false;
	
	private MapViewListener listener;
	
	public MapView(){
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setMinimumSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		addMouseListener(this);
	}
	
	public void setMap(Map map){
		this.map = map;
		mapSet = true;
		repaint();
	}
	
	public void setListener(MapViewListener listener){
		this.listener = listener;
	}
	
	public void paintComponent(Graphics g){
		if(mapSet){
			fieldHeight = getHeight() / map.getHeight();
			fieldWidth = getWidth() / map.getWidth();
			for(int row = 0; row < map.getHeight(); row++){
				for(int col = 0; col < map.getWidth(); col++){
					Field f = map.getField(col, row);
					if(!f.isEmpty()){
						g.setColor(OCCUPIED_COLOR);
					}else if(f.isInUse()){
						g.setColor(IN_USE_COLOR);
					}else{
						g.setColor(DEFAULT_COLOR);
					}
					
					if(map.isStart(col, row)){
						g.setColor(START_COLOR);
					}else if(map.isGoal(col, row)){
						g.setColor(GOAL_COLOR);
					}
					
					if(f.isActive()){
						g.setColor(ACTIVE_COLOR);
					}
					
					g.fillRect(col*fieldWidth, row*fieldHeight, fieldWidth, fieldHeight);
					g.setColor(Color.BLACK);
					g.drawRect(col*fieldWidth, row*fieldHeight, fieldWidth, fieldHeight);
					if(f.hasValue()){
						g.drawString(f.getValue(), col*fieldWidth + (fieldWidth/2), row*fieldHeight + (fieldHeight/2));
					}
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent mE) {
		int button = mE.getButton();
		
		int calcX = mE.getX() / fieldWidth;
		int calcY = mE.getY() / fieldHeight;
		
		if(button == MouseEvent.BUTTON1){
			listener.onLeftButton(calcX, calcY);
		}
		
		if(button == MouseEvent.BUTTON3){
			listener.onRightButton(calcX, calcY);
		}
		
		if(button == MouseEvent.BUTTON2){
			listener.onMiddleButton(calcX, calcY);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent mE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent mE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent mE) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMapCreated(Map map) {
		setMap(map);
	}

	@Override
	public void onMapChange() {
		repaint();
	}
	

}
