
import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int n = scanner.nextInt();
        while (n-- > 0) {
            stack.offerLast(scanner.nextInt());
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }
    }
}