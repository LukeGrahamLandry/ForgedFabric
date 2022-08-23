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

package net.fabricmc.fabric.api.client.event.lifecycle.v1;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ClientLifecycleEvents {
    public static List<ClientStarted> onClientStarted = new ArrayList<>();
    public static final Event<ClientStarted> CLIENT_STARTED = new ClientEvent();

    public static class ClientEvent extends Event<ClientStarted> {
        public void register(ClientStarted listener){
            onClientStarted.add(listener);
        }
    }

    public interface ClientStarted {
        void onClientStarted(MinecraftClient client);
    }
}
