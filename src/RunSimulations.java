import java.util.ArrayList;

public class RunSimulations {

	private Physics physics;
	private int numberOfVelocities;
	private int numberOfAngles;
	private double velocityDifference=200;
	private double angleDifference=15;
	private ArrayList<double[]> finalPositions = new ArrayList<>();
	private ArrayList<HitSimulator> hitList = new ArrayList<>();

	public RunSimulations(Physics physics, int numberOfVelocities, int numberOfAngles) {
		this.physics = physics;
		this.numberOfVelocities=numberOfVelocities;
		this.numberOfAngles=numberOfAngles;
	}

	public void simulate() {
		int counter=0;
		for(int i=0; i<numberOfVelocities; i++) {
			for (int j=0; j<numberOfAngles; j++) {
				System.out.println("Counter ="+counter);
				counter++;
				HitSimulator hit = new HitSimulator(physics,(i+1)*(velocityDifference), (j+1)*angleDifference);
				hit.setBallPosition();
				hit.setSimulation();
				hitList.add(hit);
				finalPositions.add(hit.getBallPosition());
			}
		}

	}

	public ArrayList<double[]> getFinalPositions(){
		return finalPositions;
	}

	public ArrayList<HitSimulator> getHitList(){
		return hitList;
	}



}
