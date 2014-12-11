package mnm.mods.protocol.gui;

import mnm.mods.protocol.EnumProtocols;
import mnm.mods.protocol.LiteModProtocol4;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ProtocolButton extends GuiButton {

    private EnumProtocols protocol;
    private int index;

    public ProtocolButton(int id, int x, int y, EnumProtocols version) {
        super(id, x, y, 35, 20, getLast(version.getSupportedVersions()));
        protocol = version;
        index = getIndex(protocol);
    }

    private static String getLast(String[] versions) {
        return versions[versions.length - 1];
    }

    private static int getIndex(EnumProtocols protocol) {
        if (protocol == null) {
            throw new NullPointerException();
        }
        EnumProtocols[] values = EnumProtocols.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(protocol)) {
                return i;
            }
        }
        throw new IllegalArgumentException("What the? Enum wasn't in the array.");
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y) {
        super.drawButton(mc, x, y);
        String[] versions = this.protocol.getSupportedVersions();
        if (this.field_146123_n) {
            int counter = mc.ingameGUI.getUpdateCounter();
            counter /= 20;
            int i = counter % versions.length;
            this.displayString = versions[i];
        } else {
            this.displayString = getLast(versions);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (this.mousePressed(Minecraft.getMinecraft(), x, y)) {
            selectProtocol(index++);
        }
        super.mouseReleased(x, y);
    }

    private void selectProtocol(int i) {
        index += i;
        EnumProtocols[] values = EnumProtocols.values();
        if (index >= values.length) {
            index = 0;
        }

        this.protocol = values[index];
        LiteModProtocol4.instance.setProtocol(protocol);
        this.displayString = getLast(protocol.getSupportedVersions());

        MultiplayerMenu.refresh();
    }
}
