package life.genny.qwanda;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RangeDeserializer implements JsonSerializer<Range>, JsonDeserializer<Range> {

	@Override
	public JsonElement serialize(final Range src, final Type typeOfSrc, final JsonSerializationContext context) {
	    final JsonObject jsonObject = new JsonObject();
	    if ( src.hasLowerBound() ) {
	        jsonObject.add( "lowerBoundType", context.serialize( src.lowerBoundType() ) );
	        jsonObject.add( "lowerBound", context.serialize( src.lowerEndpoint() ) );
	    } else
	        jsonObject.add( "lowerBoundType", context.serialize( BoundType.OPEN ) );

	    if ( src.hasUpperBound() ) {
	        jsonObject.add( "upperBoundType", context.serialize( src.upperBoundType() ) );
	        jsonObject.add( "upperBound", context.serialize( src.upperEndpoint() ) );
	    } else
	        jsonObject.add( "upperBoundType", context.serialize( BoundType.OPEN ) );
	    return jsonObject;
	}

	@Override
	public Range<? extends Comparable<?>> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
	    if ( !( typeOfT instanceof ParameterizedType ) )
	        throw new IllegalStateException( "typeOfT must be a parameterized Range." );

	    final JsonObject jsonObject = json.getAsJsonObject();
	    final JsonElement lowerBoundTypeJsonElement = jsonObject.get( "lowerBoundType" );
	    final JsonElement upperBoundTypeJsonElement = jsonObject.get( "upperBoundType" );

	    if ( lowerBoundTypeJsonElement == null || upperBoundTypeJsonElement == null )
	        throw new IllegalStateException( "Range " + json
	                + "was not serialized with this serializer!  The default serialization does not store the boundary types, therfore we can not deserialize." );

	    final Type type = ( ( ParameterizedType ) typeOfT ).getActualTypeArguments()[0];

	    final BoundType lowerBoundType = context.deserialize( lowerBoundTypeJsonElement, BoundType.class );
	    final JsonElement lowerBoundJsonElement = jsonObject.get( "lowerBound" );
	    final Comparable<?> lowerBound = lowerBoundJsonElement == null ? null : context.deserialize( lowerBoundJsonElement, type );

	    final BoundType upperBoundType = context.deserialize( upperBoundTypeJsonElement, BoundType.class );
	    final JsonElement upperBoundJsonElement = jsonObject.get( "upperBound" );
	    final Comparable<?> upperBound = upperBoundJsonElement == null ? null : context.deserialize( upperBoundJsonElement, type );

	    if ( lowerBound == null && upperBound != null )
	        return Range.upTo( upperBound, upperBoundType );
	    else if ( lowerBound != null && upperBound == null )
	        return Range.downTo( lowerBound, lowerBoundType );
	    else if ( lowerBound == null && upperBound == null )
	        return Range.all();

	    return Range.range( lowerBound, lowerBoundType, upperBound, upperBoundType );
	}
}
