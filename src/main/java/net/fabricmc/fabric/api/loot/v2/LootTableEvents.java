/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.loot.v2;

import org.jetbrains.annotations.Nullable;

import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.event.Event;

import java.util.ArrayList;
import java.util.List;

public final class LootTableEvents {
    public static List<Modify> modifyLootTables = new ArrayList<>();

    public static final Event<Modify> MODIFY = new LootTableModifierEvent();

    public static class LootTableModifierEvent extends Event<LootTableEvents.Modify> {
        public void register(LootTableEvents.Modify listener){

            System.out.println("WARNING: net.fabricmc.fabric.api.loot.v2 is not actually implemented by forgedfabric");
            modifyLootTables.add(listener);
        }
    }

    public interface Modify {
        void modifyLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id, LootTable.Builder tableBuilder, LootTableSource source);
    }
}