import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Bees {
    public static Integer[] getCoords(String line) {
        return Arrays.stream(line.trim().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public static Point getCoordPoint(String line, boolean isWall) {
        Integer[] temp = getCoords(line);
        return new Point(temp[0], temp[1], temp[2], isWall);
    }

    public static void main(String[] args) {
        String data = "";
        try {
            data = Files.readString(new File("./data.txt").toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = data.split("\n");
        Point size = new Point(0,0,0, false);
        Point[] end = new Point[15];
        Point[] bees = new Point[15];
        List<String> walls = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
//            System.out.printf("%d | %s", i, lines[i]);
            if (i == 0) continue; // cycle number;
            String line = lines[i];
            if (i == 1) {
                size = getCoordPoint(line, false);
            } else if (i < 17) {
                int index = i - 2;
                end[index] = getCoordPoint(line, false);
            } else if (i < 32) {
                int index = i - 17;
                bees[index] = getCoordPoint(line, false);
            } else {
                if (!line.contains(",")) {
                    System.out.println(line);
                    continue;
                }
                walls.add(line.trim());
            }
        }

        Point[][][] grid = new Point[size.x][size.y][size.z];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                for (int z = 0; z < grid.length; z++) {
                    String key = String.format("%s,%s,%s", x, y, z);
                    grid[x][y][z] = new Point(x, y, z, false);
                    if (walls.contains(key)) {
                        grid[x][y][z].isWall = true;
                    }
                }
            }
        }


        AStar aStar = new AStar();
//        aStar.getShortestPath(grid, grid[0][0][0], end).forEach(System.out::println);
        int sum = 0;
//        Arrays.stream(bees).forEach(bee -> {
//            List<Point> path = aStar.getShortestPath(grid, bee, end);
//            System.out.println(path.stream().map(Point::toString).collect(Collectors.joining(" | ")));
//        });
        for (int i = 0; i < bees.length; i++) {
            Point bee = bees[i];
            List<Point> path = aStar.getShortestPath(grid, bee, end);
            sum += path.size();
            System.out.println(path.stream().map(Point::toString).collect(Collectors.joining(" | ")));
        }
        System.out.println("Sum:" + sum);
    }
}

class Point {
    int x;
    int y;
    int z;
    boolean isWall;

    Point(int x, int y, int z, boolean isWall) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isWall = isWall;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", x, y, z);
    }
}

class PointNode extends Point {
    double heuristic;

    PointNode(int x, int y, int z, double heuristic) {
        super(x, y, z, false);
        this.heuristic = heuristic;
    }
}

class PointDistance extends Point {
    double cost;
    PointDistance previous;

    PointDistance(int x, int y, int z, double cost, PointDistance previous) {
        super(x, y, z, false);
        this.cost = cost;
        this.previous = previous;
    }
}

class AStar {
    public HashMap<String, PointDistance> minDistances = new HashMap<>();

    public double getWeight(PointNode node) {
        if (minDistances.containsKey(node.toString())) {
            return minDistances.get(node.toString()).cost + node.heuristic / 2.0;
//            return node.heuristic;
//            return minDistances.get(node.toString()).cost;
        }
        return Double.POSITIVE_INFINITY;
    }

    public double getHeuristic(Point node, Point[] end) {
        // euclidean distance
        double min = Double.POSITIVE_INFINITY;
        for (Point endPoint : end) {
            double distance = Math.sqrt(Math.pow(node.x - endPoint.x, 2) + Math.pow(node.y - endPoint.y, 2) + Math.pow(node.z - endPoint.z, 2));
            if (distance < min) min = distance;
        }
        return min;
    }

