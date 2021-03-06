package net.mcsproject.magicwar.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTModifier {

	private Object nmsItemStack;
	private Object tagCompound;

	private final String VERSION;

	private Class<?> getCraftItemStackClass() {
		try {
			return Class.forName("org.bukkit.craftbukkit." + this.VERSION + ".inventory.CraftItemStack");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNewTagCompound() {
		try {
			return Class.forName("net.minecraft.server." + this.VERSION + ".NBTTagCompound").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNMSItemStack(ItemStack itemStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
			return asNMSCopy.invoke(craftItemStack, itemStack);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ItemStack getBukkitItemStack(Object nmsStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", nmsStack.getClass());
			return (ItemStack) asBukkitCopy.invoke(craftItemStack, nmsStack);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNBTTagCompound(Object nmsItem) {
		Class<?> nmsItemClass = nmsItem.getClass();
		try {
			Method getTag = nmsItemClass.getMethod("getTag");
			return getTag.invoke(nmsItem);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object setNBTTagCompound(Object nmsItem, Object tag) {
		Method setTag;
		try {
			setTag = nmsItem.getClass().getMethod("setTag", tag.getClass());
			setTag.invoke(nmsItem, tag);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return nmsItem;
	}

	public NBTModifier(ItemStack itemStack) {
		this.VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

		this.nmsItemStack = this.getNMSItemStack(itemStack);
		this.tagCompound = this.getNBTTagCompound(this.nmsItemStack);

		if (this.tagCompound == null) {
			this.tagCompound = this.getNewTagCompound();
		}
	}

	public NBTModifier setString(String key, String value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setString", String.class, String.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public String getString(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getString", String.class);
			return (String) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getInteger(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getInt", String.class);
			return (Integer) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setInteger(String key, int value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setInt", String.class, int.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Double getDouble(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getDouble", String.class);
			return (Double) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setDouble(String key, double value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setDouble", String.class, double.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Boolean getBoolean(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getBoolean", String.class);
			return (Boolean) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setBoolean(String key, Boolean value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setBoolean", String.class, boolean.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Byte getByte(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getByte", String.class);
			return (Byte) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setByte(String key, Byte value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setByte", String.class, byte.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Float getFloat(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getFloat", String.class);
			return (Float) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setFloat(String key, Float value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setFloat", String.class, float.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Long getLong(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getLong", String.class);
			return (Long) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setLong(String key, Long value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setLong", String.class, long.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public Short getShort(String key) {
		try {
			Method getObject = this.tagCompound.getClass().getMethod("getShort", String.class);
			return (Short) getObject.invoke(this.tagCompound, key);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NBTModifier setLong(String key, Short value) {
		try {
			Method setObject = this.tagCompound.getClass().getMethod("setShort", String.class, short.class);
			setObject.invoke(this.tagCompound, key, value);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return this;
	}

	public ItemStack modify() {
		this.setNBTTagCompound(this.nmsItemStack, this.tagCompound);
		return this.getBukkitItemStack(this.nmsItemStack);
	}

}
