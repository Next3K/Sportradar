package org.example;

public record Game(Long startTime, Team homeTeam, Team awayTeam, Integer homeScore, Integer awayScore) {
}
