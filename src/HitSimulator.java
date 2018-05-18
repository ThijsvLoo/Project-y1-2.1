
public class HitSimulator {
	private Physics physics;
	private double[] ballPosition = {450, 450};
	private double velocity;
	private double angle;

	public HitSimulator(Physics physics,double velocity, double angle) {
		this.physics=physics;
		this.velocity=velocity;
		this.angle=Math.toRadians(angle);
	}

	public void setBallPosition() {
		this.ballPosition=physics.getPosition();
	}

	public void setSimulation() {
		physics.setInMotion(velocity, angle, ballPosition);

		while(!stopMovingCondition()) {
			physics.ballMotion();

			if (ballPosition[0]>27*physics.getWorld().tileWidth && ballPosition[0]<28*physics.getWorld().tileWidth &&
			ballPosition[1]>13*physics.getWorld().tileHeight && ballPosition[1]<14*physics.getWorld().tileHeight) {
				System.out.println("WIN");
				break;
			}
		}
		this.ballPosition=physics.getPosition();
	}




	public boolean stopMovingCondition() {
		  if(physics.ballVelocity <= 10 && Math.sqrt(physics.getGravity()[0]*physics.getGravity()[0]+
				    physics.getGravity()[1]*physics.getGravity()[1])<150  )
			  return true;
		  return false;
	  }

	public double[] getBallPosition() {
		return ballPosition;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getAngle() {
		return angle;
	}
}
