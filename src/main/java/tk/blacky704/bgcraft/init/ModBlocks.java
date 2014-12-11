package tk.blacky704.bgcraft.init;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.block.BlockBelt;
import tk.blacky704.bgcraft.block.BlockFirebricks;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityBelt;
import tk.blacky704.bgcraft.block.BlockPizzaOven;
import tk.blacky704.bgcraft.block.TileEntitySpecialRenderer.TileEntityBeltSpecialRenderer;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    //Pizza Oven stuff
    public static BlockFirebricks firebricks = new BlockFirebricks();
    public static BlockPizzaOven pizzaOvenIdle = new BlockPizzaOven(false);
    public static BlockPizzaOven pizzaOvenActive = new BlockPizzaOven(true);
    //Item Transportation
    public static BlockBelt belt = new BlockBelt();

    public static void init()
    {
        GameRegistry.registerBlock(firebricks, Names.Blocks.FIRE_BRICKS);
        GameRegistry.registerBlock(pizzaOvenActive, Names.Blocks.PIZZA_OVEN_ACTIVE);
        GameRegistry.registerBlock(pizzaOvenIdle, Names.Blocks.PIZZA_OVEN_IDLE);
        GameRegistry.registerBlock(belt, Names.Blocks.BELT);
        ClientRegistry.registerTileEntity(TileEntityBelt.class, "TileEntityBelt", new TileEntityBeltSpecialRenderer());
    }
}
