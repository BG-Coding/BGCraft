package tk.blacky704.bgcraft.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.BGCraft;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityPizzaOven;
import tk.blacky704.bgcraft.init.ModBlocks;
import tk.blacky704.bgcraft.reference.Names;

import java.util.Random;

/**
 * @author Blacky
 */
public class BlockPizzaOven extends BlockContainerBG
{
    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    public BlockPizzaOven(boolean isActive)
    {
        super();
        this.isActive = isActive;
        this.setHarvestLevel("pickaxe", 0);
        if(this.isActive)
        {
            this.setBlockName(Names.Blocks.PIZZA_OVEN_ACTIVE);
            this.setLightLevel(0.65f);
        } else this.setBlockName(Names.Blocks.PIZZA_OVEN_IDLE);
    }

    public Item getItemDropped(int par1, Random random, int par3)
    {
        return Item.getItemFromBlock(ModBlocks.pizzaOvenIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z)
    {
        if (!world.isRemote)
        {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b0 = 3;
            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }
            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }
            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }
            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, float hitX, float hitY, float hitZ)
    {
//        if(world.isRemote)
//        {
//            FMLNetworkHandler.openGui(player, BGCraft.instance, , world, x, y, z);
//        }
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityFurnace tileentityfurnace = (TileEntityFurnace)world.getTileEntity(x, y, z);

            if (tileentityfurnace != null)
            {
                player.func_146101_a(tileentityfurnace);
            }

            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileEntityPizzaOven();
    }

}
