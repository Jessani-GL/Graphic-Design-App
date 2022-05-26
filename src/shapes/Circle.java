package shapes;

class Circle extends Shape { // sub class of shape

	private double radius;
	private double x;
	private double y;
	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public void run() {

		System.out.println("run method in the Cat class");

	}

	@Override
	public double computeArea() {
		return radius * radius * Math.PI;
	}

	@Override
	public double computePeri() {
		return x + y * Math.PI;
	}

	@Override
	public void method() {
		System.out.println("The circle is at " + "(" + this.x + "," + this.y + 
				")");
		
	}
	
}