package com.github.L_Ender.cataclysm.config;

import com.github.L_Ender.cataclysm.cataclysm;
import net.minecraftforge.fml.config.ModConfig;

public class CMConfig {

    public static double LavaVisionOpacity = 0.5F;
    public static boolean shadersCompat = false;
    public static boolean custombossbar = true;

    public static int GauntletOfBulwarkCooldown = 80;
    public static int BulwarkOfTheFlameCooldown = 80;
    public static int InfernalForgeCooldown = 80;
    public static int VoidForgeCooldown = 120;
    public static int TheIncineratorCooldown = 400;
    public static int WASWMissileCooldown = 40;
    public static int WASWHowitzerCooldown = 100;
    public static int VASWCooldown = 120;
    public static int VoidCoreCooldown = 160;

    public static double Voidrunedamage = 7;
    public static double Ashenbreathdamage = 4;
    public static double DeathLaserdamage = 5;
    public static double DeathLaserHpdamage = 0.05D;
    public static double Laserdamage = 4;
    public static double BlazingBonedamage = 5;
    public static double WitherMissiledamage = 8;
    public static double WitherHowizterdamage = 8;
    public static double WitherHomingMissiledamage = 3;
    public static int Lavabombradius = 2;

    public static boolean ScreenShake = true;
    public static boolean BossMusic = true;

    public static int EnderguardianDamageCap = 22;
    public static int MonstrosityDamageCap = 25;
    public static int IgnisDamageCap = 20;
    public static int HarbingerDamageCap = 22;

    public static int Lavabombmagazine = 3;
    public static int Lavabombamount = 3;

    public static int EnderguardianBlockBreakingX = 15;
    public static int EnderguardianBlockBreakingY = 2;
    public static int EnderguardianBlockBreakingZ = 15;

    public static boolean EnderguardianBlockBreaking = true;
    public static boolean EndergolemBlockBreaking = false;
    public static boolean IgnisBlockBreaking = true;
    public static boolean HarbingerLightFire = true;

    public static double MonstrosityHealthMultiplier = 1D;
    public static double MonstrosityDamageMultiplier = 1D;
    public static boolean NetheritemonstrosityBodyBloking = true;

    public static double EnderguardianHealthMultiplier = 1D;
    public static double EnderguardianDamageMultiplier = 1D;

    public static double IgnisHealthMultiplier = 1D;
    public static double IgnisDamageMultiplier = 1D;

    public static double EnderGolemHealthMultiplier = 1D;
    public static double EnderGolemDamageMultiplier = 1D;

    public static double RevenantHealthMultiplier = 1D;
    public static double RevenantDamageMultiplier = 1D;

    public static double HarbingerHealthMultiplier = 1D;
    public static double HarbingerDamageMultiplier = 1D;


    public static double MonstrosityLongRangelimit = 18D;
    public static double EnderguardianLongRangelimit = 12D;
    public static double EndergolemLongRangelimit = 6D;
    public static double IgnisLongRangelimit = 15D;
    public static double HarbingerLongRangelimit = 35D;

    public static double MonstrositysHpdamage = 0.08D;
    public static double EnderguardianGravityPunchHpdamage = 0.05D;
    public static double EnderguardianTeleportAttackHpdamage = 0.05D;
    public static double EnderguardianKnockbackHpdamage = 0.06D;
    public static double EnderguardianUppercutHpdamage = 0.1D;
    public static double EnderguardianRocketPunchHpdamage = 0.1D;
    public static double EnderguardianAreaAttackHpdamage = 0.08D;
    public static double HarbingerChargeHpDamage = 0.06D;

    public static boolean Armor_Infinity_Durability = true;

