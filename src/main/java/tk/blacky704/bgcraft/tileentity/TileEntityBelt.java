package tk.blacky704.bgcraft.tileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEntity
{
    public int animationProgress = 0;

    public TileEntityBelt()
    {

    }

    public void updateEntity()
    {
        if (animationProgress < 100)
        {
            animationProgress++;
        }
        else
        {
            animationProgress++;
            animationProgress -= 100;
        }
    }


}
