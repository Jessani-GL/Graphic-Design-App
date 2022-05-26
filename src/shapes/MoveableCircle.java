package shapes;

public class MoveableCircle extends Circle implements Moveable{

	private double x;
	private double y;
	public MoveableCircle(double x, double y, double radius) {
		super(radius);
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
