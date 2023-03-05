package com.github.L_Ender.cataclysm.init;

import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.structures.*;
import com.github.L_Ender.cataclysm.structures.AncientFactoryStructure;
import com.github.L_Ender.cataclysm.structures.Burning_Arena_Structure;
import com.github.L_Ender.cataclysm.structures.RuinedCitadelStructure;
import com.github.L_Ender.cataclysm.structures.SoulBlackSmithStructure;
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
    public static final RegistryObject<StructureType<Burning_Arena_Structure>> BURNING_ARENA = STRUCTURE_TYPE_DEF_REG.register("burning_arena", () -> () ->
            Burning_Arena_Structure.CODEC);

    public static final RegistryObject<StructureType<AncientFactoryStructure>> ANCIENT_FACTORY = STRUCTURE_TYPE_DEF_REG.register("ancient_factory", () -> () ->
            AncientFactoryStructure.CODEC);

    public static final RegistryObject<StructurePieceType> SBSP = STRUCTURE_PIECE_DEF_REG.register("soul_black_smith", () -> SoulBlackSmithStructure.Piece::new);
    public static final RegistryObject<StructurePieceType> RCP = STRUCTURE_PIECE_DEF_REG.register("ruined_citadel", () -> RuinedCitadelStructure.Piece::new);
    public static final RegistryObject<StructurePieceType> BAP = STRUCTURE_PIECE_DEF_REG.register("burning_arena", () -> Burning_Arena_Structure.Piece::new);
    public static final RegistryObject<StructurePieceType> AFP = STRUCTURE_PIECE_DEF_REG.register("ancient_factory", () -> AncientFactoryStructure.Piece::new);

    public static final ResourceKey<Structure> SOUL_BLACK_SMITH_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(cataclysm.MODID, "soul_black_smith"));
    public static final ResourceKey<Structure> RUINED_CITADEL_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(cataclysm.MODID, "ruined_citadel"));
    public static final ResourceKey<Structure> BURNING_ARENA_KEY = ResourceKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(cataclysm.MODID, "burning_arena"));
}
