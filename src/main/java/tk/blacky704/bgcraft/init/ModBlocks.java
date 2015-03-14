package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import tk.blacky704.bgcraft.block.*;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static BlockFirebricks firebricks = new BlockFirebricks();
    public static BlockPizzaOven pizzaOvenIdle = new BlockPizzaOven(false);
    public static BlockPizzaOven pizzaOvenActive = new BlockPizzaOven(true);

    public static BlockCreativeGenerator creativeGenerator = new BlockCreativeGenerator();

    public static BlockBelt belt = new BlockBelt();

    public static BlockTomatoPlant tomatoPlant = new BlockTomatoPlant();

    public static void init()
    {
        GameRegistry.registerBlock(firebricks, Names.Blocks.FIRE_BRICKS);
        GameRegistry.registerBlock(pizzaOvenActive, Names.Blocks.PIZZA_OVEN_ACTIVE);
        GameRegistry.registerBlock(pizzaOvenIdle, Names.Blocks.PIZZA_OVEN_IDLE);
        GameRegistry.registerBlock(belt, Names.Blocks.BELT);
        GameRegistry.registerBlock(tomatoPlant, Names.Blocks.TOMATO_PLANT);
        GameRegistry.registerBlock(creativeGenerator, Names.Blocks.CREATIVE_GENERATOR);
    }
}
