package Math3D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		if(e.getActionCommand().equalsIgnoreCase("red")) {
			
		}
	}
	
}
