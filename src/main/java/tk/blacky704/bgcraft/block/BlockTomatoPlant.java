package tk.blacky704.bgcraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import tk.blacky704.bgcraft.init.ModBlocks;
import tk.blacky704.bgcraft.init.ModItems;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

import java.util.Random;

/**
 * @author Blacky
 */
public class BlockTomatoPlant extends BlockCropsBG implements IGrowable
{
    //private IIcon[] icons = new IIcon[0];
    private IIcon icon;

    public BlockTomatoPlant()
    {
        super();
        this.setBlockName(Names.Blocks.TOMATO_PLANT);
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return ModItems.tomato;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
//        for (int i = 0; i < this.icons.length; ++i)
//        {
//            this.icons[i] = iconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
//        }
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tomatoPlant_stage_0");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon;
    }
}
