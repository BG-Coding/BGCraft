package tk.blacky704.bgcraft.block;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tk.blacky704.bgcraft.block.handler.BlockBeltHandler;
import tk.blacky704.bgcraft.reference.Names;
import tk.blacky704.bgcraft.tileentity.TileEntityBelt;
import tk.blacky704.bgcraft.tileentity.renderer.TileEntityBeltSpecialRenderer;

/**
 * @author goeiecool9999
 */
public class BlockBelt extends BlockBG implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    public static TileEntityBeltSpecialRenderer renderer = new TileEntityBeltSpecialRenderer();
    public int renderID = RenderingRegistry.getNextAvailableRenderId();
    public BlockBelt()
    {
        super();
        this.setBlockName(Names.Blocks.BELT);
        RenderingRegistry.registerBlockHandler(new BlockBeltHandler(renderID));
        FMLCommonHandler.instance().bus().register(new TileEntityBelt.EventHandler());
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int oldMeta)
    {
        super.breakBlock(world, x, y, z, block, oldMeta);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new TileEntityBelt();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return renderID;
    }
}
