//? if fabric {
package at.yedel.clientsidehurtanimations.utils;



import at.yedel.clientsidehurtanimations.config.ClientSideHurtAnimationsConfig;
import org.polyfrost.oneconfig.api.toggle.v1.ModToggle;



public class ClientSideHurtAnimationsModToggle implements ModToggle {
    @Override
    public boolean isEnabled() {
        return ClientSideHurtAnimationsConfig.getInstance().enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        ClientSideHurtAnimationsConfig.getInstance().enabled = enabled;
        ClientSideHurtAnimationsConfig.HANDLER.save();
    }
}
//?}