package com.makrowave.util;

import javax.swing.*;

public class TogglableButtonGroup extends ButtonGroup {
    @Override
    public void setSelected(ButtonModel m, boolean b) {
        if (b && m != null && m != getSelection()) {
            super.setSelected(m, b);
        } else if (!b && m == getSelection()) {
            clearSelection();
        }
    }
}
