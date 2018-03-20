public class GrassTile extends Tile {

    public GrassTile(int id){
        super(Assets.grass,id);
    }
    //that is the class for our tile where we set the id and texture for it and check if it is a solid surface(needs collision or not) if we override the method
    //from the super class (isSolid) that will mean that it has collision , otherwise it doesn't
}
