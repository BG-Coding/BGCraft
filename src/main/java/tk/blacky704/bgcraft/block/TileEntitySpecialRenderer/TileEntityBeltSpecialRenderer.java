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
        TileEntityBelt belt = (TileEntityBelt)tileEntity;
        if (belt.testInt < 360)
        {
            belt.testInt++;
        } else {
            belt.testInt++;
            belt.testInt -= 360;
        }

        glPushMatrix();
        glTranslated(p_147500_2_, p_147500_4_, p_147500_6_);
        glTranslated(0.5,0.5,0.5);



        glTranslated(0,Math.sin(Math.toRadians(belt.testInt))*0.5,0);
        glRotated(belt.testInt*4,1,0,0);
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_CULL_FACE);
        glBegin(GL_POLYGON);
        for(double i = 0; i<360;i++)
        {
            glColor3d((Math.sin(Math.toRadians(i))*.5+.5)*255,(Math.sin(Math.toRadians(i+120))*.5+.5)*255,(Math.sin(Math.toRadians(i+240))*.5+.5)*255);
            glVertex3d(Math.cos(Math.toRadians(i))*.5,0,Math.sin(Math.toRadians(i))*.5);
        }
        glEnd();
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glPopMatrix();
    }
}
