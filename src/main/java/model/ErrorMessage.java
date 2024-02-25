package model;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessage {
    private List<String> messages;

    public ErrorMessage() {
        messages = new ArrayList<>();
    }

    public void setErrorMassage(String message) {
        messages.add(message);
    }
    
    public boolean hasError() {
        return !messages.isEmpty();
    }

    public String getErrorMessage() {
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            errorMessage.append(messages.get(i) + "\n");
        }
        return errorMessage.toString();
    }
}
