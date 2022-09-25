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
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("ConstantConditions")
public class PrepareAnvilEvents implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void processIllegalEnchantments(PrepareAnvilEvent event) {
        final AnvilInventory anvilInventory = event.getInventory();
        anvilInventory.setMaximumRepairCost(IllegalEnchantments.maximumLevel);

        if (anvilInventory.getItem(0) != null && anvilInventory.getItem(1) != null && event.getResult() != null) {
            final ItemStack input = anvilInventory.getItem(0);
            final ItemStack input1 = anvilInventory.getItem(1);
            ItemStack result = event.getResult();

            if (input.getItemMeta() != null && input1.getItemMeta() != null && result.getItemMeta() != null) {
                if (input.getItemMeta().hasEnchants() && input1.getItemMeta().hasEnchants() && input.getType() == input1.getType()) {
                    final Map<Enchantment, Integer> resultEnchantments = result.getEnchantments();
                    Map<Enchantment, Integer> finalEnchantments = new HashMap<>();

                    for (Enchantment enchantment : enchantmentSet(input)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (levelsCanBeAdded(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment) + 1);
                            } else if (firstInputIsHigher(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                            }
                        }
                    }

                    for (Enchantment enchantment : enchantmentSet(input1)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (firstInputIsHigher(enchantment, input1, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input1.getEnchantmentLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input1.getEnchantmentLevel(enchantment));
                            }
                        }
                    }

                    // remove conflicting and add result enchantments then save result
                    event.setResult(createResult(input, input1, result, finalEnchantments));
                } else if (input.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta && input.getType() == input1.getType()) {
                    final EnchantmentStorageMeta enchantmentStorageMeta1 = (EnchantmentStorageMeta) input1.getItemMeta();
                    final Map<Enchantment, Integer> resultEnchantments = result.getItemMeta().getEnchants();
                    Map<Enchantment, Integer> finalEnchantments = new HashMap<>();

                    for (Enchantment enchantment : enchantmentSet(input)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (levelsCanBeAdded(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment) + 1);
                            } else if (firstInputIsHigher(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
                            }
                        }
                    }

