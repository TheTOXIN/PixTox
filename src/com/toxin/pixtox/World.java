package com.toxin.pixtox;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.toxin.pixtox.Const.*;

public class World {

    public List<Organizm> organizms = new ArrayList<>();
    public Material[][] matrix = new Material[H][W];
    public Canvas canvas;

    private int steper = 0;

    public World(Canvas canvas) {
        this.canvas = canvas;
        generate();
        spawn();
    }

    private void spawn() {
        int countORG = 1;

        for (int i = 0; i < countORG; i++) {
            organizms.add(new Organizm(i));
        }

        for (Organizm organizm : organizms) {
            do {
                organizm.Y = Util.randTo(H);
                organizm.X = Util.randTo(W);
            } while (matrix[organizm.Y][organizm.X] != Material.GRS);

            matrix[organizm.Y][organizm.X] = organizm.material;
        }
    }

    private void generate() {
        generateGRS();
        generateEAT();
        generateRCK();
        generateWTR();
    }

    private void generateGRS() {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                matrix[y][x] = Material.GRS;
            }
        }
    }

    private void generateEAT() {
        int chance = 10;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (Util.luckyTo(chance)) matrix[y][x] = Material.EAT;
            }
        }
    }

    private void generateWTR() {
        int count = 3;
        int size = 5;
        int chance = 15;

        generateChunck(count, size, chance, Material.WTR);


    }

    private void generateRCK() {
        int count = 5;
        int size = 3;
        int chance = 20;

        generateChunck(count, size, chance, Material.RCK);
    }

    private void generateChunck(int count, int size, int chance, Material material) {
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (Util.luckyTo(chance)) {
                    for (int i = 0; i < count; i++) {
                        int randY = Util.randTo(size) + y;
                        int randX = Util.randTo(size) + x;
                        if (Util.validPoint(randX, randY)) matrix[randY][randX] = material;
                    }
                }
            }
        }
    }

    public void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, sW, sH);

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                switch (matrix[y][x]) {
                    case ORG: drawRound(x, y, Color.PURPLE, graphics); break;
                    case DED: drawRect(x, y, Color.BLACK, graphics); break;
                    case SLP: drawOval(x, y, Color.WHITE, graphics); break;
                    case GRS: drawRect(x, y, Color.GREEN, graphics); break;
                    case RCK: drawRound(x, y, Color.GRAY, graphics); break;
                    case EAT: drawOval(x, y, Color.RED, graphics); break;
                    case WTR: drawRect(x, y, Color.BLUE, graphics); break;
                }
            }
        }
    }

    private void drawRect(int x, int y, Color color, GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillRect(x * S, y * S, S, S);
    }

    private void drawRound(int x, int y, Color color, GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillRoundRect(x * S, y * S, S, S, S / 2, S / 2);
    }

    private void drawOval(int x, int y, Color color, GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillOval(x * S, y * S, S, S);
    }

    public void step() {
        System.out.println(steper);

        for (Organizm organizm : organizms) {
            organizm.move(matrix);
        }

        steper++;
    }

}
