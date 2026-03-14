package com.milad.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiveNBAScoreboardTest {

    @Test
    void canInstantiateScoreboard() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();
        assertNotNull(scoreboard);
    }

    @Test
    void scoreboardStartsEmpty() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();
        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void startMatchAddsAMatch() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");

        assertEquals(1, scoreboard.getSummary().size());
    }

    @Test
    void newMatchStartsAtZeroZero() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");

        assertEquals("Lakers 0 - Celtics 0", scoreboard.getSummary().get(0));
    }

    @Test
    void canUpdateMatchScore() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.updateScore("Lakers", "Celtics", 102, 98);

        assertEquals("Lakers 102 - Celtics 98", scoreboard.getSummary().get(0));
    }

    @Test
    void finishMatchRemovesAMatch() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.finishMatch("Lakers", "Celtics");

        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void summaryIsSortedByTotalScoreDescending() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.startMatch("Bulls", "Heat");

        scoreboard.updateScore("Lakers", "Celtics", 110, 100); // total 210
        scoreboard.updateScore("Bulls", "Heat", 90, 85);       // total 175

        List<String> summary = scoreboard.getSummary();

        assertEquals("Lakers 110 - Celtics 100", summary.get(0));
        assertEquals("Bulls 90 - Heat 85", summary.get(1));
    }

    @Test
    void tieBreakerIsMostRecentStart() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.startMatch("Bulls", "Heat");

        scoreboard.updateScore("Lakers", "Celtics", 100, 100); // total 200
        scoreboard.updateScore("Bulls", "Heat", 105, 95);      // total 200

        List<String> summary = scoreboard.getSummary();

        assertEquals("Bulls 105 - Heat 95", summary.get(0));
        assertEquals("Lakers 100 - Celtics 100", summary.get(1));
    }

    @Test
    void updateNonExistingMatchDoesNothing() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.updateScore("Lakers", "Celtics", 100, 99);

        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void finishNonExistingMatchReturnsFalse() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        boolean removed = scoreboard.finishMatch("Lakers", "Celtics");

        assertFalse(removed);
    }

    @Test
    void duplicateMatchStartDoesNothing() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.startMatch("Lakers", "Celtics");

        List<String> summary = scoreboard.getSummary();

        assertEquals(1, summary.size());
        assertEquals("Lakers 0 - Celtics 0", summary.get(0));
    }

    @Test
    void sameTeamMatchIsIgnored() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Lakers");

        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void negativeScoresAreIgnored() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch("Lakers", "Celtics");
        scoreboard.updateScore("Lakers", "Celtics", -1, 90);

        assertEquals("Lakers 0 - Celtics 0", scoreboard.getSummary().get(0));
    }

    @Test
    void nullOrBlankTeamNamesAreIgnored() {
        LiveNBAScoreboard scoreboard = new LiveNBAScoreboard();

        scoreboard.startMatch(null, "Celtics");
        scoreboard.startMatch("Lakers", null);
        scoreboard.startMatch("", "Celtics");
        scoreboard.startMatch("Lakers", "  ");

        assertTrue(scoreboard.getSummary().isEmpty());
    }
}