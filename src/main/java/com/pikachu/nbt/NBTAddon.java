package com.pikachu.nbt;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.pikachu.nbt.nms.NBTApi;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class NBTAddon extends JavaPlugin {

	private static SkriptAddon addonInstance;
	private static NBTAddon instance;
	private static NBTApi nbtApi;
	private static String minecraftRevision = Bukkit.getServer()
												.getClass()
												.getPackage()
												.getName()
												.split("\\.")[3];

	@Override
	public void onEnable() {
		try {
			nbtApi = (NBTApi) Class.forName(NBTAddon.class.getPackage().getName() + ".nms.NBT_" + minecraftRevision).newInstance();
			getLogger().info("Using NMS version " + minecraftRevision);
		} catch (Throwable e) {
			// Hopefully they wont miss this big mess in console
			Skript.exception(new RuntimeException(
						"NBTAddon is not supported on this version (" + minecraftRevision + "). " +
						"Please do not make an issue about this on Skript's repo."
					)
			);
			return;
		}
		instance = this;
		try {
			getAddonInstance().loadClasses(NBTAddon.class.getPackage().getName(), "skript");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null) {
			addonInstance = Skript.registerAddon(getInstance());
		}
		return addonInstance;
	}

	public static NBTAddon getInstance() {
		if (instance == null) {
			instance = new NBTAddon();
		}
		return instance;
	}

	public static NBTApi getNBTApi() {
		return nbtApi;
	}

	public static String getMinecraftRevision() {
		return minecraftRevision;
	}

}
