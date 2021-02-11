package me.undeadguppy.poppedcorn;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PoppedCorn extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		int pluginId = 10291;
		new Metrics(this, pluginId);
	}

	@EventHandler
	public void snowballCheck(ProjectileLaunchEvent event) {
		if (!(event.getEntity() instanceof Snowball)) {
			return;
		}

		Projectile snowball = (Snowball) event.getEntity();
		if (snowball.getShooter() instanceof Player) {
			return;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!snowball.isDead() && snowball.isValid()) {
					snowball.remove();
				}
			}
		}.runTaskLater(this, getConfig().getInt("snowball-life") * 20L);

		return;
	}

}
