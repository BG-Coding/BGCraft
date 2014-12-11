package tk.blacky704.bgcraft.block.TileEntitySpecialRenderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author goeiecool9999
 */
public class TileEntityBeltSpecialRenderer extends TileEntitySpecialRenderer
{

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        glTranslated(p_147500_2_, p_147500_4_, p_147500_6_);
        glBegin(GL_QUADS);
        glVertex3d(0,0,0);
        glVertex3d(1,0,0);
        glVertex3d(1,0,1);
        glVertex3d(0,0,1);
        glEnd();
    }
}
