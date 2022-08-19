package nathan.illegalenchantments.event;

import nathan.illegalenchantments.IllegalEnchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class PrepareAnvilEvents implements Listener {
    @SuppressWarnings("ConstantConditions")
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void prepareAnvilForEnchants(PrepareAnvilEvent event) {
        final AnvilInventory anvilInventory = event.getInventory();

        anvilInventory.setMaximumRepairCost(IllegalEnchantments.maximumLevel);

        if (anvilInventory.getItem(0) != null && anvilInventory.getItem(1) != null && event.getResult() != null) {
            final ItemStack input = anvilInventory.getItem(0);
            final ItemStack input1 = anvilInventory.getItem(1);
            ItemStack result = event.getResult();

            if (input.getItemMeta().hasEnchants() && input1.getItemMeta().hasEnchants() && input.getType() == input1.getType()) {
                final Map<Enchantment, Integer> resultEnchantments = result.getEnchantments();
                Map<Enchantment, Integer> enchantments = new HashMap<>();

                for (Enchantment enchantment : input.getEnchantments().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (input.getEnchantmentLevel(enchantment) == input1.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment) + 1)
                                && !(input.getEnchantmentLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment) + 1);
                        } else if (input.getEnchantmentLevel(enchantment) > input1.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                        } else if (input.getEnchantmentLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                        }
                    }
                }

                for (Enchantment enchantment : input1.getEnchantments().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (input1.getEnchantmentLevel(enchantment) == input.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input1.getEnchantmentLevel(enchantment) + 1)
                                && !(input1.getEnchantmentLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, input1.getEnchantmentLevel(enchantment) + 1);
                        } else if (input1.getEnchantmentLevel(enchantment) > input.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input1.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input1.getEnchantmentLevel(enchantment));
                        } else if (input1.getEnchantmentLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input1.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input1.getEnchantmentLevel(enchantment));
                        }
                    }
                }
                // add enchants to result from map and save result
                result.addUnsafeEnchantments(enchantments);
                event.setResult(result);
            } else if (input.getItemMeta() instanceof EnchantmentStorageMeta && input.getType() == input1.getType()) {
                final EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) input.getItemMeta();
                final EnchantmentStorageMeta enchantmentStorageMeta1 = (EnchantmentStorageMeta) input1.getItemMeta();
                final Map<Enchantment, Integer> resultEnchantments = result.getItemMeta().getEnchants();
                Map<Enchantment, Integer> enchantments = new HashMap<>();

                for (Enchantment enchantment : ((EnchantmentStorageMeta) input.getItemMeta()).getStoredEnchants().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (enchantmentStorageMeta.getStoredEnchantLevel(enchantment) == enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta.getStoredEnchantLevel(enchantment) + 1)
                                && !(enchantmentStorageMeta.getStoredEnchantLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment) + 1);
                        } else if (enchantmentStorageMeta.getStoredEnchantLevel(enchantment) > enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
                        } else if (enchantmentStorageMeta.getStoredEnchantLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
                        }
                    }
                }

                for (Enchantment enchantment : ((EnchantmentStorageMeta) input1.getItemMeta()).getStoredEnchants().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) == enchantmentStorageMeta.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1)
                                && !(enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1);
                        } else if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) > enchantmentStorageMeta.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                        } else if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                        }
                    }
                }
                // add enchants to result from map and save result
                ItemMeta resultMeta = result.getItemMeta();
                enchantments.forEach((enchantment, level) -> ((EnchantmentStorageMeta) resultMeta).addStoredEnchant(enchantment, level, true));
                result.setItemMeta(resultMeta);
                event.setResult(result);
            } else if (!(input.getItemMeta() instanceof EnchantmentStorageMeta)  && input1.getItemMeta() instanceof EnchantmentStorageMeta) {
                final EnchantmentStorageMeta enchantmentStorageMeta1 = (EnchantmentStorageMeta) input1.getItemMeta();
                final Map<Enchantment, Integer> resultEnchantments = result.getItemMeta().getEnchants();
                Map<Enchantment, Integer> enchantments = new HashMap<>();

                for (Enchantment enchantment : input.getEnchantments().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (input.getEnchantmentLevel(enchantment) == enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment) + 1)
                                && !(input.getEnchantmentLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment) + 1);
                        } else if (input.getEnchantmentLevel(enchantment) > enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                        } else if (input.getEnchantmentLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < input.getEnchantmentLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                        }
                    }
                }

                for (Enchantment enchantment : ((EnchantmentStorageMeta) input1.getItemMeta()).getStoredEnchants().keySet()) {
                    if (enchantment.getMaxLevel() > 1) {
                        if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) == input.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1)
                                && !(enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1 > enchantment.getMaxLevel() * 2)) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) + 1);
                        } else if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) > input.getEnchantmentLevel(enchantment)
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                        } else if (enchantmentStorageMeta1.getStoredEnchantLevel(enchantment) > enchantment.getMaxLevel()
                                && (!resultEnchantments.containsKey(enchantment)
                                || resultEnchantments.get(enchantment) < enchantmentStorageMeta1.getStoredEnchantLevel(enchantment))) {
                            // add enchantment to map
                            enchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                        }
                    }
                }
                // add enchants to result from map and save result
                result.addUnsafeEnchantments(enchantments);
                event.setResult(result);
            }
        }
    }
}
