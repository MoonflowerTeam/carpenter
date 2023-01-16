package gg.moonflower.carpenter.api.v1.registry;

import gg.moonflower.carpenter.impl.registry.ChestRegistryImpl;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

/**
 * Registers Carpenter chests and chest types.
 *
 * @author Ocelot
 * @since 1.1.0
 */
public interface ChestRegistry {

    /**
     * Creates a new chest registry and chest type registry.
     *
     * @param modId The domain to use when creating registries
     * @return A new chest registry
     */
    static ChestRegistry get(String modId) {
        return new ChestRegistryImpl(modId);
    }

    /**
     * Registers the internal chest registries. This should be called in the common init of the mod.
     */
    void register();

    /**
     * Registers a new chest block and chest type with the specified name.
     *
     * @param chestType The name of the block, item, and chest type
     * @return A new chest block
     */
    default Supplier<Block> registerChest(String chestType) {
        // register chest type alongside the block
        return this.registerChest(chestType, this.getOrRegisterChestType(chestType));
    }

    /**
     * Registers a new chest block with the specified name and chest type.
     *
     * @param name      The name of the chest block/item
     * @param chestType The chest type to use for textures
     * @return A new chest block
     */
    Supplier<Block> registerChest(String name, Supplier<CarpenterChestType> chestType);

    /**
     * Registers a new trapped chest block and chest type with the specified name.
     *
     * @param chestType The name of the block, item, and chest type
     * @return A new trapped chest block
     */
    default Supplier<Block> registerTrappedChest(String chestType) {
        // register chest type alongside the block
        return this.registerTrappedChest(chestType, this.getOrRegisterChestType(chestType));
    }

    /**
     * Registers a new trapped chest block with the specified name and chest type.
     *
     * @param name      The name of the chest block/item
     * @param chestType The chest type to use for textures
     * @return A new trapped chest block
     */
    Supplier<Block> registerTrappedChest(String name, Supplier<CarpenterChestType> chestType);

    /**
     * Registers a new carpenter chest type or returns an existing one.
     *
     * @param chestType The name of the type to register
     * @return A new chest type or a pre-registered type with all the paths to chest textures set
     */
    Supplier<CarpenterChestType> getOrRegisterChestType(String chestType);

    /**
     * Registers a new carpenter chest type. {@link #registerChest(String)} and {@link #registerTrappedChest(String)} should usually be used instead, but this is still an option.
     *
     * @param chestType The name of the type to register
     * @return A new chest type with all the paths to chest textures set
     */
    Supplier<CarpenterChestType> registerChestType(String chestType);
}
