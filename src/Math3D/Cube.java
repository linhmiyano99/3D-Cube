package Math3D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Cube extends JComponent {
	final static double PIOVER180 = Math.PI / 180.0;
	public int width = 0;
	public int height = 0;
	public int depth = 0;

	float xRotation = 0.0f;
	float yRotation = 0.0f;
	float zRotation = 0.0f;


	Vector3D cubeOrigin;

	Face[] faces;


	public float getRotateX() {
		return xRotation;
	}

	public void setRotateX(float value) {
		// rotate the difference between this rotation and last rotation
		RotateCubeX(value - xRotation);
		xRotation = value;
	}


	public float getRotateY() {
		return yRotation;
	}

	public void setRotateY(float value) {
		// rotate the difference between this rotation and last rotation
		RotateCubeY(value - yRotation);
		yRotation = value;
	}
	

	public float getRotateZ() {
		return zRotation;
	}

	public void setRotateZ(float value) {
		// rotate the difference between this rotation and last rotation
		RotateCubeZ(value - zRotation);
		zRotation = value;
	}


	public boolean FillFront;

	public Cube(int side) {
		width = side;
		height = side;
		depth = side;
		cubeOrigin = new Math3D.Vector3D(width / 2, height / 2, depth / 2);
		InitializeCube();
	}

	public Cube(int side, Vector3D origin) {
		width = side;
		height = side;
		depth = side;
		cubeOrigin = origin;

		InitializeCube();
	}

	public Cube(int Width, int Height, int Depth) {
		width = Width;
		height = Height;
		depth = Depth;
		cubeOrigin = new Math3D.Vector3D(width / 2, height / 2, depth / 2);

		InitializeCube();
	}

	public Cube(int Width, int Height, int Depth, Vector3D origin) {
		width = Width;
		height = Height;
		depth = Depth;
		cubeOrigin = origin;

		InitializeCube();
	}

	void InitializeCube() {
		// Fill in the cube
		
		 xRotation = 0.0f;
			 yRotation = 0.0f;
			 zRotation = 0.0f;


		faces = new Face[6]; // cube has 6 faces

		// Front Face --------------------------------------------
		faces[0] = new Face();
		faces[0].CubeSide = Face.Side.Front;
		faces[0].Corners3D = new Vector3D[4];
		faces[0].Corners3D[0] = new Vector3D(0, 0, 0);
		faces[0].Corners3D[1] = new Vector3D(0, height, 0);
		faces[0].Corners3D[2] = new Vector3D(width, height, 0);
		faces[0].Corners3D[3] = new Vector3D(width, 0, 0);
		faces[0].Center = new Vector3D(width / 2, height / 2, 0);
		// -------------------------------------------------------

		// Back Face --------------------------------------------
		faces[1] = new Face();
		faces[1].CubeSide = Face.Side.Back;
		faces[1].Corners3D = new Vector3D[4];
		faces[1].Corners3D[0] = new Vector3D(0, 0, depth);
		faces[1].Corners3D[1] = new Vector3D(0, height, depth);
		faces[1].Corners3D[2] = new Vector3D(width, height, depth);
		faces[1].Corners3D[3] = new Vector3D(width, 0, depth);
		faces[1].Center = new Vector3D(width / 2, height / 2, depth);
		// -------------------------------------------------------

		// Left Face --------------------------------------------
		faces[2] = new Face();
		faces[2].CubeSide = Face.Side.Left;
		faces[2].Corners3D = new Vector3D[4];
		faces[2].Corners3D[0] = new Vector3D(0, 0, 0);
		faces[2].Corners3D[1] = new Vector3D(0, 0, depth);
		faces[2].Corners3D[2] = new Vector3D(0, height, depth);
		faces[2].Corners3D[3] = new Vector3D(0, height, 0);
		faces[2].Center = new Vector3D(0, height / 2, depth / 2);
		// -------------------------------------------------------

		// Right Face --------------------------------------------
		faces[3] = new Face();
		faces[3].CubeSide = Face.Side.Right;
		faces[3].Corners3D = new Vector3D[4];
		faces[3].Corners3D[0] = new Vector3D(width, 0, 0);
		faces[3].Corners3D[1] = new Vector3D(width, 0, depth);
		faces[3].Corners3D[2] = new Vector3D(width, height, depth);
		faces[3].Corners3D[3] = new Vector3D(width, height, 0);
		faces[3].Center = new Vector3D(width, height / 2, depth / 2);
		// -------------------------------------------------------

		// Top Face --------------------------------------------
		faces[4] = new Face();
		faces[4].CubeSide = Face.Side.Top;
		faces[4].Corners3D = new Vector3D[4];
		faces[4].Corners3D[0] = new Vector3D(0, 0, 0);
		faces[4].Corners3D[1] = new Vector3D(0, 0, depth);
		faces[4].Corners3D[2] = new Vector3D(width, 0, depth);
		faces[4].Corners3D[3] = new Vector3D(width, 0, 0);
		faces[4].Center = new Vector3D(width / 2, 0, depth / 2);
		// -------------------------------------------------------

		// Bottom Face --------------------------------------------
		faces[5] = new Face();
		faces[5].CubeSide = Face.Side.Bottom;
		faces[5].Corners3D = new Vector3D[4];
		faces[5].Corners3D[0] = new Vector3D(0, height, 0);
		faces[5].Corners3D[1] = new Vector3D(0, height, depth);
		faces[5].Corners3D[2] = new Vector3D(width, height, depth);
		faces[5].Corners3D[3] = new Vector3D(width, height, 0);
		faces[5].Center = new Vector3D(width / 2, height, depth / 2);
		// -------------------------------------------------------
	}

	// Calculates the 2D points of each face
	private void Update2DPoints(Point drawOrigin) {
		// Update the 2D points of all the faces
		for (int i = 0; i < faces.length; i++) {
			Update2DPoints(drawOrigin, i);
		}
	}

	private void Update2DPoints(Point drawOrigin, int faceIndex) {
		// Calculates the projected coordinates of the 3D points in a cube face
		Point2D.Float[] point2D = new Point2D.Float[4];
		// float zoom = (float) Screen.PrimaryScreen.Bounds.Width / 1.5f;
		// Point tmpOrigin = new Point(0, 0);

		// Convert 3D Points to 2D
		Math3D.Vector3D vec;
		for (int i = 0; i < point2D.length; i++) {
			vec = faces[faceIndex].Corners3D[i];
			point2D[i] = Get2D(vec, drawOrigin);
		}

		// Update face
		faces[faceIndex].Corners2D = point2D;
	}

	// Rotating methods, has to translate the cube to the rotation point (center),
	// rotate, and translate back

	private void RotateCubeX(float deltaX) {
		for (int i = 0; i < faces.length; i++) {
			// Apply rotation
			// ------Rotate points
			Vector3D point0 = new Vector3D(0, 0, 0);
			faces[i].Corners3D = Translate(faces[i].Corners3D, cubeOrigin, point0); // Move corner to origin
			faces[i].Corners3D = RotateX(faces[i].Corners3D, deltaX);
			faces[i].Corners3D = Translate(faces[i].Corners3D, point0, cubeOrigin); // Move back

			// -------Rotate center
			faces[i].Center = Translate(faces[i].Center, cubeOrigin, point0);
			faces[i].Center = RotateX(faces[i].Center, deltaX);
			faces[i].Center = Translate(faces[i].Center, point0, cubeOrigin);
		}
	}

	private void RotateCubeY(float deltaY) {
		for (int i = 0; i < faces.length; i++) {
			// Apply rotation
			// ------Rotate points
			Vector3D point0 = new Vector3D(0, 0, 0);
			faces[i].Corners3D = Translate(faces[i].Corners3D, cubeOrigin, point0); // Move corner to origin
			faces[i].Corners3D = RotateY(faces[i].Corners3D, deltaY);
			faces[i].Corners3D = Translate(faces[i].Corners3D, point0, cubeOrigin); // Move back

			// -------Rotate center
			faces[i].Center = Translate(faces[i].Center, cubeOrigin, point0);
			faces[i].Center = RotateY(faces[i].Center, deltaY);
			faces[i].Center = Translate(faces[i].Center, point0, cubeOrigin);
		}
	}

	private void RotateCubeZ(float deltaZ) {
		for (int i = 0; i < faces.length; i++) {
			// Apply rotation
			// ------Rotate points
			Vector3D point0 = new Vector3D(0, 0, 0);
			faces[i].Corners3D = Translate(faces[i].Corners3D, cubeOrigin, point0); // Move corner to origin
			faces[i].Corners3D = RotateZ(faces[i].Corners3D, deltaZ);
			faces[i].Corners3D = Translate(faces[i].Corners3D, point0, cubeOrigin); // Move back

			// -------Rotate center
			faces[i].Center = Translate(faces[i].Center, cubeOrigin, point0);
			faces[i].Center = RotateZ(faces[i].Center, deltaZ);
			faces[i].Center = Translate(faces[i].Center, point0, cubeOrigin);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i = faces.length - 1; i >= 0; i--) // draw faces from back to front
		{
			g.drawLine((int) faces[i].Corners2D[0].x, (int) faces[i].Corners2D[0].y, (int) faces[i].Corners2D[1].x,
					(int) faces[i].Corners2D[1].y);
			g.drawLine((int) faces[i].Corners2D[1].x, (int) faces[i].Corners2D[1].y, (int) faces[i].Corners2D[2].x,
					(int) faces[i].Corners2D[2].y);
			g.drawLine((int) faces[i].Corners2D[2].x, (int) faces[i].Corners2D[2].y, (int) faces[i].Corners2D[3].x,
					(int) faces[i].Corners2D[3].y);
			g.drawLine((int) faces[i].Corners2D[3].x, (int) faces[i].Corners2D[3].y, (int) faces[i].Corners2D[0].x,
					(int) faces[i].Corners2D[0].y);

	
		}

	}
	
	 public void DrawCube(Point drawOrigin, Graphics g)
     {
         //Get the corresponding 2D
         Update2DPoints(drawOrigin);
         for (int i = faces.length - 1; i >= 0; i--) // draw faces from back to front
 		{
 			g.drawLine((int) faces[i].Corners2D[0].x, (int) faces[i].Corners2D[0].y, (int) faces[i].Corners2D[1].x,
 					(int) faces[i].Corners2D[1].y);
 			g.drawLine((int) faces[i].Corners2D[1].x, (int) faces[i].Corners2D[1].y, (int) faces[i].Corners2D[2].x,
 					(int) faces[i].Corners2D[2].y);
 			g.drawLine((int) faces[i].Corners2D[2].x, (int) faces[i].Corners2D[2].y, (int) faces[i].Corners2D[3].x,
 					(int) faces[i].Corners2D[3].y);
 			g.drawLine((int) faces[i].Corners2D[3].x, (int) faces[i].Corners2D[3].y, (int) faces[i].Corners2D[0].x,
 					(int) faces[i].Corners2D[0].y);

 	
 		}
     }
	 
	 public BufferedImage getDrawCube(Point drawOrigin)
     {
         //Get the corresponding 2D
         Update2DPoints(drawOrigin);
         Dimension imgDim = new Dimension(500,500);
         BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height, BufferedImage.TYPE_INT_RGB);
         Graphics2D g2d = mazeImage.createGraphics();
	        g2d.setBackground(new Color(238,238,238));

	        g2d.fillRect(0, 0, imgDim.width, imgDim.height);
	        g2d.setColor(Color.BLACK);
	        BasicStroke bs = new BasicStroke(2);
	        g2d.setStroke(bs);
         for (int i = faces.length - 1; i >= 0; i--) // draw faces from back to front
 		{
        	 g2d.drawLine((int) faces[i].Corners2D[0].x, (int) faces[i].Corners2D[0].y, (int) faces[i].Corners2D[1].x,
 					(int) faces[i].Corners2D[1].y);
 			g2d.drawLine((int) faces[i].Corners2D[1].x, (int) faces[i].Corners2D[1].y, (int) faces[i].Corners2D[2].x,
 					(int) faces[i].Corners2D[2].y);
 			g2d.drawLine((int) faces[i].Corners2D[2].x, (int) faces[i].Corners2D[2].y, (int) faces[i].Corners2D[3].x,
 					(int) faces[i].Corners2D[3].y);
 			g2d.drawLine((int) faces[i].Corners2D[3].x, (int) faces[i].Corners2D[3].y, (int) faces[i].Corners2D[0].x,
 					(int) faces[i].Corners2D[0].y);

 			
 		}
         g2d.dispose();
         return mazeImage;
     }

	/*
	 * public void DrawCube(Point drawOrigin) { Update2DPoints(drawOrigin);
	 * Rectangle bounds = getDrawingBounds(); bounds.width += drawOrigin.x;
	 * bounds.height += drawOrigin.y; Bitmap finalBmp = new Bitmap(bounds.Width,
	 * bounds.Height); Graphics g = Graphics.FromImage(finalBmp); }
	 */
	/*
	 * public void DrawCube(Point drawOrigin) { // Get the corresponding 2D
	 * Update2DPoints(drawOrigin);
	 * 
	 * // Get the bounds of the final bitmap Rectangle bounds = getDrawingBounds();
	 * bounds.width += drawOrigin.x; bounds.height += drawOrigin.y;
	 * 
	 * Graphics g = new Graphics();
	 * 
	 * g.setPaintMode();
	 * 
	 * // Array.Sort(faces); // sort faces from closets to farthest // message();
	 * 
	 * g.dispose(); }
	 */

	// Converts 3D points to 2D points
	private Point2D.Float Get2D(Vector3D vec, Point drawOrigin) {
		Point2D.Float point2D = Get2D(vec);
		return new Point2D.Float(point2D.x + drawOrigin.x, point2D.y + drawOrigin.y);
	}

	private Point2D.Float Get2D(Vector3D vec) {
		Point2D.Float returnPoint = new Point2D.Float();

		float zoom = (float) 500 / 1.5f;
		Camera tempCam = new Camera();

		tempCam.position.x = cubeOrigin.x;
		tempCam.position.y = cubeOrigin.y;
		tempCam.position.z = (cubeOrigin.x * zoom) / cubeOrigin.x;

		float zValue = -vec.z - tempCam.position.z;

		returnPoint.x = (tempCam.position.x - vec.x) / zValue * zoom;
		returnPoint.y = (tempCam.position.y - vec.y) / zValue * zoom;

		return returnPoint;
	}

	public Point2D.Float[] GetFrontFace() {
		// Returns the four points corresponding to the front face
		// Get the corresponding 2D
		return getFace(Face.Side.Front).Corners2D;
	}

	public Point2D.Float[] GetBackFace() {
		return getFace(Face.Side.Back).Corners2D;
	}

	public Point2D.Float[] GetRightFace() {
		return getFace(Face.Side.Right).Corners2D;
	}

	public Point2D.Float[] GetLeftFace() {
		return getFace(Face.Side.Left).Corners2D;
	}

	public Point2D.Float[] GetTopFace() {
		return getFace(Face.Side.Top).Corners2D;
	}

	public Point2D.Float[] GetBottomFace() {
		return getFace(Face.Side.Bottom).Corners2D;
	}

	private Face getFace(Face.Side side) {
		// Find the correct side
		// Since faces are sorted in order of closest to farthest
		// They won't always be in the same index
		for (int i = 0; i < faces.length; i++) {
			if (faces[i].CubeSide == side)
				return faces[i];
		}

		return null; // not found
	}