                    for (Enchantment enchantment : enchantmentSet(input1)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (firstInputIsHigher(enchantment, input1, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                            }
                        }
                    }
                    // add enchants to result from map and save result
                    event.setResult(createResult(input, input1, result, finalEnchantments));
                } else if (!(input.getItemMeta() instanceof EnchantmentStorageMeta) && !input.getEnchantments().isEmpty() && input1.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta1) {
                    final Map<Enchantment, Integer> resultEnchantments = result.getEnchantments();
                    Map<Enchantment, Integer> finalEnchantments = new HashMap<>();

                    for (Enchantment enchantment : enchantmentSet(input)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (levelsCanBeAdded(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment) + 1);
                            } else if (firstInputIsHigher(enchantment, input, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, input.getEnchantmentLevel(enchantment));
                            }
                        }
                    }

                    for (Enchantment enchantment : enchantmentSet(input1)) {
                        if (enchantment.getMaxLevel() > 1) {
                            if (firstInputIsHigher(enchantment, input1, input, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                            } else if (enchNotMoreThanDouble(enchantment, input1, resultEnchantments)) {
                                // add enchantment to map
                                finalEnchantments.put(enchantment, enchantmentStorageMeta1.getStoredEnchantLevel(enchantment));
                            }
                        }
                    }
                    // add enchants to result from map and save result
                    event.setResult(createResult(input, input1, result, finalEnchantments));
                }
            }
        }
    }

    @NotNull
    private static ItemStack createResult(final ItemStack input, final ItemStack input1, final ItemStack result, final Map<Enchantment, Integer> finalEnchantments) {
        if (result.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta) {
            finalEnchantments.forEach((enchantment, level) -> enchantmentStorageMeta.addStoredEnchant(enchantment, level, true));
            result.setItemMeta(enchantmentStorageMeta);
        } else {
            Set<Enchantment> conflicting = getConflicting(enchantmentSet(input), enchantmentSet(input1));
            if (!conflicting.isEmpty()) conflicting.forEach(finalEnchantments::remove);
            result.addUnsafeEnchantments(finalEnchantments);
        }
        return result;
    }

    @NotNull
    private static Set<Enchantment> enchantmentSet(ItemStack input) {
        return input.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta ? enchantmentStorageMeta.getStoredEnchants().keySet() : input.getEnchantments().keySet();
    }

    @NotNull
    private static Set<Enchantment> getConflicting(final Set<Enchantment> inputEnchantments, final Set<Enchantment> enchantmentsToCheck) {
        Set<Enchantment> toCheck = new HashSet<>(enchantmentsToCheck);
        for (Enchantment enchantment : inputEnchantments) {
            toCheck.removeIf(ench -> !ench.conflictsWith(enchantment));
        }
        return toCheck;
    }

    private static boolean levelsCanBeAdded(final Enchantment enchantment, final ItemStack input, final ItemStack input1, final Map<Enchantment, Integer> resultEnchantments) {
        final int inputEnchLevel = input.getEnchantmentLevel(enchantment);
        final int resultEnchLevel = resultEnchantments.get(enchantment);
        final int doubleMaxLevel = enchantment.getMaxLevel() * 2;
        final boolean resultContainsEnch = resultEnchantments.containsKey(enchantment);

        if (input.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta && input1.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta1) {
            final int storedEnchLevel = enchantmentStorageMeta.getStoredEnchantLevel(enchantment);

            return storedEnchLevel == enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                    && (!resultContainsEnch || resultEnchLevel < storedEnchLevel + 1)
                    && !(storedEnchLevel + 1 > doubleMaxLevel);
        } else if (!(input.getItemMeta() instanceof EnchantmentStorageMeta) && input1.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta1) {
            return inputEnchLevel == enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                    && (!resultContainsEnch || resultEnchLevel < inputEnchLevel + 1)
                    && !(inputEnchLevel + 1 > doubleMaxLevel);
        }
        return inputEnchLevel == input1.getEnchantmentLevel(enchantment)
                && (!resultContainsEnch || resultEnchLevel < inputEnchLevel + 1)
                && !(inputEnchLevel + 1 > doubleMaxLevel);
    }

    private static boolean firstInputIsHigher(final Enchantment enchantment, final ItemStack input, final ItemStack input1, final Map<Enchantment, Integer> resultEnchantments) {
        final int inputEnchLevel = input.getEnchantmentLevel(enchantment);
        final int resultEnchLevel = resultEnchantments.get(enchantment);
        final boolean resultContainsEnch = resultEnchantments.containsKey(enchantment);

        if (input.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta && input1.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta1) {
            return enchantmentStorageMeta.getStoredEnchantLevel(enchantment) > enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                    && (!resultContainsEnch || resultEnchLevel < enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
        } else if (!(input.getItemMeta() instanceof EnchantmentStorageMeta) && input1.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta1) {
            return inputEnchLevel > enchantmentStorageMeta1.getStoredEnchantLevel(enchantment)
                    && (!resultContainsEnch || resultEnchLevel < inputEnchLevel);
        }
        return inputEnchLevel > input1.getEnchantmentLevel(enchantment)
                && (!resultContainsEnch || resultEnchLevel < inputEnchLevel);
    }

    private static boolean enchNotMoreThanDouble(final Enchantment enchantment, final ItemStack input, final Map<Enchantment, Integer> resultEnchantments) {
        final int resultEnchLevel = resultEnchantments.get(enchantment);
        final boolean resultContainsEnch = resultEnchantments.containsKey(enchantment);

        if (input.getItemMeta() instanceof final EnchantmentStorageMeta enchantmentStorageMeta) {
            return !(enchantmentStorageMeta.getStoredEnchantLevel(enchantment) > enchantment.getMaxLevel() * 2)
                    && (!resultContainsEnch || resultEnchLevel < enchantmentStorageMeta.getStoredEnchantLevel(enchantment));
        }
        return !(input.getEnchantmentLevel(enchantment) > enchantment.getMaxLevel() * 2)
                && (!resultContainsEnch || resultEnchLevel < input.getEnchantmentLevel(enchantment));
    }

}
