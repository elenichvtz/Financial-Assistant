package com.example.finassistant.ui.user;

public interface View {
    /**
     * opens view
     */
    void open();

    /**
     * closes view
     */
    void close();

    /**
     * Shows an error message
     * @param message , the message that appears
     */
    void showError(String message);

    /**
     * Shows informative message
     * @param message , the message shown
     */
    void showInfo(String message);
}
