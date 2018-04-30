public class Physics {
	private double height = 0;
	private double xHeight = 0;
	private double yHeight = 0;
	private double rescaling = 8;
	private double frictionC = 1.2;

	private final double grAcceleration = 9.81 * rescaling;
	private final double ballMass = 1;
	private final double GRAVITY = -grAcceleration * ballMass;
	private double friction = -frictionC * ballMass * grAcceleration;
	private double delta = 1.0 / 60;

	public double ballVelocity;
	double[] accelerationAr = new double[2];
	private double velocityAngle;
	public double[] ballPosition = new double[] { 100, 100 };
	private double[] velocityAr = new double[] { 0, 0 };
	private double[] gravityAr = new double[] { 0, 0 };
	private double leftBound, rightBound, frontBound, backBound;
	private World world;

	public Physics(World world) {
		leftBound = world.tileWidth;
		System.out.println("leftBound = " + leftBound);
		rightBound = world.width * world.tileWidth - world.tileWidth;
		System.out.println("rightBound = " + rightBound);
		backBound = world.height * world.tileHeight - world.tileHeight;
		System.out.println("backBound = " + backBound);
		frontBound = world.tileHeight;
		System.out.println("frontBound = " + frontBound);
		this.world = world;
	}

	public void setInMotion(double velocity, double angle, double[] position) {
		this.ballVelocity = velocity;
		this.velocityAngle = angle;
		this.ballPosition = position;
		this.velocityAr = new double[] { velocity * Math.cos(velocityAngle) * Math.cos(Math.atan(xHeight)),
				velocity * Math.sin(velocityAngle) * Math.cos(Math.atan(yHeight)) };

		double ang = Math.atan(velocityAr[1] / velocityAr[0]);

		System.out.println("");

	}

// The method returns the array of the effective gravity force on the x,y position
	public double[] gravity(double x, double y) {

		double[] gravityArray = new double[2];
		gravityArray[0] = GRAVITY * setHeight(x, y)[1];
		gravityArray[1] = GRAVITY * setHeight(x, y)[2];
		return gravityArray;
	}

// The method returns the array of the effective friction force on the x,y position
	public double[] friction(double x, double y) {

		double[] frictionAr = { friction * Math.cos(velocityAngle) * Math.cos(Math.atan(setHeight(x, y)[1])),
				friction * Math.sin(velocityAngle) * Math.cos(Math.atan(setHeight(x, y)[2])) };

		return frictionAr;
	}

// The method returns the array of the acceleration of the ball in the x,y position
	public double[] accelerationArray(double x, double y) {
		double[] accelArray = new double[2];

		accelArray[0] = (gravity(x, y)[0] + friction(x, y)[0]) / ballMass;
		accelArray[1] = (gravity(x, y)[1] + friction(x, y)[1]) / ballMass;

		return accelArray;
	}

// The method use the runge-kutta 4th order to calculate the velocity of the ball after a certain time step
	public double[] rungeKutta4thVelocity(double x, double y) {
		double h = delta;

		double[] tempVelocityAr = new double[2];
		tempVelocityAr[0] = velocityAr[0];
		tempVelocityAr[1] = velocityAr[1];

		double k01 = accelerationArray(x, y)[0];
		double k02 = accelerationArray(x + k01 * h * h / 2, y)[0];
		double k03 = accelerationArray(x + k02 * h * h / 2, y)[0];
		double k04 = accelerationArray(x + k03 * h * h, y)[0];
		tempVelocityAr[0] += 1.0 / 6 * (k01 + 2 * k02 + 2 * k03 + k04) * delta;


		double k11 = accelerationArray(x, y)[1];
		double k12 = accelerationArray(x, k11 * h * h / 2 + y)[1];
		double k13 = accelerationArray(x, k02 * h * h / 2 + y)[1];
		double k14 = accelerationArray(x, k03 * h * h + y)[1];
		tempVelocityAr[1] += 1.0 / 6 * (k11 + 2 * k12 + 2 * k13 + k14) * delta;

		return tempVelocityAr;
	}

// The method uses the runge kutta 4th order method to calculate the position of the ball after a specific timestep
	public void rungeKutta4thPosition() {
		double h = delta;
		double k01 = rungeKutta4thVelocity(ballPosition[0], ballPosition[1])[0];
		double k02 = rungeKutta4thVelocity(ballPosition[0] + k01 * h / 2, ballPosition[1])[0];
		double k03 = rungeKutta4thVelocity(ballPosition[0] + k02 * h / 2, ballPosition[1])[0];
		double k04 = rungeKutta4thVelocity(ballPosition[0] + k03 * h, ballPosition[1])[0];

		ballPosition[0] += 1.0 / 6 * (k01 + 2 * k02 + 2 * k03 + k04) * delta;

		double k11 = rungeKutta4thVelocity(ballPosition[0], ballPosition[1])[1];
		double k12 = rungeKutta4thVelocity(ballPosition[0], k11 * h / 2 + ballPosition[1])[1];
		double k13 = rungeKutta4thVelocity(ballPosition[0], k02 * h / 2 + ballPosition[1])[1];
		double k14 = rungeKutta4thVelocity(ballPosition[0], k03 * h + ballPosition[1])[1];
		ballPosition[1] += 1.0 / 6 * (k11 + 2 * k12 + 2 * k13 + k14) * delta;

	}

	public void setTempFrictionC() {
		if (this.ballPosition[1] > 4 * world.tileHeight && this.ballPosition[1] < 8 * world.tileHeight
				&& this.ballPosition[0] > 8 * world.tileWidth && this.ballPosition[0] < 14 * world.tileWidth)
			this.frictionC = 3.6;
		else
			this.frictionC = 1.2;
	}

	public void setVelocityAngle() {
		if (velocityAr[1] > 0 && velocityAr[0] < 0)
			this.velocityAngle = Math.PI + Math.atan(velocityAr[1] / velocityAr[0]);
		else if (velocityAr[1] < 0 && velocityAr[0] < 0)
			this.velocityAngle = Math.PI + Math.atan(velocityAr[1] / velocityAr[0]);
		else if (velocityAr[1] > 0 && velocityAr[0] > 0)
			this.velocityAngle = Math.atan(velocityAr[1] / velocityAr[0]);
		else
			this.velocityAngle = Math.atan(velocityAr[1] / velocityAr[0]);
	}

// The method returns the position of the ball after a specific time step
	public double[] ballMotion() {

// We set the heigh function, in accordance to the golf course.
		setHeight();

// We set the friction coefficient at each position in the golf course.
		setTempFrictionC();

// We calculate the velocity of the ball over the x and y axis after a specific time step by using the runge kutta method
		velocityAr[0] = rungeKutta4thVelocity(ballPosition[0], ballPosition[1])[0];
		velocityAr[1] = rungeKutta4thVelocity(ballPosition[0], ballPosition[1])[1];

// We calculate the x and y coordinate of the ball after a specific time step by using the runge kutta method
		rungeKutta4thPosition();

// We set the new scalar velocity of the ball
		this.ballVelocity = Math.sqrt(velocityAr[0] * velocityAr[0] + velocityAr[1] * velocityAr[1]);

// We set the new direction of the ball
		setVelocityAngle();

// We check if there is a collision
		collision();

// Retrun the position of the ball
		return ballPosition;
	}

// This method check if a collision happens and change the course of the ball accordingly
	public void collision() {

		if (ballPosition[0] >= rightBound || ballPosition[0] <= leftBound) {

			this.velocityAngle = Math.PI - this.velocityAngle;

			this.velocityAr[0] = ballVelocity * Math.cos(velocityAngle);
			this.velocityAr[1] = ballVelocity * Math.sin(velocityAngle);

		}
		if (ballPosition[1] <= frontBound || ballPosition[1] >= backBound) {

			this.velocityAngle = -this.velocityAngle;

			this.velocityAr[0] = ballVelocity * Math.cos(velocityAngle);
			this.velocityAr[1] = ballVelocity * Math.sin(velocityAngle);

		}
	}

	public double[] getPosition() {
		return ballPosition;
	}

	public double getVelocity() {
		return ballVelocity;
	}

	public double getAngle() {
		return velocityAngle;
	}


	public double[] setHeight(double x, double y) {
		double[] tempHeight = new double[3];

		tempHeight[0] = Math.cos(Math.toRadians(x)) + Math.sin(Math.toRadians(y));
		tempHeight[1] = -Math.sin(Math.toRadians(x));
		tempHeight[2] = Math.cos(Math.toRadians(y));

		return tempHeight;
	}

	public void setHeight() {
		this.height = Math.cos(Math.toRadians(ballPosition[0])) + Math.sin(Math.toRadians(ballPosition[1]));
		this.xHeight = -Math.sin(Math.toRadians(ballPosition[0]));
		this.yHeight = Math.cos(Math.toRadians(ballPosition[1]));
	}

	public double getHeight() {
		return height;
	}

	public double getXHeight() {
		return xHeight;
	}

	public double getYHeight() {
		return yHeight;
	}

	public void setFrictionC() {

		if (height < 0)
			frictionC = -100000;

		else if (height > 0 && height < 1)
			frictionC = 0.1;

		else if (height >= 1)
			frictionC = 0.2;
	}

	public double getFrictionC() {
		return frictionC;
	}

	public double[] getAcceleration() {
		return accelerationAr;
	}

	public void reset(boolean position) {
		this.ballVelocity = 0;
		this.velocityAngle = 0;
		this.velocityAr = new double[] { 0, 0 };
		if (position)
			this.ballPosition = new double[] { 10, 10 };
	}

	public double[] getGravity() {
		return gravityAr;
	}

	public static void main(String[] args) {
		System.out.println(Math.cos(Math.toRadians(60)));
	}
}
