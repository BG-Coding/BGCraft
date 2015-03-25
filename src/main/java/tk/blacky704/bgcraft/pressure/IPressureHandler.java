package tk.blacky704.bgcraft.pressure;

/**
 * @author Blacky
 */
public interface IPressureHandler
{
    public double receivePressure(double pressure, double maxPressure, double maxNegativePressure);

    public double extractPressure(double pressure, double maxPressure, double maxNegativePressure);

    public void balancePressureWith(double pressure, int x, int y, int z);

    public double getPressure();

    public double getMaxPressure();

    public double getMaxNegativePressure();

    public void setMaxPressure(double maxPressure);

    public void setMaxNegativePressure(double maxNegativePressure);

    public void setPressure(double pressure);

    public void modifyPressure(double addedPressure);
}
