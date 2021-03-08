package corgitaco.betterweather.mixin.server;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import corgitaco.betterweather.BetterWeather;
import corgitaco.betterweather.BetterWeatherUtil;
import corgitaco.betterweather.config.json.WeatherEventControllerConfig;
import corgitaco.betterweather.datastorage.BetterWeatherEventData;
import corgitaco.betterweather.datastorage.BetterWeatherGeneralData;
import corgitaco.betterweather.datastorage.BetterWeatherSeasonData;
import corgitaco.betterweather.util.ChunkGeneratorWrapper;
import net.minecraft.resources.DataPackRegistries;
import net.minecraft.resources.ResourcePackList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.listener.IChunkStatusListener;
import net.minecraft.world.chunk.listener.IChunkStatusListenerFactory;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.net.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {

    @Shadow
    @Nullable
    public abstract ServerWorld getWorld(RegistryKey<World> dimension);

    @Inject(method = "<init>", at = @At("RETURN"))
    private void readConfigsAtWorldCreation(Thread serverThread, DynamicRegistries.Impl registries, SaveFormat.LevelSave anvilConverterForAnvilFile, IServerConfiguration p_i232576_4_, ResourcePackList dataPacks, Proxy serverProxy, DataFixer dataFixer, DataPackRegistries dataRegistries, MinecraftSessionService sessionService, GameProfileRepository profileRepo, PlayerProfileCache profileCache, IChunkStatusListenerFactory chunkStatusListenerFactory, CallbackInfo ci) {
        BetterWeather.biomeRegistryEarlyAccess = registries.getRegistry(Registry.BIOME_KEY);
        if (!BetterWeather.useSeasons)
            WeatherEventControllerConfig.handleConfig(BetterWeather.CONFIG_PATH.resolve(BetterWeather.MOD_ID + "-weather-controller.json"));

        BetterWeatherUtil.loadSeasonConfigsServer(null);
    }

    @Inject(method = "func_240787_a_", at = @At("TAIL"))
    private void assignWorldData(IChunkStatusListener p_240787_1_, CallbackInfo ci) {
        BetterWeatherGeneralData.get(getWorld(World.OVERWORLD));
        BetterWeatherEventData.get(getWorld(World.OVERWORLD));
        BetterWeatherSeasonData.get(getWorld(World.OVERWORLD));
    }

    @Redirect(method = "func_240787_a_", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Dimension;getChunkGenerator()Lnet/minecraft/world/gen/ChunkGenerator;"))
    private ChunkGenerator wrapChunkGenerator(Dimension dimension) {
        return new ChunkGeneratorWrapper(dimension.getChunkGenerator());
    }

    @Inject(method = "func_240787_a_", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.PRINT)
    private void setChunkGeneratorWorld(IChunkStatusListener statusListener, CallbackInfo ci, IServerWorldInfo iserverworldinfo, DimensionGeneratorSettings dimensiongeneratorsettings, boolean flag, long i, long j, List list, SimpleRegistry simpleregistry, Dimension dimension, ChunkGenerator chunkgenerator, DimensionType dimensiontype, ServerWorld serverworld, DimensionSavedDataManager dimensionsaveddatamanager, WorldBorder worldborder, Iterator var17, Map.Entry entry, RegistryKey registrykey, RegistryKey registrykey1, DimensionType dimensiontype1, ChunkGenerator chunkgenerator1, DerivedWorldInfo derivedworldinfo, ServerWorld serverworld1, Dimension var26) {
        BetterWeather.worldToChunkGenerator.put(serverworld.getDimensionKey(), serverworld.getChunkProvider().getChunkGenerator());
        BetterWeather.worldToChunkGenerator.put(serverworld1.getDimensionKey(), serverworld1.getChunkProvider().getChunkGenerator());

    }
}
