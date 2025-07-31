package com.icinema.seating.utility;

import java.util.stream.IntStream;

public class SeatingUtility {
public static Byte[] convertToObjBytes(byte[] inp) {
	Byte[] byteObjectArray = IntStream.range(0, inp.length).mapToObj(i->inp[i]).toArray(Byte[]::new);
	return byteObjectArray;
}
}
