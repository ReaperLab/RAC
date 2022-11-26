package mc.reaperlab.rac.events;

import mc.reaperlab.rac.RAC;
import mc.reaperlab.rac.checks.Check;
import mc.reaperlab.rac.checks.CheckReturn;
import mc.reaperlab.rac.checks.CheckType;
import mc.reaperlab.rac.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        RAC.Users.add(new User(ev.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent ev) {
        RAC.Users.remove(new User(ev.getPlayer()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerMoveEvent ev) {
        User user = RAC.getUser(ev.getPlayer());
        for (Check check : RAC.checkManager.getChecks()) {
            CheckReturn checkReturn = check.onMoveEvent(ev);
            if (!checkReturn.isPassed()) {
                user.getPlayer().sendMessage(check.getName() + " " + check.getType() + " | " + checkReturn.reason);
            }
        }
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent ev) {
        User user = RAC.getUser(ev.getPlayer());
        for (Check check : RAC.checkManager.getChecks()) {
            CheckReturn checkReturn = check.onVelocityEvent(ev);
            if (!checkReturn.isPassed()) {
                user.getPlayer().sendMessage(check.getName() + " " + check.getType() + " | " + checkReturn.reason);
            }
        }
    }
}
