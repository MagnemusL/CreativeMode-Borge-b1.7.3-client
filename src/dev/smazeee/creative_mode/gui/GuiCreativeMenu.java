package dev.smazeee.creative_mode.gui;

import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiCreativeMenu extends GuiContainer {
    private static final RenderItem itemRenderer = new RenderItem();
    private final MenuInventory menuInventory;

    public GuiCreativeMenu(InventoryPlayer inventory, MenuInventory menuInventory) {
        super(new ContainerCreativeMenu(inventory, menuInventory));
        this.menuInventory = menuInventory;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.xSize = 176;
        this.ySize = 198;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick) {
        int textureID = this.mc.renderEngine.getTexture("assets/gui/creative_mode/creative_menu.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(textureID);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);


        //GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

    @Override
    public void handleKeyboardInput() {
        if(Keyboard.getEventKeyState()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                menuInventory.incrementScroll();
                return;
            }

            if(Keyboard.getEventKey() == Keyboard.KEY_UP) {
                menuInventory.decreaseScroll();
                return;
            }

            this.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }

        super.handleKeyboardInput();
    }
}
