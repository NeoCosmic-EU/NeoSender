package net.neocosmic.neosender;

import lombok.Getter;
import net.neocosmic.neosender.commands.SendCMD;
import org.bukkit.plugin.java.JavaPlugin;

public final class NeoSender extends JavaPlugin {

    @Getter
    private static NeoSender instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("send").setExecutor(new SendCMD());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }
}
