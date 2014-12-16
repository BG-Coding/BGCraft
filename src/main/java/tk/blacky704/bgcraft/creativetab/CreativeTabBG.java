package tk.blacky704.bgcraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tk.blacky704.bgcraft.init.ModItems;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
public class CreativeTabBG
{
    public static final CreativeTabs BG_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.tomato;
        }
    };
}
