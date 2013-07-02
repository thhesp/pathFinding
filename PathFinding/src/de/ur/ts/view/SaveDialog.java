package de.ur.ts.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.ur.ts.interfaces.DialogListener;
import de.ur.ts.map.Map;
import de.ur.ts.map.MapSaver;

public class SaveDialog extends JDialog implements ActionListener {

	private static String TITLE = "Save Map";
	private static final String SAVE = "Save Map";
	private static final String SELECT = "Select Directory";
	private static final String CHOOSER_TITLE = "Choose Save Directory";
	private static final int DEFAULT_WINDOW_HEIGHT = 110;
	private static final int DEFAULT_WINDOW_WIDTH = 160;

	private Map map;
	private DialogListener listener;
	private JFileChooser fc;
	private JTextField filenameField;
	private File directory = new File("./src/de/ur/ts/maps");

	public SaveDialog(GUI parent, Map map) {
		super(parent, TITLE);
		this.map = map;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setupDialog();
		requestFocus();
	}

	public void setListener(DialogListener listener) {
		this.listener = listener;
	}

	private void setupDialog() {
		fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("./src/de/ur/ts/maps"));
		fc.setDialogTitle(CHOOSER_TITLE);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);

		JPanel panel = new JPanel(new FlowLayout());
		JLabel filenameLabel = new JLabel("Filename:", JLabel.TRAILING);
		panel.add(filenameLabel);
		filenameField = new JTextField("", 10);
		filenameField.setToolTipText("Filename");
		filenameLabel.setLabelFor(filenameField);
		panel.add(filenameField);

		JButton select = new JButton(SELECT);
		select.setActionCommand(SELECT);
		select.addActionListener(this);
		panel.add(select);

		JButton save = new JButton(SAVE);
		save.setActionCommand(SAVE);
		save.addActionListener(this);
		panel.add(save);

		setContentPane(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(SELECT)) {
			 int returnVal = fc.showOpenDialog(this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		             directory = fc.getSelectedFile();
		        }
		}

		if (e.getActionCommand().equals(SAVE)) {
			String filename = filenameField.getText();
			boolean saved = MapSaver.saveMap(directory, filename, map);
			if(saved){
				dispose();
			}else{
				//return error message
			}
		}

	}

}
