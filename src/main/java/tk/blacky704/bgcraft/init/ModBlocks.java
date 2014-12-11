package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.block.BlockBelt;
import tk.blacky704.bgcraft.block.BlockFirebricks;
import tk.blacky704.bgcraft.reference.Names;

/**
 * @author Blacky
 */
public class ModBlocks
{
    //Pizza Oven stuff
    public static BlockFirebricks firebricks = new BlockFirebricks();
    //Item Transportation
    public static BlockBelt belt = new BlockBelt();

    public static void init()
    {
        GameRegistry.registerBlock(firebricks, Names.Blocks.FIRE_BRICKS);
        GameRegistry.registerBlock(belt, Names.Blocks.BELT);
    }
}
