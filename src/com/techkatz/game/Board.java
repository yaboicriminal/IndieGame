package com.techkatz.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel {

    private final int SPACE = 20;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList grass = new ArrayList();
    private Player present;
    private Object balloons;
    private Object cake;
    private Object chat;
    private Object bigcloud;
    private Object smallcloud;
    private Object sparkles;
    private int bw = 0;
    private int bh = 0;
    // private boolean completed = false;

    private String level =
                      "                  \n"
                    + " !           %    \n"
                    + "         *        \n"
                    + "                  \n"
                    + "                  \n"
                    + "                  \n"
                    + "                  \n"
                    + "  ^             $ \n"
                    + "                  \n"
                    + "               @  \n"
                    + "##################\n";

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.bw;
    }

    public int getBoardHeight() {
        return this.bh;
    }

    public final void initWorld() {

        int x = 0;
        int y = 0;

        Grass wall;

        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.bw < x) {
                    this.bw = x;
                }
                x = 0;
            } else if (item == '#') {
                wall = new Grass(x, y);
                grass.add(wall);
                x += SPACE;
            } else if (item == '@') {
                present = new Player(x, y);
                x += SPACE;
            } else if (item == '$') {
                balloons = new Balloons(x, y);
                x += SPACE;
            } else if (item == '%') {
                bigcloud = new BigCloud(x, y);
                x += SPACE;
            } else if (item == '*') {
                smallcloud = new SmallCloud(x, y);
                x += SPACE;
            } else if (item == '^') {
                cake = new Cake(x, y);
                x += SPACE;
            } else if (item == '!') {
                chat = new Chat(x, y);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }
            bh = y;
        }
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(134, 226, 226));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(grass);
        world.add(present);
        world.add(balloons);
        world.add(bigcloud);
        world.add(smallcloud);
        world.add(cake);
        world.add(chat);
        // loop through the world array
        for (int i = 0; i < world.size(); i++) {
            // get the item of the index of the loop
            Actor item = (Actor) world.get(i);
            // add 1 to the y so it knows when to stack grass and same with the player (I think)
            if ((item instanceof Player) || (item instanceof Grass)) {
                g.drawImage(item.getImage(), item.x() + 0, item.y() + 1, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

          /*
          if (completed) {
                return;
            }
            */

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {

                if (checkWallCollision(present, LEFT_COLLISION)) {
                    return;
                }
                present.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {

                if (checkWallCollision(present, RIGHT_COLLISION)) {
                    return;
                }
                present.move(SPACE, 0);
            } else if (key == KeyEvent.VK_UP) {

                if (checkWallCollision(present, TOP_COLLISION)) {
                    return;
                }
                present.move(0, -SPACE);
            } else if (key == KeyEvent.VK_DOWN) {

                if (checkWallCollision(present, BOTTOM_COLLISION)) {
                    return;
                }
                present.move(0, SPACE);

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }
            repaint();
        }
    }

        private boolean checkWallCollision(Actor actor, int type) {

            if (type == LEFT_COLLISION) {

                for (int i = 0; i < grass.size(); i++) {
                    Grass wall = (Grass) grass.get(i);
                    if (actor.isLeftCollision(wall)) {
                        return true;
                    }
                }
                return false;

            } else if (type == RIGHT_COLLISION) {

                for (int i = 0; i < grass.size(); i++) {
                    Grass wall = (Grass) grass.get(i);
                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }
                return false;

            } else if (type == TOP_COLLISION) {

                for (int i = 0; i < grass.size(); i++) {
                    Grass wall = (Grass) grass.get(i);
                    if (actor.isTopCollision(wall)) {
                        return true;
                    }
                }
                return false;

            } else if (type == BOTTOM_COLLISION) {

                for (int i = 0; i < grass.size(); i++) {
                    Grass wall = (Grass) grass.get(i);
                    if (actor.isBottomCollision(wall)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

    public void restartLevel() {
            grass.clear();
            initWorld();
            /*
            if (completed) {
                completed = false;
            }
            */
        }
    }
