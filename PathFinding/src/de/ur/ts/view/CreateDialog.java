package de.ur.ts.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.ur.ts.interfaces.DialogListener;

public class CreateDialog extends JDialog implements ActionListener{
	
	private static String TITLE = "Create Map";
	private static final String START = "Create Map";
	private static final int DEFAULT_WINDOW_HEIGHT = 110;
	private static final int DEFAULT_WINDOW_WIDTH = 100;
	
	private int rows = 0;
	private int cols = 0;
	
	private DialogListener listener;
	
	private JTextField colsTextField, rowsTextField;
	
	public CreateDialog(GUI parent){
		super(parent, TITLE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	    setLocationRelativeTo(null);
	    setupDialog();
	    requestFocus();
	}
	
	public void setListener(DialogListener listener){
		this.listener = listener;
	}
	
	private void setupDialog(){
		JPanel panel = new JPanel(new FlowLayout());
        JLabel rowsLabel = new JLabel("Row Count:       ", JLabel.TRAILING);
        panel.add(rowsLabel);
        rowsTextField = new JTextField("10", 3);
        rowsLabel.setLabelFor(rowsTextField);
        panel.add(rowsTextField);
        
        
        JLabel colsLabel = new JLabel("Columns Count: ", JLabel.TRAILING);
        panel.add(colsLabel);
        colsTextField = new JTextField("10", 3);
        colsLabel.setLabelFor(colsTextField);
        panel.add(colsTextField);
        
        
        JButton start = new JButton("Start");
        start.setActionCommand(START);
        start.addActionListener(this);
        panel.add(start);
        
       setContentPane(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		rows = Integer.parseInt(rowsTextField.getText());
		cols = Integer.parseInt(colsTextField.getText());
		
		if(e.getActionCommand().equals(START)){
			// get Data from TextFields and save in WeightInfo for Second Part
			if(rows >= 0 && cols >= 0){
				listener.createMap(cols, rows);
				dispose();
			}
		}
		
	}

}
