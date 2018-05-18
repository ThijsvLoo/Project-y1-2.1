import java.util.ArrayList;

import com.sun.media.jai.opimage.MaxCRIF;

public class AI {
	private Player bot;

	public AI(Player bot) {
		this.bot = bot;
	}

	public double[] holePosition() {
		double[] holePosition = new double[2];
		World world = getWorld();

		holePosition[1] = 13.5*world.tileHeight;
		holePosition[0] = 27.5*world.tileWidth;

		return holePosition;
	}

	public int findBestHit(RunSimulations simulations) {
		ArrayList<double[]> positionList=simulations.getFinalPositions();
		int bestHit=0;
		double bestDistance = Double.MAX_VALUE;

		for (int i = 0; i<positionList.size(); i++) {
			if(distanceFromHole(positionList.get(i))<bestDistance) {
				bestDistance = distanceFromHole(positionList.get(i));
				bestHit=i;
			}
		}
		return bestHit;
	}

	public HitSimulator bestHit(RunSimulations simulations) {
		return simulations.getHitList().get(findBestHit(simulations));
	}

	public void playBestHit(RunSimulations simulations) {
		HitSimulator bestHit = bestHit(simulations);
		bot.hit(bestHit.getVelocity(),bestHit.getAngle());
	}

	public double distanceFromHole(double[] position) {
		double[] holePosition = holePosition();

		return Math.sqrt((position[0]-holePosition[0])*(position[0]-holePosition[0])
				+(position[1]-holePosition[1])*(position[1]-holePosition[1]));
	}

	public Physics getPhysics() {
		return bot.getEngine();
	}

	public World getWorld() {
		return bot.getEngine().getWorld();
	}


}
