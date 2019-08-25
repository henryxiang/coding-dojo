package dojo.uva;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class QuizTeamTest {

    private QuizTeam quizTeam;

    @Before
    public void init() {
        quizTeam = new QuizTeam();
    }

//    @Test
//    public void testGetAllTeams() {
//        int n = 16;
//        List<List<String>> allTeams = quizTeam.getAllTeams(n);
////        for (List<String> teams : allTeams) {
////            System.out.println(String.join("|", teams));
////        }
//        assertEquals(countTeamArrangements(n), allTeams.size());
//    }
//
//    private int countTeamArrangements(int n) {
//        int count = 1;
//        for(int i = n-1; i >= 1; i -= 2) count *= i;
//        return count;
//    }

    @Test
    public void testReadData() {
        InputStream inputStream = getClass().getResourceAsStream("QuizTeamData.txt");
        quizTeam.readData(inputStream);
        double[][] dists = quizTeam.calculateDists(quizTeam.data.get("Case 1"));
        assertTrue(Math.abs(dists[0][1] - 10) < 1e-9);
        assertTrue(Math.abs(dists[2][3] - Math.sqrt(32)) < 1e-9);
    }

    @Test
    public void testSolve() {
        InputStream inputStream = getClass().getResourceAsStream("QuizTeamData-01.txt");
        quizTeam.readData(inputStream);
        quizTeam.solve();
        quizTeam.printResults();
//        assertTrue(1 == 1);
        assertEquals("118.40", String.format("%.2f", quizTeam.results.get("Case 1")));
        assertEquals("1.41", String.format("%.2f", quizTeam.results.get("Case 2")));
    }
}