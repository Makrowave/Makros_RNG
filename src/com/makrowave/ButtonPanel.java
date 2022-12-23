package com.makrowave;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(JLabel label, JTextField textField){
        this.add(label);
        this.add(textField);
        this.setSize(200, 50);
        this.setLayout(new GridLayout(1, 2));
    }
}
