package org.example;

import lombok.Getter;

import java.util.*;

public class ScoreBoard {

    @Getter
    private final Map<Integer, SortedSet<Game>> board;
    private final Comparator<Game> byStartTime = Comparator.comparingLong(Game::startTime).reversed();

    public ScoreBoard() {
        this.board = new HashMap<>();
    }

    public ScoreBoard(Game... games) {
            this();
            for (var game: games) {
               this.addGameToBoard(game);
            }
    }

    public Game startGame(Team homeTeam, Team awayTeam) {
        Game game = new Game(System.currentTimeMillis(), homeTeam, awayTeam, 0, 0);
        addGameToBoard(game);
        return game;
    }


    private void addGameToBoard(Game game) {
        var total = game.awayScore() + game.homeScore();
        if (this.board.containsKey(total)) {
            SortedSet<Game> treeSet = this.board.get(total);
            treeSet.add(game);
        } else {
            SortedSet<Game> byStartTime = new TreeSet<>(this.byStartTime);
            byStartTime.add(game);
            this.board.put(total, byStartTime);
        }

    }
}
