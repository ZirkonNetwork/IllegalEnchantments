package nathan.illegalenchantments;

import nathan.illegalenchantments.event.PrepareAnvilEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IllegalEnchantments extends JavaPlugin {

    public static final String VERSION = "1.2.5";
    public static int maximumLevel;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        maximumLevel = getConfig().getInt("maximum-level");

        getServer().getPluginManager().registerEvents(new PrepareAnvilEvents(), this);
    }
}
