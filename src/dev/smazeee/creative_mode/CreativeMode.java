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
import sun.awt.AWTAccessor;

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
                }

                item.getSubItems(cache);
            }
        }
        return cache;
    }

    @EventHandler
    public static void worldTick(WorldTickEvent event) {
        Minecraft minecraft = Minecraft.getTheMinecraft();
        if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
            if (minecraft == null || minecraft.thePlayer == null || minecraft.thePlayer.inventory == null) return;

            if (minecraft.currentScreen == null)
                minecraft.displayGuiScreen(new GuiCreativeMenu(minecraft.thePlayer.inventory, new MenuInventory()));
        }
    }
}
