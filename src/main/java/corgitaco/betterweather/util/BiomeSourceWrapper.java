package corgitaco.betterweather.util;

import com.mojang.serialization.Codec;
import corgitaco.betterweather.mixin.access.BiomeSourceAccess;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class BiomeSourceWrapper extends BiomeProvider {

    private final BiomeProvider biomeProvider;

    private double tempModifier = 0.0;
    private double humidityModifier = 0.0;

    protected BiomeSourceWrapper(BiomeProvider biomeProvider) {
        super(biomeProvider.getBiomes());
        this.biomeProvider = biomeProvider;
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return ((BiomeSourceAccess) this.biomeProvider).getBiomeSourceCodec();
    }

    public void processBiomeClimateUpdates(double tempModifier, double humidityModifier) {
        this.tempModifier = tempModifier;
        this.humidityModifier = humidityModifier;
    }

    @Override
    public BiomeProvider getBiomeProvider(long seed) {
        return this.biomeProvider.getBiomeProvider(seed);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return this.biomeProvider.getNoiseBiome(x, y, z);
    }

    @Override
    public List<Biome> getBiomes() {
        return this.biomeProvider.getBiomes();
    }

    @Override
    public Set<Biome> getBiomes(int xIn, int yIn, int zIn, int radius) {
        return this.biomeProvider.getBiomes(xIn, yIn, zIn, radius);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int xIn, int yIn, int zIn, int radiusIn, Predicate<Biome> biomesIn, Random randIn) {
        return this.biomeProvider.findBiomePosition(xIn, yIn, zIn, radiusIn, biomesIn, randIn);
    }

    @Nullable
    @Override
    public BlockPos findBiomePosition(int x, int y, int z, int radius, int increment, Predicate<Biome> biomes, Random rand, boolean findClosest) {
        return this.biomeProvider.findBiomePosition(x, y, z, radius, increment, biomes, rand, findClosest);
    }

    @Override
    public boolean hasStructure(Structure<?> structureIn) {
        return this.biomeProvider.hasStructure(structureIn);
    }

    @Override
    public Set<BlockState> getSurfaceBlocks() {
        return this.biomeProvider.getSurfaceBlocks();
    }
}
