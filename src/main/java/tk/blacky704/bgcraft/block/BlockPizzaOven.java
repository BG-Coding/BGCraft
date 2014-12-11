package tk.blacky704.bgcraft.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            FMLNetworkHandler.openGui(player, BGCraft.instance, 1, world, x, y, z);
            return true;
        }
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack)
    {
        int l = determineOrientation(world, x, y, z, player);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        System.out.println("metadata:" + world.getBlockMetadata(x, y, z));
    }

    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLivingBase par4EntityLivingBase)
    {
        if (MathHelper.abs((float) par4EntityLivingBase.posX - (float) par1) < 2.0F && MathHelper.abs((float) par4EntityLivingBase.posZ - (float) par3) < 2.0F)
        {
            double d0 = par4EntityLivingBase.posY + 1.82D - (double) par4EntityLivingBase.yOffset;

            if (d0 - (double) par2 > 2.0D)
            {
                return 1;
            }

            if ((double) par2 - d0 > 0.0D)
            {
                return 0;
            }
        }
        int l = MathHelper.floor_double((double) (par4EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TileEntityPizzaOven();
    }

}
