package engine.backend.systems.Events;


import engine.backend.entities.IEntity;

public class UpdateEntityEvent extends EntityEvent {

	double x;
	double y;
	String image;
	int id;
	double sizex;
	double sizey;
	boolean show;

	public UpdateEntityEvent(double x, double y, String image, int id, double sizex, double sizey, boolean show) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.image = image;
		this.sizex = sizex;
		this.sizey = sizey;
		this.show = show;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getImage() {
		return image;
	}

	public int getID() {
		return id;
	}

	public double getSizeX() {
		return sizex;
	}

	public double getsizeY() {
		return sizey;
	}

	public boolean getShow() {
		return show;
	}

}
