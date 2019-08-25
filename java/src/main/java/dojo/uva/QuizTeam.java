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
            double minDist = getMinDist(0, dists, dists.length);
            results.put(key, minDist);
        }
    }

    public double getMinDist(int bitmask, double[][] dists, int size) {
        int target = (1 << size) - 1;
        if (bitmask == target) return 0.0;
        int p1, p2;
        double minDist = Double.MAX_VALUE;
        for (p1 = 0; p1 < size; p1++) {
            if (((1 << p1) & bitmask) == 0) break;
        }
        for (p2 = p1 + 1; p2 < size; p2++) {
            if (((1 << p2) & bitmask) == 0) {
                int newBitmask = bitmask | (1 << p1) | (1 << p2);
                double dist = dists[p1][p2] + getMinDist(newBitmask, dists, size);
                if (dist < minDist) minDist = dist;
            }
        }
        return minDist;
    }

//    public List<List<String>> getAllTeams(int n) {
//        boolean[] slots = new boolean[n];
//        List<List<String>> allTeams = new ArrayList<>();
//        List<String> teams = new ArrayList<>();
//        getAllTeams(slots, teams, allTeams);
//        return allTeams;
//    }
//
//    private void getAllTeams(boolean[] slots, List<String> teams, List<List<String>> allTeams) {
//        int i = getFirstAvailable(slots);
//        if (i == -1) {
//            allTeams.add(new ArrayList<>(teams));
//            return;
//        }
//        slots[i] = true;
//        for (int j = i + 1; j < slots.length; j++) {
//            if (!slots[j]) {
//                slots[j] = true;
//                teams.add(i + "," + j);
//                getAllTeams(slots, teams, allTeams);
//                teams.remove(i + "," + j);
//                slots[j] = false;
//            }
//        }
//        slots[i] = false;
//    }

//    private int getFirstAvailable(boolean[] slots) {
//        for (int i = 0; i < slots.length; i++) {
//            if (!slots[i]) return i;
//        }
//        return -1;
//    }

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
