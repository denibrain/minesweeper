import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextByte();
        int [][]m = new int[n][n];
        m[0][0] = 1;
        if (n > 1) {
            int dx = 1;
            int dy = 0;
            int x = 1;
            int y = 0;
            int r = 0;
            int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            int total = n*n;
            for (int a = 2; a <= total; a++) {
                m[y][x] = a;
                int newY = y + dy;
                int newX = x + dx;
                if (newX >= 0 && newY >= 0 && newX < n && newY < n && m[newY][newX] == 0) {
                    x = newX;
                    y = newY;
                } else {
                    r++;
                    if (r == directions.length) {
                        r = 0;
                    }
                    dx = directions[r][0];
                    dy = directions[r][1];
                    x += dx;
                    y += dy;
                }

            }
        }
        for(int[] row: m) {
            System.out.println(Arrays.stream(row)
                    .mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        }
    }
}