package shapes;

public class MoveableRectangle extends Rectangle implements Moveable {

	private double x;
	private double y;
	public MoveableRectangle(double x, double y, double width, double height) {
		super(width, height);
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}

}
