package tk.blacky704.bgcraft.tileentity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import tk.blacky704.bgcraft.reference.Integers;

import java.util.ArrayList;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEnergyHandler
{
    public double animationProgress = 0;
    public final double animationProgressMax = 20;
    public double animationSpeed = 1;
    public static ArrayList<Entity> pushedList = new ArrayList<Entity>();

    public int r = 255;
    public int g = 255;
    public int b = 255;

    private ForgeDirection from = ForgeDirection.DOWN;

    public TileEntityBelt()
    {
        super(Integers.Energy.Storage.BELT);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if (false)
        {
            animationSpeed = 0;
            return;
        } else
        {
            animationSpeed = 1;
        }
        if (isLast())
        {
            if (animationProgress < animationProgressMax)
            {
                animationProgress += animationSpeed;
            }
            else
            {
                animationProgress += animationSpeed;
                animationProgress -= Math.floor(animationProgress / animationProgressMax) * animationProgressMax;
            }

            syncPreviousBelt(this.animationProgress, this.animationSpeed);
        }

        this.moveExcludingEntities();
        this.nextBeltPush();

        this.setEnergyStored(this.getEnergyStored() - Integers.Energy.Usages.BELT);
        this.syncEnergyWithBeltLine(this.from);
    }

    public void moveExcludingEntities()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 1.01, zCoord + 1);
        ArrayList list = (ArrayList) worldObj.getEntitiesWithinAABB(Entity.class, bb);
        for (Object e : pushedList)
        {
            if (list.contains(e))
            {
                list.remove(e);
            }
        }
        for (Object object : list)
        {
            Entity e = (Entity) object;
            e.moveEntity(0, 0, animationSpeed / animationProgressMax / 2);
            e.onGround = true;
            pushedList.add(e);
        }
    }

    public boolean isFirst()
    {
        return getPreviousBelt() == null;
    }

    public boolean isLast()
    {
        return getNextBelt() == null;
    }

    private void nextBeltPush()
    {
        TileEntityBelt nextBelt = getNextBelt();
        if (nextBelt != null)
        {
            nextBelt.moveExcludingEntities();
            nextBelt.nextBeltPush();
        }
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

    private void syncEnergyWithBeltLine(ForgeDirection from)
    {
        if (from.equals(ForgeDirection.NORTH) || from.equals(ForgeDirection.DOWN))
        {
            this.from = from.equals(ForgeDirection.DOWN) ? ForgeDirection.NORTH : from;
            if (this.getNextBelt() != null)
            {
                if(this.getNextBelt().getEnergyStored() < this.getEnergyStored())
                {
                    this.extractEnergy(this.getNextBelt().receiveEnergy(this.getEnergyStored(), false), false);
                    this.getNextBelt().syncEnergyWithBeltLine(this.from);
                }
            }
        }
        if (from.equals(ForgeDirection.SOUTH) || from.equals(ForgeDirection.DOWN))
        {
            this.from = from.equals(ForgeDirection.DOWN) ? ForgeDirection.SOUTH : from;
            if (this.getPreviousBelt() != null)
            {
                if(this.getPreviousBelt().getEnergyStored() < this.getEnergyStored())
                {
                    this.extractEnergy(this.getPreviousBelt().receiveEnergy(this.getEnergyStored(), false), false);
                    this.getPreviousBelt().syncEnergyWithBeltLine(this.from);
                }
            }
        }
    }

    private TileEntityBelt getPreviousBelt()
    {
        TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord - 1);
        if (entity != null && entity instanceof TileEntityBelt)
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
        if (entity != null && entity instanceof TileEntityBelt)
        {
            return (TileEntityBelt) entity;
        }
        return null;
    }

    public void setAnimationProgressAndSpeed(double animationProgress, double animationSpeed)
    {
        this.animationProgress = animationProgress;
        this.animationSpeed = animationSpeed;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return super.getRenderBoundingBox().expand(0, 0, 1);
    }

    public boolean hasEnergyToOperate()
    {
        return this.getEnergyStored() >= Integers.Energy.Usages.BELT;
    }

    public static class eventHandler
    {
        @SubscribeEvent
        public void onPostTick(WorldTickEvent event)
        {
            pushedList.clear();
        }
    }



}
