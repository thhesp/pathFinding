package de.ur.ts.view;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.ur.ts.main.Controller;
import de.ur.ts.map.Map;
import de.ur.ts.map.MapLoader;

public class GUI extends JFrame {
	private static final String WINDOW_NAME = "Path Finding";
	
	private MapView mapView;
	private ControlView controlView;
	
	public GUI(Controller controller, String[] algorithms){
		super(WINDOW_NAME);
		setLookAndFeel();
	    mapView = new MapView();
	    controlView = new ControlView(algorithms);
		setupFrame();
		setListeners(controller);
	}
	
	private void setupFrame(){
		  setTitle(WINDOW_NAME);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

	public void createMapDialog(Controller controller) {
		CreateDialog startDialog = new CreateDialog(this);
		startDialog.setListener(controller);
		
		startDialog.setVisible(true);
	}

	public void loadMapDialog(Controller controller) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("./src/de/ur/ts/maps"));
		fc.setDialogTitle("Choose Load File");
		
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Map map;
			try {
				map = MapLoader.loadMap(file);
				controller.loadMap(map);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            
        }

	}

	public void saveMapDialog(Controller controller, Map map) {
		SaveDialog saveDialog = new SaveDialog(this, map);
		saveDialog.setVisible(true);
		
	}

}
