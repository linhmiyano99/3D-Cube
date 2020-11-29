package Math3D;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Draw3D {
	static final Cube comp = new Cube(200, 400, 150);
	static float rotateX = 0.0f, rotateY = 0.0f, rotateZ =0.0f;
	static JFrame testFrame = new JFrame();

	public static void main(String[] args) {

		
		
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		testFrame.setVisible(true);
		testFrame.setBounds(100, 100, 1200, 1000);
		testFrame.setBackground(Color.WHITE);

		Label lx, ly, lz;
		Button btnReset;
		//SeekBar  scrollX, scrollY, scrollZ;
		JTextField txtX, txtY, txtZ;
		 lx = new Label(); testFrame.add(lx);
		 lx.setText("X");
		 lx.setBounds(700, 100, 50, 50);
		  
		 ly = new Label(); testFrame.add(ly); 
		 ly.setBounds(700, 200, 50, 50);
		 ly.setText("Y");
		  
		 lz = new Label(); testFrame.add(lz); 
		 lz.setBounds(700, 300, 50, 50);
		 lz.setText("Z");
		  
		  
		 btnReset = new Button("Reset"); 
		 testFrame.add(btnReset);
		 btnReset.setBackground(Color.darkGray);
		 btnReset.setBounds(700, 400, 50, 50);
			System.out.print("Reset " + btnReset.getWidth()+ " ");
		 btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotateX = 0f;
				rotateY = 0f;
				rotateZ = 0f;
				System.out.print("Reset " + btnReset.getWidth()+ " ");		
				System.out.print("x " + lx.getWidth()+ " ");				
			}
			 
		 });

		  
	/*	  
		 scrollX = new JSlider  (); 
		 testFrame.add(scrollX); 
		 scrollX.setBounds(800, 100, 300, 50);
		  
		 scrollY = new JSlider  (JSlider.HORIZONTAL, 10, 5); 
		 testFrame.add(scrollY); 
		 scrollY.setBounds(800, 200, 300, 50);
		  
		 scrollZ = new JSlider  (JSlider.HORIZONTAL, 10, 5); 
		 testFrame.add(scrollZ); 
		 scrollZ.setBounds(800, 300, 300, 50);
*/
		
	/*	 txtX = new JTextField();
		 testFrame.add(txtX); 
		 txtX.setBounds(800, 100, 300, 50);
		 
		 txtY = new JTextField();
		 testFrame.add(txtY); 
		 txtY.setBounds(800, 200, 300, 50);
		 
		 txtZ = new JTextField();
		 testFrame.add(txtZ); 
		 txtZ.setBounds(800, 300, 300, 50);
		 */ 
		 

		Draw( comp,  testFrame,  50,  rotateY,  rotateZ) ;
		

	}
	public static void Draw(Cube comp, JFrame testFrame, float rotateX, float rotateY, float rotateZ) {
		comp.setRotateX(rotateX);
		comp.setRotateY(rotateY);
		comp.setRotateZ(rotateZ);
		comp.DrawCube(new Point(300,300));

		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
	}
}
