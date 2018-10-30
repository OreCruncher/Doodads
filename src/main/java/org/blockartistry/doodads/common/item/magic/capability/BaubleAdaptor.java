/*
 * This file is part of Doodads, licensed under the MIT License (MIT).
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

package org.blockartistry.doodads.common.item.magic.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.blockartistry.doodads.common.item.ItemMagicDevice;
import org.blockartistry.doodads.common.item.ModItems;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * Simple Bauble capability that gives Bauble what it needs for display and
 * redirects state changes to ItemMagicDevice for handling.
 */
public class BaubleAdaptor implements IBauble {

	private static final ItemMagicDevice DEVICE = (ItemMagicDevice) ModItems.MAGIC_DEVICE;

	private final BaubleType type;

	private BaubleAdaptor(@Nonnull final ItemStack stack) {
		this.type = ItemMagicDevice.Type.byMetadata(stack.getMetadata()).getBaubleType();
	}

	@Override
	public BaubleType getBaubleType(@Nonnull final ItemStack itemstack) {
		return this.type;
	}

	public void onWornTick(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		DEVICE.onWornTick(itemstack, player);
	}

	public void onEquipped(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		DEVICE.onEquipped(itemstack, player);
	}

	public void onUnequipped(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		DEVICE.onUnequipped(itemstack, player);
	}

	public boolean canEquip(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		return DEVICE.canEquip(itemstack, player);
	}

	public boolean canUnequip(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		return DEVICE.canUnequip(itemstack, player);
	}

	public boolean willAutoSync(@Nonnull final ItemStack itemstack, @Nonnull final EntityLivingBase player) {
		return DEVICE.willAutoSync(itemstack, player);
	}

	@Nonnull
	public static ICapabilityProvider createBaubleProvider(@Nonnull final ItemStack stack) {
		return new ICapabilityProvider() {

			@Override
			public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
				return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE;
			}

			@Nullable
			@Override
			public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
				return capability == BaublesCapabilities.CAPABILITY_ITEM_BAUBLE
						? BaublesCapabilities.CAPABILITY_ITEM_BAUBLE.cast(new BaubleAdaptor(stack))
						: null;
			}

		};
	}
}
