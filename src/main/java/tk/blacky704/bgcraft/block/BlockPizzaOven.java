package tk.blacky704.bgcraft.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.BGCraft;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityPizzaOven;
import tk.blacky704.bgcraft.init.ModBlocks;
import tk.blacky704.bgcraft.reference.Integers;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.reference.Reference;

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

    private static boolean keepInventory;

    public BlockPizzaOven(boolean isActive)
    {
        super();
        this.isActive = isActive;
        this.setHarvestLevel("pickaxe", 0);
        if (this.isActive)
        {
            this.setBlockName(Names.Blocks.PIZZA_OVEN_ACTIVE);
            this.setLightLevel(0.65f);
        } else this.setBlockName(Names.Blocks.PIZZA_OVEN_IDLE);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":pizzaoven_side");
        this.iconFront = iconRegister.registerIcon(Reference.MOD_ID + ":" + (this.isActive ? "pizzaoven_front_on" : "pizzaoven_front_off"));
        this.iconBottom = iconRegister.registerIcon(Reference.MOD_ID + ":pizzaoven_bottom");
        this.iconTop = iconRegister.registerIcon(Reference.MOD_ID + ":pizzaoven_top");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconBottom : (side != meta ? this.blockIcon : this.iconFront));
    }

    public Item getItemDropped(int par1, Random random, int par3)
    {
        return Item.getItemFromBlock(ModBlocks.pizzaOvenIdle);
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.setDefautDirection(world, x, y , z);
    }

    private void setDefautDirection(World world, int x, int y, int z)
    {
        if(!world.isRemote)
        {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);
            byte b0 = 3;
            if(b1.func_149730_j() && !b2.func_149698_L())
            {
                b0 = 3;
            }
            if(b2.func_149730_j() && !b1.func_149698_L())
            {
                b0 = 2;
            }
            if(b3.func_149730_j() && !b4.func_149698_L())
            {
                b0 = 5;
            }
            if(b4.func_149730_j() && !b3.func_149698_L())
            {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, BGCraft.instance, Integers.GuiIds.PIZZA_OVEN, world, x, y, z);
        }
        return true;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }if(l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }if(l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }if(l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if(itemStack.hasDisplayName())
        {
            TileEntityPizzaOven pizzaOven = (TileEntityPizzaOven) world.getTileEntity(x, y, z);
            pizzaOven.setGuiDisplayName(Names.Containers.PIZZA_OVEN);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileEntityPizzaOven();
    }

    public static void updateBlockState(boolean burning, World world, int x, int y, int z)
    {
        int i = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        keepInventory = true;
        if(burning)
        {
            world.setBlock(x, y, z, ModBlocks.pizzaOvenActive);
        } else
            world.setBlock(x, y, z, ModBlocks.pizzaOvenIdle);
        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, i, 2);
        if(tileEntity != null)
        {
            tileEntity.validate();
            world.setTileEntity(x, y, z, tileEntity);
        }
    }
}
