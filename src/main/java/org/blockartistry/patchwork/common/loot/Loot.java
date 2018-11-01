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

package org.blockartistry.patchwork.common.loot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.blockartistry.patchwork.ModInfo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;

public class Loot {

	public static final ResourceLocation COIN_LOOT_TABLE = new ResourceLocation(ModInfo.MOD_ID, "coins");
	public static final ResourceLocation REPAIRPASTE_LOOT_TABLE = new ResourceLocation(ModInfo.MOD_ID, "repairpaste");

	@Nullable
	public static LootTable getTable(@Nonnull final World world, @Nonnull final ResourceLocation resource) {
		return world.getLootTableManager().getLootTableFromLocation(resource);
	}

	@Nullable
	public static LootPool getPool(@Nonnull final World world, @Nonnull final ResourceLocation resource,
			@Nonnull final String pool) {
		final LootTable table = getTable(world, resource);
		return table != null ? table.getPool(pool) : null;
	}
}
