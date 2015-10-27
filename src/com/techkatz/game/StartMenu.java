package com.techkatz.game;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {

    /*
        Still in the process of deciding if this feature is necessary or if it could be
        incorporated in a cooler and more innovative 'method', ha see what I did there?
        Anyways this will be worked on, but still not sure if it is needed, this will be
        decided in the near future.
     */

    private int hsm = 220;
    private int wsm = 360;

    public StartMenu() {
        setFocusable(true);
    }

    public int getMenuWidth() {
        return this.wsm;
    }

    public int getMenuHeight() {
        return this.hsm;
    }

    public void buildMenu(Graphics g) {
        g.setColor(new Color(134, 226, 226));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }

    public void paint(Graphics g) {
        super.paint(g);
        buildMenu(g);
    }
}
