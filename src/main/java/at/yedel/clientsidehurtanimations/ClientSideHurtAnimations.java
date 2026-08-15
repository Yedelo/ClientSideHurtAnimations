package at.yedel.clientsidehurtanimations;



import at.yedel.clientsidehurtanimations.config.ClientSideHurtAnimationsConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
    /*?} else if neoforge {*/
/*import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
    *//*?}*/



// Maud
/*? if neoforge */ //@Mod("clientsidehurtanimations")
public class ClientSideHurtAnimations /*? if fabric {*/implements ClientModInitializer/*?}*/ {
	/*? if fabric {*/
	@Override
	public void onInitializeClient() {
		ClientSideHurtAnimationsConfig.init();
        AttackEntityCallback.EVENT.register((user, world, hand, entity, result) -> {
            doClientSideHurtAnimation(entity);
            return InteractionResult.PASS;
        });
	}
	/*?} elif neoforge {*/
	/*public ClientSideHurtAnimations(ModContainer container) {
		ClientSideHurtAnimationsConfig.init();
		container.registerExtensionPoint(IConfigScreenFactory.class, (tainer, parent) -> ClientSideHurtAnimationsConfig.getScreen(parent));
		NeoForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        doClientSideHurtAnimation(event.getTarget());
    }
	*//*?}*/

    private void doClientSideHurtAnimation(Entity entity) {
        if (ClientSideHurtAnimationsConfig.getInstance().isEnabled()) {
            if (entity instanceof Player player) {
                if (ClientSideHurtAnimationsConfig.getInstance().isEnabledOnPlayers()) {
                    if (ClientSideHurtAnimationsConfig.getInstance().isOnlyEnabledOnRealPlayers() && !isRealPlayer(player)) return;
                    performHurtAnimation(entity);
                }
            }
            else {
                if (ClientSideHurtAnimationsConfig.getInstance().isEnabledOnOtherEntities()) {
                    performHurtAnimation(entity);
                }
            }
        }
    }

    private void performHurtAnimation(Entity entity) {
        entity.animateHurt(0);
    }

    private boolean isRealPlayer(Player player) {
        return player.getUUID().version() == 4;
    }
}