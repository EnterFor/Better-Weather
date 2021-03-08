package corgitaco.betterweather.mixin.access;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkGenerator.class)
public interface ChunkGeneratorAccess {

    @Invoker("func_230347_a_")
    Codec<? extends ChunkGenerator> getChunkGeneratorCodec();

    @Accessor("field_235950_e_")
    long getSeed();

}
