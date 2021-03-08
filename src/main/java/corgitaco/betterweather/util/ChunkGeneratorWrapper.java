package corgitaco.betterweather.util;

import com.mojang.serialization.Codec;
import corgitaco.betterweather.mixin.access.ChunkGeneratorAccess;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class ChunkGeneratorWrapper extends ChunkGenerator {
    private final ChunkGenerator generator;

    public ChunkGeneratorWrapper(ChunkGenerator generator) {
        super(generator.getBiomeProvider(), generator.getBiomeProvider(), generator.func_235957_b_(), ((ChunkGeneratorAccess) generator).getSeed());
        this.generator = generator;
    }

    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return ((ChunkGeneratorAccess) generator).getChunkGeneratorCodec();
    }

    @Override
    public ChunkGenerator func_230349_a_(long seed) {
        return generator.func_230349_a_(seed);
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk chunk) {
        generator.generateSurface(region, chunk);
    }

    @Override
    public void func_230352_b_(IWorld world, StructureManager structureManager, IChunk chunk) {
        generator.func_230352_b_(world, structureManager, chunk);
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return generator.getHeight(x, z, heightmapType);
    }

    @Override
    public IBlockReader func_230348_a_(int x, int z) {
        return generator.func_230348_a_(x, z);
    }

    @Override
    public void func_242706_a(Registry<Biome> biomes, IChunk chunk) {
        generator.func_242706_a(biomes, chunk);
    }

    @Override
    public void func_230350_a_(long seed, BiomeManager manager, IChunk chunk, GenerationStage.Carving carving) {
        generator.func_230350_a_(seed, manager, chunk, carving);
    }

    @Nullable
    @Override
    public BlockPos func_235956_a_(ServerWorld world, Structure<?> structure, BlockPos pos, int i, boolean b) {
        return generator.func_235956_a_(world, structure, pos, i, b);
    }

    @Override
    public void func_230351_a_(WorldGenRegion worldGenRegion, StructureManager structureManager) {
        generator.func_230351_a_(worldGenRegion, structureManager);
    }

    @Override
    public void func_230354_a_(WorldGenRegion world) {
        generator.func_230354_a_(world);
    }

    @Override
    public DimensionStructuresSettings func_235957_b_() {
        return generator.func_235957_b_();
    }

    @Override
    public int getGroundHeight() {
        return generator.getGroundHeight();
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return new BiomeSourceWrapper(generator.getBiomeProvider());
    }

    @Override
    public int getMaxBuildHeight() {
        return generator.getMaxBuildHeight();
    }

    @Override
    public List<MobSpawnInfo.Spawners> func_230353_a_(Biome biome, StructureManager manager, EntityClassification entity, BlockPos pos) {
        return generator.func_230353_a_(biome, manager, entity, pos);
    }

    @Override
    public void func_242707_a(DynamicRegistries dynamicRegistries, StructureManager structureManager, IChunk chunk, TemplateManager templateManager, long seed) {
        generator.func_242707_a(dynamicRegistries, structureManager, chunk, templateManager, seed);
    }

    @Override
    public void func_235953_a_(ISeedReader world, StructureManager structureManager, IChunk chunk) {
        generator.func_235953_a_(world, structureManager, chunk);
    }

    @Override
    public int getSeaLevel() {
        return generator.getSeaLevel();
    }

    @Override
    public int getNoiseHeight(int x, int z, Heightmap.Type heightmapType) {
        return generator.getNoiseHeight(x, z, heightmapType);
    }

    @Override
    public int getNoiseHeightMinusOne(int x, int z, Heightmap.Type heightmapType) {
        return generator.getNoiseHeightMinusOne(x, z, heightmapType);
    }

    @Override
    public boolean func_235952_a_(ChunkPos pos) {
        return generator.func_235952_a_(pos);
    }
}
