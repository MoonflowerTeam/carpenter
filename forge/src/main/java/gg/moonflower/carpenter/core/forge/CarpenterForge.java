package gg.moonflower.carpenter.core.forge;

import gg.moonflower.carpenter.core.Carpenter;
import net.minecraftforge.fml.common.Mod;

@Mod(Carpenter.MOD_ID)
public class CarpenterForge {
    public CarpenterForge() {
        Carpenter.PLATFORM.setup();
    }
}
