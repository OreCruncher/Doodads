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

package org.orecruncher.patchwork.world.event;

import javax.annotation.Nonnull;

import org.orecruncher.patchwork.ModBase;
import org.orecruncher.patchwork.ModInfo;
import org.orecruncher.patchwork.ModOptions;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Any mobs spawned in a spawner will have it's drops removed
 */
@EventBusSubscriber(modid = ModInfo.MOD_ID)
public class SpawnerDropPurge {

	private static final String spawnTag = new ResourceLocation(ModInfo.MOD_ID, "tag/spawner").toString();

	/*
	 * Tag a mob when it is spawned by a spawner
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void checkSpawn(@Nonnull final LivingSpawnEvent.CheckSpawn event) {
		if (!ModOptions.features.noDropsFromSpawnerMobs)
			return;

		if (event.getSpawner() != null && event.getResult() != Result.DENY) {
			event.getEntity().getTags().add(spawnTag);
		}
	}

	/*
	 * If a mob dies, check it's tag. If it was spawned by a spawner cancel the
	 * event.
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onLivingDrops(@Nonnull final LivingDropsEvent event) {
		if (event.getEntity().getTags().contains(spawnTag)) {
			final Entity entity = event.getEntity();
			ModBase.log().debug("Cancelling drops for entity [%s/%d]", entity.getName(), entity.getEntityId());
			event.setResult(Result.DENY);
			event.setCanceled(true);
		}
	}

}
