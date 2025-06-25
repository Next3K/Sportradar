package org.example;

import lombok.Getter;

import java.util.*;
import java.util.function.Function;

public class ScoreBoard {

    @Getter
    private final SortedMap<Integer, SortedSet<Game>> board;
    private final Comparator<Game> byStartTime = Comparator.comparingLong(Game::startTime).reversed();

    public ScoreBoard() {
        this.board = new TreeMap<>(Comparator.reverseOrder());
    }

    public ScoreBoard(Game... games) {
        this();
        for (var game : games) {
            this.addGameToBoard(game);
        }
    }

    /**
     * Starts game between two chosen teams.
     * @param homeTeam - home ground team.
     * @param awayTeam - away team.
     * @return returns new Game.
     */
    public Game startGame(Team homeTeam, Team awayTeam) {
        Game game = new Game(System.currentTimeMillis(), homeTeam, awayTeam, 0, 0);
        while (this.contains(game)) { // wait for about one millisecond
            game = new Game(System.currentTimeMillis(), homeTeam, awayTeam, 0, 0);
        }
        addGameToBoard(game);
        return game;
    }

    /**
     * Ends chosen game and removes it from the board.
     * @param game - the game to be ended.
     */
    public void endGame(Game game) {
        removeGameFromBoard(game);
    }


    /**
     * Update chosen game.
     * @param game - game to be updated.
     * @param newHomeScore - new goal score for home team.
     * @param newAwayScore - new goal score for away team.
     * @return updated game
     */
    public Game updateScore(Game game, int newHomeScore, int newAwayScore) {
        removeGameFromBoard(game);
        Game newGame = new Game(game.startTime(), game.homeTeam(), game.awayTeam(), newHomeScore, newAwayScore);
        addGameToBoard(newGame);
        return newGame;
    }

    /**
     * Provides overall summary of game board state ordered by total number of goals.
     * @return List of Strings where each String represents one match.
     */
    public List<String> getSummaryOfMatches() {
        List<String> res = new ArrayList<>();
        for (var entry : this.board.entrySet()) {
            var set = entry.getValue();
            Function<Game, String> gameStrMapper =
                    g -> g.homeTeam() + " - " + g.awayTeam() + ": " + g.homeScore() + " - " + g.awayScore();
            res.addAll(set.stream().map(gameStrMapper).toList());
        }
        return res;
    }

    private boolean contains(Game game) {
        int total = game.awayScore() + game.homeScore();
        return this.board.containsKey(total) && this.board.get(total).contains(game);
    }

    private void removeGameFromBoard(Game game) {
        int total = game.awayScore() + game.homeScore();
        this.board.get(total).remove(game);
        if (this.board.get(total).isEmpty()) {
            this.board.remove(total);
        }
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
