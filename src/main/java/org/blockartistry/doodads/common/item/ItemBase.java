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

package org.blockartistry.doodads.common.item;

import javax.annotation.Nonnull;

import org.blockartistry.doodads.Doodads;
import org.blockartistry.doodads.ModInfo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	protected final String name;

	public ItemBase(@Nonnull final String name) {
		this.name = name;
		setRegistryName(this.name);
		setUnlocalizedName(ModInfo.MOD_ID + "." + this.name);

		// Let the registration handler know about this
		// new item.
		ItemRegistrationHandler.add(this);
	}

	public void registerItemModel() {
		Doodads.proxy().registerItemRenderer(this, 0, this.name);
	}

	@Override
	@Nonnull
	public ItemBase setCreativeTab(@Nonnull final CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
