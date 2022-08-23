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

package net.fabricmc.fabric.api.event.lifecycle.v1;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ServerLifecycleEvents {
    public static List<ServerStarted> onServerStarted = new ArrayList<>();
    public static final Event<ServerStarted> SERVER_STARTED = new ServerEvent();

    public static class ServerEvent extends Event<ServerStarted>{
        public void register(ServerStarted listener){
            onServerStarted.add(listener);
        }
    }

    public interface ServerStarted {
        void onServerStarted(MinecraftServer server);
    }

}
