package de.ur.ts.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.ur.ts.interfaces.DialogListener;
import de.ur.ts.main.Controller;
import de.ur.ts.map.Map;

public class GUI extends JFrame {
	private static final String WINDOW_NAME = "Path Finding";
	
	private Controller controller;
	
	private MapView mapView;
	private ControlView controlView;
	private StartDialog startDialog;
	
	public GUI(Controller controller){
		super(WINDOW_NAME);
		setLookAndFeel();
		setupFrame();
		setListeners(controller);
		
		startDialog.setVisible(true);
	}
	
	private void setupFrame(){
		  setTitle(WINDOW_NAME);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      startDialog = new StartDialog(this);
	      mapView = new MapView();
	      controlView = new ControlView();
	      JPanel panel = new JPanel();
	      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	      panel.add(mapView);
	      panel.add(controlView);
	      setContentPane(panel);
	      pack();
	      setResizable(false);
	      setLocationRelativeTo(null);
		  setVisible(true);
	}
	
	private void setListeners(Controller controller){
		mapView.setListener(controller);
		controlView.setListener(controller);
		startDialog.setListener(controller);
		
		controller.addListener(mapView);
	}

	
	private void setLookAndFeel(){
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        //javax.swing.plaf.metal.MetalLookAndFeel
	        if(UIManager.getSystemLookAndFeelClassName().equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")){
	        	UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	        }
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	}

	public void setMapSize(int cols, int rows) {
		
		
	}

}
