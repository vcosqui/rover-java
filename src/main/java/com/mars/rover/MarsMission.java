package com.mars.rover;

import com.mars.rover.command.CommandParser;

import java.util.List;

import static com.mars.rover.Heading.NORTH;

public class MarsMission {

    private final CommandParser commandParser;
    private final Rover rover;

    public MarsMission(final CommandParser commandParser, final Rover rover) {
        this.commandParser = commandParser;
        this.rover = rover;
    }

    public void runMission(final String missionCommands) {
        rover.apply(commandParser.parse(missionCommands));
    }

    public static void main(final String[] args) {
        final var rover = new Rover(0, 0, NORTH, List.of());
        final var commandParser = new CommandParser();
        final var marsMission = new MarsMission(commandParser, rover);
        marsMission.runMission(args[0]);
        System.out.println(rover.peek());
    }
}
