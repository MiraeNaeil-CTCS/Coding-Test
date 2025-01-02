// 백준 14502: 연구소
// 알고리즘 분류: DFS, BFS

// 3 <= N, M <= 8, 벽 3개로 널널한 제한 사항
// DFS로 3개의 벽 모든 경우의 수 확인
// 벽 경우의 수마다 BFS로 바이러스 퍼짐 구현
// 지도를 원래대로 원상복귀

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, answer = 0;
    static int[][] map;
    static boolean[][] visited;
    static List<Virus> vira;

    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n][m];
        visited = new boolean[n][m];
        vira = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                if (map[i][j] != 0) visited[i][j] = true;
                if (map[i][j] == 2) vira.add(new Virus(i, j));
            }
        }

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            bfs();

            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    map[i][j] = 1;

                    dfs(depth + 1);

                    visited[i][j] = false;
                    map[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {

        Queue<Virus> queue = new LinkedList<>();
        List<Virus> visitedList = new ArrayList<>();

        for (Virus v: vira) queue.offer(v);

        while (!queue.isEmpty()) {
            Virus virus = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = virus.x + dx[i];
                int nextY = virus.y + dy[i];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && !visited[nextX][nextY]) {
                    queue.offer(new Virus(nextX, nextY));
                    map[nextX][nextY] = 2;
                    visited[nextX][nextY] = true;
                    visitedList.add(new Virus(nextX, nextY));
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) count++;
            }
        }

        answer = Math.max(count, answer);

        for (Virus visit: visitedList) {
            visited[visit.x][visit.y] = false;
            map[visit.x][visit.y] = 0;
        }
    }
}

class Virus {
    int x = 0;
    int y = 0;

    Virus(int x, int y) {
        this.x = x;
        this.y = y;
    }
}