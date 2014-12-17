package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.TileEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEnergyHandler
{
    public double animationProgress = 0;
    public double animationProgressMax = 20;

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

        World w = this.getWorldObj();
        TileEntityBelt other = (TileEntityBelt) w.getTileEntity(this.xCoord,this.yCoord,this.zCoord-1);
        if(other != null)
        {
            other.setAnimationProgress(this.animationProgress);
        }

    }

    public void setAnimationProgress(double animationProgress)
    {
        this.animationProgress = animationProgress;
    }


}
