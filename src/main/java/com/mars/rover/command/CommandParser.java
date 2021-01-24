package com.mars.rover.command;

import java.util.List;
import java.util.Map;

import static com.mars.rover.command.Command.BACKWARD;
import static com.mars.rover.command.Command.FORWARD;
import static com.mars.rover.command.Command.LEFT;
import static com.mars.rover.command.Command.RIGHT;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public class CommandParser {

    private final Map<String, Command> dictionary = Map.of(
        "F", FORWARD,
        "B", BACKWARD,
        "L", LEFT,
        "R", RIGHT);

    public List<Command> parse(final String commandString) {
        return commandString.chars()
            .mapToObj(c -> String.valueOf((char) c))
            .map(c -> ofNullable(dictionary.get(c))
                .orElseThrow(() -> new IllegalArgumentException(format("Can not parse command string %s", commandString))))
            .collect(toList());
    }
}
