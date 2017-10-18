package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {

		final HTMLEditor HtmlEditor = new HTMLEditor();
		stage.setScene(new Scene(HtmlEditor));
		stage.show();

		Node toolNode = HtmlEditor.lookup(".top-toolbar");
		Node webNode = HtmlEditor.lookup(".web-view");
		
		HtmlEditor.setHtmlText("<html dir = \"rtl\">");
		 
		if (toolNode instanceof ToolBar && webNode instanceof WebView) {
			ToolBar bar = (ToolBar) toolNode;
			WebView webView = (WebView) webNode;
			WebEngine engine = webView.getEngine();
			
			

			Button rtl = new Button("RTL");
			bar.getItems().add(rtl);
			rtl.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					String selection = engine.executeScript("window.getSelection()").toString();
					int range = selection.length();

					if (selection != null && range > 0) {

						String formatted = "<span dir=\"rtl\">" + selection + "</span>";
						String niu = HtmlEditor.getHtmlText().replace(selection, formatted);
						HtmlEditor.setHtmlText(niu);
						System.out.println(HtmlEditor.getHtmlText());
					}
				}
			});
			
			Button ltr = new Button("LTR");
			bar.getItems().add(ltr);
			ltr.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					String selection = engine.executeScript("window.getSelection()").toString();
					int range = selection.length();

					if (selection != null && range > 0) {

						String formatted = "<span dir=\"ltr\">" + selection + "</span>";
						String niu = HtmlEditor.getHtmlText().replace(selection, formatted);
						HtmlEditor.setHtmlText(niu);
					//	System.out.println(HtmlEditor.getHtmlText());
					}
				}
			});
			

			

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
