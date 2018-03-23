public class Handler {
        public Game game;
        public World world;
        public Physics physics;

        public Handler(Game game) {
            this.game=game;
        }

        public MouseManager getMouseManager() {
            return game.getMouseManager();
        }

        public Physics getPhysics(){
            return world.ball.getEngine();
        }

        public int getWidth() {
            return game.getWidth();
        }

        public int getHeight() {
            return game.getHeight();
        }

        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public World getWorld() {
            return world;
        }

        public void setWorld(World world) {
            this.world = world;
        }
    }

