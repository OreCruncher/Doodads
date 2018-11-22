/*
 * Licensed under the MIT License (MIT).
 *
 * Copyright (c) OreCruncher
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.orecruncher.patchwork.block.shopshelf;

import org.orecruncher.patchwork.lib.StackHandlerBase;

public class ShopShelfStackHandler extends StackHandlerBase {

	public static final int CONFIG_SLOT_COUNT = 6 * 3;
	public static final int INVENTORY_SLOT_COUNT = 9 * 3;
	public static final int TOTAL_SLOTS = CONFIG_SLOT_COUNT + INVENTORY_SLOT_COUNT;
	public static final int CONFIG_SLOT_START = 0;
	public static final int CONFIG_SLOT_END = CONFIG_SLOT_COUNT - 1;
	public static final int INVENTORY_SLOT_START = CONFIG_SLOT_COUNT;
	public static final int INVENTORY_SLOT_END = (INVENTORY_SLOT_START + INVENTORY_SLOT_COUNT) - 1;
	
	public static final int TRADE_SLOT_1 = 2;
	public static final int TRADE_SLOT_2 = 5;
	public static final int TRADE_SLOT_3 = 8;
	public static final int TRADE_SLOT_4 = 11;
	public static final int TRADE_SLOT_5 = 14;
	public static final int TRADE_SLOT_6 = 17;

	public ShopShelfStackHandler() {
		super(TOTAL_SLOTS);
	}

}