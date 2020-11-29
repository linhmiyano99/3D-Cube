package Math3D;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

public class Draw3D {
	public static void main(String[] args) {
		JFrame testFrame = new JFrame();
	    testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    final Cube comp = new Cube(100, 200, 75);
	    comp.DrawCube(new Point(250,250));
	    testFrame.getContentPane().add(comp, BorderLayout.CENTER);
	    testFrame.setVisible(true);
	    testFrame.setBounds(100, 100, 500, 500);
	    testFrame.setBackground(Color.WHITE);
	}
	
}
