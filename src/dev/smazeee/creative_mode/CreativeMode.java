package dev.smazeee.creative_mode;

import dev.smazeee.creative_mode.gui.CreativeRemoteGUI;
import dev.smazeee.creative_mode.gui.GuiCreativeMenu;
import dev.smazeee.creative_mode.gui.MenuInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraftborge.loader.event.EventBusSubscriber;
import net.minecraftborge.loader.event.EventHandler;
import net.minecraftborge.loader.event.IModLifecycleListener;
import net.minecraftborge.loader.event.lifecycle.ModInitializationEvent;
import net.minecraftborge.loader.event.world.WorldTickEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

@EventBusSubscriber(CreativeMode.MODID)
public class CreativeMode implements IModLifecycleListener {
    public static final Minecraft mc = Minecraft.getTheMinecraft();
    public static final String MODID = "creative_mode";

    public static String path(String path) {
        return MODID + "/" + path;
    }

    public static ArrayList<ItemStack> cachedItemList = new ArrayList<>();

    @Override
    public void modInit(ModInitializationEvent event) {
        cachedItemList = cacheItems();
        event.registerLanguage();

        event.registerGUIFactory(CreativeRemoteGUI.INSTANCE);
    }

    private ArrayList<ItemStack> cacheItems() {
        ArrayList<ItemStack> cache = new ArrayList<>();
        for (Item item : Item.itemsList) {
            if (item != null) {
                if (item.shiftedIndex < Block.ID_SIZE) {
                    if (!Block.blocksList[item.shiftedIndex].getEnableStats()) continue;
                    if (Block.blocksList[item.shiftedIndex] instanceof BlockLockedChest) continue; // locked chest crashes the game🔥
                    if (Block.blocksList[item.shiftedIndex] instanceof BlockPistonMoving) continue; // crashes your game if you take it from the menu
                }

                item.getSubItems(cache);
            }
        }

        System.out.println("Cache size: " + cache.size());
        return cache;
    }

    private static int buffer = 0;

    @EventHandler
    public static void worldTick(WorldTickEvent event) {
        buffer++;

        Minecraft minecraft = Minecraft.getTheMinecraft();

        if (buffer >= 100) {
            if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                if (minecraft == null) {
                    System.out.println("Minecraft is null again.");
                    return;
                }
                if (minecraft.thePlayer == null) {
                    System.out.println("The player is null.");
                    return;
                }
                if (minecraft.thePlayer.inventory == null) {
                    System.out.println("The player inventory is null.");
                    return;
                }

                minecraft.displayGuiScreen(new GuiCreativeMenu(minecraft.thePlayer.inventory, new MenuInventory()));
            }
        }
    }
}
