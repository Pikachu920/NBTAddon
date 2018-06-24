package com.pikachu.nbt.nms;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.World;

public class NBT_v1_8_R3 implements NBTApi {

	@Override
	public Class<?> getCompoundClass() {
		return NBTTagCompound.class;
	}

	@Override
	public String getNBT(Block block) {
		World world = ((CraftWorld) block.getWorld()).getHandle();
		NBTTagCompound nbt = new NBTTagCompound();
		TileEntity tileEntity = world.getTileEntity(new BlockPosition(block.getX(), block.getY(), block.getZ()));
		if (tileEntity == null) {
			return null;
		}
		tileEntity.b(nbt);
		return nbt.toString();
	}

	@Override
	public String getNBT(Entity entity) {
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		NBTTagCompound nbt = new NBTTagCompound();
		nmsEntity.e(nbt);
		return nbt.toString();
	}

	@Override
	public String getNBT(ItemStack item) {
		if (item.getType() == Material.AIR) {
			return null;
		}
		NBTTagCompound nbt = CraftItemStack.asNMSCopy(item).getTag();
		return nbt == null ? null : nbt.toString();
	}

}
