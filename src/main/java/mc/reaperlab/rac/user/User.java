package mc.reaperlab.rac.user;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class User {

    private Player player;

    public Player getPlayer() {
        return this.player;
    }

    public Location getLocation() {
        return player.getLocation();
    }

    public int getPing() {
        return player.getPing();
    }

    public boolean onGround() {
        return !player.getLocation().subtract(0,1,0).getBlock().isEmpty();
    }

}
