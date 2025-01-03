package thaumic.tinkerer.common.compat;

import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;

/**
 * Created by Katrina on 31/01/2015.
 */
public class SpecialMobs {

    public static void setupClass() {
        try {
            ItemFocusDeflect.DeflectWhitelist.add(Class.forName("toast.specialMobs.entity.EntitySpecialFishHook"));
            ItemFocusDeflect.DeflectWhitelist.add(Class.forName("toast.specialMobs.entity.EntitySpecialSpitball"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
