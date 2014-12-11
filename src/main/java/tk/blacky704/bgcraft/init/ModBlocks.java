package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.block.BlockFirebricks;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class ModBlocks
{
    public static BlockFirebricks firebricks = new BlockFirebricks();

    public static void init()
    {
        GameRegistry.registerBlock(firebricks, Names.Blocks.FIRE_BRICKS);
    }
}
