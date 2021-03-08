package corgitaco.betterweather.mixin.biome;

import corgitaco.betterweather.util.BiomeClimateContext;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class MixinBiome implements BiomeClimateContext {

    public float temperatureModifier;

    public float downfallModifier;

    @Override
    public void setTempModifier(float temp) {
        this.temperatureModifier = temp;
    }

    @Override
    public void setHumidityModifier(float humidity) {
        this.downfallModifier = humidity;
    }

    @Inject(method = "getDownfall", at = @At("RETURN"), cancellable = true)
    private void modifyDownfall(CallbackInfoReturnable<Float> cir) {
    }

    @Inject(method = "getTemperature()F", at = @At("RETURN"), cancellable = true)
    private void modifyTemperature(CallbackInfoReturnable<Float> cir) {
    }

}
