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

import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * 
 * 
 * @author D. Campione
 * 
 */
public class MouseManager {

    Robot robot;

    public MouseManager() throws Exception {
        robot = new Robot();
    }

    public void pushLeftButton() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
    }

    public void pushRightBuutton() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    public void releaseButtons() {
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void releaseLeftButton() {
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public void releaseRightButton() {
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
}