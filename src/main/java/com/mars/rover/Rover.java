package com.mars.rover;

import com.mars.rover.command.Command;

import java.util.List;

import static com.mars.rover.command.Command.BACKWARD;
import static com.mars.rover.command.Command.FORWARD;
import static com.mars.rover.command.Command.LEFT;

public class Rover {

    private final List<Coordinate> obstacles;
    private Position position;

    public Rover(final int x, final int y, final Heading heading, List<Coordinate> obstacles) {
        this.obstacles = obstacles;
        position = new Position(x, y, heading);
    }

    public String peek() {
        return position.peek();
    }

    public void apply(final Command command) {
        if (FORWARD == command) {
            final var targetPosition = position.forward();
//            obstacles.stream().filter( coordinate -> true );
            this.position = targetPosition;
        } else if (BACKWARD == command) {
            this.position = position.backward();
        } else if (LEFT == command ){
            this.position = position.turnLeft();
        } else {
            this.position = position.turnRight();
        }
    }

    public void apply(final List<Command> commands) {
        commands.forEach(command -> apply(command));
    }
}
