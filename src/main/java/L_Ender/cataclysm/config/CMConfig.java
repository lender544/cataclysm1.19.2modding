package L_Ender.cataclysm.config;

import L_Ender.cataclysm.cataclysm;
import net.minecraftforge.fml.config.ModConfig;

public class CMConfig {

    public static int Voidrunedamage = 7;
    public static int Lavabombradius = 2;

    public static boolean ScreenShake = true;
    public static boolean BossMusic = true;

    public static int EnderguardianDamageCap = 22;
    public static int MonstrosityDamageCap = 25;
    public static int IgnisDamageCap = 20;

    public static int Lavabombmagazine = 3;
    public static int Lavabombamount = 3;

    public static int EnderguardianBlockBreakingX = 15;
    public static int EnderguardianBlockBreakingY = 2;
    public static int EnderguardianBlockBreakingZ = 15;

    public static boolean EnderguardianBlockBreaking = true;
    public static boolean EndergolemBlockBreaking = false;
    public static boolean IgnisBlockBreaking = true;

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

    public static double MonstrosityLongRangelimit = 18D;
    public static double EnderguardianLongRangelimit = 12D;
    public static double EndergolemLongRangelimit = 6D;
    public static double IgnisLongRangelimit = 15D;


    public static void bake(ModConfig config) {
        try {
            Voidrunedamage = ConfigHolder.COMMON.Voidrunedamage.get();
            Lavabombradius = ConfigHolder.COMMON.Lavabombradius.get();
            ScreenShake = ConfigHolder.COMMON.ScreenShake.get();
            BossMusic = ConfigHolder.COMMON.BossMusic.get();
            EnderguardianDamageCap = ConfigHolder.COMMON.EnderguardianDamageCap.get();
            MonstrosityDamageCap = ConfigHolder.COMMON.MonstrosityDamageCap.get();
            IgnisDamageCap = ConfigHolder.COMMON.IgnisDamageCap.get();
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
            IgnisBlockBreaking = ConfigHolder.COMMON.IgnisBlockBreaking.get();

            EnderGolemHealthMultiplier = ConfigHolder.COMMON.EndergolemHealthMultiplier.get();
            EnderGolemDamageMultiplier = ConfigHolder.COMMON.EndergolemDamageMultiplier.get();

            IgnisHealthMultiplier = ConfigHolder.COMMON.IgnisHealthMultiplier.get();
            IgnisDamageMultiplier = ConfigHolder.COMMON.IgnisDamageMultiplier.get();
            MonstrosityLongRangelimit = ConfigHolder.COMMON.MonstrosityLongRangelimit.get();
            EnderguardianLongRangelimit = ConfigHolder.COMMON.EnderguardianLongRangelimit.get();
            EndergolemLongRangelimit = ConfigHolder.COMMON.EndergolemLongRangelimit.get();
            IgnisLongRangelimit = ConfigHolder.COMMON.IgnisLongRangelimit.get();

        } catch (Exception e) {
            cataclysm.LOGGER.warn("An exception was caused trying to load the config for CM");
            e.printStackTrace();
        }
    }
}