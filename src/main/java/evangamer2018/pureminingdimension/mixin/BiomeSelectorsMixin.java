package evangamer2018.pureminingdimension.mixin;

import evangamer2018.pureminingdimension.PureMiningDimension;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.dimension.DimensionOptions;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;

@Mixin(BiomeSelectors.class)
public class BiomeSelectorsMixin {
    /**
     * @author EvanGamer2018
     * @reason So other mod ores can be added to the dimension
     * @return if it can spawn in overworld or the mining dimension
     **/
    @Overwrite(remap = false)
    public static @NotNull Predicate<BiomeSelectionContext> foundInOverworld() {
        return context -> context.canGenerateIn(DimensionOptions.OVERWORLD) || context.canGenerateIn(PureMiningDimension.MINING_DIM_KEY);
    }
}