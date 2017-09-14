package org.rapid.util.common.consts.conveter.io;

import java.io.InputStream;

import org.rapid.util.common.consts.ObjConst;
import org.rapid.util.exception.ConvertFailuerException;

public class Ins2InsConstConverter extends ObjConst<InputStream> implements InsConstConverter<InputStream> {

	public Ins2InsConstConverter(int id, String key) {
		super(id, key);
	}

	@Override
	public InputStream convert(InputStream k) throws ConvertFailuerException {
		return k;
	}
}
