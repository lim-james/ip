import duke.Duke;
import duke.command.CommandResponse;
import duke.personality.PersonalityResponses;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for the main GUI. */
public class MainWindow extends AnchorPane {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;
    private Duke duke;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jim.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/dwight.jpg"));

    /**
     * Initializes the controller after its root element has been completely processed. Sets up
     * scroll pane binding and displays the welcome message.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        String welcomeMsg = PersonalityResponses.WELCOME.getRandomResponse();
        DialogBox welcomeDialog = DialogBox.getDukeDialog(welcomeMsg, this.dukeImage);
        this.dialogContainer.getChildren().addAll(welcomeDialog);
    }

    /**
     * Injects the Duke instance.
     *
     * @param d The Duke instance to be used by this controller.
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResponse response = this.duke.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, this.userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response.getMessage(), this.dukeImage);
        this.dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();
    }
}
