// 백준 20057: 마법사 상어와 토네이도
// 알고리즘 분류: 구현

// 모래의 비율을 정하기 위한 Percent 클래스 구현 (방향에 따라 하드코딩)
// 토네이도의 방향은 1번 움직일때마다 바뀌고 이동 거리는 2번 움직일때마다 증가
// ex) 방향 - 0 > 1 > 2 > 3 > 0 > ... / 거리 - 1 > 1 > 2 > 2 > 3 > 3 > ...
// 마지막 거리는 N - 1까지 증가하고 마지막 거리는 3번 적용
// 거리가 1 1 > 1 > 2 > 2 > 3 > 3 > ... 순이 되도록 3중 for문을 구현
// 이후는 칸의 범위 체크 후 소멸되었는지 확인

import java.io.*;

public class Main {

    static int N, dir, x, y, sand;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        dir = 0;
        x = N / 2;
        y = N / 2;
        sand = 0;

        for (int i = 1; i < N; i++) {
            for (int l = 0; l < 2; l++) {
                for (int k = 0; k < i; k++) {
                    move();
                }
                dir = (dir + 1) % 4;
            }
        }

        for (int k = 0; k < N - 1; k++) {
            move();
        }

        System.out.println(sand);
    }

    static void move() {
        Percent percent = new Percent(dir);
        x += dx[dir];
        y += dy[dir];
        int sandSum = 0;

        for (int j = 0; j < 9; j++) {
            int nextX = x + percent.dx[j];
            int nextY = y + percent.dy[j];

            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                int sandTmp = board[x][y] * percent.ratio[j] / 100;
                board[nextX][nextY] += sandTmp;
                sandSum += sandTmp;
            } else {
                int sandTmp = board[x][y] * percent.ratio[j] / 100;
                sand += sandTmp;
                sandSum += sandTmp;
            }
        }

        board[x][y] -= sandSum;

        int nextX = x + percent.dx[9];
        int nextY = y + percent.dy[9];

        if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
            board[nextX][nextY] += board[x][y];
            board[x][y] = 0;
        } else {
            sand += board[x][y];
            board[x][y] = 0;
        }
    }
}

class Percent {
    int dir;
    int[] dx;
    int[] dy;
    int[] ratio;

    Percent(int dir) {
        if (dir == 0) {
            dx = new int[] {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
            dy = new int[] {0, -1, 0, 1, -2, -1, 0, 1, 0, -1};
            ratio = new int[] {2, 10, 7, 1, 5, 10, 7, 1, 2, -1};
            // 왼
            //     2
            //  10 7 1
            // 5 a y x
            //  10 7 1
            //     2
        } else if (dir == 1) {
            dx = new int[] {-1, -1, 0, 0, 0, 0, 1, 1, 2, 1};
            dy = new int[] {-1, 1, -2, -1, 1, 2, -1, 1, 0, 0};
            ratio = new int[] {1, 1, 2, 7, 7, 2, 10, 10, 5, -1};
            // 아래
            //   1 x 1
            // 2 7 y 7 2
            //  10 a 10
            //     5
        } else if (dir == 2) {
            dx = new int[] {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
            dy = new int[] {0, -1, 0, 1, 2, -1, 0, 1, 0, 1};
            ratio = new int[] {2, 1, 7, 10, 5, 1, 7, 10, 2, -1};
            // 오른
            //   2
            // 1 7 10
            // x y a 5
            // 1 7 10
            //   2
        } else if (dir == 3) {
            dx = new int[] {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1};
            dy = new int[] {0, -1, 1, -2, -1, 1, 2, -1, 1, 0};
            ratio = new int[] {5, 10, 10, 2, 7, 7, 2, 1, 1, -1};
            // 위
            //     5
            //  10 a 10
            // 2 7 y 7 2
            //   1 x 1
        }
    }
}