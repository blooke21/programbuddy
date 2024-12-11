package com.programbuddy;

import java.awt.MouseInfo;
import java.awt.Point;

public class MouseChecker {

    Point firstPoint = MouseInfo.getPointerInfo().getLocation();

    public boolean checkPointer() {
        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
        if (firstPoint.equals(currentPoint)) { //if mouse hasn't moved
            return true;
        }
        firstPoint = currentPoint;
        return false;
    }
}
