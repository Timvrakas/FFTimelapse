package net.vrakiver.FFT;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ClipFilter extends FileFilter {
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String name = f.getName().toLowerCase();
		if (name != null) {
			if (name.endsWith(".mp4"))
				return true;
		} else {
			return false;
		}

		return false;
	}

	public String getDescription() {
		return "Video Files";
	}
}