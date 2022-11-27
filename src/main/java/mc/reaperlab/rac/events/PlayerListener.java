package mc.reaperlab.rac.events;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        RAC.Users.add(new User(ev.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent ev) {
        RAC.Users.remove(RAC.getUser(ev.getPlayer()));
    }

    @EventHandler
    public void onEntityAttacked(EntityDamageByEntityEvent ev) {
        if (ev.getEntity() instanceof Player) {
            RAC.getUser((Player) ev.getEntity()).setDamagedLast(true);
            RAC.getUser((Player) ev.getEntity()).packetsInGUI = 0;
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent ev) {
        User user = RAC.getUser(ev.getPlayer());
        String RED = ChatColor.RED.toString();
        String BOLD = ChatColor.BOLD.toString();
        String GRAY = ChatColor.GRAY.toString();
        for (Check check : RAC.checkManager.getChecks()) {
            CheckReturn checkReturn = check.onBreakEvent(ev);
            if (!checkReturn.isPassed()) {
                RAC.verbose(check.getName() + " " + check.getType() + GRAY + " [" + checkReturn.reason + GRAY + "]", user);
                user.flags++;
            }
        }
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent ev) {
        User user = RAC.getUser(ev.getPlayer());
        String RED = ChatColor.RED.toString();
        String BOLD = ChatColor.BOLD.toString();
        String GRAY = ChatColor.GRAY.toString();
        for (Check check : RAC.checkManager.getChecks()) {
            CheckReturn checkReturn = check.onPlaceEvent(ev);
            if (!checkReturn.isPassed()) {
                RAC.verbose(check.getName() + " " + check.getType() + GRAY + " [" + checkReturn.reason + GRAY + "]", user);
                user.flags++;
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev) {
        User user = RAC.getUser(ev.getPlayer());
        String RED = ChatColor.RED.toString();
        String BOLD = ChatColor.BOLD.toString();
        String GRAY = ChatColor.GRAY.toString();
        for (Check check : RAC.checkManager.getChecks()) {
            CheckReturn checkReturn = check.onMoveEvent(ev);
            if (!checkReturn.isPassed() && !user.wasDamagedLast()) {
                RAC.verbose(check.getName() + " " + check.getType() + GRAY + " [" + checkReturn.reason + GRAY + "]", user);
                user.flags++;
            }
        }
        if (user.wasDamagedLast()) {
            if (user.packetsImmune++ > 15) {
                user.packetsImmune = 0;
                user.setDamagedLast(false);
            }
        }
        if (user.flags > 3) {
            user.flags = 0;
            user.getPlayer().kickPlayer(
                    RED + BOLD + "RAC" +
                            "\n" + RED + "   Unfair Advantage"
            );
        }
    }
}
