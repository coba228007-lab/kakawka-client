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
        // Логика открытия меню на Правый Шифт
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null) return;
            
            if (GLFW.glfwGetKey(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
                menuOpen = !menuOpen;
                // Задержка, чтобы меню не мигало при одном нажатии
                try { Thread.sleep(200); } catch (Exception e) {}
            }
        });

        // Отрисовка текста в левом верхнем углу
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            if (menuOpen) {
                MinecraftClient.getInstance().textRenderer.draw(matrixStack, "--- KAKAWKA MENU ACTIVE ---", 10, 10, 0xFF0000);
            }
        });
    }
}
