package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityPizzaOven;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityPizzaOven.class, Names.TileEntities.PIZZA_OVEN);
    }
}
