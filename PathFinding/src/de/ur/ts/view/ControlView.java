package de.ur.ts.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import de.ur.ts.interfaces.ControlViewListener;

public class ControlView extends JPanel implements ActionListener{
	
	private static final int DEFAULT_WINDOW_HEIGHT = 150;
	private static final int DEFAULT_WINDOW_WIDTH = 500;
	
	private static final String ALGORITHM_SELECTED = "Selected";
	private static final String START = "Start";
	private static final String STOP = "Stop";
	private static final String PAUSE = "Pause";
	
	private static final String CREATE_MAP = "Create Map";
	private static final String LOAD_MAP = "Load Map";
	private static final String SAVE_MAP = "Save Map";
	private static final String RESET_MAP = "Reset Map";
	
	private static final String SLOWER = "-";
	private static final String FASTER = "+";
	private static final String SPEED_RESET = "Speed Reset";
	
	private ControlViewListener listener;
	private String[] algorithms;
	private JComboBox algorithmsBox;
	private String selectedAlgorithm;
	
	public ControlView(String[] algorithms){
		this.algorithms = algorithms;
		selectedAlgorithm = algorithms[0];
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setMinimumSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
		setupControls();
	}
	
	public void setupControls(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel(new FlowLayout());
		
		algorithmsBox = new JComboBox(algorithms);
		algorithmsBox.setEditable(false);
		algorithmsBox.setActionCommand(ALGORITHM_SELECTED);
		algorithmsBox.addActionListener(this);
		panel.add(algorithmsBox);
	
		add(panel);
		
		JPanel mapSettings = new JPanel(new FlowLayout());
		
		JButton create = new JButton(CREATE_MAP);
		create.setActionCommand(CREATE_MAP);
		create.addActionListener(this);
		mapSettings.add(create);
		
		JButton load = new JButton(LOAD_MAP);
		load.setActionCommand(LOAD_MAP);
		load.addActionListener(this);
		mapSettings.add(load);
		
		JButton save = new JButton(SAVE_MAP);
		save.setActionCommand(SAVE_MAP);
		save.addActionListener(this);
		mapSettings.add(save);
		
		JButton reset = new JButton(RESET_MAP);
		reset.setActionCommand(RESET_MAP);
		reset.addActionListener(this);
		mapSettings.add(reset);
		
		add(mapSettings);
		
		
		
		JPanel speedControls = new JPanel(new FlowLayout());
		
		JButton slower = new JButton(SLOWER);
		slower.setActionCommand(SLOWER);
		slower.addActionListener(this);
		speedControls.add(slower);
		
		JButton resetSpeed = new JButton(SPEED_RESET);
		resetSpeed.setActionCommand(SPEED_RESET);
		resetSpeed.addActionListener(this);
		speedControls.add(resetSpeed);
		
		JButton faster = new JButton(FASTER);
		faster.setActionCommand(FASTER);
		faster.addActionListener(this);
		speedControls.add(faster);
		
		add(speedControls);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		
		JButton start = new JButton(new ImageIcon("./src/de/ur/ts/icons/start_icon.png", START));
		start.setActionCommand(START);
		start.addActionListener(this);
		panel2.add(start);
		
		JButton pause = new JButton(new ImageIcon("./src/de/ur/ts/icons/pause_icon.png", PAUSE));
		pause.setActionCommand(PAUSE);
		pause.addActionListener(this);
		panel2.add(pause);
		
		JButton stop = new JButton(new ImageIcon("./src/de/ur/ts/icons/stop_icon.png", STOP));
		stop.setActionCommand(STOP);
		stop.addActionListener(this);
		panel2.add(stop);
		
		add(panel2);
	}
	
	public void setListener(ControlViewListener listener){
		this.listener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent aE) {
		String actionCommand = aE.getActionCommand();
		
		if(actionCommand == ALGORITHM_SELECTED){
			selectedAlgorithm = algorithms[algorithmsBox.getSelectedIndex()];
		}
		
		if(actionCommand == START){
			listener.onStart(selectedAlgorithm);
		}
		
		if(actionCommand == STOP){
			listener.onStop();
		}
		
		if(actionCommand == PAUSE){
			listener.onPause();
		}
		
		if(actionCommand == CREATE_MAP){
			listener.onCreateMap();
		}
			
		if(actionCommand == LOAD_MAP){
			listener.onLoadMap();
		}
		
		if(actionCommand == SAVE_MAP){
			listener.onSaveMap();
		}
			
		
		if(actionCommand == RESET_MAP){
			listener.onResetMap();
		}
			
		if(actionCommand == SLOWER){
			listener.onSlower();
		}
		
		
		if(actionCommand == SPEED_RESET){
			listener.onResetSpeed();
		}
		
		
		if(actionCommand == FASTER){
			listener.onFaster();
		}
		
		
		
	}

}
