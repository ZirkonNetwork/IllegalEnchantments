package nathan.illegalenchantments;

import nathan.illegalenchantments.event.PrepareAnvilEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class IllegalEnchantments extends JavaPlugin {

    public static final String VERSION = "1.2.2gr";
    public static int maximumLevel;
    public static JavaPlugin plugin = null;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        maximumLevel = getConfig().getInt("maximum-level");

        getServer().getPluginManager().registerEvents(new PrepareAnvilEvents(), this);
    }
}
