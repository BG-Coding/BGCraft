package tk.blacky704.bgcraft.block.TileEntitySpecialRenderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import tk.blacky704.bgcraft.block.tileEntity.TileEntityBelt;
import tk.blacky704.bgcraft.reference.Reference;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author goeiecool9999
 */
public class TileEntityBeltSpecialRenderer extends TileEntitySpecialRenderer
{

    private static int baseList;
    private static int ridgeList;
    private static final ResourceLocation Texture = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/pizzaoven_bottom.png");

    public TileEntityBeltSpecialRenderer()
    {



        //intialize lists
        baseList = glGenLists(1);
        ridgeList = glGenLists(1);
        Tessellator t = Tessellator.instance;
        //compile lists.
        glNewList(baseList, GL_COMPILE);
        t.startDrawingQuads();
        t.setColorOpaque(255,255,255);
        t.setBrightness(1);
        t.addVertexWithUV(0,0,0,0,0);
        t.addVertexWithUV(1,0,0,1,0);
        t.addVertexWithUV(1,0,1,1,1);
        t.addVertexWithUV(0,0,1,0,1);
        t.draw();
        glEndList();


    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_147500_8_)
    {
        TileEntityBelt belt = (TileEntityBelt)tileEntity;
        if (belt.animationProgress < 100)
        {
            belt.animationProgress++;
        } else
        {
            belt.animationProgress++;
            belt.animationProgress -= 100;

        }

        glPushMatrix();
        glTranslated(posX, posY, posZ);
        this.bindTexture(Texture);
        glCallList(baseList);
        glPopMatrix();

    }
}
