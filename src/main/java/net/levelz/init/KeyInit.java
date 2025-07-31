package net.levelz.init;

import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.levelz.screen.SkillScreen;
import net.levelz.screen.SkillScreenFull;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class KeyInit {
    public static KeyBinding screenKey = new KeyBinding("key.levelz.openskillscreen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "category.levelz.keybind");
    public static KeyBinding devKey = new KeyBinding("key.levelz.dev", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, "category.levelz.keybind");

    public static void init() {
        // Registering
        KeyBindingHelper.registerKeyBinding(screenKey);
        KeyBindingHelper.registerKeyBinding(devKey);
        // Callback
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (screenKey.wasPressed()) {
                client.setScreen(new SkillScreen());
                return;
            }

            if (devKey.wasPressed()) {
                client.setScreen(new SkillScreenFull());
                return;
            }
        });
    }

    public static void writeId(String string) {
        try (FileWriter idFile = new FileWriter("idlist.json", true)) {
            idFile.append("\"" + string + "\",");
            idFile.append(System.lineSeparator());
        } catch (IOException e) {
        }
    }

}