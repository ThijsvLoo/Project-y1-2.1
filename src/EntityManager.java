import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;



public class EntityManager {

   /* private Handler handler;
    private Ball ball;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {

        @Override
        public int compare(Entity a, Entity b) {
            if((a.getY()+a.getHeight())<(b.getY()+b.getHeight())) {return -1;}
            return 1;
        }

    };


    public EntityManager(Handler handler,Ball ball) {
        this.handler=handler;
        this.ball=ball;
        entities = new ArrayList<Entity>();
        addEntity(ball);
    }

    public void tick() {
        for(int i =0;i<entities.size();i++) {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    public void render(Graphics g) {
        for(Entity e : entities) {
            e.render(g);
        }
        entities.sort(renderSorter);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

*/

}

