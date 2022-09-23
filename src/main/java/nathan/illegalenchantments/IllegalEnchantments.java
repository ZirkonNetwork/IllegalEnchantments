package nathan.illegalenchantments;

import nathan.illegalenchantments.event.PrepareAnvilEventsV2;
import org.bukkit.plugin.java.JavaPlugin;

public final class IllegalEnchantments extends JavaPlugin {

    public static final String VERSION = "1.2.1";
    public static int maximumLevel;
    public static JavaPlugin plugin = null;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        maximumLevel = getConfig().getInt("maximum-level");

        getServer().getPluginManager().registerEvents(new PrepareAnvilEventsV2(), this);
    }
}
