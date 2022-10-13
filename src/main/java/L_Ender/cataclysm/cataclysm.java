package L_Ender.cataclysm;


import L_Ender.cataclysm.client.model.armor.CMModelLayers;
import L_Ender.cataclysm.config.CMConfig;
import L_Ender.cataclysm.config.ConfigHolder;
import L_Ender.cataclysm.event.ServerEventHandler;
import L_Ender.cataclysm.init.*;
import L_Ender.cataclysm.message.MessageCMMultipart;
import L_Ender.cataclysm.message.MessageUpdateblockentity;
import L_Ender.cataclysm.util.Cataclysm_Group;
import L_Ender.cataclysm.util.Modcompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import L_Ender.cataclysm.init.ModStructures;

@Mod(cataclysm.MODID)
@Mod.EventBusSubscriber(modid = cataclysm.MODID)
public class cataclysm {
    public static final String MODID = "cataclysm";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final SimpleChannel NETWORK_WRAPPER;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static CreativeModeTab CATACLYSM_GROUP = new Cataclysm_Group();
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    private static int packetsRegistered;


    static {
        NetworkRegistry.ChannelBuilder channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation("cataclysm", "main_channel"));
        String version = PROTOCOL_VERSION;
        version.getClass();
        channel = channel.clientAcceptedVersions(version::equals);
        version = PROTOCOL_VERSION;
        version.getClass();
        NETWORK_WRAPPER = channel.serverAcceptedVersions(version::equals).networkProtocolVersion(() -> {
            return PROTOCOL_VERSION;
        }).simpleChannel();
    }

    public cataclysm() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        bus.addListener(this::onModConfigEvent);
        bus.addListener(this::setupEntityModelLayers);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, "cataclysm.toml");
        ModItems.ITEMS.register(bus);
        ModEffect.EFFECTS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModParticle.PARTICLE.register(bus);
        ModStructures.STRUCTURE_PIECE_DEF_REG.register(bus);
        ModStructures.STRUCTURE_TYPE_DEF_REG.register(bus);
        ModTileentites.TILE_ENTITY_TYPES.register(bus);
        ModEntities.ENTITY_TYPE.register(bus);
        ModSounds.SOUNDS.register(bus);
        PROXY.init();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());
    }

    private void setupEntityModelLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        CMModelLayers.register(event);
    }

    @SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        // Rebake the configs when they change
        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            CMConfig.bake(config);
        }
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }

    public static <MSG> void sendMSGToAll(MSG message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendNonLocal(message, player);
        }
    }

    public static <MSG> void sendNonLocal(MSG msg, ServerPlayer player) {
        NETWORK_WRAPPER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }


    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Modcompat::registerDispenserBehaviors);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageCMMultipart.class, MessageCMMultipart::encode, MessageCMMultipart::new, MessageCMMultipart.Handler::onMessage);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, MessageUpdateblockentity.class, MessageUpdateblockentity::write, MessageUpdateblockentity::read, MessageUpdateblockentity.Handler::handle);
    }

}