    public static void bake(ModConfig config) {
        try {
            LavaVisionOpacity = ConfigHolder.COMMON.LavaVisionOpacity.get();
            shadersCompat = ConfigHolder.COMMON.shadersCompat.get();
            custombossbar = ConfigHolder.COMMON.custombossbar.get();

            GauntletOfBulwarkCooldown = ConfigHolder.COMMON.GauntletOfBulwarkCooldown.get();
            BulwarkOfTheFlameCooldown = ConfigHolder.COMMON.BulwarkOfTheFlameCooldown.get();
            InfernalForgeCooldown = ConfigHolder.COMMON.InfernalForgeCooldown.get();
            VoidForgeCooldown = ConfigHolder.COMMON.VoidForgeCooldown.get();
            TheIncineratorCooldown = ConfigHolder.COMMON.TheIncineratorCooldown.get();
            WASWMissileCooldown = ConfigHolder.COMMON.WASWMissileCooldown.get();
            WASWHowitzerCooldown = ConfigHolder.COMMON.WASWHowitzerCooldown.get();
            VASWCooldown = ConfigHolder.COMMON.VASWCooldown.get();
            VoidCoreCooldown = ConfigHolder.COMMON.VoidCoreCooldown.get();

            Voidrunedamage = ConfigHolder.COMMON.Voidrunedamage.get();
            Ashenbreathdamage = ConfigHolder.COMMON.Ashenbreathdamage.get();
            DeathLaserdamage = ConfigHolder.COMMON.DeathLaserdamage.get();
            DeathLaserHpdamage = ConfigHolder.COMMON.DeathLaserHpdamage.get();
            Laserdamage =  ConfigHolder.COMMON.Laserdamage.get();
            BlazingBonedamage = ConfigHolder.COMMON.BlazingBonedamage.get();
            WitherMissiledamage = ConfigHolder.COMMON.WitherMissiledamage.get();
            WitherHowizterdamage = ConfigHolder.COMMON.WitherHowizterdamage.get();
            WitherHomingMissiledamage = ConfigHolder.COMMON.WitherHomingMissiledamage.get();

            Lavabombradius = ConfigHolder.COMMON.Lavabombradius.get();
            ScreenShake = ConfigHolder.COMMON.ScreenShake.get();
            BossMusic = ConfigHolder.COMMON.BossMusic.get();
            EnderguardianDamageCap = ConfigHolder.COMMON.EnderguardianDamageCap.get();
            MonstrosityDamageCap = ConfigHolder.COMMON.MonstrosityDamageCap.get();
            IgnisDamageCap = ConfigHolder.COMMON.IgnisDamageCap.get();
            HarbingerDamageCap = ConfigHolder.COMMON.IgnisDamageCap.get();
            Lavabombmagazine = ConfigHolder.COMMON.Lavabombmagazine.get();
            Lavabombamount = ConfigHolder.COMMON.Lavabombamount.get();
            EnderguardianBlockBreakingX = ConfigHolder.COMMON.EnderguardianBlockBreakingX.get();
            EnderguardianBlockBreakingY = ConfigHolder.COMMON.EnderguardianBlockBreakingY.get();
            EnderguardianBlockBreakingZ = ConfigHolder.COMMON.EnderguardianBlockBreakingZ.get();
            NetheritemonstrosityBodyBloking = ConfigHolder.COMMON.NetheritemonstrosityBodyBloking.get();
            EnderguardianBlockBreaking = ConfigHolder.COMMON.EnderguardianBlockBreaking.get();
            EndergolemBlockBreaking = ConfigHolder.COMMON.EndergolemBlockBreaking.get();
            MonstrosityHealthMultiplier = ConfigHolder.COMMON.MonstrosityHealthMultiplier.get();
            MonstrosityDamageMultiplier = ConfigHolder.COMMON.MonstrosityDamageMultiplier.get();

            EnderguardianHealthMultiplier = ConfigHolder.COMMON.EnderguardianHealthMultiplier.get();
            EnderguardianDamageMultiplier = ConfigHolder.COMMON.EnderguardianDamageMultiplier.get();

            RevenantHealthMultiplier = ConfigHolder.COMMON.RevenantHealthMultiplier.get();
            RevenantDamageMultiplier = ConfigHolder.COMMON.RevenantDamageMultiplier.get();

            HarbingerHealthMultiplier = ConfigHolder.COMMON.HarbingerHealthMultiplier.get();
            HarbingerDamageMultiplier = ConfigHolder.COMMON.HarbingerDamageMultiplier.get();
            HarbingerLightFire = ConfigHolder.COMMON.HarbingerLightFire.get();

            IgnisBlockBreaking = ConfigHolder.COMMON.IgnisBlockBreaking.get();

            EnderGolemHealthMultiplier = ConfigHolder.COMMON.EndergolemHealthMultiplier.get();
            EnderGolemDamageMultiplier = ConfigHolder.COMMON.EndergolemDamageMultiplier.get();

            IgnisHealthMultiplier = ConfigHolder.COMMON.IgnisHealthMultiplier.get();
            IgnisDamageMultiplier = ConfigHolder.COMMON.IgnisDamageMultiplier.get();
            MonstrosityLongRangelimit = ConfigHolder.COMMON.MonstrosityLongRangelimit.get();
            EnderguardianLongRangelimit = ConfigHolder.COMMON.EnderguardianLongRangelimit.get();
            EndergolemLongRangelimit = ConfigHolder.COMMON.EndergolemLongRangelimit.get();
            IgnisLongRangelimit = ConfigHolder.COMMON.IgnisLongRangelimit.get();
            HarbingerLongRangelimit = ConfigHolder.COMMON.HarbingerLongRangelimit.get();

            MonstrositysHpdamage = ConfigHolder.COMMON.MonstrositysHpdamage.get();
            EnderguardianTeleportAttackHpdamage = ConfigHolder.COMMON.EnderguardianTeleportAttackHpdamage.get();
            EnderguardianGravityPunchHpdamage = ConfigHolder.COMMON.EnderguardianGravityPunchHpdamage.get();
            EnderguardianKnockbackHpdamage = ConfigHolder.COMMON.EnderguardianKnockbackHpdamage.get();
            EnderguardianUppercutHpdamage = ConfigHolder.COMMON.EnderguardianUppercutHpdamage.get();
            EnderguardianRocketPunchHpdamage = ConfigHolder.COMMON.EnderguardianRocketPunchHpdamage.get();
            EnderguardianAreaAttackHpdamage = ConfigHolder.COMMON.EnderguardianAreaAttackHpdamage.get();
            HarbingerChargeHpDamage = ConfigHolder.COMMON.HarbingerChargeHpDamage.get();

            Armor_Infinity_Durability = ConfigHolder.COMMON.Armor_Infinity_Durability.get();
        } catch (Exception e) {
            cataclysm.LOGGER.warn("An exception was caused trying to load the config for CM");
            e.printStackTrace();
        }
    }
}