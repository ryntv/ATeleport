package ru.anime.ateleport;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.anime.ateleport.command.Teleport;

public final class ATeleport extends JavaPlugin {

    private static Plugin instance;
    public static Plugin  getInstance() {
        return instance;
    }
    private static Message message;
    public static Message getMessage(){
        return message;
    }
    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        message = new Message(getLogger());
        message.logger("Start");
        getCommand("teleport").setExecutor(new Teleport());

    }

    @Override
    public void onDisable() {
        message.logger("Stop");
        // Plugin shutdown logic
    }
}
