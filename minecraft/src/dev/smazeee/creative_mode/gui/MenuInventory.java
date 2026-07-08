package dev.smazeee.creative_mode.gui;

import dev.smazeee.creative_mode.CreativeMode;
import net.minecraft.src.*;

public class MenuInventory implements IInventory {
    public int scroll = 0;
    private final int maxScroll = getMaxScroll();

    private int getMaxScroll() {
        final int ROW_SIZE = 9;
        final int COLUMN_SIZE = 9;

        int maxItems = CreativeMode.cachedItemList.size();
        int rowsTotal = maxItems / ROW_SIZE;
        return rowsTotal - COLUMN_SIZE + 1;
    }

    public void incrementScroll() {
        if (scroll + 1 <= maxScroll) this.scroll++;
    }

    public void decreaseScroll() {
        if (scroll - 1 >= 0) this.scroll--;
    }

    @Override
    public int getSizeInventory() {
        return 82;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        int id = slot + scroll * 9;
        if (id >= CreativeMode.cachedItemList.size()) return null;

        return slot == 0 ? null : CreativeMode.cachedItemList.get(id).copy(); // make sure the trashcan slot doesn't have anything in it
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        int id = slot + scroll * 9;
        if (id >= CreativeMode.cachedItemList.size()) return null;

        if (CreativeMode.cachedItemList.get(id) != null) {
            return CreativeMode.cachedItemList.get(id).copy();
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {}

    @Override
    public String getInvName() {
        return "Creative Menu";
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public void onInventoryChanged() {}

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !player.isPlayerSleeping();
    }
}
