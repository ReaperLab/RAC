package mc.reaperlab.rac.user;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class User {

    private Player player;
    private boolean damagedLast = false;
    public int packetsImmune = 0;
    public int packetsInGUI = 0;
    public int flags = 0;
    public int CPS = 0;
    public int floatTime = 0;
    public int glideTime = 0;
    public int upTime = 0;

    public User(Player player) {this.player = player;}

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
        return !(player.getLocation().subtract(0,0.1,0).getBlock().isEmpty()
                && player.getLocation().subtract(0,0.1,1).getBlock().isEmpty()
                && player.getLocation().subtract(0,0.1,-1).getBlock().isEmpty()
                && player.getLocation().subtract(1,0.1,0).getBlock().isEmpty()
                && player.getLocation().subtract(-1,0.1,0).getBlock().isEmpty()
                && player.getLocation().subtract(1,0.1,1).getBlock().isEmpty()
                && player.getLocation().subtract(-1,0.1,-1).getBlock().isEmpty()
                && player.getLocation().subtract(1,0.1,-1).getBlock().isEmpty()
                && player.getLocation().subtract(-1,0.1,1).getBlock().isEmpty()
                && player.getLocation().subtract(-1,0.1,1).getBlock().isEmpty()
                && player.getLocation().subtract(1,0.1,-1).getBlock().isEmpty()
        );
    }

    public boolean isBlockAbove() {
        return !(player.getLocation().add(0,2,0).getBlock().isEmpty()
                && player.getLocation().add(0,2,1).getBlock().isEmpty()
                && player.getLocation().add(0,2,-1).getBlock().isEmpty()
                && player.getLocation().add(1,2,0).getBlock().isEmpty()
                && player.getLocation().add(-1,2,0).getBlock().isEmpty()
                && player.getLocation().add(1,2,1).getBlock().isEmpty()
                && player.getLocation().add(-1,2,-1).getBlock().isEmpty()
                && player.getLocation().add(1,2,-1).getBlock().isEmpty()
                && player.getLocation().add(-1,2,1).getBlock().isEmpty()
                && player.getLocation().add(-1,2,1).getBlock().isEmpty()
                && player.getLocation().add(1,2,-1).getBlock().isEmpty()
        );
    }

    public boolean wasDamagedLast() {
        return damagedLast;
    }

    public void setDamagedLast(boolean bool) {
        this.damagedLast = bool;
    }

}
