package mc.reaperlab.rac;

import mc.reaperlab.rac.checks.CheckManager;
import mc.reaperlab.rac.events.PlayerListener;
import mc.reaperlab.rac.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class RAC extends JavaPlugin {

    public RAC INSTANCE = this;
    public static ArrayList<User> Users = new ArrayList<User>();

    public static CheckManager checkManager;

    @Override
    public void onEnable() {
        checkManager = new CheckManager();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(),  this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static User getUser(Player player) {
        for (User user : Users)
            if (user.getPlayer() == player || user.getPlayer().getUniqueId().equals(player.getUniqueId()))
                return user;
        return null;
    }

    public static void verbose(String message, User user) {
        String RED = ChatColor.RED.toString();
        String BOLD = ChatColor.BOLD.toString();
        String GRAY = ChatColor.GRAY.toString();
        String GOLD = ChatColor.GOLD.toString();
        user.getPlayer().sendMessage(GRAY + "[" + RED + BOLD + "RAC" + GRAY + "] " + GOLD + message);
    }

}
