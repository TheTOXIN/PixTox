package com.toxin.pixtox;

import static com.toxin.pixtox.Const.H;
import static com.toxin.pixtox.Const.W;

public class Organizm extends Entity {

    public int id;
    public Stats stats;
    public float[][] memory = new float[H][W];

    public Organizm(int id) {
        this.id = id;
        this.stats = new Stats();
        this.material = Material.ORG;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                memory[y][x] = (float) Math.random();
            }
        }
    }

    public void move(Material[][] matrix) {
        Point[][] pointsArea = new Point[Const.AREA][Const.AREA];

        float[][] rateMemory = new float[Const.AREA][Const.AREA];
        float[][] rateMatrix = new float[Const.AREA][Const.AREA];

        int[] dx = { -1,  0,  1, -1, 0, 1, -1, 0, 1 };
        int[] dy = { -1, -1, -1,  0, 0, 0,  1, 1, 1 };

        for (int i = 0; i < Const.AREA * Const.AREA; i++) {
            int rateX = this.X + dx[i];
            int rateY = this.Y + dy[i];

            if (Util.validPoint(rateX, rateY)) {
                pointsArea[dy[i] + 1][dx[i] + 1] = new Point(rateX, rateY);
                rateMemory[dy[i] + 1][dx[i] + 1] = this.memory[rateY][rateX];
                rateMatrix[dy[i] + 1][dx[i] + 1] = computeMaterial(matrix[rateY][rateX]);
            }
        }

        Point point = computeMove(pointsArea);
        if (point == null) return;
        computeAction();

        matrix[this.Y][this.X] = Material.GRS;
        matrix[point.Y][point.X] = this.material;

        this.Y = point.Y;
        this.X = point.X;
    }

    private float computeMaterial(Material material) {
        return (float) Math.random();
    }

    private Point computeMove(Point[][] area) {
        return area[Util.randTo(Const.AREA)][Util.randTo(Const.AREA)];
    }

    private void computeAction() {
        stats.hungry = (float) Math.random();
        stats.sleep = (float) Math.random();
        stats.social = (float) Math.random();
        stats.water = (float) Math.random();
    }

}
