package tk.blacky704.bgcraft.block.BlockRenderingHandler;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import tk.blacky704.bgcraft.block.BlockBelt;

/**
 * Created by Klaas on 1-2-2015.
 */
public class BlockBeltHandler implements ISimpleBlockRenderingHandler
{
    private int renderID;

    public BlockBeltHandler(int renderID)
    {
        this.renderID = renderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {

        BlockBelt.renderer.renderModelWithoutAnimation();

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return renderID;
    }
}
