package dojo.uva;

import java.io.InputStream;
import java.util.*;

/**
 * UVA 20911 - Quiz Team
 *
 * Sample Input
 *
 * 5
 * sohel 10 10
 * mahmud 20 10
 * sanny 5 5
 * prince 1 1
 * per 120 3
 * mf 6 6
 * kugel 50 60
 * joey 3 24
 * limon 6 9
 * manzoor 0 0
 * 1
 * derek 9 9
 * jimmy 10 10
 * 0
 *
 * Sample Output
 *
 * Case 1: 118.40
 * Case 2: 1.41
 */
public class QuizTeam {
    protected Map<String, Record[]> data;
    protected Map<String, Double> results;

    class Record {
        String id;
        int x;
        int y;

        Record(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        QuizTeam quizTeam = new QuizTeam();
        quizTeam.readData(System.in);
        quizTeam.solve();
        quizTeam.printResults();
    }

    public void printResults() {
        for (String key : results.keySet())   {
            System.out.printf("%s: %.2f\n", key, results.get(key));
        }
    }

    public void solve() {
        results = new LinkedHashMap<>();
        for (String key : data.keySet()) {
            double[][] dists = calculateDists(data.get(key));
            double minDist = Double.MAX_VALUE;
            for (List<String> teams : getAllTeams(data.get(key).length)) {
                double dist = 0.0;
                for(String team : teams) {
                    int i = Integer.valueOf(team.split(",")[0]);
                    int j = Integer.valueOf(team.split(",")[1]);
                    dist += dists[i][j];
                }
                if (dist < minDist) minDist = dist;
            }
            results.put(key, minDist);
        }
    }

    public List<List<String>> getAllTeams(int n) {
        boolean[] slots = new boolean[n];
        List<List<String>> allTeams = new ArrayList<>();
        List<String> teams = new ArrayList<>();
        getAllTeams(slots, teams, allTeams);
        return allTeams;
    }

    private void getAllTeams(boolean[] slots, List<String> teams, List<List<String>> allTeams) {
        int i = getFirstAvailable(slots);
        if (i == -1) {
            allTeams.add(new ArrayList<>(teams));
            return;
        }
        slots[i] = true;
        for (int j = i + 1; j < slots.length; j++) {
            if (!slots[j]) {
                slots[j] = true;
                teams.add(i + "," + j);
                getAllTeams(slots, teams, allTeams);
                teams.remove(i + "," + j);
                slots[j] = false;
            }
        }
        slots[i] = false;
    }

    private int getFirstAvailable(boolean[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (!slots[i]) return i;
        }
        return -1;
    }

    public double[][] calculateDists(Record[] records) {
        double[][] dists = new double[records.length][records.length];
        for (int i = 0; i < records.length; i++) {
            for (int j = i+1; j < records.length; j++) {
                int dx = records[i].x - records[j].x;
                int dy = records[i].y - records[j].y;
                dists[i][j] = Math.sqrt(dx*dx + dy*dy);
            }
        }
        return dists;
    }

    public void readData(InputStream inputStream) {
        data = new LinkedHashMap<>();
        Scanner scanner = new Scanner(inputStream);
        int size = scanner.nextInt();
        int n = 1;
        while (size != 0) {
            String caseId = "Case " + n++;
            data.put(caseId, new Record[size*2]);
            for (int i = 0; i < size*2; i++) {
                String id = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                data.get(caseId)[i] = (new Record(id, x, y));
            }
            size = scanner.nextInt();
        }
        scanner.close();
    }
}
