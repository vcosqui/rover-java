package com.mars.rover;

import java.util.Map;

import static com.mars.rover.Heading.EAST;
import static com.mars.rover.Heading.NORTH;
import static com.mars.rover.Heading.SOUTH;
import static com.mars.rover.Heading.WEST;
import static java.lang.String.format;

public class Position {

    private final int x;
    private final int y;
    private Coordinate coordinate;
    final Heading heading;

    final static Map<Heading, Heading> turnLeft = Map.of(
        NORTH, WEST,
        SOUTH, EAST,
        EAST, NORTH,
        WEST, SOUTH
    );

    final static Map<Heading, Heading> turnRight = Map.of(
        NORTH, EAST,
        SOUTH, WEST,
        EAST, SOUTH,
        WEST, NORTH
    );

    Position(final int x, final int y, final Heading heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public String peek() {
        return format("(%s: %s) -> %s", x, y, heading.name());
    }

    public Position forward() {
        if (this.heading == NORTH) {
            return movingNorth();
        } else if (this.heading == SOUTH) {
            return movingSouth();
        } else if (this.heading == EAST) {
            return movingEast();
        } else {
            return movingWest();
        }
    }

    public Position backward() {
        if (this.heading == NORTH) {
            return movingSouth();
        } else if (this.heading == EAST) {
            return movingWest();
        } else if (this.heading == SOUTH) {
            return movingNorth();
        } else {
            return movingEast();
        }
    }

    public Position turnRight() {
        return new Position(x, y, turnRight.get(heading));
    }

    public Position turnLeft() {
        return new Position(x, y, turnLeft.get(heading));
    }

    private Position movingNorth() {
        return new Position(x, y + 1, heading);
    }

    private Position movingWest() {
        return new Position(x - 1, y, heading);
    }

    private Position movingSouth() {
        return new Position(x, y - 1, heading);
    }

    private Position movingEast() {
        return new Position(x + 1, y, heading);
    }
}