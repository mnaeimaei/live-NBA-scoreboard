package com.milad.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    @Test
    void newMatchStartsWithZeroScores() {
        Match match = new Match("Lakers", "Celtics", 1);

        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
        assertEquals(0, match.getTotalScore());
    }

    @Test
    void setScoreUpdatesScoresAndTotal() {
        Match match = new Match("Lakers", "Celtics", 1);

        match.setScore(101, 99);

        assertEquals(101, match.getHomeScore());
        assertEquals(99, match.getAwayScore());
        assertEquals(200, match.getTotalScore());
    }

    @Test
    void summaryReturnsExpectedFormat() {
        Match match = new Match("Lakers", "Celtics", 1);
        match.setScore(101, 99);

        assertEquals("Lakers 101 - Celtics 99", match.summary());
    }
}