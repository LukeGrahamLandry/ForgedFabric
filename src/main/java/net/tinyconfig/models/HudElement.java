/*
    https://github.com/ZsoltMolnarrr/TinyConfig

    The MIT License (MIT)

    Copyright (c) 2022

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
 */
package net.tinyconfig.models;

public class HudElement {
    public Origin origin;
    public Vec2f offset;

    public HudElement(Origin origin, Vec2f offset) {
        this.origin = origin;
        this.offset = offset;
    }

    public enum Origin {
        TOP, TOP_LEFT, TOP_RIGHT,
        BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT;

        public Vec2f getPoint(int screenWidth, int screenHeight) {
            if (this == Origin.TOP) {
                return new Vec2f(screenWidth / 2F, 0);
            } else if (this == Origin.TOP_LEFT) {
                return new Vec2f(0, 0);
            } else if (this == Origin.TOP_RIGHT) {
                return new Vec2f(screenWidth, 0);
            } else if (this == Origin.BOTTOM) {
                return new Vec2f(screenWidth / 2F, screenHeight);
            } else if (this == Origin.BOTTOM_LEFT) {
                return new Vec2f(0, screenHeight);
            } else if (this == Origin.BOTTOM_RIGHT) {
                return new Vec2f(screenWidth, screenHeight);
            }
            return new Vec2f(screenWidth / 2F, screenHeight / 2F); // Should never run
        }
    }
}