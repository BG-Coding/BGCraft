package tk.blacky704.bgcraft.tileentity.tileentityspecialrenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import tk.blacky704.bgcraft.reference.Reference;
import tk.blacky704.bgcraft.tileentity.TileEntityBelt;

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
        //compile lists.
        glNewList(baseList, GL_COMPILE);
        renderBase();
        glEndList();
        glNewList(ridgeList, GL_COMPILE);
        renderRidges();
        glEndList();
    }

    private static float topHeight = 8f / 16f;

    private static void renderBase()
    {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setNormal(0, 0, 0);
        //top
        t.setColorOpaque(255, 255, 255);
        t.addVertexWithUV(0, 0, 0, 0, 0);
        t.addVertexWithUV(1, 0, 0, 1, 0);
        t.addVertexWithUV(1, 0, 1, 1, 1);
        t.addVertexWithUV(0, 0, 1, 0, 1);
        //bottom
        t.addVertexWithUV(1, topHeight, 0, 0, 0);
        t.addVertexWithUV(0, topHeight, 0, 1, 0);
        t.addVertexWithUV(0, topHeight, 1, 1, 1);
        t.addVertexWithUV(1, topHeight, 1, 0, 1);
        //side1
        t.addVertexWithUV(0, topHeight, 0, 0, 0);
        t.addVertexWithUV(1, topHeight, 0, 1, 0);
        t.addVertexWithUV(1, 0, 0, 1, 1);
        t.addVertexWithUV(0, 0, 0, 0, 1);
        //side2
        t.addVertexWithUV(1, topHeight, 0, 0, 0);
        t.addVertexWithUV(1, topHeight, 1, 1, 0);
        t.addVertexWithUV(1, 0, 1, 1, 1);
        t.addVertexWithUV(1, 0, 0, 0, 1);
        //side3
        t.addVertexWithUV(1, topHeight, 1, 0, 0);
        t.addVertexWithUV(0, topHeight, 1, 1, 0);
        t.addVertexWithUV(0, 0, 1, 1, 1);
        t.addVertexWithUV(1, 0, 1, 0, 1);
        //side4
        t.addVertexWithUV(0, topHeight, 1, 0, 0);
        t.addVertexWithUV(0, topHeight, 0, 1, 0);
        t.addVertexWithUV(0, 0, 0, 1, 1);
        t.addVertexWithUV(0, 0, 1, 0, 1);

        t.draw();
    }

    private static void renderRidges()
    {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setNormal(0, 0, 0);
        t.setColorOpaque(255, 255, 255);

        t.addVertexWithUV(0,topHeight,0,0,0);
        t.addVertexWithUV(0,1,0.5,1,0);
        t.addVertexWithUV(1,1,0.5,1,1);
        t.addVertexWithUV(1,topHeight,0,0,1);

        t.addVertexWithUV(0,1,0.5,0,0);
        t.addVertexWithUV(0,topHeight,0.5,1,0);
        t.addVertexWithUV(1,topHeight,0.5,1,1);
        t.addVertexWithUV(1,1,0.5,0,1);

        t.addVertexWithUV(0,topHeight,0.5,0,0);
        t.addVertexWithUV(0,1,1,1,0);
        t.addVertexWithUV(1,1,1,1,1);
        t.addVertexWithUV(1,topHeight,0.5,0,1);

        t.addVertexWithUV(0,1,1,0,0);
        t.addVertexWithUV(0,topHeight,1,1,0);
        t.addVertexWithUV(1,topHeight,1,1,1);
        t.addVertexWithUV(1,1,1,0,1);

        t.draw();

        t.startDrawing(GL_TRIANGLES);
        t.setNormal(0, 0, 0);
        t.setColorOpaque(255, 255, 255);

        t.addVertexWithUV(0,topHeight,0,0,0);
        t.addVertexWithUV(0,topHeight,0.5,1,0);
        t.addVertexWithUV(0,1,0.5,1,1);

        t.addVertexWithUV(0,topHeight,0.5,0,0);
        t.addVertexWithUV(0,topHeight,1,1,0);
        t.addVertexWithUV(0,1,1,1,1);

        t.addVertexWithUV(1,topHeight,1,0,0);
        t.addVertexWithUV(1,topHeight,0.5,1,0);
        t.addVertexWithUV(1,1,1,0,1);

        t.addVertexWithUV(1,topHeight,0.5,0,0);
        t.addVertexWithUV(1,topHeight,0,1,0);
        t.addVertexWithUV(1,1,0.5,0,1);



        t.draw();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_147500_8_)
    {
        TileEntityBelt belt = (TileEntityBelt) tileEntity;
        glPushMatrix();
        glTranslated(posX, posY, posZ);
        this.bindTexture(Texture);
        //renderBase();
        glCallList(baseList);
        //renderRidges();
        glTranslated(0, 0, -((belt.animationProgress + (p_147500_8_ * belt.animationSpeed)) / belt.animationProgressMax) / 2);
        glCallList(ridgeList);
        glPopMatrix();

    }
}
