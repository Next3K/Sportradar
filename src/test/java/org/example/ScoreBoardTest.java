package org.example;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {


    @Test
    public void startGameEmptyBoardTest(){
        // given
        ScoreBoard emptyBoard = new ScoreBoard();

        // when
        Game game = emptyBoard.startGame(Team.AUSTRALIA, Team.ARGENTINA);

        // then
        var firstGame = emptyBoard.getBoard().get(0).first();
        assertEquals(firstGame, game);
    }


    @Test
    public void startGameNonEmptyBoardTest(){
        // given
        LocalDateTime futureDate = LocalDateTime.of(2050, 1, 1, 0, 0);
        long millisFuture = futureDate.toInstant(ZoneOffset.UTC).toEpochMilli();

        var oldestGame =  new Game(11L, Team.ECUADOR, Team.ARGENTINA, 0, 0);
        var newsetGame = new Game(millisFuture, Team.POLAND, Team.PORTUGAL, 0, 0);
        ScoreBoard emptyBoard = new ScoreBoard(oldestGame, newsetGame);

        // when
        Game game = emptyBoard.startGame(Team.AUSTRALIA, Team.SOUTH_KOREA);

        // then
        var gamesSorted = emptyBoard.getBoard().get(0).stream().limit(3).toList();
        assertEquals(gamesSorted, List.of(newsetGame, game, oldestGame));
    }
}