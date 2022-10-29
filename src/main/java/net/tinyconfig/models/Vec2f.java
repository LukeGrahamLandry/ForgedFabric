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

public class Vec2f {
    public static final Vec2f ZERO = new Vec2f(0.0F, 0.0F);
    public static final Vec2f UP_RIGHT_UNIT = new Vec2f(1.0F, 1.0F);
    public static final Vec2f RIGHT_UNIT = new Vec2f(1.0F, 0.0F);
    public static final Vec2f LEFT_UNIT = new Vec2f(-1.0F, 0.0F);
    public static final Vec2f DOWN_UNIT = new Vec2f(0.0F, 1.0F);
    public static final Vec2f UP_UNIT = new Vec2f(0.0F, -1.0F);
    public static final Vec2f DOWN_LEFT = new Vec2f(-1.0F, -1.0F);
    public static final Vec2f MAX_UP_RIGHT = new Vec2f(Float.MAX_VALUE, Float.MAX_VALUE);
    public static final Vec2f MIN_DOWN_LEFT = new Vec2f(Float.MIN_VALUE, Float.MIN_VALUE);
    public final float x;
    public final float y;

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f multiply(float value) {
        return new Vec2f(this.x * value, this.y * value);
    }

    public float dot(Vec2f vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    public Vec2f add(Vec2f vec) {
        return new Vec2f(this.x + vec.x, this.y + vec.y);
    }

    public Vec2f add(float value) {
        return new Vec2f(this.x + value, this.y + value);
    }

    public boolean equals(Vec2f other) {
        return this.x == other.x && this.y == other.y;
    }

    public Vec2f normalize() {
        float f = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        return f < 1.0E-4F ? ZERO : new Vec2f(this.x / f, this.y / f);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    public float distanceSquared(Vec2f vec) {
        float f = vec.x - this.x;
        float g = vec.y - this.y;
        return f * f + g * g;
    }

    public Vec2f negate() {
        return new Vec2f(-this.x, -this.y);
    }
}
