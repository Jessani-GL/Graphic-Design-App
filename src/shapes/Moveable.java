package shapes;

// Used to use multiple inheritance
public interface Moveable {
	
	// can only have 2 things in interface: constants and abstract methods
	// we want to add one omre adbstract method in our program. we 
	// dont want to add abstract method in shape class, we want to seperate it
	// because of this, the class can directly extend from the shape class
	abstract void move(double x, double y);

}
