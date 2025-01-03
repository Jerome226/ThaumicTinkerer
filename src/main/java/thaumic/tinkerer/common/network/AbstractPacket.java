package thaumic.tinkerer.common.network;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Katrina on 03/06/14.
 */
public abstract class AbstractPacket implements IMessage {

    public abstract void handleClientSide(EntityPlayer entityPlayer);

    public abstract void handleServerSide(EntityPlayer entityPlayer);
}
