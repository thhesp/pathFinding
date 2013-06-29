package de.ur.ts.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import de.ur.ts.interfaces.ControlViewListener;

public class ControlView extends JPanel{
	
	private static final int DEFAULT_WINDOW_HEIGHT = 150;
	private static final int DEFAULT_WINDOW_WIDTH = 500;
	
	
	private ControlViewListener listener;
	
	public ControlView(){
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setMinimumSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
	}
	
	public void setListener(ControlViewListener listener){
		this.listener = listener;
	}

}
