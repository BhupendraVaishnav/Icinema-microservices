package com.icinema.seating.entities;

import java.nio.ByteBuffer;
import java.util.BitSet;

import jakarta.persistence.AttributeConverter;

public class BitSetConverter implements AttributeConverter<BitSet, byte[]> {
	@Override
	public byte[] convertToDatabaseColumn(BitSet attribute) {
		ByteBuffer buffer = ByteBuffer.allocate(2);
		buffer.put(attribute.toByteArray());
		return buffer.array();
	}

	public BitSet convertToEntityAttribute(byte[] dbData) {
		BitSet bitSet = new BitSet(14);
		ByteBuffer buffer = ByteBuffer.wrap(dbData);
		Byte byt = buffer.get();
		int bytPos;
		for(int i=0; i<14;i++) {
			bytPos =(i)%8;
			if(i==8) {
				byt = buffer.get();
		}
		bitSet.set(i, ((byt>>bytPos)&1)==1);
	}
		return bitSet;
	}
}
