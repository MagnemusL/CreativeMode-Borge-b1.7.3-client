package dev.smazeee.creative_mode.gui;

import net.minecraft.src.*;

public class ContainerCreativeMenu extends Container {
    public ContainerCreativeMenu(InventoryPlayer inventory, IInventory menuInventory) {
        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(menuInventory, x + y * 9, 8 + x * 18, 8 + y * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 174));
        }
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return player != null;
    }
}
