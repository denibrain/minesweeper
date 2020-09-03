import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        int [][]s = new int[n][m];
        for(int i = 0; i < n; i++) {
            s[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        int k = scanner.nextInt();
        int foundRow = getFoundRow(n, m, s, k);
        System.out.println(foundRow);
    }

    private static int getFoundRow(int n, int m, int[][] s, int k) {
        for(int i = 0; i < n; i++) {
            int r = 0;
            for(int j = 0; j < m; j++) {
                if (s[i][j] == 0) {
                    r++;
                    if (r == k) {
                        return i + 1;
                    }
                } else {
                    r = 0;
                }
            }
        }
        return 0;
    }
}