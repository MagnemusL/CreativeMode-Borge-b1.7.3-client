package dev.smazeee.creative_mode.gui;

import net.minecraft.src.*;

public class ContainerCreativeMenu extends Container {
    public ContainerCreativeMenu(InventoryPlayer inventory, IInventory menuInventory) {
        this.addSlot(new Slot(menuInventory, 0, 174, 174)); // trashcan

        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(menuInventory, x + y * 9 + 1, 8 + x * 18, 8 + y * 18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 174));
        }
    }

    @Override
    public ItemStack clickGuiSlot(int slotId, int type, boolean shift, EntityPlayer player) {
        if (slotId < 0 || slotId >= this.slots.size()) return super.clickGuiSlot(slotId, type, shift, player);
        if (slotId >= 82) return super.clickGuiSlot(slotId, type, shift, player); // hotbar slots

        Slot slot = this.slots.get(slotId);
        InventoryPlayer inventory = player.inventory;
        ItemStack slotStack = slot.getStack();
        ItemStack inventoryStack = inventory.getItemStack();

        if (slotId == 0) {
            player.inventory.setItemStack(null);
            return null;
        }

        if (slotStack == null) return null;

        if (shift) {
            ItemStack inventoryStackReturn = slotStack.copy();
            inventoryStackReturn.stackSize = slotStack.getMaxStackSize();

            player.inventory.setItemStack(inventoryStackReturn);
            return null;
        }

        if (inventoryStack == null) {
            player.inventory.setItemStack(slotStack.copy());
            return null;
        }

        if (slotStack.itemID == inventoryStack.itemID && inventoryStack.getMaxStackSize() > 1 && (!slotStack.getHasSubtypes()
                || slotStack.getItemDamage() == inventoryStack.getItemDamage())) {
            inventoryStack.stackSize = Math.min(inventoryStack.stackSize + 1, inventoryStack.getMaxStackSize());
            return null;
        }

        player.inventory.setItemStack(slotStack.copy());
        return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return player != null;
    }
}
