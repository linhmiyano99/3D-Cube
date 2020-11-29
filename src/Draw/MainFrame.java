package Draw;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private MenuBar mb;
	private Menu mFile;
	private Menu mEdit;
	private MenuItem miOpen;
	private MenuItem miClose;
	private MenuItem miExit;
	
	public MainFrame(String title) {
		this.setTitle(title);
		this.setBounds(50,50,800,800);
		initComponent();
	}
	
	public void initComponent() {
		mb = new MenuBar();
		mFile = new Menu("File");
		mEdit = new Menu("Edit");
		miOpen = new MenuItem("Open");
		miClose = new MenuItem("Close");
		miExit = new MenuItem("Exit");

		mFile.add(miOpen);
		mFile.add(miClose);
		mFile.add(miExit);
		mb.add(mFile);
		mb.add(mEdit);
		this.setMenuBar(mb);

	}
}
