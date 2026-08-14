package at.yedel.clientsidehurtanimations.config;



import dev.isxander.yacl3.api.*;
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

    public static Screen getScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> {
                builder.title(Component.literal("ClientSideHurtAnimations Config"));
                return builder;
            }
        ).generateScreen(parent);
    }
}