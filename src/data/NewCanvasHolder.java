package data;

public class NewCanvasHolder {

	// This class saves the data for the width and height
	private double width;
	private double height;
	
	private final static NewCanvasHolder INSTANCE = new NewCanvasHolder();

	public static NewCanvasHolder getInstance() {
		return INSTANCE;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	
}
