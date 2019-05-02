package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
//	Serverprogrammet
	
	@Override
	public void start(Stage primaryStage) {
		
			//Här är allt i fönstret
			BorderPane rootPane = new BorderPane();
			Scene scene = new Scene(rootPane,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			FlowPane centerPane = new FlowPane();
			centerPane.setAlignment(Pos.CENTER);
			centerPane.setOrientation(Orientation.VERTICAL);
			rootPane.setCenter(centerPane);
			
			FlowPane bottomPane = new FlowPane();
			bottomPane.setAlignment(Pos.CENTER);
			rootPane.setBottom(bottomPane);
			
			FlowPane rightPane = new FlowPane();
			rightPane.setAlignment(Pos.CENTER);
			rightPane.setOrientation(Orientation.VERTICAL);
			rootPane.setRight(rightPane);
			
			Text txt = new Text("Chat Program");
			txt.setFont(new Font(36));
			centerPane.getChildren().add(txt);
			
			TextField txtField = new TextField();
			txtField.setMaxWidth(100);
			centerPane.getChildren().add(txtField);
			
			Server s = new Server();
			s.start();
			
			
			Button btnl = new Button("Start");
			btnl.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if(s.canSendMessage) {
					s.sendMessage(txtField.getText());
					txt.setText(txtField.getText());
					}
				}
			});
			bottomPane.getChildren().add(btnl);
	}
	
//	@Override
//	public void start(Stage primaryStage) {
//		
//			//Här är allt i fönstret
//			BorderPane rootPane = new BorderPane();
//			Scene scene = new Scene(rootPane,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			
//			FlowPane centerPane = new FlowPane();
//			centerPane.setAlignment(Pos.CENTER);
//			centerPane.setOrientation(Orientation.VERTICAL);
//			rootPane.setCenter(centerPane);
//			
//			FlowPane bottomPane = new FlowPane();
//			bottomPane.setAlignment(Pos.CENTER);
//			rootPane.setBottom(bottomPane);
//			
//			FlowPane rightPane = new FlowPane();
//			rightPane.setAlignment(Pos.CENTER);
//			rightPane.setOrientation(Orientation.VERTICAL);
//			rootPane.setRight(rightPane);
//			
//			Text txt = new Text("Chat Program");
//			txt.setFont(new Font(36));
//			centerPane.getChildren().add(txt);
//			
//			Client c = new Client("10.158.13.143");
//			c.start(txt);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
