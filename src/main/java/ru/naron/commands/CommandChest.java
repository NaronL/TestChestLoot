package ru.naron.commands;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import ru.naron.chest.ChestInfo;
import ru.naron.chest.LootInfo;
import ru.naron.chest.menu.ChestMenu;

public class CommandChest implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof ConsoleCommandSender) return true;

        val player = Bukkit.getPlayer(sender.getName());

        new ChestMenu(player);
        return false;
    }
}
