package com.makrowave.util;

import javax.swing.*;
import java.awt.*;

public class ValueTextField extends JTextField {
    public ValueTextField(String mText){
        this.setText(mText);
        this.setFont(new Font("Helvetica", Font.PLAIN, 20));

    }
}
