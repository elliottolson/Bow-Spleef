package me.elliottolson.bowspleef.listeners;

import me.elliottolson.bowspleef.game.Game;
import me.elliottolson.bowspleef.game.GameManager;
import me.elliottolson.bowspleef.util.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Copyright Elliott Olson (c) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class GameListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        if (GameManager.getInstance().getPlayerGame(player) != null){
            Game game = GameManager.getInstance().getPlayerGame(player);
            game.removePlayer(player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            Player player = (Player) e.getEntity();

            if (GameManager.getInstance().getPlayerGame(player) != null){
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();

        if (GameManager.getInstance().getPlayerGame(player) != null){
            e.setCancelled(true);
            MessageManager.msg(MessageManager.MessageType.ERROR, player, "You cannot build while playing BowSpleef.");
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();

        if (GameManager.getInstance().getPlayerGame(player) != null){
            e.setCancelled(true);
            MessageManager.msg(MessageManager.MessageType.ERROR, player, "You cannot break blocks while playing BowSpleef.");
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e){
        Player player = e.getPlayer();

        if (GameManager.getInstance().getPlayerGame(player) != null){
            e.setCancelled(true);
            MessageManager.msg(MessageManager.MessageType.ERROR, player, "You cannot drop items while playing BowSpleef.");
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent e){
        Player player = e.getPlayer();

        if (GameManager.getInstance().getPlayerGame(player) != null){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        if (e.getEntity() instanceof Player){

            Player player = (Player) e.getEntity();

            if (GameManager.getInstance().getPlayerGame(player) != null){
                e.setCancelled(true);
            }
        }
    }

}
