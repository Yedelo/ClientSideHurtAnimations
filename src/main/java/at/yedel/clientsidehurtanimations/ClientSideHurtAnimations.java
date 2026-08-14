package at.yedel.clientsidehurtanimations;



import at.yedel.clientsidehurtanimations.config.ClientSideHurtAnimationsConfig;
/*? if fabric {*/
import net.fabricmc.api.ClientModInitializer;
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
	}
	/*?} elif neoforge {*/
	/*public ClientSideHurtAnimations(ModContainer container) {
		ClientSideHurtAnimationsConfig.init();
		container.registerExtensionPoint(IConfigScreenFactory.class, (tainer, parent) -> ClientSideHurtAnimationsConfig.getScreen(parent));
	}
	*//*?}*/
}