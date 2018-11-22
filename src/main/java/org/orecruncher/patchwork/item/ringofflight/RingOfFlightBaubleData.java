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

package org.orecruncher.patchwork.item.ringofflight;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.orecruncher.patchwork.item.ItemRingOfFlight;
import org.orecruncher.patchwork.item.ItemRingOfFlight.Variant;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class RingOfFlightBaubleData implements IBauble {

	private static final float DEFAULT_FLY_SPEED = 0.05F;
	private static final int RINGSLOT1 = 1;
	private static final int RINGSLOT2 = 2;

	@Override
	@Nonnull
	public BaubleType getBaubleType(@Nonnull final ItemStack stack) {
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		final EntityPlayer player = (EntityPlayer) entity;
		final World world = player.getEntityWorld();
		if (world.isRemote || player.capabilities.isCreativeMode || !player.capabilities.isFlying)
			return;

		final IRingOfFlightSettable caps = (IRingOfFlightSettable) CapabilityRingOfFlight.getCapability(stack);
		if (caps != null && caps.getVariant() != Variant.CORE && !caps.damage(1)) {
			onUnequipped(stack, player);
			((ItemRingOfFlight) (stack.getItem())).makeInert(stack);
		}
	}

	@Override
	public void onEquipped(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		if (entity.getEntityWorld().isRemote)
			return;
		
		final EntityPlayer player = (EntityPlayer) entity;
		final IRingOfFlightSettable caps = (IRingOfFlightSettable) CapabilityRingOfFlight.getCapability(stack);
		if (caps != null && caps.getVariant() != Variant.CORE) {
			player.getEntityData().setBoolean(NBT.TAG_FLIGHT, true);
			player.capabilities.allowFlying = true;
			player.capabilities.setFlySpeed(caps.getVariant().getSpeed());
			player.sendPlayerAbilities();
		}
	}

	@Override
	public void onUnequipped(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		if (entity.getEntityWorld().isRemote)
			return;
		final IRingOfFlightSettable caps = (IRingOfFlightSettable) CapabilityRingOfFlight.getCapability(stack);
		if (caps != null) {
			final EntityPlayer player = (EntityPlayer) entity;
			if (player.getEntityData().hasKey(NBT.TAG_FLIGHT)) {
				if (!player.isCreative()) {
					player.capabilities.isFlying = false;
					player.capabilities.allowFlying = false;
				}
				player.capabilities.setFlySpeed(DEFAULT_FLY_SPEED);
				player.sendPlayerAbilities();
				player.getEntityData().removeTag(NBT.TAG_FLIGHT);
			}
		}
	}

	@Override
	public boolean canEquip(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		final EntityPlayer player = (EntityPlayer) entity;
		final IBaublesItemHandler h = BaublesApi.getBaublesHandler(player);
		return h != null && !isRingEquiped(h, player, RINGSLOT1) && !isRingEquiped(h, player, RINGSLOT2);
	}

	@Override
	public boolean canUnequip(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		return true;
	}

	@Override
	public boolean willAutoSync(@Nonnull final ItemStack stack, @Nonnull final EntityLivingBase entity) {
		return false;
	}

	private boolean isRingEquiped(@Nonnull final IBaublesItemHandler handler, @Nonnull final EntityPlayer player,
			final int slot) {
		final ItemStack stack = handler.getStackInSlot(slot);
		return stack.getItem() instanceof ItemRingOfFlight;
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
						? BaublesCapabilities.CAPABILITY_ITEM_BAUBLE.cast(new RingOfFlightBaubleData())
						: null;
			}

		};
	}

	private static class NBT {
		private static final String TAG_FLIGHT = "rof_flight";
	}
}