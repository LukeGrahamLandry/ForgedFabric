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

public class EnchantmentConfig {
    public int max_level = 0;
    public int min_cost = 0;
    public int step_cost = 0;
    public float bonus_per_level = 0;

    public EnchantmentConfig() { }

    public EnchantmentConfig(int max_level, int min_cost, int step_cost, float bonus_per_level) {
        this.max_level = max_level;
        this.min_cost = min_cost;
        this.step_cost = step_cost;
        this.bonus_per_level = bonus_per_level;
    }
}
