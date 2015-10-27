package com.techkatz.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public final class Party extends JFrame {

    private final int HOFFSET = 30;
    private final int LOFFSET = 6;

    public Party() {
        BoardInitUI();
    }

    public void BoardInitUI() {
        Board board = new Board();
        add(board);

        Image image;
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/small-icon.png"));
        image = icon.getImage();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + LOFFSET, board.getBoardHeight() + HOFFSET);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Your Party");
        setIconImage(image);

        try {
            Clip c = AudioSystem.getClip();
            AudioInputStream inputs = AudioSystem.getAudioInputStream(Party.class.getResource("/music/EnchantedJourney.wav"));
            c.open(inputs);
            c.start();
            c.loop(-1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Party party = new Party();
        party.setVisible(true);
    }
}

