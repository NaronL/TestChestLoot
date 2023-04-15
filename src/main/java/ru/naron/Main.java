package ru.naron;

import lombok.Getter;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;
import ru.naron.chest.ChestInfo;
import ru.naron.chest.LootInfo;
import ru.naron.commands.CommandChest;
import ru.naron.configuration.Configuration;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        new Configuration()
                .fileName("chest")
                .action(file -> {
                    val sectionChest = file.getConfigurationSection("chest");
                    val sectionItems = file.getConfigurationSection("chest.items");

                    if(sectionChest != null && sectionChest.getKeys(false).size() > 0){
                        new ChestInfo(sectionChest);
                    }

                    if(sectionItems != null && sectionItems.getKeys(false).size() > 0){
                        sectionItems.getKeys(false).forEach(key -> {
                            val section = sectionItems.getConfigurationSection(key);

                            new LootInfo(key, section);
                        });
                    }
                })
                .build();

        this.getCommand("chest").setExecutor(new CommandChest());
    }

    @Override
    public void onDisable() {

    }
}