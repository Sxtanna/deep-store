package com.sxtanna.mc.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.sxtanna.mc.gson.base.GsonCodec;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Optional;

public final class GsonCodecItemStack implements GsonCodec<ItemStack>
{

	public static final GsonCodec<ItemStack> INSTANCE = new GsonCodecItemStack();


	private GsonCodecItemStack()
	{}

	@NotNull
	@Override
	public JsonElement push(@Nullable final ItemStack data) throws Exception
	{
		if (data == null)
		{
			return JsonNull.INSTANCE;
		}

		try (final ByteArrayOutputStream bos = new ByteArrayOutputStream(); final BukkitObjectOutputStream oos = new BukkitObjectOutputStream(bos))
		{
			oos.writeObject(data);

			return new JsonPrimitive(Base64.getEncoder().encodeToString(bos.toByteArray()));
		}
	}

	@NotNull
	@Override
	public Optional<ItemStack> pull(@NotNull final JsonElement json) throws Exception
	{
		if (!json.isJsonPrimitive())
		{
			return Optional.empty();
		}

		try (final ByteArrayInputStream bis = new ByteArrayInputStream(Base64.getDecoder().decode(json.getAsString())); final BukkitObjectInputStream ois = new BukkitObjectInputStream(bis))
		{
			return Optional.ofNullable((ItemStack) ois.readObject());
		}
	}

}
