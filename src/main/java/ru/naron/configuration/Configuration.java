package ru.naron.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.val;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.Consumer;
import ru.naron.Main;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Setter
@Getter
@Accessors(chain = true, fluent = true)
public class Configuration {
    private String fileName;
    private Consumer<FileConfiguration> action;
    public static ConcurrentMap<String, Configuration> cache = new ConcurrentHashMap<>();

    @SneakyThrows
    public FileConfiguration build(){
        val file = new File(Main.getInstance().getDataFolder(), fileName.concat(".yml"));
        if(!Main.getInstance().getDataFolder().exists()){
            Main.getInstance().getDataFolder().mkdirs();
        }

        val configuration = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()){
            file.createNewFile();
        }

        if(action != null){
            action.accept(configuration);
        }
        cache.put(fileName, this);
        return configuration;
    }

    public FileConfiguration getFile(String fileName){
        return cache.values().stream()
                .filter(file -> file.fileName().equalsIgnoreCase(fileName))
                .findAny()
                .get()
                .build();
    }
}
