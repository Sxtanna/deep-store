package com.sxtanna.mc.gson.base;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Optional;

public interface GsonCodec<T> extends JsonSerializer<T>, JsonDeserializer<T>
{

	@NotNull
	JsonElement push(@Nullable final T data) throws Exception;

	@NotNull
	Optional<T> pull(@NotNull final JsonElement json) throws Exception;


	@Override
	default JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context)
	{
		try
		{
			return push(src);
		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	default T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{
		try
		{
			return pull(json).orElseThrow(() -> new JsonParseException("value not present"));
		}
		catch (final Exception ex)
		{
			ex.printStackTrace();
		}

		return null;
	}

}
