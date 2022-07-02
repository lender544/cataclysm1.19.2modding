package L_Ender.cataclysm.init;

import L_Ender.cataclysm.cataclysm;
import L_Ender.cataclysm.structures.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_DEF_REG = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, cataclysm.MODID);
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPE_DEF_REG = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, cataclysm.MODID);

    public static final RegistryObject<StructureType<SoulBlackSmithStructure>> SOUL_BLACK_SMITH = STRUCTURE_TYPE_DEF_REG.register("soul_black_smith", () -> () -> SoulBlackSmithStructure.CODEC);
    public static final RegistryObject<StructureType<RuinedCitadelStructure>> RUINED_CITADEL = STRUCTURE_TYPE_DEF_REG.register("ruined_citadel", () -> () -> RuinedCitadelStructure.CODEC);

    public static final RegistryObject<StructurePieceType> SBSP = STRUCTURE_PIECE_DEF_REG.register("soul_black_smith", () -> SoulBlackSmithStructure.Piece::new);
    public static final RegistryObject<StructurePieceType> RCP = STRUCTURE_PIECE_DEF_REG.register("ruined_citadel", () -> RuinedCitadelStructure.Piece::new);

    public static final ResourceKey<Structure> SOUL_BLACK_SMITH_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(cataclysm.MODID, "soul_black_smith"));
    public static final ResourceKey<Structure> RUINED_CITADEL_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(cataclysm.MODID, "ruined_citadel"));
}
