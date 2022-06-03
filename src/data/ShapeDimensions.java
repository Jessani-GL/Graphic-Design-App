package data;

public class ShapeDimensions {

	// This class saves the shape dimensions in order to display it
	
	private double circleWidth;
	private double circleHeight;
	private double rectHeight;
	private double rectWidth;

	private final static ShapeDimensions INSTANCE = new ShapeDimensions();

	public static ShapeDimensions getInstance() {
		return INSTANCE;
	}

	public double getCircleWidth() {
		return circleWidth;
	}

	public void setCircleWidth(double circleWidth) {
		this.circleWidth = circleWidth;
	}

	public double getCircleHeight() {
		return circleHeight;
	}

	public void setCircleHeight(double circleHeight) {
		this.circleHeight = circleHeight;
	}

	public double getRectHeight() {
		return rectHeight;
	}

	public void setRectHeight(double rectHeight) {
		this.rectHeight = rectHeight;
	}

	public double getRectWidth() {
		return rectWidth;
	}

	public void setRectWidth(double rectWidth) {
		this.rectWidth = rectWidth;
	}

	
}
