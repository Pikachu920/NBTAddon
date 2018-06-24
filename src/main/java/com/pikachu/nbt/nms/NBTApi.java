package com.pikachu.nbt.nms;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public interface NBTApi {

	Class<?> getCompoundClass();

	String getNBT(Block block);

	String getNBT(Entity entity);

	String getNBT(ItemStack item);

}
