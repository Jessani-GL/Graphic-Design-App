package shapes;
class Rectangle extends Shape { // sub class of shape

	private double width;
	private double height;
	private double x;
	private double y;
	
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	

	@Override
	public void run() { 

		System.out.println("run Rectangle method");

	}

	@Override
	public double computeArea() {
		return width * height * Math.PI;
	}

	@Override
	public double computePeri() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void method() {
		System.out.println("The rectangle is at " + "(" + this.x + "," + 
				this.y + ")");
		
	}

}