package net.neocosmic.neosender.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.neocosmic.neosender.NeoSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SendCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            return true;
        }

        if (args.length < 2) {
            return true;
        }

        String playerName = args[0];
        String serverName = args[1];

        Player player = Bukkit.getPlayer(playerName);

        if (player == null) {
            return true;
        }

        send(player, serverName);

        return true;
    }

    public void send(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(player.getName());
        out.writeUTF(server);
        player.sendPluginMessage(NeoSender.getInstance(), "BungeeCord", out.toByteArray());
    }
}
