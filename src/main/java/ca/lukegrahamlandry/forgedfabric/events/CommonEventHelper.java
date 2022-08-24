package ca.lukegrahamlandry.forgedfabric.events;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;

import java.util.ArrayList;
import java.util.List;

public class CommonEventHelper {
    public static List<CommandRegistrationCallback> commands = new ArrayList<>();

    public static class CommandEvent extends Event<CommandRegistrationCallback> {
        @Override
        public void register(CommandRegistrationCallback listener) {
            commands.add(listener);
        }
    }
}
