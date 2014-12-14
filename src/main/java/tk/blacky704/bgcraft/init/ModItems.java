package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;
import tk.blacky704.bgcraft.item.ItemDriedTomato;
import tk.blacky704.bgcraft.item.ItemFirebrick;
import tk.blacky704.bgcraft.item.ItemTomato;
import tk.blacky704.bgcraft.item.ItemTomatoSeeds;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemFirebrick firebrick = new ItemFirebrick();
    public static final ItemTomatoSeeds tomatoSeeds = new ItemTomatoSeeds(ModBlocks.tomatoPlant, Blocks.farmland);
    public static final ItemTomato tomato = new ItemTomato();
    public static final ItemDriedTomato driedTomato = new ItemDriedTomato();

    public static void init()
    {
        GameRegistry.registerItem(firebrick, Names.Items.FIRE_BRICK);
        GameRegistry.registerItem(tomatoSeeds, Names.Items.TOMATO_SEEDS);
        GameRegistry.registerItem(tomato, Names.Items.TOMATO);
        GameRegistry.registerItem(driedTomato, Names.Items.DRIED_TOMATO);
    }

    public static void initOreDic()
    {
        OreDictionary.registerOre("seedTomato", tomatoSeeds);
        OreDictionary.registerOre("foodTomato", tomato);
        OreDictionary.registerOre("foodDriedTomato", driedTomato);
    }
}
