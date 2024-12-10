package com.programbuddy;

import java.awt.MouseInfo;
import java.awt.Point;

public class MouseChecker {

    Point firstPoint = MouseInfo.getPointerInfo().getLocation();

    public boolean checkPointer() {
        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
        //if the mouse has moved 
        if (firstPoint.equals(currentPoint)) {
            return true;
        }
        firstPoint = currentPoint;
        return false;
    }
}
