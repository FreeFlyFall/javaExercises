/*Write a program that displays a moving text, as shown in the following figures. The text
moves from left to right circularly. When it completely disappears in the right, it reappears
from the left. The text freezes when the mouse is pressed, and moves again when the mouse
is released.
Hint: You need to use the PathTransition class (see section 15.11.1 P.619) that animates
the moves of a node along a path from one end to the other over a given time. In this case
the path should be a line (see section 14.11.2 P.569 to see how to create a line)*/

package movingtext;

import javafx.application.Application;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Line;
import javafx.beans.property.Property;

public class MovingText extends Application {
    
    @Override//Override the start method in the Application class
    public void start(Stage primaryStage) {
	//Create a pane
	Pane pane = new Pane();
        
        //Create a line for the path
        Line line1 = new Line(-55, 40, 305, 40);
        //line1.endXProperty().bind(pane.widthProperty().add(305));
        //^^^doesn't track changes automatically^^^
        //Can't use it for this purpose
        
	//Create text shape
	Text text = new Text("Programming is fun");
	pane.getChildren().addAll(text);//Add line1 to see path

	//Create a path transition. Set the duration, path, and shape.
	PathTransition pt = new PathTransition(Duration.millis(4000), 
            line1, text);
                        
	pt.setCycleCount(Timeline.INDEFINITE);
	pt.play();//Start animation

	//Create and register the handles
	pane.setOnMousePressed(e -> {
            pt.pause();
            });

	pane.setOnMouseReleased(e -> {
            pt.play();
            });

	//Create a scene and place it in the stage
	Scene scene = new Scene(pane, 250, 200);
	primaryStage.setTitle("A Moving Text");//Set stage title
	primaryStage.setScene(scene);//Place the scene in the stage
	primaryStage.show();//Display the stage
    }
}
