/*? if fabric {*/

package at.yedel.clientsidehurtanimations.utils;



import at.yedel.clientsidehurtanimations.config.ClientSideHurtAnimationsConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;



public class ClientSideHurtAnimationsModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ClientSideHurtAnimationsConfig::getScreen;
    }
}

/*?}*/
