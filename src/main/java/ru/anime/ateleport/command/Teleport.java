package ru.anime.ateleport.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import ru.anime.ateleport.ATeleport;
import ru.anime.ateleport.util.TpaAccept;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Teleport implements CommandExecutor {
    Map <UUID, TpaAccept> datatp = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 0){
                ATeleport.getMessage().sendMsg(sender, "&cНе достаточно аргументов");
                return true;
            }
            if (args[0].equals("accept")){
                TpaAccept tpaa = datatp.remove(player.getUniqueId());
                if (tpaa == null || System.currentTimeMillis() - tpaa.getTime() > 1000*30 || !tpaa.getPlayer().isOnline()){
                    ATeleport.getMessage().sendMsg(sender, "&cНет запросов на телепортацию");
                    return true;
                }
                new BukkitRunnable() {
                    int i = 3;
                    Player pl = tpaa.getPlayer();
                    @Override
                    public void run() {
                        if(i == 0){
                            pl.teleport(player);
                            cancel();
                        } else {
                            ATeleport.getMessage().sendTitle(pl, "&aТелепортация через","&a&l"+i);
                        }
                        i--;
                    }

                }.runTaskTimer(ATeleport.getInstance(),0, 20);
                ATeleport.getMessage().sendMsg(sender, "Запрос на телепортацию был принят");
                return true;
            }
           Player player2 = Bukkit.getPlayer(args[0]);
            if(player2 == null){
                ATeleport.getMessage().sendMsg(sender, "&cТакого игрока не существует");
            } else if(player2.equals(sender)){
                ATeleport.getMessage().sendMsg(sender, "&c&lЧто-то не так!");
            } else {
                TpaAccept tpaAccept = new TpaAccept(player, player2);
                ATeleport.getMessage().sendMsg(player2, "К вам хочет телепортироваться "+player.getName());
                ATeleport.getMessage().sendMsg(player2, "Для принятия тп напишите /tpa accept");
                datatp.put(player2.getUniqueId(), tpaAccept);
                ATeleport.getMessage().sendMsg(sender, "Запрос на телепортацию был отправлен");
            }

            return true;
        }
        ATeleport.getMessage().sendMsg(sender, "Команда не для консоли!");
        return false;
    }
}
