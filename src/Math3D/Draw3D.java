package Math3D;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Draw3D {
	static final Cube comp = new Cube(100, 200, 75);
	static float rotateX = 0.0f, rotateY = 0.0f, rotateZ =0.0f;
	static JFrame testFrame = new JFrame();
	static BufferedImage mazeImage;
	static JLabel wIcon;
	static JPanel rotate ;
	public static void main(String[] args) {

		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		testFrame.setVisible(true);
		testFrame.setBounds(100, 100, 1200, 800);
		testFrame.setBackground(Color.WHITE);
		testFrame.setLayout(new FlowLayout ());
		
		
	    
		mazeImage = comp.getDrawCube(new Point(300,300));
		wIcon = new JLabel(new ImageIcon(mazeImage));
		testFrame.add(wIcon);
		
		rotate= new JPanel();
		rotate.setLayout(new BoxLayout(rotate, BoxLayout.PAGE_AXIS));
		testFrame.add(rotate);
		
		Label lx, ly, lz;
		Button btnReset;
		JSlider  scrollX, scrollY, scrollZ;
		//JTextField txtX, txtY, txtZ;
		 lx = new Label(); 
		 rotate.add(lx);
		 lx.setText("X");
		 lx.setBounds(700, 100, 10, 10);
		 
		  
		 scrollX = new JSlider  (JSlider.HORIZONTAL, 360, 0); 
		 rotate.add(scrollX); 
		 scrollX.setBounds(800, 100, 300, 50);
		 scrollX.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int value = scrollX.getValue();
				comp.setRotateX(value);
				Draw();

			}
			 
		 });
		  
		 ly = new Label(); 
		 rotate.add(ly); 
		 ly.setBounds(700, 200, 10, 10);
		 ly.setText("Y");
		 
		
		  
		 scrollY = new JSlider  (JSlider.HORIZONTAL, 360, 0); 
		 rotate.add(scrollY); 
		 scrollY.setBounds(800, 200, 300, 50);
		 scrollY.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					int value = scrollY.getValue();
					comp.setRotateY(value);
					Draw();

				}
				 
			 });
		 
		  
		 lz = new Label(); 
		 rotate.add(lz); 
		 lz.setBounds(700, 300, 10, 10);
		 lz.setText("Z");
		 

		  
		 scrollZ = new JSlider  (JSlider.HORIZONTAL, 360, 0); 
		 rotate.add(scrollZ); 
		 scrollZ.setBounds(800, 300, 300, 50);
		 scrollZ.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent arg0) {
					int value = scrollZ.getValue();
					comp.setRotateZ(value);
					Draw();

				}
				 
			 });
		  
		  
		 btnReset = new Button("Reset"); 
		 rotate.add(btnReset);
		 btnReset.setBounds(700, 400, 50, 50);
		 btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				scrollX.setValue(0);
				scrollY.setValue(0);
				scrollZ.setValue(0);
				reset();
			}
			 
		 });
		 reset();

	}
	public static void Draw() {
		
		mazeImage = comp.getDrawCube(new Point(200,200));
		wIcon.setIcon(new ImageIcon(mazeImage));
		wIcon.updateUI();
	
	}
	
	public static void reset() {
		comp.InitializeCube();
		Draw();

	}
}
