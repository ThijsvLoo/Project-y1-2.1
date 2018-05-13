
public class BSpline {

	private World world;
	private int scaling=100;
	private int numberOfXKnots=10;
	private int numberOfYKnots=10;
	private double[] xKnots;
	private double[] yKnots;
	private double xKnotDistance;
	private double yKnotDistance;


	public BSpline(World world) {
		this.world=world;
		this.xKnotDistance = (world.width * world.tileWidth - 2*world.tileWidth)/(numberOfXKnots-2);
		this.yKnotDistance = (world.height * world.tileHeight - 2*world.tileHeight)/(numberOfYKnots-2);
		this.xKnots = new double[numberOfXKnots];
		this.yKnots = new double[numberOfYKnots];
		this.setXKnots();
		this.setYKnots();

	}

	private void setXKnots(){

		for( int i = 0; i < xKnots.length; i++) {
			xKnots[i]=world.tileWidth+i*xKnotDistance;
		}
	}

	private void setYKnots(){

		for( int i = 0; i < yKnots.length; i++) {
			yKnots[i]=world.tileHeight+i*yKnotDistance;
		}
	}

	public double xBspline(int i,int k, double x) {
		if(k==0 && (xKnots[i]>x || xKnots[i+1]<x))
			return 0;
		else if(k==0)
			return 1;
		else
			return (x-xKnots[i])*xBspline(i,k-1,x) / (k*xKnotDistance)+(xKnots[i+k+1]-x)*xBspline(i+1,k-1,x)/(k*xKnotDistance);

	}

	public double xHeight(int i, int k, double x) {
		if(k==0)
			return 0;
		else
			return scaling*k*xBspline(i,k-1,x) / (k*xKnotDistance)+scaling*k*xBspline(i+1,k-1,x)/(k*xKnotDistance);
	}



	public double yHeight(int i, int k, double y) {
		if(k==0)
			return 0;
		else
			return scaling*k*yBspline(i,k-1,y) / (k*xKnotDistance)+scaling*k*yBspline(i+1,k-1,y)/(k*xKnotDistance);
	}



	public double yBspline(int i, int k, double y) {
		if(k==0 && (yKnots[i]>y || xKnots[i+1]<y))
			return 0;
		else if(k==0)
			return 1;
		else
			return (y-yKnots[i])*yBspline(i,k-1,y) / (k*yKnotDistance)+(yKnots[i+k+1]-y)*yBspline(i+1,k-1,y)/(k*yKnotDistance);
	}

	public double height(double x, double y) {
		return scaling*xBspline(0,xKnots.length-2,x)+scaling*yBspline(0,yKnots.length-2,y);
	}

	public int getNumberOfXKnots() {
		return xKnots.length;
	}

	public int getNumberOfYKnots() {
		return yKnots.length;
	}


}
