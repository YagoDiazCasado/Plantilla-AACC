package com.aadd.ydc.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class ImagenesUtil {
	public static byte[] getImagen(String url) {
		URI i;
		try {
			i = new URI(url);
			try (InputStream inputStream = i.toURL().openStream()) {
				return inputStream.readAllBytes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

}
