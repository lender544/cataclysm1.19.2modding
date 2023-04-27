package com.github.L_Ender.cataclysm.entity.etc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SemiAquaticPathNavigator extends WaterBoundPathNavigation {

    public SemiAquaticPathNavigator(Mob entitylivingIn, Level worldIn) {
        super(entitylivingIn, worldIn);
    }

    protected PathFinder createPathFinder(int p_179679_1_) {
        this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
        return new PathFinder(this.nodeEvaluator, p_179679_1_);
    }

    protected boolean canUpdatePath() {
        return true;
    }

    protected Vec3 getTempMobPos() {
        return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
    }

    protected double getGroundY(Vec3 p_186136_) {
        return p_186136_.y;
    }

    protected boolean canMoveDirectly(Vec3 p_186138_, Vec3 p_186139_) {
        return isClearForMovementBetween(this.mob, p_186138_, p_186139_);
    }

    public boolean isStableDestination(BlockPos pos) {
        return  !this.level.getBlockState(pos.below()).isAir();
    }

    public void setCanFloat(boolean canSwim) {
    }
}
