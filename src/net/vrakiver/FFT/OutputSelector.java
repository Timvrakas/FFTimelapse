package net.vrakiver.FFT;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class OutputSelector extends JPanel {
	private static final long serialVersionUID = 4630009282313490053L;

	JLabel label;
	JTextField path;
	JButton browse;
	JFileChooser fc;
	File outputFolder;

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;
		path.setText(outputFolder.getAbsolutePath());
	}

	public File getOutputFolder() {
		return outputFolder;
	}

	public OutputSelector() {
		this.setSize(new Dimension(260, 200));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		label = new JLabel("Output Folder:");
		path = new JTextField();
		path.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));
		browse = new JButton("Browse");

		add(Box.createRigidArea(new Dimension(5, 0)));
		add(label);
		add(Box.createRigidArea(new Dimension(5, 0)));
		add(path);
		add(Box.createRigidArea(new Dimension(5, 0)));
		add(browse);
		add(Box.createRigidArea(new Dimension(5, 0)));
		
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFolder();
			}
		});
	}
	
	public void selectFolder(){
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
		outputFolder = fc.getSelectedFile();
		path.setText(outputFolder.getAbsolutePath());
		}
		}

}
