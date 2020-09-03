import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String [][]matrix = new String[m][n];
        for(int i = 0; i < n; i++) {
            int j = 0;
            for(String v:scanner.nextLine().split(" ")) {
                matrix[j++][n - i - 1] = v;
            }
        }
        for(String[] row: matrix){
            System.out.println(String.join(" ", row));
        }
    }
}