package net.vrakiver.FFT;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;

public class FFTimelapse extends JFrame {
	private static final long serialVersionUID = 7770451513348605738L;

	InputSelector inputSelector;
	OutputSelector outputSelector;
	JButton runButton;

	public FFTimelapse() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("FFTimeLapse");
		this.setBackground(Color.BLACK);
		this.setSize(400, 400);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		inputSelector = new InputSelector();
		outputSelector = new OutputSelector();

		runButton = new JButton("Process Queue");

		add(inputSelector);
		add(outputSelector);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(runButton);
		add(buttonPanel);

		setVisible(true);

		runButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
	}

	public static void main(String[] args) {
		new FFTimelapse();

	}

	private void run() {
		int queueSize = inputSelector.getQueueSize();

		FFmpeg ffmpeg = null;
		FFprobe ffprobe = null;
		try {
			ffmpeg = new FFmpeg("C:/Program Files/ffmpeg/bin/ffmpeg.exe");
			ffprobe = new FFprobe("C:/Program Files/ffmpeg/bin/ffprobe.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TimelapseProcess process = new TimelapseProcess(new FFmpegExecutor(ffmpeg, ffprobe));

		int i = 1;
		while (inputSelector.hasMoreClips()) {
			try{
			File input = inputSelector.getNextClip();
			File output = new File(outputSelector.getOutputFolder(), input.getName());
			runButton.setText("Converting: " +i+"/"+queueSize);
			process.run(input, output);
			}catch(RuntimeException r){
			r.printStackTrace();	
			}
			i++;
		}
		runButton.setText("Process Queue");

	}
}
