package corgitaco.betterweather.mixin.access;

import com.mojang.serialization.Codec;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BiomeProvider.class)
public interface BiomeSourceAccess {

    @Invoker("getBiomeProviderCodec")
    Codec<? extends BiomeProvider> getBiomeSourceCodec();
}
