public class GeometricObject{
    private String color;
    private boolean filled;
    private java.util.Date dateCreated = new java.util.Date();

    public GeometricObject(){
        this("white", true);
        //color = "white";
        //filled = true; 
    } 

    public GeometricObject(String color, boolean filled){
        this.color = color; 
        this.filled = filled;
    }

    public String getColor(){
        return color; 
    }

    public void setColor(String color){
        this.color = color; 
    }

    public boolean isFilled(){
        return filled;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public java.util.Date getDateCreated(){
        return dateCreated; //Warning! 
    }

    @Override
    public String toString(){
        return super.toString() + "\nGeometricOject\n color: " + color + "\n filled: " 
            + filled + "\n dateCreated: " + dateCreated;
    }


    public static void main(String[] args){
        GeometricObject go = new GeometricObject();
        System.out.println(go.toString());
        
    }
}