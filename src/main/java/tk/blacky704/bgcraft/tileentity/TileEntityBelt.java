package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.TileEnergyHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tk.blacky704.bgcraft.reference.Integers;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEnergyHandler
{
    public double animationProgress = 0;
    public double animationProgressMax = 20;
    public double animationSpeed = 1;

    public TileEntityBelt()
    {

    }

    public void updateEntity()
    {
//        if(this.getEnergyStored(ForgeDirection.UNKNOWN) >= Integers.EnergyUsages.BELT)
//        {
            if (animationProgress < animationProgressMax)
            {
                animationProgress += animationSpeed;
            }
            else
            {
                animationProgress += animationSpeed;
                animationProgress -= animationProgressMax;
            }

            World w = this.getWorldObj();
            TileEntityBelt other = (TileEntityBelt) w.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            if (other != null)
            {
                other.setAnimationProgress(this.animationProgress);
            }
//        }
    }

    public void setAnimationProgress(double animationProgress)
    {
        this.animationProgress = animationProgress;
    }


}
