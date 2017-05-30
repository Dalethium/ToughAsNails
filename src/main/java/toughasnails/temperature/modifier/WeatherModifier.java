package toughasnails.temperature.modifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import toughasnails.api.temperature.Temperature;
import toughasnails.temperature.TemperatureDebugger;
import toughasnails.temperature.TemperatureDebugger.Modifier;

public class WeatherModifier extends TemperatureModifier
{
    public static final int WET_RATE_MODIFIER = -750;
    public static final int WET_TARGET_MODIFIER = -7;
    public static final int SNOW_TARGET_MODIFIER = -10;
    
    public WeatherModifier(TemperatureDebugger debugger)
    {
        super(debugger);
    }
    
    @Override
    public Temperature modifyTarget(World world, EntityPlayer player, Temperature temperature)
    {
        int temperatureLevel = temperature.getRawValue();
        int newTemperatureLevel = temperatureLevel;
        
        BlockPos playerPos = player.getPosition();
        
        if (player.isWet())
        {
            debugger.start(Modifier.WET_TARGET, newTemperatureLevel);
            newTemperatureLevel += WET_TARGET_MODIFIER;
            debugger.end(newTemperatureLevel);
        }
        else if (world.isRaining() && world.canSeeSky(playerPos) && world.getBiome(playerPos).getEnableSnow())
        {
            debugger.start(Modifier.SNOW_TARGET, newTemperatureLevel);
            newTemperatureLevel += SNOW_TARGET_MODIFIER;
            debugger.end(newTemperatureLevel);
        }

        return new Temperature(newTemperatureLevel);
    }
}
