package jlyrics.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import jlyrics.JLyrics;

public class Mp3Chooser extends JFileChooser {
	private static final long serialVersionUID = 3124698411887801417L;
	private JLyrics parent;

	public Mp3Chooser(JLyrics parent) {
		this.parent=parent;
		setEnabled(false);
		setMultiSelectionEnabled(false);
		
		setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory()
						|| f.getName().toLowerCase().endsWith(".mp3");
			}

			@Override
			public String getDescription() {
				return "Mp3 Files(*.mp3)";
			}
		});
	}
	
	public void show(){
		if(JFileChooser.APPROVE_OPTION == showOpenDialog(parent))
			parent.selectFile(this.getSelectedFile().getAbsolutePath());
	}
}
