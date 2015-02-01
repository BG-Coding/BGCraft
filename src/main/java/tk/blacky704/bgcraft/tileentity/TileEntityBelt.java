package tk.blacky704.bgcraft.tileentity;

import cofh.api.energy.TileEnergyHandler;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEnergyHandler
{
    public double animationProgress = 0;
    public final double animationProgressMax = 20;
    public double animationSpeed = 0;

    public int r = 255;
    public int g = 255;
    public int b = 255;

    public TileEntityBelt()
    {

    }

    public void updateEntity()
    {
        if (isLast())
        {
            if (animationProgress < animationProgressMax)
            {
                animationProgress += animationSpeed;
            } else
            {
                animationProgress += animationSpeed;
                animationProgress -= animationProgressMax;
            }

            syncPreviousBelt(this.animationProgress, this.animationSpeed);
        }

        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 1.2, zCoord + 1);
        List list = worldObj.getEntitiesWithinAABB(Entity.class, bb);
        for (Object object : list)
        {
            Entity e = (Entity) object;
            e.setVelocity(e.motionX, e.motionY, animationSpeed / animationProgressMax / 2);
            //e.addVelocity(0,0,animationSpeed/animationProgressMax/2);
        }

    }

    //Todo: if (isFirst()) { add velocity to all entities in bb, then pass a list off all entities affected to the next belt. Exclude affected entities from the function of the next belt.

    public boolean isLast()
    {
        if (getNextBelt() == null)
        {
            return true;
        }

        return false;
    }

    private void syncPreviousBelt(double newProgress, double newSpeed)
    {
        TileEntityBelt prevBelt = getPreviousBelt();
        if (prevBelt != null)
        {
            prevBelt.setAnimationProgressAndSpeed(newProgress, newSpeed);
            prevBelt.syncPreviousBelt(newProgress, newSpeed);
        }
    }

    private TileEntityBelt getPreviousBelt()
    {
        TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
        if (entity instanceof TileEntityBelt)
        {
            return (TileEntityBelt) entity;
        } else
        {
            return null;
        }
    }

    private TileEntityBelt getNextBelt()
    {
        TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord + 1);
        if (entity instanceof TileEntityBelt)
        {
            return (TileEntityBelt) entity;
        } else
        {
            return null;
        }
    }

    public void setAnimationProgressAndSpeed(double animationProgress, double animationSpeed)
    {
        this.animationProgress = animationProgress;
        this.animationSpeed = animationSpeed;
    }


}
