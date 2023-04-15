package ru.naron.chest.menu;

import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.naron.chest.ChestInfo;
import ru.naron.chest.LootInfo;
import ru.naron.utils.PercentUtils;

import java.util.Random;
import java.util.Set;

public class ChestMenu {

    final Inventory inventory;

    public ChestMenu(Player player){
        inventory = Bukkit.createInventory(null, ChestInfo.get().getRow() * 9, ChestInfo.get().getName());

        LootInfo.getCache().forEach((key, value) -> {
            LootInfo lootInfo = LootInfo.getLoot(key);
            Set<ItemStack> drops = Sets.newHashSet();
            if(LootInfo.getCache().size() > 0){
                if(PercentUtils.randomPercent(value.getChance())){
                    drops.add(lootInfo.getIcon());
                }
            }
            drops.forEach(action -> inventory.setItem(new Random().nextInt(inventory.getSize() - 1), action));
        });
        player.openInventory(inventory);
    }
}
