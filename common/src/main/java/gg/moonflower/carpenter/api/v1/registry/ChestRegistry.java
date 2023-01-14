package gg.moonflower.carpenter.api.v1.registry;

import gg.moonflower.carpenter.core.registry.CarpenterChests;
import gg.moonflower.carpenter.impl.registry.ChestRegistryImpl;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
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
     * @param blockRegistry The block registry to use
     * @param modId         The domain to use when creating a new chest type registry
     * @return A new chest registry
     */
    static ChestRegistry get(PollinatedBlockRegistry blockRegistry, String modId) {
        return ChestRegistry.get(blockRegistry, PollinatedRegistry.create(CarpenterChests.REGISTRY, modId));
    }

    /**
     * Creates a new chest registry.
     *
     * @param blockRegistry     The block registry to use
     * @param chestTypeRegistry The chest type registry to use
     * @return A new chest registry
     */
    static ChestRegistry get(PollinatedBlockRegistry blockRegistry, PollinatedRegistry<CarpenterChestType> chestTypeRegistry) {
        return new ChestRegistryImpl(blockRegistry, chestTypeRegistry);
    }

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
