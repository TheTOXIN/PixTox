package com.toxin.pixtox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.toxin.pixtox.Const.FPS;

public class Game extends Application {

    public static World world;

    private Canvas canvas;
    private Pane root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        this.canvas = new Canvas(Const.sW, Const.sH);
        this.root = new Pane();
        this.scene = new Scene(root, Const.sW, Const.sH);

        root.getChildren().add(canvas);

        primaryStage.setTitle("PixTox");
        primaryStage.setScene(scene);
        primaryStage.show();

        Game.world = new World(canvas);

        new Thread(cycle()).start();
    }

    private Runnable cycle() {
        return () -> {
            while (true) {
                world.render();
                world.step();
                sleep();
            }
        };
    }

    private void sleep() {
        try {
            Thread.sleep(FPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
