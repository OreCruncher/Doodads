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

package org.orecruncher.patchwork.block.furnace3d;

import javax.annotation.Nonnull;

import org.orecruncher.patchwork.lib.StackHandlerBase;

import net.minecraft.item.ItemStack;

public class Furnace3DStackHandler extends StackHandlerBase {

	public static final int INPUT_SLOT = 0;
	public static final int FUEL_SLOT = 1;
	public static final int OUTPUT_SLOT = 2;
	public static final int TOTAL_SLOTS = 3;

	public Furnace3DStackHandler() {
		super(TOTAL_SLOTS);
	}

	@Nonnull
	public ItemStack getInputStack() {
		return getStackInSlot(INPUT_SLOT);
	}

	@Nonnull
	public ItemStack getOutputStack() {
		return getStackInSlot(OUTPUT_SLOT);
	}

	@Nonnull
	public ItemStack getFuelStack() {
		return getStackInSlot(FUEL_SLOT);
	}

	public void setInputStack(@Nonnull final ItemStack stack) {
		setStackInSlot(INPUT_SLOT, stack);
	}

	public void setOutputStack(@Nonnull final ItemStack stack) {
		setStackInSlot(OUTPUT_SLOT, stack);
	}

	public void setFuelStack(@Nonnull final ItemStack stack) {
		setStackInSlot(FUEL_SLOT, stack);
	}

}
