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
        renderRidges(255, 255, 255);
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

    private static void renderRidges(int r, int g, int b)
    {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.setNormal(0, 0, 0);
        t.setColorOpaque(r, g, b);

        //back panels

        t.setTranslation(0, 0, 0);
        t.setTextureUV(0, 0);
        t.addVertex(1, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(1, topHeight, 0);
        t.setTextureUV(1, 1);
        t.addVertex(0, topHeight, 0);
        t.setTextureUV(0, 1);
        t.addVertex(0, 1, 0);

        t.setTranslation(0, 0, 0.5);
        t.setTextureUV(0, 0);
        t.addVertex(1, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(1, topHeight, 0);
        t.setTextureUV(1, 1);
        t.addVertex(0, topHeight, 0);
        t.setTextureUV(0, 1);
        t.addVertex(0, 1, 0);

        //diagonal panels

        t.setTranslation(0, 0, 0);
        t.setTextureUV(0, 0);
        t.addVertex(0, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(0, topHeight, 0.5);
        t.setTextureUV(1, 1);
        t.addVertex(1, topHeight, 0.5);
        t.setTextureUV(0, 1);
        t.addVertex(1, 1, 0);

        t.setTranslation(0, 0, 0.5);
        t.setTextureUV(0, 0);
        t.addVertex(0, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(0, topHeight, 0.5);
        t.setTextureUV(1, 1);
        t.addVertex(1, topHeight, 0.5);
        t.setTextureUV(0, 1);
        t.addVertex(1, 1, 0);

        t.draw();

        t.startDrawing(GL_TRIANGLES);
        t.setNormal(0, 0, 0);
        t.setColorOpaque(255, 255, 255);

        //triangles
        //Negative X
        t.setTranslation(0, 0, 0);
        t.setTextureUV(0, 1);
        t.addVertex(0, 1, 0);
        t.setTextureUV(0, 0);
        t.addVertex(0, topHeight, 0);
        t.setTextureUV(1, 0);
        t.addVertex(0, topHeight, 0.5);

        t.setTranslation(0, 0, 0.5);
        t.setTextureUV(0, 1);
        t.addVertex(0, 1, 0);
        t.setTextureUV(0, 0);
        t.addVertex(0, topHeight, 0);
        t.setTextureUV(1, 0);
        t.addVertex(0, topHeight, 0.5);

        // positive X

        t.setTranslation(0, 0, 0);
        t.setTextureUV(0, 1);
        t.addVertex(1, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(1, topHeight, 0.5);
        t.setTextureUV(0, 0);
        t.addVertex(1, topHeight, 0);

        t.setTranslation(0, 0, 0.5);
        t.setTextureUV(0, 1);
        t.addVertex(1, 1, 0);
        t.setTextureUV(1, 0);
        t.addVertex(1, topHeight, 0.5);
        t.setTextureUV(0, 0);
        t.addVertex(1, topHeight, 0);

        t.draw();
        t.setTranslation(0, 0, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_147500_8_)
    {
        glDisable(GL_LIGHTING);
        TileEntityBelt belt = (TileEntityBelt) tileEntity;
        glPushMatrix();
        glTranslated(posX, posY, posZ);
        this.bindTexture(Texture);
        renderBase();
        //glCallList(baseList);
        glTranslated(0.5, 0, 0.5);
        glRotated(0, 0, 1, 0);
        glTranslated(-0.5, 0, -0.5);
        glTranslated(0, 0, ((belt.animationProgress + p_147500_8_ * belt.animationSpeed) / belt.animationProgressMax) / 2);
        renderRidges(belt.r, belt.g, belt.b);
        //glCallList(ridgeList);
        glPopMatrix();
        glEnable(GL_LIGHTING);
    }
}
