package com.milad.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LiveNBAScoreboard {

    private final Map<String, Match> matches = new LinkedHashMap<>();
    private long sequence = 0;

    public void startMatch(String home, String away) {
        if (!isValidTeamName(home) || !isValidTeamName(away) || home.equals(away)) {
            return;
        }

        String key = matchKey(home, away);
        if (matches.containsKey(key)) {
            return;
        }

        sequence++;
        matches.put(key, new Match(home, away, sequence));
    }

    public void updateScore(String home, String away, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            return;
        }

        Match match = matches.get(matchKey(home, away));
        if (match == null) {
            return;
        }

        match.setScore(homeScore, awayScore);
    }

    public boolean finishMatch(String home, String away) {
        return matches.remove(matchKey(home, away)) != null;
    }

    public List<String> getSummary() {
        List<Match> sortedMatches = new ArrayList<>(matches.values());

        sortedMatches.sort(
                Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparingLong(Match::getStartOrder).reversed())
        );

        List<String> summary = new ArrayList<>();
        for (Match match : sortedMatches) {
            summary.add(match.summary());
        }
        return summary;
    }

    private String matchKey(String home, String away) {
        return home + "||" + away;
    }

    private boolean isValidTeamName(String team) {
        return team != null && !team.trim().isEmpty();
    }
}