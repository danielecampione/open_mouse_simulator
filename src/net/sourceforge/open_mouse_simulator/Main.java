/*
 * Open Mouse Simulator ( kernel )
 * Copyright (C) 2011, D. Campione
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sourceforge.open_mouse_simulator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sourceforge.open_mouse_simulator.util.Utilities;
import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;

/**
 * 
 * 
 * @author D. Campione
 * 
 */
public class Main {

    public static final String APPLICATION_NAME = "Open Mouse Simulator";

    public static void main(String[] args) {
        try {
            final MouseManager mouseManager = new MouseManager();

            new GlobalKeyListener().addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent event) {
                    if (event.getVirtualKeyCode() == KeyEvent.VK_CAPITAL) {
                        mouseManager.pushLeftButton();
                        mouseManager.releaseLeftButton();
                    }
                }
            });

            try {
                if (Utilities.isWindows()) {
                    UIManager
                            .setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
                    // These lines need to be implemented in order to make JWS work properly
                    UIManager
                            .getLookAndFeelDefaults()
                            .put("ClassLoader",
                                    Class.forName(
                                            "com.jgoodies.looks.windows.WindowsLookAndFeel")
                                            .newInstance().getClass()
                                            .getClassLoader());
                } else {
                    System.exit(0);
                }
            } catch (UnsupportedLookAndFeelException e) {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
            } catch (ClassNotFoundException e) {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
            } catch (InstantiationException e) {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
            } catch (IllegalAccessException e) {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
            }

            Object response = Dialog.show(Main.APPLICATION_NAME,
                    "Press CAPS LOCK to left click.", Dialog.PLAIN_MESSAGE,
                    new Object[]{"Ok", "Close"}, null);
            if ("Ok".equals(response)) {
                showLicense();
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // ignore.
                    }
                }
            } else {
                System.exit(0);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void showLicense() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = Main.class.getResourceAsStream("/license.txt");
        byte[] bytes = new byte[1024];
        int length = in.read(bytes);
        while (length != -1) {
            out.write(bytes, 0, length);
            length = in.read(bytes);
        }
        in.close();
        JTextArea textArea = new JTextArea(new String(out.toByteArray()));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        Dialog.show("License", scrollPane, Dialog.PLAIN_MESSAGE,
                Dialog.DEFAULT_OPTION);
    }
}
