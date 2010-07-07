package backgammon.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import backgammon.util.BackgammonConfig;

public final class BoardLoader {

	private static BoardLoader instance = null;

	private BoardLoader() {

	}

	public static BoardLoader getInstance() {
		if (instance == null)
			instance = new BoardLoader();
		return instance;
	}

	public void save(BackgammonBoard board, String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(BackgammonConfig.getProperty("backgammon.game.savesDir")
					+ File.separator + filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(board);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public BackgammonBoard load(String filename) {
		BackgammonBoard board = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(BackgammonConfig.getProperty("savesDir")
					+ File.separator + filename);
			in = new ObjectInputStream(fis);
			board = (BackgammonBoard) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return board;

	}
}
