package tk.blacky704.bgcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import tk.blacky704.bgcraft.creativetab.CreativeTabBG;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
public class ItemFoodBG extends ItemFood
{
    public ItemFoodBG(int healAmount, float saturationModifier, boolean wolf)
    {
        super(healAmount, saturationModifier, wolf);
        this.setCreativeTab(CreativeTabBG.BG_TAB);
    }

    public ItemFoodBG(int healAmount, boolean wolf)
    {
        super(healAmount, 0.6f, wolf);
        this.setCreativeTab(CreativeTabBG.BG_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
