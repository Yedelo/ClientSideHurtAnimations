package at.yedel.clientsidehurtanimations;



import at.yedel.clientsidehurtanimations.config.ClientSideHurtAnimationsConfig;
/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
    /*?} else if neoforge {*/
/*import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
    *//*?}*/



// Maud
/*? if neoforge */ //@Mod("clientsidehurtanimations")
public class ClientSideHurtAnimations /*? if fabric {*/implements ClientModInitializer/*?}*/ {
	/*? if fabric {*/
	@Override
	public void onInitializeClient() {
		ClientSideHurtAnimationsConfig.init();
        AttackEntityCallback.EVENT.register((user, world, hand, entity, result) -> {
            System.out.println("In event");
            doClientSideHurtAnimation(entity);
            return InteractionResult.PASS;
        });
	}
	/*?} elif neoforge {*/
	/*public ClientSideHurtAnimations(ModContainer container) {
		ClientSideHurtAnimationsConfig.init();
		container.registerExtensionPoint(IConfigScreenFactory.class, (tainer, parent) -> ClientSideHurtAnimationsConfig.getScreen(parent));
	}

    public void onAttackEntity(AttackEntityEvent event) {
        doClientSideHurtAnimation(event.getTarget());
    }
	*//*?}*/

    private void doClientSideHurtAnimation(Entity entity) {
        if (ClientSideHurtAnimationsConfig.getInstance().isEnabled()) {
            System.out.println("enabled");
            if (entity instanceof Player player) {
                System.out.println("instanceof player");
                if (ClientSideHurtAnimationsConfig.getInstance().isEnabledOnPlayers()) {
                    System.out.println("enabled on players");
                    if (ClientSideHurtAnimationsConfig.getInstance().isOnlyEnabledOnRealPlayers() && !isRealPlayer(player)) return;
                    System.out.println("going to perform hurt animation");
                    performHurtAnimation(entity);
                }
            }
            else {
                System.out.println("not player");
                if (ClientSideHurtAnimationsConfig.getInstance().isEnabledOnOtherEntities()) {
                    System.out.println("is enabled on other entities, going to perform");
                    performHurtAnimation(entity);
                }
            }
        }
    }

    private void performHurtAnimation(Entity entity) {
        System.out.println("In perform hurt animation");
        entity.animateHurt(0);
    }

    private boolean isRealPlayer(Player player) {
        return player.getUUID().version() == 4;
    }
}