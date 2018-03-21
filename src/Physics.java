
public class Physics {
	private double ballVelocity;
	private double velocityAngle;
	private double[] ballPosition;
	private final double grAcceleration = 10;
	private final double ballMass=1;
	private double leftBound, rightBound, frontBound, backBound;


	public Physics(double velocity, double angle, double[] position) {
		ballVelocity=velocity;
		velocityAngle=angle;
		ballPosition=position;
		leftBound=-10;
		rightBound=10;
		backBound=0;
		frontBound=20;
	}

	public void ballMotion(double frictionC, double slopeAngle, double slopeDirection) {

		double[] velocityAr = {ballVelocity*Math.cos(velocityAngle), ballVelocity*Math.sin(velocityAngle)};
		double[] accelerationAr = new double[2];

		double gravity = ballMass*grAcceleration ;
		double parallelGr = gravity*Math.sin(slopeAngle);
		double verticalGr = gravity*Math.cos(slopeAngle);
		double[] gravityAr = {parallelGr*Math.cos(slopeDirection), parallelGr*Math.sin(slopeDirection)};

		double friction=frictionC*verticalGr;
		double[] frictionAr = {friction*Math.cos(Math.PI+velocityAngle), friction*Math.sin(Math.PI+velocityAngle)};
		double delta = 1.0;


		gravity=ballMass*grAcceleration;


		accelerationAr[0]=(frictionAr[0]+gravityAr[0])/ballMass;
		accelerationAr[1]=(frictionAr[1]+gravityAr[1])/ballMass;

		velocityAr[0] += accelerationAr[0]*delta;
		velocityAr[1] += accelerationAr[1]*delta;

		this.ballPosition[0] += velocityAr[0]*delta + 0.5*accelerationAr[0]*delta*delta;
		this.ballPosition[1] += velocityAr[1]*delta + 0.5*accelerationAr[1]*delta*delta;

		this.ballVelocity=Math.sqrt(velocityAr[0]*velocityAr[0]+velocityAr[1]*velocityAr[1]);
//		this.velocityAngle=Math.atan(velocityAr[1]/velocityAr[0]);

	}

	public boolean isColide() {
		if(ballPosition[0]==rightBound || ballPosition[0]==leftBound || ballPosition[1]==frontBound || ballPosition[1]==backBound)
			return true;
		return false;
	}

	public void collision() {
		if(isColide()) {
			if(ballPosition[0]==rightBound || ballPosition[0]==leftBound)
				velocityAngle=-velocityAngle;
			else if(ballPosition[0]==frontBound || ballPosition[0]==backBound)
				velocityAngle=Math.PI-velocityAngle;
			else if(ballPosition[0]==rightBound && ballPosition[1]==frontBound ||
					ballPosition[0]==leftBound && ballPosition[1]==frontBound ||
					ballPosition[0]==rightBound && ballPosition[1]==backBound ||
					ballPosition[0]==leftBound && ballPosition[1]==backBound)
				velocityAngle+=Math.PI;
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

	public static void main(String[] args) {
		double[] position = {0,0};
		Physics physics = new Physics(10,Math.PI/4,position);
		while(physics.getVelocity()>0.05 ) {
			physics.ballMotion(0.1, Math.PI/6, Math.PI);
			System.out.println(physics.getPosition()[0]+" , "+physics.getPosition()[1]);
			System.out.println(physics.getAngle());
			System.out.println(physics.getVelocity());
		}


	}
}
