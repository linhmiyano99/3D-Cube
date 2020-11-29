package Math3D;

public class Vector3D
{
    public float x;
    public float y;
    public float z;

    public Vector3D(int _x, int _y, int _z){
    	
        x = _x;
        y = _y;
        z = _z;
    }

    public Vector3D(double _x, double _y, double _z){
    	
        x = (float)_x;
        y = (float)_y;
        z = (float)_z;
    }

    public Vector3D(float _x, float _y, float _z){
    	
    	x = _x;
        y = _y;
        z = _z;
    }

    public Vector3D(){
    }

    public String ToString(){
        return "(" + x + ", " + y + ", " + z+ ")";
    }
}