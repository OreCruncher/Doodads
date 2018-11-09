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

package org.orecruncher.patchwork.sided;

import javax.annotation.Nonnull;

import org.orecruncher.lib.Localization;
import org.orecruncher.patchwork.ModInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ServerSupport extends SideSupport {

	@Override
	public boolean isDedicatedServer() {
		return true;
	}

	public void preInit(@Nonnull final FMLPreInitializationEvent event) {
		super.preInit(event);
		Localization.initialize(Side.SERVER, ModInfo.MOD_ID);
	}

	@Override
	public IThreadListener getThreadListener(@Nonnull final MessageContext context) {
		if (context.side.isServer()) {
			return context.getServerHandler().player.getServer();
		} else {
			throw new IllegalStateException(
					"Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
		}
	}

	@Override
	public EntityPlayer getPlayer(MessageContext context) {
		if (context.side.isServer()) {
			return context.getServerHandler().player;
		} else {
			throw new IllegalStateException(
					"Tried to get the player from a client-side MessageContext on the dedicated server");
		}
	}
}