    public List<Point> getNeighbors(PointNode node, Point[][][] grid) {
        List<Point> neighbors = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    // we can only move in one or two axis at a time
                    if (x != 0 && y != 0 && z != 0) {
                        continue;
                    }
                    // we can't stay in the same place
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    // loop until we hit a wall or run out of bounds
                    int newX = node.x + x;
                    int newY = node.y + y;
                    int newZ = node.z + z;

                    int maxX = grid.length;
                    int maxY = grid[0].length;
                    int maxZ = grid[0][0].length;

                    while (newX >= 0 && newX < maxX && newY >= 0 && newY < maxY && newZ >= 0 && newZ < maxZ && grid[newX][newY][newZ] != null) {
                        if (grid[newX][newY][newZ].isWall) {
                            break; // we can't move through walls
                        }
                        // if we're moving diagonally, we can't move through corners
                        if (x != 0 && (y != 0 || z!= 0)) {
                            // we are moving diagonally w/ x movement
                            // check if we moved through a block
                            if (grid[newX - x][newY][newZ] != null && grid[newX - x][newY][newZ].isWall) {
                                break;
                            }
                        }
                        // check y movement
                        if (y != 0 && (x != 0 || z!= 0)) {
                            // we are moving diagonally w/ y movement
                            // check if we moved through a block
                            if (grid[newX][newY - y][newZ] != null && grid[newX][newY - y][newZ].isWall) {
                                break;
                            }
                        }
                        // check z movement
                        if (z != 0 && (x != 0 || y!= 0)) {
                            // we are moving diagonally w/ z movement
                            // check if we moved through a block
                            if (grid[newX][newY][newZ - z] != null && grid[newX][newY][newZ - z].isWall) {
                                break;
                            }
                        }
                        neighbors.add(grid[newX][newY][newZ]);
                        newX += x;
                        newY += y;
                        newZ += z;
                    }
                }
            }
        }
        return neighbors;
    }

    public List<Point> getShortestPath(Point[][][] grid, Point start, Point[] end) {
        Set<String> reachedEnd = Arrays.stream(end).map(Point::toString).collect(Collectors.toSet());

        // flatten the grid
        List<Point> nodes = new ArrayList<>();
        for (Point[][] plane : grid) {
            for (Point[] row : plane) {
                nodes.addAll(Arrays.asList(row));
            }
        }

//        PriorityQueue<PointNode> unseenNodes = new PriorityQueue<>((b, a) -> (int) (this.getWeight(a) - this.getWeight(b)));
        ArrayList<PointNode> unseenNodes = new ArrayList<>();
        for (Point node : nodes) {
            unseenNodes.add(new PointNode(node.x, node.y, node.z, getHeuristic(node, end)));
        }
        minDistances = new HashMap<>(); // reset
        minDistances.put(start.toString(), new PointDistance(start.x, start.y, start.z, 0, null));

        while (!unseenNodes.isEmpty()) {
//            PointNode current = unseenNodes.remove();
//            unseenNodes.sort((a, b) -> Double.compare(a.heuristic, b.heuristic));
            unseenNodes.sort((a, b) -> Double.compare(this.getWeight(a), this.getWeight(b)));
            PointNode current = unseenNodes.remove(0);
            if (reachedEnd.contains(current.toString())) {
                reachedEnd.remove(current.toString());
                // check if all endpoints reached
                if (reachedEnd.isEmpty()) break;
            }
            List<Point> neighbors = getNeighbors(current, grid);
            double distance = minDistances.get(current.toString()).cost + 1;
            for (Point neighbor : neighbors) {
                if (!minDistances.containsKey(neighbor.toString()) || distance < minDistances.get(neighbor.toString()).cost) {
                    minDistances.put(neighbor.toString(), new PointDistance(neighbor.x, neighbor.y, neighbor.z, distance, minDistances.get(current.toString())));
                }
            }
        }
        // get the closest end point
        PointDistance min = minDistances.get(end[0].toString());
        for (Point endPoint: end) {
            PointDistance temp = minDistances.get(endPoint.toString());
            if (temp.cost < min.cost) {
                min = temp;
            }
        }
        // reconstruct path
        PointDistance current = min;
        List<Point> path = new ArrayList<>();
        while (!current.toString().equals(start.toString())) {
            path.add(0, current);
            current = current.previous;
        }
        path.add(0, start);
        return path;
    }
}