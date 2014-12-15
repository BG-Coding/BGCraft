package tk.blacky704.bgcraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;
import tk.blacky704.bgcraft.item.*;
import tk.blacky704.bgcraft.reference.Integers;
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
    public static final ItemTomato tomato = new ItemTomato(Integers.FoodLevels.TOMATO);
    public static final ItemDriedTomato driedTomato = new ItemDriedTomato(Integers.FoodLevels.DRIED_TOMATO);
    public static final ItemMortar mortar = new ItemMortar();
    public static final ItemPasataBottle pasataBottle = new ItemPasataBottle(Integers.FoodLevels.PASATA_BOTTLE);

    public static void init()
    {
        GameRegistry.registerItem(firebrick, Names.Items.FIRE_BRICK);
        GameRegistry.registerItem(tomatoSeeds, Names.Items.TOMATO_SEEDS);
        GameRegistry.registerItem(tomato, Names.Items.TOMATO);
        GameRegistry.registerItem(driedTomato, Names.Items.DRIED_TOMATO);
        GameRegistry.registerItem(mortar, Names.Items.MORTAR);
        GameRegistry.registerItem(pasataBottle, Names.Items.PASATA_BOTTLE);
    }

    public static void initOreDic()
    {
        OreDictionary.registerOre("seedTomato", tomatoSeeds);
        OreDictionary.registerOre("foodTomato", tomato);
        OreDictionary.registerOre("foodDriedTomato", driedTomato);
        OreDictionary.registerOre("itemMortar", mortar);
        OreDictionary.registerOre("itemFireBrick", firebrick);
        OreDictionary.initVanillaEntries();
    }
}
