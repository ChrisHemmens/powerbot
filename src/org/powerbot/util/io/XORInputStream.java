package org.powerbot.util.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.crypto.Cipher;

/**
 * @author Paris
 */
public final class XORInputStream extends FilterInputStream {
	private final byte[] key;
	private final int opmode, l;
	private int n;

	public XORInputStream(final InputStream in, final byte[] key, final int opmode) {
		super(in);
		this.key = key;
		if (!(opmode == Cipher.DECRYPT_MODE || opmode == Cipher.ENCRYPT_MODE)) {
			throw new IllegalArgumentException();
		}
		this.opmode = opmode;
		l = this.key.length;
		n = 0;
	}

	private void rotate(final byte[] b, final int off, final int len) {
		for (int i = off; i < off + len; i++) {
			final int z = n++ % l, d = key[l - z - 1], x = key[z];
			b[i] = (byte) (opmode == Cipher.DECRYPT_MODE ? (b[i] + d) % 0xff ^ x : (b[i] ^ x) - d % 0xff);
		}
	}

	@Override
	public int read() throws IOException {
		return read(new byte[1], 0, 1);
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		final int result = super.read(b, off, len);
		rotate(b, off, len);
		return result;
	}
}
