package Math3D;

import java.awt.geom.Point2D;


public class Face {

    public enum Side{
    	
        Front,
        Back,
        Left,
        Right,
        Top,
        Bottom
    }

        public Point2D.Float[] Corners2D;
        public Vector3D[] Corners3D;
        public Vector3D Center;
        public Side CubeSide;

        public Face()
        {
        }

        public int CompareTo(Face otherFace)
        {
            return (int)(this.Center.z - otherFace.Center.z); //In order of which is closest to the screen
        }
}
