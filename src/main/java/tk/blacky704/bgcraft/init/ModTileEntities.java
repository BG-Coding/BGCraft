package tk.blacky704.bgcraft.init;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tk.blacky704.bgcraft.block.BlockBelt;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;
import tk.blacky704.bgcraft.tileentity.TileEntityBelt;
import tk.blacky704.bgcraft.tileentity.TileEntityPizzaOven;
import tk.blacky704.bgcraft.tileentity.TileEntityVacuumPump;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityPizzaOven.class, Names.TileEntities.PIZZA_OVEN);
        GameRegistry.registerTileEntity(TileEntityBelt.class, Names.TileEntities.BELT);
        GameRegistry.registerTileEntity(TileEntityVacuumPump.class, Names.TileEntities.VACUUM_PUMP);

        initRenderer();
    }

    @SideOnly(Side.CLIENT)
    private static void initRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBelt.class, BlockBelt.renderer);
    }
}
