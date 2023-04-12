package Robot4;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class RobotMap {

    private final int n;
    private final int m;
    private int id=1;

    public final Map<UUID, Robot> robots;
    public final Map<Integer, UUID> ids;

    public RobotMap(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Недопустимые значения размера карты!");
        }
        this.n = n;
        this.m = m;
        this.robots = new HashMap<>();
        this.ids =new HashMap<>();
    }

    public Robot createRobot(Point position) throws PositionException {
        chekPosition(position);

        Robot robot = new Robot(position);
        robots.put(robot.id, robot);
        ids.put(this.id, robot.id);
        this.id++;
        return robot;
    }

    public void showRobots() {
        int i = 1;
        for (Map.Entry<UUID, Robot> robot : robots.entrySet()) {
            System.out.println(String.format("%d-> ", i) + robot.getValue());
            i ++;
        }
    }

    private void chekPosition(Point position) throws PositionException {
        if (!isFree(position)) {
            throw new PositionException("Точка" + position + "Занята");
        }
        if (position.getX() < 0 || position.getY() < 0 || position.getX() > n || position.getY() > m) {
            throw new PositionException("Некорректное значение точки" + position);
        }

    }

    private boolean isFree(Point position) {
        return robots.values().stream()
                .map(Robot::getPosition)
                .noneMatch(position::equals);

        // for (Robot value : robots.values()) {
        // if (value.getPosition().equals(position)) {
        // return false;
        // }
        // }
        // return true;

    }

    public class Robot {

        private final UUID id;
        private Point position;
        private Direction direction;

        public Robot(Point position) {
            this.id = UUID.randomUUID();
            this.position = position;
            this.direction = Direction.TOP;

        }

        public Direction getDirection() {
            return direction;
        }

        public UUID getId() {
            return id;
        }

        public Point getPosition() {
            return position;
        }

        public void move() throws PositionException {
            Point newPosition = switch (direction) {
                case TOP -> new Point(position.getX() - 1, position.getY());
                case RIGHT -> new Point(position.getX(), position.getY() + 1);
                case LEFT -> new Point(position.getX(), position.getY() - 1);
                case BOTTOM -> new Point(position.getX() + 1, position.getY());

            };
            chekPosition(newPosition);

            position = newPosition;
        }

        public void changeDirection(Direction direction) {
            this.direction = direction;

        }

        @Override
        public String toString() {
            return String.format("%s %s %s", id.toString(), position.toString(), direction.toString());
        }
    }

    public enum Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }

}