/*
	private Rectangle getDrawingBounds() {
		// Find the farthest most points to calculate the size of the returning bitmap
		float left = Float.MAX_VALUE;
		float right = Float.MIN_VALUE;
		float top = Float.MAX_VALUE;
		float bottom = Float.MIN_VALUE;

		for (int i = 0; i < faces.length; i++) {
			for (int j = 0; j < faces[i].Corners2D.length; j++) {
				if (faces[i].Corners2D[j].x < left)
					left = faces[i].Corners2D[j].x;
				if (faces[i].Corners2D[j].x > right)
					right = faces[i].Corners2D[j].x;
				if (faces[i].Corners2D[j].y < top)
					top = faces[i].Corners2D[j].y;
				if (faces[i].Corners2D[j].y > bottom)
					bottom = faces[i].Corners2D[j].y;
			}
		}

		return new Rectangle(0, 0, (int) Math.round(right - left), (int) Math.round(bottom - top));
	}
*/
	public static Vector3D RotateX(Vector3D point3D, float degrees) {
		// [ a b c ] [ x ] [ x*a + y*b + z*c ]
		// [ d e f ] [ y ] = [ x*d + y*e + z*f ]
		// [ g h i ] [ z ] [ x*g + y*h + z*i ]

		// [ 1 0 0 ]
		// [ 0 cos(x) sin(x)]
		// [ 0 -sin(x) cos(x)]

		double cDegrees = degrees * PIOVER180;
		double cosDegrees = Math.cos(cDegrees);
		double sinDegrees = Math.sin(cDegrees);

		double y = (point3D.y * cosDegrees) + (point3D.z * sinDegrees);
		double z = (point3D.y * -sinDegrees) + (point3D.z * cosDegrees);

		return new Vector3D(point3D.x, y, z);
	}

	public static Vector3D RotateY(Vector3D point3D, float degrees) {

		double cDegrees = degrees * PIOVER180;
		double cosDegrees = Math.cos(cDegrees);
		double sinDegrees = Math.sin(cDegrees);

		double x = (point3D.x * cosDegrees) + (point3D.z * sinDegrees);
		double z = (point3D.x * -sinDegrees) + (point3D.z * cosDegrees);

		return new Vector3D(x, point3D.y, z);
	}

	public static Vector3D RotateZ(Vector3D point3D, float degrees) {
		// [ cos(x) sin(x) 0]
		// [ -sin(x) cos(x) 0]
		// [ 0 0 1]

		double cDegrees = degrees * PIOVER180;
		double cosDegrees = Math.cos(cDegrees);
		double sinDegrees = Math.sin(cDegrees);

		double x = (point3D.x * cosDegrees) + (point3D.y * sinDegrees);
		double y = (point3D.x * -sinDegrees) + (point3D.y * cosDegrees);

		return new Vector3D(x, y, point3D.z);
	}

	public static Vector3D Translate(Vector3D points3D, Vector3D oldOrigin, Vector3D newOrigin) {
		points3D.x += (newOrigin.x - oldOrigin.x);
		points3D.y += (newOrigin.y - oldOrigin.y);
		points3D.z += (newOrigin.z - oldOrigin.z);
		return points3D;
	}

	public static Vector3D[] RotateX(Vector3D[] points3D, float degrees) {
		for (int i = 0; i < points3D.length; i++) {
			points3D[i] = RotateX((Vector3D) points3D[i], degrees);
		}
		return points3D;
	}

	public static Vector3D[] RotateY(Vector3D[] points3D, float degrees) {
		for (int i = 0; i < points3D.length; i++) {
			points3D[i] = RotateY((Vector3D) points3D[i], degrees);
		}
		return points3D;
	}

	public static Vector3D[] RotateZ(Vector3D[] points3D, float degrees) {
		for (int i = 0; i < points3D.length; i++) {
			points3D[i] = RotateZ((Vector3D) points3D[i], degrees);
		}
		return points3D;
	}

	public static Vector3D[] Translate(Vector3D[] points3D, Vector3D oldOrigin, Vector3D newOrigin) {
		for (int i = 0; i < points3D.length; i++) {
			points3D[i] = Translate(points3D[i], oldOrigin, newOrigin);
		}
		return points3D;
	}

}
