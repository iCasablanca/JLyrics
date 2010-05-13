package jlyrics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import jlyrics.components.Mp3Chooser;
import jlyrics.exceptions.JLyricsException;
import jlyrics.listener.MouseClickListener;

/**
 * The applet itself
 * @author rengo.Java
 *
 */
public class JLyrics extends JApplet {
	private static final long serialVersionUID = -2612293446677848626L;
	
	private LyricsManager manager;
	private Mp3Chooser chooser;
	private JButton loadBtn;
	private JTextField fileTxt;
	private JLabel artistLbl, titleLbl;
	private JEditorPane lyricsPane;
	
	public JLyrics(){
		manager= new LyricsManager(this);
	}
	
	public void init(){
		setLayout(new BorderLayout());
		setSize(new Dimension(800,600));
		add(buildFilePanel(),BorderLayout.NORTH);
		add(buildLyricsPanel(),BorderLayout.CENTER);
	}
	
	/**
	 * Builds the panel that contains the lyrics of the file
	 * @return
	 */
	private JPanel buildLyricsPanel() {
		JPanel lyricsPanel=new JPanel();
		artistLbl= new JLabel("");
		titleLbl= new JLabel("");
		lyricsPane= new JEditorPane();
		artistLbl.setPreferredSize(new Dimension(200, 20));
		titleLbl.setPreferredSize(new Dimension(200, 20));
		JScrollPane scroll= new JScrollPane();
		lyricsPane.setPreferredSize(new Dimension(400,400));
		scroll.getViewport().add(lyricsPane);
		lyricsPanel.add(new JLabel("Artist:"));
		lyricsPanel.add(artistLbl);
		lyricsPanel.add(new JLabel("Title:"));
		lyricsPanel.add(titleLbl);
		lyricsPanel.add(scroll);
		return lyricsPanel;
	}
	
	/**
	 * Builds the panel that contains the file to process
	 * @return
	 */
	private JPanel buildFilePanel() {
		JPanel filePanel=new JPanel();
		filePanel.add(new JLabel("Select File:"));
		filePanel.add(buildFileChooser());
		filePanel.add(buildLoadBtn());
		return filePanel;
	}

	private JButton buildLoadBtn() {
		loadBtn= new JButton("Load lyrics");
		loadBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		return loadBtn;
	}

	private JTextField buildFileChooser() {
		chooser= new Mp3Chooser(this);
		fileTxt= new JTextField();
		fileTxt.setEnabled(false);
		fileTxt.setPreferredSize(new Dimension(300,20));
		fileTxt.addMouseListener(new MouseClickListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				chooser.show();		
			}
		});
		return fileTxt;
	}

	public void selectFile(String file) {
		try{
			manager.setFile(file);
		}catch(JLyricsException ex){
			showError(ex.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
			showError("Unexpected error.");
		}
	}
	
	public void setFileName(String fileName){
		fileTxt.setText(fileName);
	}
	
	public void setArtist(String artist){
		artistLbl.setText(artist);
	}
	
	public void setTitle(String title){
		titleLbl.setText(title);
	}
	
	public void setLyrics(String lyric){
		lyricsPane.setText(lyric);
	}

	public void showError(String string) {
		JOptionPane.showMessageDialog(this, string, "ERROR", JOptionPane.ERROR_MESSAGE);		
	}
}
