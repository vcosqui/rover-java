package com.mars.rover;

import com.mars.rover.command.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.mars.rover.Heading.NORTH;
import static com.mars.rover.command.Command.FORWARD;
import static java.lang.Integer.parseInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RoverTest {

    @ParameterizedTest
    @CsvSource({
        " 0,  0,  NORTH, (0: 0) -> NORTH",
        "-1,  1,  EAST,  (-1: 1) -> EAST",
        "-1, -9,  WEST,  (-1: -9) -> WEST",
        " 1, -1,  SOUTH, (1: -1) -> SOUTH"})
    public void roverShouldBeAtSpecifiedInitialPosition(String x, String y, String heading, String expected) {
        final var rover = new Rover(parseInt(x), parseInt(y), Heading.valueOf(heading), List.of());

        assertThat(rover.peek(), is(expected));
    }

    @ParameterizedTest
    @CsvSource({
        " 0,  0,  NORTH, FORWARD, (0: 1) -> NORTH",
        "-1,  1,  EAST,  FORWARD, (0: 1) -> EAST",
        "-1, -9,  WEST,  FORWARD, (-2: -9) -> WEST",
        " 1, -1,  SOUTH, FORWARD, (1: -2) -> SOUTH",
        " 0,  0,  NORTH, BACKWARD, (0: -1) -> NORTH",
        "-1, -2,   EAST, BACKWARD, (-2: -2) -> EAST",
        " 1,  2,  SOUTH, BACKWARD, (1: 3) -> SOUTH",
        " 0, -1,   WEST, BACKWARD, (1: -1) -> WEST"})
    public void roverShouldMoveToSpecifiedPosition(String x, String y, String heading, String command, String expected) {
        final var rover = new Rover(parseInt(x), parseInt(y), Heading.valueOf(heading), List.of());

        rover.apply(Command.valueOf(command));

        assertThat(rover.peek(), is(expected));
    }

    @Test
    public void roverShouldRunSeveralForwardCommands() {
        final var rover = new Rover(1, 1, NORTH, List.of());

        rover.apply(List.of(FORWARD, FORWARD, FORWARD, FORWARD));

        assertThat(rover.peek(), is("(1: 5) -> NORTH"));
    }

    @ParameterizedTest
    @CsvSource({
        " 0,  0,  NORTH, LEFT, (0: 0) -> WEST",
        " 0,  0,  SOUTH, LEFT, (0: 0) -> EAST",
        " 0,  0,  EAST, LEFT, (0: 0) -> NORTH",
        " 0,  0,  WEST, LEFT, (0: 0) -> SOUTH"})
    public void roverShouldTurnLeft(String x, String y, String heading, String command, String expected) {
        final var rover = new Rover(parseInt(x), parseInt(y), Heading.valueOf(heading), List.of());

        rover.apply(Command.valueOf(command));

        assertThat(rover.peek(), is(expected));
    }

    @ParameterizedTest
    @CsvSource({
        " 0,  0,  NORTH, RIGHT, (0: 0) -> EAST",
        " 0,  0,  SOUTH, RIGHT, (0: 0) -> WEST",
        " 0,  0,  EAST, RIGHT, (0: 0) -> SOUTH",
        " 0,  0,  WEST, RIGHT, (0: 0) -> NORTH"})
    public void roverShouldTurnRight(String x, String y, String heading, String command, String expected) {
        final var rover = new Rover(parseInt(x), parseInt(y), Heading.valueOf(heading), List.of());

        rover.apply(Command.valueOf(command));

        assertThat(rover.peek(), is(expected));
    }


    @Test
    public void roverShouldStopUponObstacleAndReportPosition() {
        final var obstacle = new Coordinate(0, 1);
        final var rover = new Rover(0, 0, NORTH, List.of(obstacle));

        rover.apply(FORWARD);

        assertThat(rover.peek(), is("(0: 0) -> NORTH"));
    }
}