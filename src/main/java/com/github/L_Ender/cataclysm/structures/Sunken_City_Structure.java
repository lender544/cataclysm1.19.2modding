package com.github.L_Ender.cataclysm.structures;

import com.github.L_Ender.cataclysm.cataclysm;
import com.github.L_Ender.cataclysm.entity.Ignited_Revenant_Entity;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModStructures;
import com.github.L_Ender.cataclysm.init.ModTag;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProtectedBlockProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Map;
import java.util.Optional;

public class Sunken_City_Structure extends Structure {

    public static final Codec<Sunken_City_Structure> CODEC = simpleCodec(Sunken_City_Structure::new);

    private static final ResourceLocation CITY_MID = new ResourceLocation(cataclysm.MODID, "sunken_city_mid");
    private static final ResourceLocation CITY_LOWER = new ResourceLocation(cataclysm.MODID, "sunken_city_lower");
    private static final ResourceLocation CITY_UPPER = new ResourceLocation(cataclysm.MODID, "sunken_city_upper");

    private static final ResourceLocation CITY_MID_EAST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_east");
    private static final ResourceLocation CITY_MID_NORTH = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_north");
    private static final ResourceLocation CITY_MID_SOUTH = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_south");
    private static final ResourceLocation CITY_MID_WEST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_west");

    private static final ResourceLocation CITY_MID_NORTHEAST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_northeast");
    private static final ResourceLocation CITY_MID_NORTHWEST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_northwest");
    private static final ResourceLocation CITY_MID_SOUTHEAST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_southeast");
    private static final ResourceLocation CITY_MID_SOUTHWEST = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_southwest");

    private static final ResourceLocation CITY_LOWER_EAST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_east");
    private static final ResourceLocation CITY_LOWER_NORTH = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_north");
    private static final ResourceLocation CITY_LOWER_SOUTH = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_south");
    private static final ResourceLocation CITY_LOWER_WEST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_west");

    private static final ResourceLocation CITY_LOWER_NORTHEAST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_northeast");
    private static final ResourceLocation CITY_LOWER_NORTHWEST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_northwest");
    private static final ResourceLocation CITY_LOWER_SOUTHEAST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_southeast");
    private static final ResourceLocation CITY_LOWER_SOUTHWEST = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_southwest");

    private static final ResourceLocation CITY_UPPER_TREASURE = new ResourceLocation(cataclysm.MODID, "sunken_city_upper_treasureroom");
    private static final ResourceLocation CITY_LOWER_TREASURE = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_treasureroom");

    private static final ResourceLocation CITY_ENTRANCE1 = new ResourceLocation(cataclysm.MODID, "sunken_city_entrance1");
    private static final ResourceLocation CITY_ENTRANCE2 = new ResourceLocation(cataclysm.MODID, "sunken_city_entrance2");
    private static final ResourceLocation CITY_ENTRANCE3 = new ResourceLocation(cataclysm.MODID, "sunken_city_entrance3");
    private static final ResourceLocation CITY_ENTRANCE4 = new ResourceLocation(cataclysm.MODID, "sunken_city_entrance4");


    private static final ResourceLocation CITY_TEMPLE1 = new ResourceLocation(cataclysm.MODID, "sunken_city_temple1");
    private static final ResourceLocation CITY_TEMPLE2 = new ResourceLocation(cataclysm.MODID, "sunken_city_temple2");

    private static final ResourceLocation CITY_UPPER_PRISON = new ResourceLocation(cataclysm.MODID, "sunken_city_upper_prison");
    private static final ResourceLocation CITY_UPPERSIDE_PRISON = new ResourceLocation(cataclysm.MODID, "sunken_city_upperside_prison");
    private static final ResourceLocation CITY_LOWER_PRISON = new ResourceLocation(cataclysm.MODID, "sunken_city_lower_prison");
    private static final ResourceLocation CITY_LOWERSIDE_PRISON = new ResourceLocation(cataclysm.MODID, "sunken_city_lowerside_prison");

    private static final ResourceLocation CITY_MID_NORTH_SIDE = new ResourceLocation(cataclysm.MODID, "sunken_city_mid_north_side");

    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(CITY_MID, new BlockPos(0, 1, 0))
            .put(CITY_LOWER, new BlockPos(0, 1, 0))
            .put(CITY_UPPER, new BlockPos(0, 1, 0))
            .put(CITY_MID_EAST, new BlockPos(0, 1, 0))
            .put(CITY_MID_NORTH, new BlockPos(0, 1, 0))
            .put(CITY_MID_SOUTH, new BlockPos(0, 1, 0))
            .put(CITY_MID_WEST, new BlockPos(0, 1, 0))

