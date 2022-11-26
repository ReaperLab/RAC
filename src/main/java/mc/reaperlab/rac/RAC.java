package mc.reaperlab.rac;

import mc.reaperlab.rac.checks.CheckManager;
import mc.reaperlab.rac.events.PlayerJoinListener;
import mc.reaperlab.rac.events.PlayerMoveListener;
import mc.reaperlab.rac.events.PlayerQuitListener;
import mc.reaperlab.rac.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class RAC extends JavaPlugin {

    public static RAC INSTANCE = new RAC();
    public ArrayList<User> Users = new ArrayList<User>();

    private CheckManager checkManager;

    @Override
    public void onEnable() {

        checkManager = new CheckManager();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }

    public User getUser(Player player) {
        for (User user : Users)
            if (user.getPlayer() == player || user.getPlayer().getUniqueId().equals(player.getUniqueId()))
                return user;
        return null;
    }
}
