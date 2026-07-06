package dev.smazeee.creative_mode.gui;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraftborge.loader.ByteArrayReader;
import net.minecraftborge.loader.IModGUIFactory;

public enum CreativeRemoteGUI implements IModGUIFactory {
    INSTANCE;

    private final ByteArrayReader reader = new ByteArrayReader();

    @Override
    public GuiContainer createGUI(int id, EntityPlayer player, byte[] extraData) {
        this.reader.setData(extraData);
        switch (id) {
            case 0:
                return new GuiCreativeMenu(player.inventory, new MenuInventory());
            default:
                return null;
        }
    }
}
