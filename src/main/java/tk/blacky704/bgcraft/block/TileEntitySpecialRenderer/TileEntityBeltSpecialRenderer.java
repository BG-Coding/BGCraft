package tk.blacky704.bgcraft.block.TileEntitySpecialRenderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityBelt;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author goeiecool9999
 */
public class TileEntityBeltSpecialRenderer extends TileEntitySpecialRenderer
{

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        glPushMatrix();
        glTranslated(p_147500_2_, p_147500_4_, p_147500_6_);
        glTranslated(0,0.5,0);
        TileEntityBelt belt = (TileEntityBelt)tileEntity;
        glTranslated(0,Math.sin(Math.toRadians(belt.testInt))*0.5,0);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_CULL_FACE);
        glBegin(GL_QUADS);
        glColor3d(255,255,255);
        glVertex3d(0,0,0);
        glVertex3d(1,0,0);
        glVertex3d(1,0,1);
        glVertex3d(0,0,1);
        glEnd();
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}
