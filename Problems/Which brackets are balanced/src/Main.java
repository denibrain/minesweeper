import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Deque<Character> stack = new ArrayDeque<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        String sequence = scanner.nextLine();
        boolean balanced = true;
        for(int i = 0; i < sequence.length(); i++) {
            Character a = sequence.charAt(i);
            if (map.values().contains(a)) {
                stack.offerLast(a);
            } else {
                if (stack.isEmpty() || !map.get(a).equals(stack.pollLast())) {
                    balanced = false;
                    break;
                }
            }

        }
        System.out.println(balanced && stack.isEmpty());
    }
}