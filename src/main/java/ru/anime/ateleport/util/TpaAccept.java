package ru.anime.ateleport.util;

import org.bukkit.entity.Player;

public class TpaAccept {
    private final Player player;
    private final Player player2;
    private  final long time;

    public TpaAccept(Player player, Player player2) {
        this.player = player;
        this.player2 = player2;
        time = System.currentTimeMillis();
    }
    public Player getPlayer() {
        return player;
    }
    public Player getPlayer2() {
        return player2;
    }
    public long getTime(){
        return time;
    }

}
