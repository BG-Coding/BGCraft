package tk.blacky704.bgcraft.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEntity
{
    public double animationProgress = 0;
    public double animationProgressMax = 50;

    public TileEntityBelt()
    {

    }

    public void updateEntity()
    {
        if (animationProgress < animationProgressMax)
        {
            animationProgress++;
        }
        else
        {
            animationProgress++;
            animationProgress -= animationProgressMax;
        }
    }


}
