package com.mars.rover;

import com.mars.rover.command.CommandParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mars.rover.command.Command.FORWARD;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MarsMissionTest {

    @Test
    public void applicationShouldDecodeCommandsAndSendThemToRover() {
        final var commandParserMock = mock(CommandParser.class);
        final var roverMock = mock(Rover.class);
        when(commandParserMock.parse("ABCDE")).thenReturn(List.of(FORWARD));

        new MarsMission(commandParserMock, roverMock).runMission("ABCDE");

        verify(roverMock).apply(List.of(FORWARD));
    }
}