package evangamer2018.pureminingdimension;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PureMiningDimension implements ModInitializer {
	public static final String MODID = "pureminingdimension";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final Block MINING_DIMENSION_PORTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));
	public static final RegistryKey<Biome> MINING_DIM_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MODID, "mining_dimension_biome"));
	public static final Biome MINING_DIM_BIOME = createMiningDimBiome();
	public static final RegistryKey<DimensionOptions> MINING_DIM_KEY = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier(MODID, "mining_dimension"));

	private static Biome createMiningDimBiome() {
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addMonsters(spawnSettings, 100 , 0 ,100, false);

		GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
		DefaultBiomeFeatures.addForestTrees(generationSettings);
		DefaultBiomeFeatures.addDefaultOres(generationSettings);
		DefaultBiomeFeatures.addLandCarvers(generationSettings);

		return (new Biome.Builder()).precipitation(Biome.Precipitation.NONE).category(Biome.Category.NONE)
				.temperature(0.4F).downfall(0.0f)
				.effects((new BiomeEffects.Builder())
						.waterColor(0x3f76e4).waterFogColor(0x050533)
						.fogColor(0xc0d8ff).skyColor(0x77adff)
						.build()).spawnSettings(spawnSettings.build())
				.generationSettings(generationSettings.build()).build();
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MODID, "mining_dimension_portal_block"), MINING_DIMENSION_PORTAL_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MODID, "mining_dimension_portal_block"), new BlockItem(MINING_DIMENSION_PORTAL_BLOCK, new FabricItemSettings().group(ItemGroup.MISC)));

		Registry.register(BuiltinRegistries.BIOME, MINING_DIM_BIOME_KEY.getValue(), MINING_DIM_BIOME);
		CustomPortalBuilder.beginPortal()
				.frameBlock(MINING_DIMENSION_PORTAL_BLOCK)
				.lightWithItem(Items.DIAMOND_PICKAXE)
				.destDimID(new Identifier("pureminingdimension", "mining_dimension"))
				.tintColor(139,203,139)
				.registerPortal();
		LOGGER.info("Done Loading PureMiningDimension");
	}
}