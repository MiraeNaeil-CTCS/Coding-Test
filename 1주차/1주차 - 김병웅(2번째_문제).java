// 백준 17822: 원판 돌리기
// 알고리즘 분류: 구현

// 시간 초과가 날까봐 윗 방향의 인덱스를 저장하는 것으로 원판 회전 구현
// 다른 풀이 보니 그냥 Array로 구현해도 됐을거같다..
// 이후 모든 수에 대하여 인접한 수를 확인 (제일 바깥쪽 원판과 안쪽 원판은 인접한 수 3개)
// 인접하고 같은 수가 있으면 모두 지우고 없으면 평균을 구함

import java.io.*;
import java.util.*;

public class Main { // 1 1 2 3 -> 3 1 1 2

    static int N, M, T;
    static int[] centers;
    static int[][] circles;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        centers = new int[N];
        circles = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                circles[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < T; i++) {
            input = br.readLine().split(" ");

            int x = Integer.parseInt(input[0]);
            int d = Integer.parseInt(input[1]);
            int k = Integer.parseInt(input[2]);

            rotate(x, d, k);

            List<Point> closePoint = getClosePoint();

            if (closePoint.isEmpty()) {
                int sum = 0;
                int count = 0;
                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (circles[j][l] != -1) {
                            sum += circles[j][l];
                            count++;
                        }
                    }
                }

                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < M; l++) {
                        if (circles[j][l] != -1) {
                            if (circles[j][l] > (double)sum / count) {
                                circles[j][l]--;
                            } else if (circles[j][l] < (double)sum / count) {
                                circles[j][l]++;
                            }
                        }
                    }
                }
            } else {
                for (Point point: closePoint) {
                    circles[point.x][point.y] = -1;
                }
            }
        }
        
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circles[i][j] != -1) sum += circles[i][j];
            }
        }

        System.out.println(sum);
    }

    static void rotate(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            centers[i - 1] += d == 0 ? (M - k) % M : k % M;
        }
    }

    static List<Point> getClosePoint() {
        List<Point> closePoint = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = centers[i]; j < centers[i] + M; j++) {
                boolean isInsert = false;
                int jndex = j % M;
                if (circles[i][jndex] == -1) continue;

                Point left = new Point(i, jndex == 0 ? M - 1 : jndex - 1);
                Point right = new Point(i, (jndex + 1) % M);
                Point top;
                Point bottom;

                if (i == 0) {
                    bottom = null;
                    jndex = (centers[i + 1] + (j - centers[i])) % M;
                    top = new Point(i + 1, jndex);
                } else if (i == N - 1) {
                    top = null;
                    jndex = (centers[i - 1] + (j - centers[i])) % M;
                    bottom = new Point(i - 1, jndex);
                } else {
                    jndex = (centers[i + 1] + (j - centers[i])) % M;
                    top = new Point(i + 1, jndex);

                    jndex = (centers[i - 1] + (j - centers[i])) % M;
                    bottom = new Point(i - 1, jndex);
                }

                jndex = j % M;
                if (circles[i][jndex] == circles[left.x][left.y]) {
                    closePoint.add(left);
                    if (!isInsert) {
                        closePoint.add(new Point(i, jndex));
                        isInsert = true;
                    }
                }
                if (circles[i][jndex] == circles[right.x][right.y]) {
                    closePoint.add(right);
                    if (!isInsert) {
                        closePoint.add(new Point(i, jndex));
                        isInsert = true;
                    }
                }
                if (top != null && circles[i][jndex] == circles[top.x][top.y]) {
                    closePoint.add(top);
                    if (!isInsert) {
                        closePoint.add(new Point(i, jndex));
                        isInsert = true;
                    }
                }
                if (bottom != null && circles[i][jndex] == circles[bottom.x][bottom.y]) {
                    closePoint.add(bottom);
                    if (!isInsert) {
                        closePoint.add(new Point(i, jndex));
                        isInsert = true;
                    }
                }
            }
        }

        return closePoint;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}