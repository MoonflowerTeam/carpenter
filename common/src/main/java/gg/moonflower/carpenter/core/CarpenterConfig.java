package gg.moonflower.carpenter.core;

import gg.moonflower.pollen.api.config.PollinatedConfigBuilder;

public class CarpenterConfig {

    public final PollinatedConfigBuilder.ConfigValue<Boolean> enableStructureReplacements;

    CarpenterConfig(PollinatedConfigBuilder builder) {
        this.enableStructureReplacements = builder
                .comment(" Replaces blocks in structures with their respective wood variant.",
                        " Villages: Bookshelves and chests use the respective wood from the village",
                        " Stronghold: All oak wood is replaced with spruce, including bookshelves, chests, doors",
                        " Mansion: Bookshelves and chests are replaced with Dark Oak variants",
                        " Bastions: Chests replaced with crimson chests")
                .define("Enable Structure Replacements", true);
    }
}