            .put(CITY_MID_NORTHEAST, new BlockPos(0, 1, 0))
            .put(CITY_MID_NORTHWEST, new BlockPos(0, 1, 0))
            .put(CITY_MID_SOUTHEAST, new BlockPos(0, 1, 0))
            .put(CITY_MID_SOUTHWEST, new BlockPos(0, 1, 0))

            .put(CITY_LOWER_EAST, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_NORTH, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_SOUTH, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_WEST, new BlockPos(0, 1, 0))

            .put(CITY_LOWER_NORTHEAST, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_NORTHWEST, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_SOUTHEAST, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_SOUTHWEST, new BlockPos(0, 1, 0))

            .put(CITY_UPPER_TREASURE, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_TREASURE, new BlockPos(0, 1, 0))

            .put(CITY_ENTRANCE1, new BlockPos(0, 1, 0))
            .put(CITY_ENTRANCE2, new BlockPos(0, 1, 0))
            .put(CITY_ENTRANCE3, new BlockPos(0, 1, 0))
            .put(CITY_ENTRANCE4, new BlockPos(0, 1, 0))


            .put(CITY_TEMPLE1, new BlockPos(0, 1, 0))
            .put(CITY_TEMPLE2, new BlockPos(0, 1, 0))

            .put(CITY_UPPER_PRISON, new BlockPos(0, 1, 0))
            .put(CITY_UPPERSIDE_PRISON, new BlockPos(0, 1, 0))
            .put(CITY_LOWER_PRISON, new BlockPos(0, 1, 0))
            .put(CITY_LOWERSIDE_PRISON, new BlockPos(0, 1, 0))

            .put(CITY_MID_NORTH_SIDE, new BlockPos(0, 1, 0))

            .build();


