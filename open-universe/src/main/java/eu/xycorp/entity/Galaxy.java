package eu.xycorp.entity;

import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Galaxy extends SubScene {

    public Galaxy(final Group root, final double width, final double height) {
        super(root, width, height, true, SceneAntialiasing.BALANCED);

        final PhongMaterial material = new PhongMaterial(Color.AQUA);

        for (int i = 0; i < 3600; i += 180) {
            for (int j = 0; j < 500; j += 100) {

                final Box box = new Box(50, 50, 50);
                box.setTranslateX(Math.sin(i / 10) * 1000);
                box.setTranslateY(j);
                box.setTranslateZ(Math.cos(i / 10) * 1000);
                box.setMaterial(material);

                root.getChildren().add(box);
            }
        }
    }
}