import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Battleship {

    public static void main(String[] args) {
        Battleship game = new Battleship();

        game.print();

        while (game.guess()) {
            game.print();
        }
    }

    public void print() {
        System.out.println("Misses: " + misses);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == Ship.MISS) {
                    System.out.print("O ");
                } else if (grid[i][j] == Ship.SUNK) {
                    System.out.print("X ");
                } else {
//                    System.out.print(grid[i][j].length + " ");
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    enum Ship {
        MISS(-2),
        SUNK(-1),
        EMPTY(0),
        SUBMARINE(3),
        CRUISER(4),
        BATTLESHIP(5);

        public final int length;

        Ship(int length) {
            this.length = length;
        }
    }

    public Ship[][] grid;

    public int height;
    public int width;
    public static Random random = new Random();

    public int misses;
    public int maxMisses;

    public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Battleship(int height, int width, int maxMisses) {
        this.height = height;
        this.width = width;
        this.maxMisses = maxMisses;

        grid = new Ship[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = Ship.EMPTY;
            }
        }

        for (Ship ship : Ship.values()) {
            placeShip:
            for (; ; ) {
                int originX = random.nextInt(width);
                int originY = random.nextInt(height);
                boolean horizontal = random.nextBoolean();

                for (int i = 0; i < ship.length; i++) {
                    int x = horizontal ? originX + i : originX;
                    int y = horizontal ? originY : originY + i;

                    if (x >= width || y >= height || grid[y][x] != Ship.EMPTY) {
                        continue placeShip;
                    }
                }
                // valid placement
                for (int i = 0; i < ship.length; i++) {
                    int x = horizontal ? originX + i : originX;
                    int y = horizontal ? originY : originY + i;
                    grid[y][x] = ship;
                }
                break;
            }
        }
    }

    public Battleship() {
        this(6, 6, 8);
    }

    public int checkWin() {
        // -1 if out of misses
        // 0 if game not over
        // 1 if won
        if (misses == maxMisses) {
            return -1;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != Ship.EMPTY && grid[i][j] != Ship.MISS && grid[i][j] != Ship.SUNK) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public boolean guess() {
        int x;
        for (; ; ) {
            System.out.print("Enter X coordinate: ");
            try {
                x = Integer.parseInt(br.readLine()) - 1;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input");
                continue;
            }
            if (x < 0 || x >= width) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        int y;
        for (; ; ) {
            System.out.print("Enter Y coordinate: ");
            try {
                y = Integer.parseInt(br.readLine()) - 1;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input");
                continue;
            }
            if (y < 0 || y >= height) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }

        if (grid[y][x] == Ship.EMPTY) {
            grid[y][x] = Ship.MISS;
            misses++;
            System.out.println("Miss");
        } else if (grid[y][x] == Ship.MISS || grid[y][x] == Ship.SUNK) {
            System.out.println("You already guessed that");
        } else {
            grid[y][x] = Ship.SUNK;
        }
        int win = checkWin();
        if (win == 1) {
            System.out.println("You win!");
        } else if (win == -1) {
            System.out.println("You lose!");
        }
        return win == 0;
    }
}
