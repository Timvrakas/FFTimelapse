package net.vrakiver.FFT;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class InputSelector extends JPanel {
	private static final long serialVersionUID = 5267927542713105321L;
	private DefaultListModel<File> queue;

	public InputSelector() {

		this.setLayout(new BorderLayout());

		queue = new DefaultListModel<File>();
		final JList<File> queueList = new JList<File>(queue);
		JScrollPane scrollList = new JScrollPane(queueList);
		scrollList.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		

		JLabel label = new JLabel("Add Input Files to Queue:", SwingConstants.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		

		JButton add = new JButton("Add Clip");
		JButton remove = new JButton("Remove Clip");

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClipDialog();
			}
		});

		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (queue.getSize() > 0)
					for (Object o : queueList.getSelectedValuesList())
						queue.removeElement(o);
			}
		});

		this.add(label, BorderLayout.NORTH);
		this.add(scrollList, BorderLayout.CENTER);
		JPanel buttons = new JPanel();
		buttons.add(add);
		buttons.add(remove);
		this.add(buttons, BorderLayout.SOUTH);
	}

	public void addClipDialog() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ClipFilter());
		fc.setMultiSelectionEnabled(true);
		fc.setAccessory(new ImagePreview(fc));
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			for (File f : fc.getSelectedFiles()) {
				queue.addElement(f);
			}
		}
	}

	public File getNextClip() {
		return queue.remove(0);
	}
	
	public int getQueueSize(){
		return queue.size();
	}
	public boolean hasMoreClips(){
		return !queue.isEmpty();
	}

}
