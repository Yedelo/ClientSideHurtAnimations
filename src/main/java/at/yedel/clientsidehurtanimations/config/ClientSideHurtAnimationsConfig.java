package at.yedel.clientsidehurtanimations.config;



import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
    /*? if fabric {*/
import net.fabricmc.loader.api.FabricLoader;
    /*?} elif neoforge {*/
/*import net.neoforged.fml.loading.FMLPaths;
 *//*?}*/
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.nio.file.Path;
import java.util.function.Consumer;



public class ClientSideHurtAnimationsConfig {
    /*? if fabric {*/
    private static final Path CONFIG_FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("clientsidehurtanimations.json");
    /*?} else if neoforge {*/
    /*private static final Path CONFIG_FILE_PATH = FMLPaths.CONFIGDIR.get().resolve("clientsidehurtanimations.json");
     *//*?}*/
    public static final ConfigClassHandler<ClientSideHurtAnimationsConfig> HANDLER = ConfigClassHandler.createBuilder(ClientSideHurtAnimationsConfig.class)
        .id(Identifier.fromNamespaceAndPath("clientsidehurtanimations", "clientsidehurtanimations-config"))
        .serializer(
            config -> GsonConfigSerializerBuilder.create(config)
                .setPath(CONFIG_FILE_PATH)
                .setJson5(false)
                .build()
        )
        .build();

    public static ClientSideHurtAnimationsConfig getInstance() {
        return HANDLER.instance();
    }

    public static void init() {
        HANDLER.load();
    }

    @SerialEntry private boolean enabled = false;

    @SerialEntry private boolean enabledOnPlayers = true;
    @SerialEntry private boolean onlyEnabledOnRealPlayers = false;
    @SerialEntry private boolean enabledOnOtherEntities = true;

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
                builder.title(Component.literal("ClientSideHurtAnimations Config"));
                builder.category(
                    ConfigCategory.createBuilder().name(Component.literal("General"))
                        .option(Option.<Boolean>createBuilder()
                            .name(Component.literal("Enabled"))
                            .binding(
                                defaults.isEnabled(),
                                config::isEnabled,
                                config::setEnabled
                            )
                            .controller(BooleanControllerBuilder::create)
                            .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                            .name(Component.literal("Enabled on Players"))
                            .description(OptionDescription.of(Component.literal("Enable client-side hurt animations for players.")))
                            .binding(
                                defaults.isEnabledOnPlayers(),
                                config::isEnabledOnPlayers,
                                config::setEnabledOnPlayers
                            )
                            .controller(TickBoxControllerBuilder::create)
                            .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                            .name(Component.literal("Only Enabled on Real Players"))
                            .description(OptionDescription.of(Component.literal("Only enable client-side hurt animations for real players (UUID version == 4).")))
                            .binding(
                                defaults.isOnlyEnabledOnRealPlayers(),
                                config::isOnlyEnabledOnRealPlayers,
                                config::setOnlyEnabledOnRealPlayers
                            )
                            .controller(TickBoxControllerBuilder::create)
                            .build()
                        )
                        .option(Option.<Boolean>createBuilder()
                            .name(Component.literal("Enabled on Other Entities"))
                            .description(OptionDescription.of(Component.literal("Enable client-side hurt animations for other non-player entities, such as zombies.")))
                            .binding(
                                defaults.isEnabledOnOtherEntities(),
                                config::isEnabledOnOtherEntities,
                                config::setEnabledOnOtherEntities
                            )
                            .controller(TickBoxControllerBuilder::create)
                            .build()
                        )
                        .build()
                );
                return builder;
            }
        ).generateScreen(parent);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabledOnPlayers() {
        return enabledOnPlayers;
    }

    public void setEnabledOnPlayers(boolean enabledOnPlayers) {
        this.enabledOnPlayers = enabledOnPlayers;
    }

    public boolean isOnlyEnabledOnRealPlayers() {
        return onlyEnabledOnRealPlayers;
    }

    public void setOnlyEnabledOnRealPlayers(boolean onlyEnabledOnRealPlayers) {
        this.onlyEnabledOnRealPlayers = onlyEnabledOnRealPlayers;
    }

    public boolean isEnabledOnOtherEntities() {
        return enabledOnOtherEntities;
    }

    public void setEnabledOnOtherEntities(boolean enabledOnOtherEntities) {
        this.enabledOnOtherEntities = enabledOnOtherEntities;
    }
}