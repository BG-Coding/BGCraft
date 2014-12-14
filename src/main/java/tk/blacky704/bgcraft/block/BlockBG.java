package tk.blacky704.bgcraft.block;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import tk.blacky704.bgcraft.creativetab.CreativeTabBG;
import tk.blacky704.bgcraft.reference.Reference;

/**
 * @author Blacky
 */
public class BlockBG extends Block
{
    public BlockBG(Material m)
    {
        super(m);
        this.setCreativeTab(CreativeTabBG.BG_TAB);
        this.setHardness(1.5f);
        this.setHarvestLevel("pickaxe", 0);
    }

    public BlockBG()
    {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
