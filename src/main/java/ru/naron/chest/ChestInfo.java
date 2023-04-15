package ru.naron.chest;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import ru.naron.utils.LocationUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class ChestInfo {
    private final String name;
    private final int row;
    public static Set<ChestInfo> cache = new HashSet<>();

    public ChestInfo(ConfigurationSection section){
        this.name = section.getString("name");
        this.row = section.getInt("row");

        cache.add(this);
    }

    public static ChestInfo get(){
        return cache.stream().findAny().get();
    }
}
