package io.github.tn_potato.deadOfGoldRemastered;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class DeadOfGoldRemastered extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // プラグイン起動時にイベントリスナーを登録
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != event.getWhoClicked().getInventory()) {
            return;
        }
        if (!(event.getClickedInventory() instanceof PlayerInventory)) {
            return;
        }
        if (event.getSlotType() == InventoryType.SlotType.QUICKBAR) {
            if (event.isShiftClick()) {
                cancelClick(event);
            }
        } else {
            cancelClick(event);
        }
    }

    private void cancelClick(InventoryClickEvent event) {
        var sound = Sound.sound(Key.key("block.note_block.harp"), Sound.Source.MASTER, 1f, 0f);
        var clickedPlayer = event.getWhoClicked();
        clickedPlayer.sendRichMessage("<red>ホットバーのみが使用できます！");
        clickedPlayer.playSound(sound);
        event.setCancelled(true);
    }
}