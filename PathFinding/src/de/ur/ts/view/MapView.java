package de.ur.ts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.ur.ts.interfaces.ControllerListener;
import de.ur.ts.interfaces.MapViewListener;
import de.ur.ts.map.Field;
import de.ur.ts.map.Map;

public class MapView extends JPanel implements MouseListener, ControllerListener{
	
	
	private static final int BORDER_WIDTH = 1;
	private static final int DEFAULT_WINDOW_HEIGHT = 500;
	private static final int DEFAULT_WINDOW_WIDTH = 500;
	
	
	private static final Color ORANGE = new Color(204,102,0);
	private static final Color BLUE = new Color(0,102,153);
	private static final Color PURPLE = new Color(139,0,197);
	private static final Color TRANSPARENT_PURPLE = new Color(139,0,197, 150);
	
	
	private static final Color DEFAULT_BACKGROUND_COLOR = new Color(255,255,255);
	private static final Color DEFAULT_START_COLOR = new Color(0,255,0);
	private static final Color DEFAULT_GOAL_COLOR = new Color(255,0,0);
	private static final Color DEFAULT_OCCUPIED_COLOR = new Color(0,0,0);
	private static final Color DEFAULT_IN_USE_COLOR = new Color(0,255,255);
	private static final Color DEFAULT_ACTIVE_COLOR = new Color(255,255,0);
	private static final Color DEFAULT_FIELD_COLOR = new Color(125,125,125);
	private static final Color DEFAULT_PATH_COLOR = new Color(255);
	
	
	private int fieldHeight, fieldWidth, xOffset, yOffset;
	
	private Map map;
	private boolean mapSet = false;
	private boolean valuesActivated = false;
	private MapViewListener listener;
	
	private Color pathColor = DEFAULT_PATH_COLOR;
	private Color inUseColor = DEFAULT_IN_USE_COLOR;
	private Color occupiedColor = DEFAULT_OCCUPIED_COLOR;
	private Color fieldColor = DEFAULT_FIELD_COLOR;
	private Color startColor = DEFAULT_START_COLOR;
	private Color goalColor = DEFAULT_GOAL_COLOR;
	private Color activeColor = DEFAULT_ACTIVE_COLOR;
	
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

	public void useShowcaseColors(){
		occupiedColor = ORANGE;
		fieldColor = BLUE;
		pathColor = PURPLE;
		inUseColor = TRANSPARENT_PURPLE;
	}
	
	public void resetColors(){
		occupiedColor = DEFAULT_OCCUPIED_COLOR;
		fieldColor = DEFAULT_FIELD_COLOR;
		pathColor = DEFAULT_PATH_COLOR;
		inUseColor = DEFAULT_IN_USE_COLOR;
	}
	
	public void setListener(MapViewListener listener){
		this.listener = listener;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(DEFAULT_BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
		if(mapSet){
			fieldHeight = (getHeight() / map.getHeight()) - BORDER_WIDTH;
			fieldWidth = (getWidth()/ map.getWidth()) - BORDER_WIDTH;
			
			xOffset = (getWidth() - ((fieldWidth + BORDER_WIDTH) * map.getWidth())) /2;
			yOffset = (getHeight() - ((fieldHeight+BORDER_WIDTH) * map.getHeight())) /2;
			if(map.getHeight() <= 20 && map.getWidth() <= 20){
				useShowcaseColors();
			}else{
				resetColors();
			}
			
			for(int row = 0; row < map.getHeight(); row++){
				for(int col = 0; col < map.getWidth(); col++){
					Field f = map.getField(col, row);
					if(!f.isEmpty()){
						g.setColor(occupiedColor);
					}else if(f.isInUse()){
						g.setColor(inUseColor);
					}else{
						g.setColor(fieldColor);
					}
					
					if(map.isStart(col, row)){
						g.setColor(startColor);
					}else if(map.isGoal(col, row)){
						g.setColor(goalColor);
					}
					
					if(f.isActive()){
						g.setColor(activeColor);
					}
					
					if(f.isPath()){
						g.setColor(pathColor);
					}
					
					g.fillRect(xOffset+ col*fieldWidth + col* BORDER_WIDTH, yOffset + row*fieldHeight + row* BORDER_WIDTH, fieldWidth,fieldHeight);
					g.setColor(Color.BLACK);
					g.drawRect(xOffset+col*fieldWidth + col*BORDER_WIDTH, yOffset + row*fieldHeight + row * BORDER_WIDTH,fieldWidth,fieldHeight);
					if(f.hasValue() && valuesActivated){
						String value = f.getValueString();
						g.drawString(value,xOffset+ col*fieldWidth + (fieldWidth/2) - value.length() + col* BORDER_WIDTH,yOffset + row*fieldHeight + (fieldHeight/2) + row* BORDER_WIDTH);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent mE) {
		int button = mE.getButton();
		
		int calcX = (mE.getX() -xOffset)/ (fieldWidth + BORDER_WIDTH);
		int calcY = (mE.getY() -yOffset) / (fieldHeight + BORDER_WIDTH);
		
		if(calcX >= map.getWidth() || calcY >= map.getHeight()) return;
		
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
	
	public void onSaveImage(String algorithmName){
		BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D cg = img.createGraphics();
		paintComponent(cg);
		try{
			File directory = new File("./out/" + algorithmName+"/");
			if(!directory.isFile()){
				directory.mkdir();
			}
			File outputfile = new File("./out/" + algorithmName +"/" + System.currentTimeMillis() + ".png");
			ImageIO.write(img, "png", outputfile);
		} catch(IOException e){
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void mouseEntered(MouseEvent mE) {	
	}

	@Override
	public void mouseExited(MouseEvent mE) {
	}

	@Override
	public void mousePressed(MouseEvent mE) {
	}

	@Override
	public void mouseReleased(MouseEvent mE) {
	}

	@Override
	public void onMapCreated(Map map) {
		setMap(map);
	}

	@Override
	public void onMapChange() {
		repaint();
	}

	@Override
	public void onDeactivateValues() {
		valuesActivated = false;
	}

	@Override
	public void onActivateValues() {
		valuesActivated = true;
	}
	

}