    /*
     * Begins assembling your structure and where the pieces needs to go.
     */
    public static void start(StructureTemplateManager templateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieceList, RandomSource random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID, blockpos, rotation));
        rotationOffSet = new BlockPos(0, 48, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_UPPER, blockpos, rotation));
        rotationOffSet = new BlockPos(0, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER, blockpos, rotation));

        rotationOffSet = new BlockPos(47, 0, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_EAST, blockpos, rotation));
        rotationOffSet = new BlockPos(0, 0, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_NORTH, blockpos, rotation));
        rotationOffSet = new BlockPos(0, 0, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_SOUTH, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, 0, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_WEST, blockpos, rotation));

        rotationOffSet = new BlockPos(47, 0, -12).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_NORTHEAST, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, 0, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_NORTHWEST, blockpos, rotation));
        rotationOffSet = new BlockPos(47, 0, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_SOUTHEAST, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, 0, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_SOUTHWEST, blockpos, rotation));


        rotationOffSet = new BlockPos(47, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_EAST, blockpos, rotation));
        rotationOffSet = new BlockPos(0, -38, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_NORTH, blockpos, rotation));
        rotationOffSet = new BlockPos(0, -38, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_SOUTH, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_WEST, blockpos, rotation));


        rotationOffSet = new BlockPos(47, -38, -9).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_NORTHEAST, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, -38, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_NORTHWEST, blockpos, rotation));
        rotationOffSet = new BlockPos(47, -38, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_SOUTHEAST, blockpos, rotation));
        rotationOffSet = new BlockPos(-47, -38, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_SOUTHWEST, blockpos, rotation));


        rotationOffSet = new BlockPos(-105, 0, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_ENTRANCE1, blockpos, rotation));
        rotationOffSet = new BlockPos(-94, 0, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_ENTRANCE2, blockpos, rotation));
        rotationOffSet = new BlockPos(-105, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_ENTRANCE3, blockpos, rotation));
        rotationOffSet = new BlockPos(-94, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_ENTRANCE4, blockpos, rotation));

        rotationOffSet = new BlockPos(-94, 0, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_UPPER_PRISON, blockpos, rotation));
        rotationOffSet = new BlockPos(-94, -38, 47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_PRISON, blockpos, rotation));
        rotationOffSet = new BlockPos(-52, 0, 94).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_UPPERSIDE_PRISON, blockpos, rotation));
        rotationOffSet = new BlockPos(-52, -38, 94).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWERSIDE_PRISON, blockpos, rotation));

        rotationOffSet = new BlockPos(-94, 0, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_UPPER_TREASURE, blockpos, rotation));
        rotationOffSet = new BlockPos(-94, -38, -47).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_LOWER_TREASURE, blockpos, rotation));


        rotationOffSet = new BlockPos(94, -38, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_TEMPLE1, blockpos, rotation));
        rotationOffSet = new BlockPos(94, 0, 0).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_TEMPLE2, blockpos, rotation));


        rotationOffSet = new BlockPos(0, 0, -50).rotate(rotation);
        blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.addPiece(new Piece(templateManager, CITY_MID_NORTH_SIDE, blockpos, rotation));

    }

    public Sunken_City_Structure(StructureSettings p_227593_) {
        super(p_227593_);
    }

    public Optional<GenerationStub> findGenerationPoint(GenerationContext p_228964_) {
        int i = p_228964_.chunkPos().getBlockX(9);
        int j = p_228964_.chunkPos().getBlockZ(9);
        for (Holder<Biome> holder : p_228964_.biomeSource().getBiomesWithin(i, p_228964_.chunkGenerator().getSeaLevel(), j, 96, p_228964_.randomState().sampler())) {
            if (!holder.is(ModTag.REQUIRED_SUNKEN_CITY_SURROUNDING)) {
                return Optional.empty();
            }
        }
        return onTopOfChunkCenter(p_228964_, Heightmap.Types.OCEAN_FLOOR_WG, (p_228967_) -> {
            generatePieces(p_228967_, p_228964_);
        });
    }

    private static void generatePieces(StructurePiecesBuilder p_197233_, GenerationContext p_197234_) {
        int i = p_197234_.chunkPos().getMinBlockX();
        int j = p_197234_.chunkPos().getMinBlockZ();
        BlockPos blockpos = new BlockPos(i, 68, j);
        Rotation rotation = Rotation.getRandom(p_197234_.random());
        Sunken_City_Structure.start(p_197234_.structureTemplateManager(), blockpos, rotation, p_197233_, p_197234_.random());
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.SUNKEN_CITY.get();
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static class Piece extends TemplateStructurePiece {

        public Piece(StructureTemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotation) {
            super(ModStructures.SCP.get(), 0, templateManagerIn, resourceLocationIn, resourceLocationIn.toString(), makeSettings(rotation), makePosition(resourceLocationIn, pos));
        }

        public Piece(StructureTemplateManager templateManagerIn, CompoundTag tagCompound) {
            super(ModStructures.SCP.get(), tagCompound, templateManagerIn, (p_162451_) -> {
                return makeSettings(Rotation.valueOf(tagCompound.getString("Rot")));
            });

        }

        public Piece(StructurePieceSerializationContext context, CompoundTag tag) {
            this(context.structureTemplateManager(), tag);
        }

        private static StructurePlaceSettings makeSettings(Rotation p_163156_) {
            BlockIgnoreProcessor blockignoreprocessor = BlockIgnoreProcessor.STRUCTURE_BLOCK;

            StructurePlaceSettings structureplacesettings = (new StructurePlaceSettings()).setRotation(p_163156_).setMirror(Mirror.NONE)
                    .addProcessor(blockignoreprocessor)
                    .addProcessor(new ProtectedBlockProcessor(BlockTags.FEATURES_CANNOT_REPLACE));


            return structureplacesettings;
        }

        private static BlockPos makePosition(ResourceLocation p_162453_, BlockPos p_162454_) {
            return p_162454_.offset(OFFSET.get(p_162453_));
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext p_162444_, CompoundTag tagCompound) {
            super.addAdditionalSaveData(p_162444_, tagCompound);
            tagCompound.putString("Rot", this.placeSettings.getRotation().name());
        }


        @Override
        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, RandomSource rand, BoundingBox sbb) {
            if ("revenant".equals(function)) {
                worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                Ignited_Revenant_Entity revenant = ModEntities.IGNITED_REVENANT.get().create(worldIn.getLevel());
                revenant.moveTo(pos, 180.0F, 180.0F);
                worldIn.addFreshEntity(revenant);
            }
        }
    }
}
