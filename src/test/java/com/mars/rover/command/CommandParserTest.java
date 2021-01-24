package com.mars.rover.command;

import org.junit.jupiter.api.Test;

import static com.mars.rover.command.Command.BACKWARD;
import static com.mars.rover.command.Command.FORWARD;
import static com.mars.rover.command.Command.LEFT;
import static com.mars.rover.command.Command.RIGHT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {

    CommandParser commandParser = new CommandParser();

    @Test
    public void commandParserShouldParseSingleForwardCommand() {
        final var commandString = "F";

        final var command = commandParser.parse(commandString);

        assertThat(command.size(), is(1));
        assertThat(command.get(0), is(FORWARD));
    }

    @Test
    public void commandParserShouldParseSeveralCommands() {
        final var commandString = "FFBLBR";

        final var command = commandParser.parse(commandString);

        assertThat(command.size(), is(6));
        assertThat(command.get(0), is(FORWARD));
        assertThat(command.get(1), is(FORWARD));
        assertThat(command.get(2), is(BACKWARD));
        assertThat(command.get(3), is(LEFT));
        assertThat(command.get(4), is(BACKWARD));
        assertThat(command.get(5), is(RIGHT));
    }

    @Test
    public void commandParserShouldFailWhenUnknownCommand() {
        final var commandString = "FX";
        final var exception = assertThrows(IllegalArgumentException.class, () -> {
            commandParser.parse(commandString);
        });
        assertThat(exception.getMessage(), is("Can not parse command string FX"));
    }

    @Test
    public void commandParserShouldParseSingleBackwardCommand() {
        final var commandString = "B";

        final var command = commandParser.parse(commandString);

        assertThat(command.size(), is(1));
        assertThat(command.get(0), is(BACKWARD));
    }
}