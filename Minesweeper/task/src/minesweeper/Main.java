package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.print("How many mines do you want on the field? ");
        Scanner scanner = new Scanner(System.in);
        int mines = scanner.nextInt();
        if (mines < 0) {
            mines = 0;
        }
        if (mines > 81) {
            mines = 81;
        }
        Field field = new Field(9, 9, mines);
        if (mines == 0) {
            return;
        }
        int error = 0;
        while (true) {
            if (error == 1) {
                error = 0;
            } else {
                field.output();
            }
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String operation = scanner.nextLine().trim();
            if (!operation.equals("mine") && !operation.equals("free")) {
                System.out.println("Invalid operation: ");
                error = 1;
                continue;
            }
            if (x < 1 || x > 9 || y < 1 || y > 9) {
                System.out.println("Invalid coordinates: ");
                error = 1;
                continue;
            }
            if (operation.equals("mine")) {
                if (!field.canToggleMark(x - 1, y - 1)) {
                    System.out.println("There is a number here!");
                    error = 1;
                    continue;
                }
                field.toggleMark(x - 1, y - 1);
            } else  {
                if (!field.canSetFree(x - 1, y - 1)) {
                    System.out.println("There is a number here!");
                    error = 1;
                    continue;
                }
                if (!field.setFree(x - 1, y - 1)) {
                    field.output();
                    System.out.println("You stepped on a mine and failed!");
                    break;
                }
            }

            if (field.correct()) {
                field.output();
                System.out.println("Congratulations! You found all mines!");
                break;
            }
        }

    }
}
