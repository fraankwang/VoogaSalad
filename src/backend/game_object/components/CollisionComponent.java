/**
 * @author cpd20
 */
package backend.game_object.components;

public class CollisionComponent extends Component implements IComponent{
    private boolean isCollided;
    private double myX, myY, myWidth, myHeight;


    public CollisionComponent(double myX, double myY, double myWidth, double myHeight){
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        //default
       isCollided = false;
    }

    public double getMyX() {
        return myX;
    }

    public double getMyY() {
        return myY;
    }

    public double getMyHeight() {
        return myHeight;
    }

    public double getMyWidth() {
        return myWidth;
    }

    public boolean isCollided() {
        return isCollided;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    public void setMyX(double myX) {
        this.myX = myX;
    }

    public void setMyY(double myY) {
        this.myY = myY;
    }

    public void setMyHeight(double myHeight) {
        this.myHeight = myHeight;
    }

    public void setMyWidth(double myWidth) {
        this.myWidth = myWidth;
    }

    @Override
    public String getTag(){
        return "Collision";
    }




}