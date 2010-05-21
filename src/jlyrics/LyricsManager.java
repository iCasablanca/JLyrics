package jlyrics;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import jlyrics.exceptions.JLyricsException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyUSLT;

/**
 * Do all the manages of the lyrics and the file
 * @author rengo.Java
 *
 */
public class LyricsManager {
	private JLyrics parent;
	private File file;
	
	public LyricsManager(JLyrics parent){
		this.parent=parent;
	}
	
	public void setFile(String fileName) throws IOException, TagException, JLyricsException{
		file= new File(fileName);
		if(!file.exists())
			throw new JLyricsException("The selected file does not exists!");
		else{
			updateApplet();
		}
	}

	/**
	 * Update the file info in the applet 
	 * @throws TagException 
	 * @throws IOException 
	 */
	private void updateApplet() throws IOException, TagException {
		MP3File mp3File= new MP3File(file); 
		AbstractID3 tag=mp3File.getID3v1Tag();
		parent.setArtist(tag.getLeadArtist());
		parent.setTitle(tag.getSongTitle());
		parent.setLyrics(getLyrics(mp3File));
	}
	
	private String getLyrics(MP3File mp3File) throws IOException, TagException{
//		Lyrics3v2 tag= new Lyrics3v2();
//		tag.setSongLyric("HOLAAA");
//		mp3File.setLyrics3Tag(tag);
//		mp3File.save();
		Iterator i=mp3File.getID3v2Tag().getFrameIterator();
		while(i.hasNext()){
			 AbstractID3v2Frame  o=(AbstractID3v2Frame)i.next();
			System.out.println(o.getIdentifier()); //USLT
		}
		return t.getLyric();
	}
}
