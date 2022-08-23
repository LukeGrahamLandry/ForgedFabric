/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 * Copyright (c) 2022 LukeGrahamLandry
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

package net.fabricmc.fabric.api.networking.v1;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;

public class PacketByteBufs {
    public static PacketByteBuf create() {
        return new PacketByteBuf(Unpooled.buffer());
    }
}
