package ru.geekbrains.chatdoors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CowAndBullController {
    @FXML
   private TextArea tipArea;
    @FXML
    private TextField answerField;

    public void onClickCheckButton(ActionEvent actionEvent) {
        final String answer = answerField.getText();
        if (!"".equals(answer)){
            tipArea.appendText(answer + "\n");
        }
        answerField.clear();
    }

    public void oneNewGameSelect(ActionEvent actionEvent) {
    }

    public void oneExitSelect(ActionEvent actionEvent) {
        System.exit(0);
    }
}