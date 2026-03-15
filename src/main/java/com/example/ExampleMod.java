package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class ExampleMod implements ModInitializer {
    private boolean menuOpen = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Нажатие на Правый Шифт
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                menuOpen = !menuOpen;
                try { Thread.sleep(200); } catch (Exception e) {}
            }
        });

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            if (menuOpen) {
                MinecraftClient.getInstance().textRenderer.draw(matrixStack, "--- KAKAWKA CLIENT ---", 10, 10, 0xFF0000);
            }
        });
    }
}
