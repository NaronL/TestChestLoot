package ru.naron.chest;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import ru.naron.utils.ItemBuilder;
import java.util.HashMap;
import java.util.Map;

@Getter
public class LootInfo {
    private final String id;
    private final String name;
    private final Material material;
    private final int chance;
    public static @Getter Map<String, LootInfo> cache = new HashMap<>();

    public LootInfo(String key, ConfigurationSection section){
        this.id = key;
        this.name = section.getString("name");
        this.material = Material.valueOf(section.getString("material"));
        this.chance = section.getInt("chance");

        cache.put(id, this);
    }

    public static LootInfo getLoot(String id){
        return cache.values().stream()
                .filter(loot -> loot.getId().equalsIgnoreCase(id))
                .findAny()
                .get();
    }

    public ItemStack getIcon(){
        return ItemBuilder.newBuilder(material)
                .name(name)
                .build();
    }
}
