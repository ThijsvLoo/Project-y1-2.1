import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    //Static Stuff
    //Those are the tiles we have for now and that can be used
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new Grass(0);
    public static Tile sandTile = new Sand(1);
    public static Tile waterTile = new Water(2);
    public static Tile stoneTile = new Stone(3);
    public static Tile holeTile = new Hole(9);


    //CLASS
    public static final int TILE_WIDTH=64,TILE_HEIGHT=64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {    //the constructor takes an img and int  which are further used to define a tile
        this.texture=texture;
        this.id=id;
        tiles[id]= this;
    }

    public int getId() { return id; }

    public void tick() {}

    public void render(Graphics g,int x , int y) {
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }

    public boolean isSolid() {   //That is a method that will check for the collision afterwards.
        return false;
    }
}

class Grass extends Tile {
    //that is the class for our tile where we set the id and texture for it and check if it is a solid surface(needs collision or not) if we override the method
    //from the super class (isSolid) that will mean that it has collision , otherwise it doesn't
    public Grass(int id){
        super(Assets.grass,id);
    }
}

class Stone extends Tile {
    public Stone(int id){
        super(Assets.stone,id);
    }
}

class Sand extends Tile{
    //Same as GrassTile
    public Sand(int id){
        super(Assets.sand,id);
    }
}

class Water extends Tile {
    //Same as grassTile
    public Water(int id){
        super(Assets.water,id);
    }
}

class Hole extends Tile {
    //Same as grassTile
    public Hole(int id){
        super(Assets.hole,id);
    }
}


