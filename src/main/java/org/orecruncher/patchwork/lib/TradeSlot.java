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

package org.orecruncher.patchwork.lib;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TradeSlot extends Slot {

	public static interface IResourceAvailableCheck {
		boolean isAvailable(final Slot slot);
	}

	protected IResourceAvailableCheck available = null;
	protected boolean isPhantom;
	protected boolean isInfinite;
	protected boolean canAdjustPhantom = true;
	protected boolean canShift = true;
	protected int stackLimit;

	public TradeSlot(final IInventory inventory, final int slotIndex, final int xPos, final int yPos) {
		super(inventory, slotIndex, xPos, yPos);
		this.stackLimit = -1;
	}

	public TradeSlot setInfinite() {
		this.isInfinite = true;
		return this;
	}

	public TradeSlot setPhantom() {
		this.isPhantom = true;
		return this;
	}

	public TradeSlot setResourceAvaialbleCheck(final IResourceAvailableCheck check) {
		this.available = check;
		return this;
	}

	public TradeSlot blockShift() {
		this.canShift = false;
		return this;
	}

	@Override
	public void putStack(final ItemStack itemStack) {
		if (!isPhantom() || canAdjustPhantom()) {
			super.putStack(itemStack);
		}
	}

	public TradeSlot setCanAdjustPhantom(final boolean canAdjust) {
		this.canAdjustPhantom = canAdjust;
		return this;
	}

	public TradeSlot setCanShift(final boolean canShift) {
		this.canShift = canShift;
		return this;
	}

	public TradeSlot setStackLimit(final int limit) {
		this.stackLimit = limit;
		return this;
	}

	public boolean isPhantom() {
		return this.isPhantom;
	}

	public boolean canAdjustPhantom() {
		return this.canAdjustPhantom;
	}

	public boolean isAvailable() {
		return this.available == null || this.available.isAvailable(this);
	}

	@Override
	public boolean canTakeStack(@Nonnull final EntityPlayer stack) {
		return !isPhantom();
	}

	public boolean canShift() {
		return this.canShift;
	}

	@Override
	public int getSlotStackLimit() {
		if (this.stackLimit < 0) {
			return super.getSlotStackLimit();
		} else {
			return this.stackLimit;
		}
	}

	@Override
	public ItemStack decrStackSize(final int i) {
		if (!this.isInfinite) {
			return super.decrStackSize(i);
		}

		ItemStack stack = this.inventory.getStackInSlot(getSlotIndex());
		if (!stack.isEmpty()) {
			stack = stack.copy();
			stack.shrink(i);
		}
		return stack;
	}
}
