package com.toxin.pixtox;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.toxin.pixtox.Const.*;

public class World {

    public String[][] matrix = new String[H][W];
    public Canvas canvas;
    private int steper = 0;

    public World(Canvas canvas) {
        this.canvas = canvas;
        generate();
    }

    private void generate() {
        Material[] values = Material.values();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                matrix[i][j] = values[Util.randTo(values.length)].toString();
            }
        }
    }

    public void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, sW, sH);

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                switch (Material.valueOf(matrix[y][x])) {
                    case ORG: drawM(x, y, Color.LIME, graphics); break;
                    case DED: drawM(x, y, Color.BLACK, graphics); break;
                    case SLP: drawM(x, y, Color.WHITE, graphics); break;
                    case GRS: drawM(x, y, Color.GREEN, graphics); break;
                    case RCK: drawM(x, y, Color.GRAY, graphics); break;
                    case EAT: drawM(x, y, Color.RED, graphics); break;
                    case WTR: drawM(x, y, Color.BLUE, graphics); break;
                }
            }
        }
    }

    private void drawM(int x, int y, Color color, GraphicsContext graphics) {
        graphics.setFill(color);
        graphics.fillRoundRect(x * S, y * S, S, S, S / 2, S / 2);
    }

    public void step() {
        System.out.println(steper);
        matrix[Util.randTo(H)][Util.randTo(W)] = Material.DED.toString();
        steper++;
    }

}
