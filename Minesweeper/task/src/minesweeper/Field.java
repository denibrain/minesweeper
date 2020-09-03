package minesweeper;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Field {

    private final int[][]map;
    private final int width;
    private final int height;
    private final int mineCount;
    private int hidden = 0;
    private int correctMarked = 0;
    private boolean mineTouched = false;

    public Field(int width, int height, int mineCount) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.hidden = height * width;
        map = fill(width, height, mineCount);
    }

    @NotNull
    private int[][] fill(int width, int height, int mineCount) {
        final int[][] map = new int[height][width];
        Random r = new Random();
        int fillValue = 0x10;
        if (mineCount > (width * height / 2 + 1)) {
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    map[i][j] = 0x10;
                }
            }
            mineCount = width * height - mineCount;
            fillValue = 0;
        }
        for(int i = 0; i < mineCount; i++) {
            while (true) {
                int y = r.nextInt(height);
                int x = r.nextInt(width);
                if (map[y][x] != fillValue) {
                    map[y][x] = fillValue;
                    break;
                }
            }
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = (i > 0 ? sumMines(map[i-1], j) : 0)
                            + sumMines(map[i], j)
                            + (i < height - 1 ? sumMines(map[i+1], j) : 0);
                }
            }
        }
        return map;
    }

    private int sumMines(@NotNull int[] row, int j) {
        return (j > 0 && row[j - 1] == 0x10 ? 1 : 0) +
                (row[j] == 0x10 ? 1 : 0) +
                (j < width - 1 && row[j + 1] == 0x10 ? 1 : 0);
    }

    public void output() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for(int i = 0; i < height; i++) {
            System.out.println(((i + 1) % 10) + "|" + Arrays.stream(map[i])
                    .mapToObj(this::displayCell)
                    .collect(Collectors.joining("")) + "|");
        }
        System.out.println("—│—————————│");
    }

    public boolean canToggleMark(int x, int y) {
        return (map[y][x] & 0x80) == 0;
    }

    public void toggleMark(int x, int y) {
        map[y][x] ^= 0x20;
        int v = map[y][x] & 0x30;
        if (v == 0x30) {
            correctMarked++;
        } else
        if (v == 0x10) {
            correctMarked--;
        }
    }

    public boolean canSetFree(int x, int y) {
        return (map[y][x] & 0x80) == 0;
    }
    
    public boolean setFree(int x, int y) {
        if ((map[y][x] & 0x80) != 0) {
            return true;
        }
        hidden--;
        map[y][x] |= 0x80;
        if ((map[y][x] & 0x10) != 0) {
            mineTouched = true;
            return false;
        }
        if ((map[y][x] & 0x20) != 0) {
            map[y][x] ^= 0x20;
        }

        int v = map[y][x] & 0xF;
        if (v == 0) {
            boolean y0 = y > 0;
            boolean y1 = y < height - 1;
            if (x > 0) {
                if (y0 && (map[y - 1][x - 1] & 0x80) == 0) setFree(x - 1, y - 1);
                if ((map[y][x - 1] & 0x80) == 0)setFree(x - 1, y);
                if (y1 && (map[y + 1][x - 1] & 0x80) == 0) setFree(x - 1, y + 1);
            }
            if (x < width - 1) {
                if (y0 && (map[y - 1][x + 1] & 0x80) == 0) setFree(x + 1, y - 1);
                if ((map[y][x + 1] & 0x80) == 0) setFree(x + 1, y);
                if (y1 && (map[y + 1][x + 1] & 0x80) == 0) setFree(x + 1, y + 1);
            }
            if (y0 && (map[y - 1][x] & 0x80) == 0) setFree(x, y - 1);
            if (y1 && (map[y + 1][x] & 0x80) == 0) setFree(x, y + 1);
        }
        return true;
    }

    public boolean correct() {
        return correctMarked == mineCount || hidden == mineCount;
    }

    private String displayCell(int v) {
        if ((v & 0x10) != 0 && mineTouched) {
            return "X";
        }
        if ((v & 0x20) != 0) {
            return "*";
        }
        if ((v & 0x80) == 0) {
            return ".";
        }
        v &= 0xF;
        return v > 0 && v < 9 ? Integer.toString(v) : "/";
    }
}
