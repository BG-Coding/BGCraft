package tk.blacky704.bgcraft.pressure;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Blacky
 */
public interface IPressureConnection
{
    boolean canConnectToPressurePipe(ForgeDirection from);

    boolean canConnectToPressurePipe();

    boolean canConnectToPressureBlocks(ForgeDirection from);

    boolean canConnectToPressureBlocks();
}
