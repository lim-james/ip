import duke.Duke;
import duke.command.CommandResponse;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        this.dialogContainer
                .getChildren()
                .addAll(
                        DialogBox.getDukeDialog(
                                "Hello! I'm Peter. How can I help you?", this.dukeImage));
    }

    /** Injects the Duke instance */
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
