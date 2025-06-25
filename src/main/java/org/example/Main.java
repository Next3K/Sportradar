package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting World Cup");

        ScoreBoard board = new ScoreBoard();

        long start = System.nanoTime();

        // current games
        var m1 = board.startGame(Team.MEXICO, Team.CANADA);
        var m2 = board.startGame(Team.SPAIN, Team.BRAZIL);
        var m3 = board.startGame(Team.GERMANY, Team.FRANCE);
        var m4 = board.startGame(Team.URUGUAY, Team.PORTUGAL);
        var m5 = board.startGame(Team.ARGENTINA, Team.SAUDI_ARABIA);
        var m6 = board.startGame(Team.POLAND, Team.JAPAN);

        // first score update
        m1 = board.updateScore(m1, 0, 5);
        m2 = board.updateScore(m2, 2, 3);
        m3 = board.updateScore(m3, 2, 2);
        m4 = board.updateScore(m4, 1, 1);
        m5 = board.updateScore(m5, 0, 1);
        m6 = board.updateScore(m6, 1, 0);


        System.out.println("----------- Current Games -----------");
        board.getSummaryOfMatches().forEach(System.out::println);
        System.out.println("------------------------------------- \n\n");

        // second score update
        m1 = board.updateScore(m1, 0, 6);
        m2 = board.updateScore(m2, 4, 3);
        m3 = board.updateScore(m3, 3, 2);
        m4 = board.updateScore(m4, 2, 1);
        m5 = board.updateScore(m5, 1, 1);
        m6 = board.updateScore(m6, 1, 1);

        System.out.println("----------- Current Games -----------");
        board.getSummaryOfMatches().forEach(System.out::println);
        System.out.println("------------------------------------- \n\n");

        // end some games
        board.endGame(m1);
        board.endGame(m2);
        board.endGame(m3);
        board.endGame(m4);

        // third game update
        m5 = board.updateScore(m5, 1, 3);
        m6 = board.updateScore(m6, 3, 2);

        System.out.println("----------- Current Games -----------");
        board.getSummaryOfMatches().forEach(System.out::println);
        System.out.println("------------------------------------- \n\n");

        board.endGame(m5);
        board.endGame(m6);

        System.out.println("Finished in: " + ((System.nanoTime() - start) / 1_000_000) + " ms");

    }
}