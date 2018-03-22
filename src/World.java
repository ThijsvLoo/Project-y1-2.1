import java.awt.*;

public class World {

    public static int width,height;
    private int spawnX,spawnY;
    private int[][] tiles;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Entity.Manager entityManager;
    public int tileWidth, tileHeight;

    public World(Handler handler,String path){
        loadWorld(path);
        this.tileWidth = (int)((double) Tile.TILE_WIDTH/ (double) (1920/screenSize.width));
        this.tileHeight = (int)((double) Tile.TILE_HEIGHT/ (double) (1080/screenSize.height));
        System.out.println(screenSize);
    }

    public void tick(){}

    public void render(Graphics g){
      for(int y=0;y<height;y++){
          for(int x=0;x<width;x++){
              getTile(x,y).render(g,x*tileWidth,y*tileHeight);//A loop that is getting the current tile from the getTile method in order to
          }                                                                         //render it ...Draws the map
      }
    }

    public Tile getTile(int x,int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {                     //if the tile is something outside our map , it will assume it is water so we can
            return Tile.waterTile;                        //restart the game
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.waterTile;
        }
        return t;
    }

/*
it is reading from the path(our text document) each number should be divided by a space , first row : 2 integers , height/width of the map
second row 2 integers: coords where our ball will spawn( we will add that later and when we add we have to uncomment spawnX and spawnY and instead of +2 change to +4)
third row : here is the map , as much integers as we set the width and as many rows as we set the height
 */
    private void loadWorld(String path){
        String file = Util.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Util.parseInt(tokens[0]);
        height=Util.parseInt(tokens[1]);
       // spawnX= Util.parseInt(tokens[2]);
       // spawnY=Util.parseInt(tokens[3]);

        tiles=new int[width][height];
        for(int y=0;y<height;y++) {
            for(int x =0 ; x<width;x++) {
                tiles[x][y]=Util.parseInt(tokens[(x+y*width)+2]);//later change to +4 when spawn is added
            }
        }
    }
    //Getters and setters
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}


