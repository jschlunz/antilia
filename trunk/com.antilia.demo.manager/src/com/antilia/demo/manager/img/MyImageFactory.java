package com.antilia.demo.manager.img;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;

/**
 * 
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MyImageFactory {

	public static enum MODE {
		FROM_CODE,
		FROM_FOLDER
	}
	
	static int BUFFER_SIZE = 10*1024;
	
	 /**
     * Copies one stream into the other..
	 * @param is	Stream fuente
	 * @param os	Stream destino*/
	static public void copy(InputStream is, OutputStream os) throws IOException {
		byte[] buf = new byte[BUFFER_SIZE];
		while (true) {
			int tam = is.read(buf);
			if (tam == -1) {
				return;
			}
			os.write(buf, 0, tam);
		}
	}
	
	public static  byte[] bytes(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(is, out);
		return out.toByteArray();
	}
	
	private static class ImageFromFolderWebResource extends DynamicImageResource {
		
		private static final long serialVersionUID = 1L;

		private String name;
		
		private File folder;
		
		public ImageFromFolderWebResource(String name, File folder) {
			this.name = name;
			this.folder = folder;
		}
		
		@Override
		protected byte[] getImageData() {
		try {
				// instead of reading it form class path read it form a folder
				return bytes(TestPage.class.getResourceAsStream(name));
			} catch (Exception e) {
				return null;
			}
		}

		public File getFolder() {
			return folder;
		}
	}
	
	public static Image createImage(String id, String imageName, MODE mode) {
		if(mode.equals(MODE.FROM_CODE)) {
			return new Image(id, new ResourceReference(MyImageFactory.class, imageName));
		} else {
			// maybe has some system property from where you read file?
			// e.g. File folder = new File(System.getProperty("xxx.externalImageFolder"));
			File folder = null;
			return new Image(id, new ImageFromFolderWebResource(imageName, folder));
		}
	}
}
