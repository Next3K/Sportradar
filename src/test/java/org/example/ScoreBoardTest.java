package org.example;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {


    @Test
    public void testStartGameEmptyBoard(){
        // given
        ScoreBoard emptyBoard = new ScoreBoard();

        // when
        Game game = emptyBoard.startGame(Team.AUSTRALIA, Team.ARGENTINA);

        // then
        var firstGame = emptyBoard.getBoard().get(0).first();
        assertEquals(firstGame, game);
    }


    @Test
    public void testStartGameNonEmptyBoard(){
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

    @Test
    public void testEndGameSparseBoard() {
        // given
        ScoreBoard sparseBoard = new ScoreBoard();
        Game g1 = sparseBoard.startGame(Team.AUSTRALIA, Team.SOUTH_KOREA);
        int newHomeScore = 2;
        int newAwayScore = 1;
        g1 = sparseBoard.updateScore(g1, newHomeScore, newAwayScore);

        // when
        sparseBoard.endGame(g1);

        // then
        assertFalse(sparseBoard.getBoard().containsKey(newAwayScore + newHomeScore));
    }

    @Test
    public void testEndGameFullBoard() {
        // given
        ScoreBoard board = new ScoreBoard();
        Game g1 = board.startGame(Team.AUSTRALIA, Team.SOUTH_KOREA);
        Game g2 = board.startGame(Team.POLAND, Team.PORTUGAL);
        Game g3 = board.startGame(Team.FRANCE, Team.JAPAN);
        int total = 0;

        // when
        board.endGame(g2);

        // then
        var theGames = board.getBoard().get(total).stream().toList();
        assertEquals(theGames, List.of(g3, g1));
    }

    @Test
    public void testSparseBoardUpdated() {
        // given
        ScoreBoard sparseBoard = new ScoreBoard();
        Game g1 = sparseBoard.startGame(Team.AUSTRALIA, Team.SOUTH_KOREA);
        int newHomeScore = 2;
        int newAwayScore = 1;

        // when
        g1 = sparseBoard.updateScore(g1, newHomeScore, newAwayScore);

        // then
        var theGames = sparseBoard.getBoard().get(newAwayScore + newHomeScore).stream().toList();
        assertEquals(theGames, List.of(g1));
    }

    @Test
    public void testWhenGameUpdatedThenOtherListIsValid() {
        // given
        ScoreBoard board = new ScoreBoard();
        Game g1 = board.startGame(Team.AUSTRALIA, Team.SOUTH_KOREA);
        Game g2 = board.startGame(Team.POLAND, Team.PORTUGAL);
        Game g3 = board.startGame(Team.FRANCE, Team.JAPAN);
        int newHomeScore = 2;
        int newAwayScore = 1;

        // when
         board.updateScore(g2, newHomeScore, newAwayScore);

        // then
        var otherGamesList = board.getBoard().get(0).stream().toList();
        assertEquals(otherGamesList, List.of(g3, g1));

    }


    @Test
    public void testWhenGameUpdatedThenNewListIsValid() {
        // given
        ScoreBoard board = new ScoreBoard();
        int newHomeScore = 2;
        int newAwayScore = 1;
        Game g2 = board.startGame(Team.POLAND, Team.PORTUGAL);
        Game g4 = board.startGame(Team.MEXICO, Team.MOROCCO);
        g4 = board.updateScore(g4, newHomeScore, newAwayScore);

        // when
        g2 = board.updateScore(g2, newHomeScore, newAwayScore);

        // then
        var newList = board.getBoard().get(newAwayScore + newHomeScore).stream().toList();
        assertEquals(newList, List.of(g4, g2));
    }


    @Test
    public void testSummaryIsValid() {
        // given
        ScoreBoard board = new ScoreBoard();
        board.startGame(Team.MEXICO, Team.CANADA);
        board.startGame(Team.SPAIN, Team.BRAZIL);
        var g3 = board.startGame(Team.GERMANY, Team.FRANCE);
        var g4 = board.startGame(Team.URUGUAY, Team.PORTUGAL);
        var g5 = board.startGame(Team.ARGENTINA, Team.SAUDI_ARABIA);
        var g6 = board.startGame(Team.POLAND, Team.JAPAN);
        board.updateScore(g3, 1, 0);
        board.updateScore(g4, 0, 1);
        board.updateScore(g5, 2, 0);
        board.updateScore(g6, 0, 2);

        // when
        var res = board.getSummaryOfMatches();

        // then
        assertEquals(res, List.of(
                "POLAND - JAPAN: 0 - 2",
                "ARGENTINA - SAUDI_ARABIA: 2 - 0",
                "URUGUAY - PORTUGAL: 0 - 1",
                "GERMANY - FRANCE: 1 - 0",
                "SPAIN - BRAZIL: 0 - 0",
                "MEXICO - CANADA: 0 - 0"));
    }
}