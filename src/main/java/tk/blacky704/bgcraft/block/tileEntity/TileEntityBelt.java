package tk.blacky704.bgcraft.block.tileEntity;

import net.minecraft.tileentity.TileEntity;

/**
 * @author goeiecool9999
 */
public class TileEntityBelt extends TileEntity
{
    public int testInt = 0;
    public TileEntityBelt ()
    {
        System.out.println("tileEntity intialised");
    }

    public void updateEntity()
    {
        if (testInt <360)
        {
            testInt++;
        } else {
            testInt++;
            testInt -= 360;
        }
    }


}
