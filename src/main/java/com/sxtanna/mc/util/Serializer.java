package com.sxtanna.mc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sxtanna.mc.gson.GsonCodecItemStack;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class Serializer
{

	@NotNull
	private static final Gson GSON = createGson();


	private Serializer()
	{}


	@NotNull
	public static <T> Optional<String> push(@NotNull final T data)
	{
		final DelegateDeserialization delegate = data.getClass().getAnnotation(DelegateDeserialization.class);

		try
		{
			return Optional.ofNullable(GSON.toJson(data, delegate != null ? delegate.value() : data.getClass()));
		}
		catch (final JsonIOException ex)
		{
			return Optional.empty();
		}
	}

	@NotNull
	public static <T> Optional<T> pull(@NotNull final String text, @NotNull final Class<T> clazz)
	{
		try
		{
			return Optional.ofNullable(GSON.fromJson(text, clazz));
		}
		catch (final JsonSyntaxException ex)
		{
			return Optional.empty();
		}
	}


	@NotNull
	private static Gson createGson()
	{
		final GsonBuilder builder = new GsonBuilder();

		builder.serializeNulls()
			   .disableHtmlEscaping()
			   .enableComplexMapKeySerialization()
			   .serializeSpecialFloatingPointValues();

		builder.registerTypeAdapter(ItemStack.class,
									GsonCodecItemStack.INSTANCE);

		return builder.create();
	}

}
