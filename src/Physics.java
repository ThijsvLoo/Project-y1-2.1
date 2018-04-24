public class Physics {
	private double height = 0;
	private double xHeight = 0;
  	private	double yHeight = 0;
		private double rescaling=8;
  	private double frictionC=1.2;

	private final double grAcceleration = 9.81*rescaling;
	private final double ballMass=1;
	private double delta = 1.0/60;

	public double ballVelocity;
	double[] accelerationAr = new double[2];
	private double velocityAngle;
	public double[] ballPosition = new double[]{100, 100};
	private double[] velocityAr = new double[]{0, 0};
	private double[] gravityAr = new double[]{0,0};
 	private double leftBound, rightBound, frontBound, backBound;
	private World world;

	public Physics(World world) {
		leftBound=world.tileWidth;
		System.out.println("leftBound = "+leftBound);
		rightBound=world.width*world.tileWidth - world.tileWidth;
		System.out.println("rightBound = "+rightBound);
		backBound=world.height*world.tileHeight - world.tileHeight;
		System.out.println("backBound = "+backBound);
		frontBound=world.tileHeight;
		System.out.println("frontBound = "+frontBound);
		this.world=world;
	}

	public void setInMotion(double velocity, double angle, double[] position) {
		this.ballVelocity=velocity;
		this.velocityAngle=angle;
		this.ballPosition=position;
		this.velocityAr = new double[]{velocity*Math.cos(velocityAngle)*Math.cos(Math.atan(xHeight)), velocity*Math.sin(velocityAngle)*Math.cos(Math.atan(yHeight))};


	}

	public double[] ballMotion() {

		System.out.println("X = "+ballPosition[0]);

		setHeight();
		if(this.ballPosition[1]>4*world.tileHeight && this.ballPosition[1]<8*world.tileHeight
        && this.ballPosition[0]>8*world.tileWidth &&  this.ballPosition[0]<14*world.tileWidth)
				this.frictionC=3.6;
		else this.frictionC=1.2;


		// System.out.println("VELOCITY X START = " +velocityAr[0]);
    /* The velocity array contains the Velocity of the x and y axis respectively */
		// this.velocityAr[0] = ballVelocity*Math.cos(velocityAngle)*Math.cos(Math.atan(xHeight));
		// this.velocityAr[1] = ballVelocity*Math.sin(velocityAngle)*Math.cos(Math.atan(yHeight));

			// System.out.println("velocityX = "+velocityAr[0]+" BallVelocityY = "+velocityAr[1]);

    /* The gravity Force */
		double gravity = -ballMass*grAcceleration ;

    /* The gravityAr contains the effective gravity for the x and y axis respectively */
		this.gravityAr[0] = gravity*xHeight;
		this.gravityAr[1] = gravity*yHeight;

    /* The friction force */
		double friction=-frictionC*ballMass*grAcceleration;

    /* The frictionAr contains the friction for the x and y axis respectively */
		double[] frictionAr = {friction*Math.cos(velocityAngle)*Math.cos(Math.atan(xHeight)), friction*Math.sin(velocityAngle)*Math.cos(Math.atan(yHeight))};


      /* Equations of Acceleration*/
		accelerationAr[0]=(frictionAr[0]+gravityAr[0])/ballMass;
		accelerationAr[1]=(frictionAr[1]+gravityAr[1])/ballMass;
		System.out.println("Friction: "+frictionAr[0]+" "+frictionAr[1]);
		System.out.println("Gravity: "+gravityAr[0]+" "+gravityAr[1]);
		System.out.println("AccelerationX = "+accelerationAr[0]+" AccelerationY = "+accelerationAr[1]);
		System.out.println("deltaVelocityX = "+delta*accelerationAr[0]);
          /* The equations of Velocity */

					// System.out.println("VELOCITY X BEFORE = "+ velocityAr[0]);
		velocityAr[0] += accelerationAr[0]*delta;
		velocityAr[1] += accelerationAr[1]*delta;
					// System.out.println("VELOCITY X AFTER = "+ velocityAr[0]);
		if(velocityAr[0]>300)
			velocityAr[0]=300;
		if(velocityAr[1]>300)
			velocityAr[1]=300;


		// System.out.println("Acceleration = "+  Math.sqrt(getAcceleration()[0]*getAcceleration()[0]+
		// 	getAcceleration()[1]*getAcceleration()[1]));
            /* The equations of the position */


		this.ballPosition[0] += velocityAr[0]*delta;
		this.ballPosition[1] += velocityAr[1]*delta;





		this.ballVelocity=Math.sqrt(velocityAr[0]*velocityAr[0]+velocityAr[1]*velocityAr[1]);
											// +(velocityAr[0]*Math.sin(Math.atan(xHeight))/Math.cos(Math.atan(xHeight))
											// *velocityAr[0]*Math.sin(Math.atan(xHeight))/Math.cos(Math.atan(xHeight))
											// +velocityAr[1]*Math.sin(Math.atan(yHeight))/Math.cos(Math.atan(yHeight))
											// *velocityAr[1]*Math.sin(Math.atan(yHeight))/Math.cos(Math.atan(yHeight)))/2);

										if(velocityAr[1]>0 && velocityAr[0]<0)
											this.velocityAngle=Math.PI+Math.atan(velocityAr[1]/velocityAr[0]);
										else if(velocityAr[1]<0 && velocityAr[0]<0)
											this.velocityAngle=Math.PI+Math.atan(velocityAr[1]/velocityAr[0]);
										else
											this.velocityAngle=Math.atan(velocityAr[1]/velocityAr[0]);


											collision();
											return ballPosition;
											// System.out.println("VELOCITY X FINAL =" + velocityAr[0]);
	}

	public void collision() {
		if(ballPosition[0]>=rightBound || ballPosition[0]<=leftBound){
			System.out.println("BOOM");
			this.velocityAngle = Math.PI - this.velocityAngle;
			System.out.println("velocityX before= "+velocityAr[0]);
			this.velocityAr[0] = ballVelocity*Math.cos(velocityAngle);
			this.velocityAr[1] = ballVelocity*Math.sin(velocityAngle);
				System.out.println("velocityX after = "+velocityAr[0]);
			// this.velocityAr[0] = -this.velocityAr[0];
		}
		if(ballPosition[1]<=frontBound || ballPosition[1]>=backBound){
			this.velocityAngle = -this.velocityAngle;
			this.velocityAr[0] = ballVelocity*Math.cos(velocityAngle);
			this.velocityAr[1] = ballVelocity*Math.sin(velocityAngle);
			// this.velocityAr[1] = -this.velocityAr[1];
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
		this.height = Math.cos(Math.toRadians(ballPosition[0]))+Math.sin(Math.toRadians(ballPosition[1]));
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

	public double[] getAcceleration(){
		return accelerationAr;
	}

	public void reset(boolean position){
		this.ballVelocity = 0;
		this.velocityAngle = 0;
		this.velocityAr = new double[]{0, 0};
		if(position)
			this.ballPosition = new double[]{10, 10};
	}

	public double[] getGravity(){
		return gravityAr;
	}

	public static void main(String[] args){
		System.out.println(Math.cos(Math.toRadians(60)));
	}
}
