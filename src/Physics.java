public class Physics {


	private double height = 0;
	private double xHeight = 0;
  	private	double yHeight = 0;
  	private double frictionC=0.1;
	private final double grAcceleration = 9.81;
	private final double ballMass=1;
	private double delta = 1.0/60;

	private double ballVelocity;
	private double velocityAngle;
	public double[] ballPosition;
	private double leftBound, rightBound, frontBound, backBound;

	public Physics(int width, int height) {
		leftBound=0;
		rightBound=width;
		backBound=height;
		frontBound=0;
	}

	public void setInMotion(double velocity, double angle, double[] position) {
		this.ballVelocity=velocity;
		this.velocityAngle=angle;
		this.ballPosition=position;
	}

	public void ballMotion() {
    /* The velocity array contains the Velocity of the x and y axis respectively */
		double[] velocityAr = {ballVelocity*Math.cos(velocityAngle)*Math.cos(Math.atan(xHeight)), ballVelocity*Math.sin(velocityAngle)*Math.cos(Math.atan(yHeight))};

      /* The acceleration array contains the Velocity of the x and y axis respectively */
		double[] accelerationAr = new double[2];

    /* The gravity Force */
		double gravity = -ballMass*grAcceleration ;

    /* The gravityAr contains the effective gravity for the x and y axis respectively */
		double[] gravityAr = {gravity*xHeight, gravity*yHeight};

    /* The friction force */
		double friction=-frictionC*ballMass*grAcceleration;

    /* The frictionAr contains the friction for the x and y axis respectively */
		double[] frictionAr = {friction*Math.cos(velocityAngle)*Math.cos(Math.atan(xHeight)), friction*Math.sin(velocityAngle)*Math.cos(Math.atan(yHeight))};

      /* Equations of Acceleration*/
		accelerationAr[0]=(frictionAr[0]+gravityAr[0])/ballMass;
		accelerationAr[1]=(frictionAr[1]+gravityAr[1])/ballMass;

          /* The equations of Velocity */
		velocityAr[0] += accelerationAr[0]*delta;
		velocityAr[1] += accelerationAr[1]*delta;

            /* The equations of the position */
		this.ballPosition[0] += velocityAr[0]*delta ;
		this.ballPosition[1] += velocityAr[1]*delta ;

		this.ballVelocity=Math.sqrt(velocityAr[0]*velocityAr[0]+velocityAr[1]*velocityAr[1]
											+(velocityAr[0]*Math.sin(Math.atan(xHeight))/Math.cos(Math.atan(xHeight))
											*velocityAr[0]*Math.sin(Math.atan(xHeight))/Math.cos(Math.atan(xHeight))
											+velocityAr[1]*Math.sin(Math.atan(yHeight))/Math.cos(Math.atan(yHeight))
											*velocityAr[1]*Math.sin(Math.atan(yHeight))/Math.cos(Math.atan(yHeight)))/2);
		this.velocityAngle=Math.atan(velocityAr[1]/velocityAr[0]);

	}


	public boolean isCollide() {
		if(ballPosition[0]==rightBound || ballPosition[0]==leftBound || ballPosition[1]==frontBound || ballPosition[1]==backBound)
			return true;
		return false;
	}

	public void collision() {
		if(isCollide()) {
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

	public void setHeight() {
		this.height = Math.sin(ballPosition[0])+Math.cos(ballPosition[1]);
		this.xHeight = Math.cos(ballPosition[0]);
		this.yHeight = -Math.sin(ballPosition[1]);
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

		if (height<0)
			frictionC = -100000;

		else if ( height > 0 && height < 1 )
			frictionC = 0.1;

		else if ( height >= 1)
			frictionC = 0.2;
	}

	public double getFrictionC(){
		return frictionC;
	}

	public static void main(String[] args) {
		double[] position = {0,0};
		Physics physics = new Physics(10, 10);
		physics.setInMotion(10,Math.PI/4,position);
		physics.setHeight();
		while(physics.getVelocity()>0.5 ) {
			physics.setFrictionC();
			physics.ballMotion();
			System.out.println(physics.getPosition()[0]+" , "+physics.getPosition()[1]);
			System.out.println(physics.getAngle());
			System.out.println(physics.getVelocity());
		}
	}
}