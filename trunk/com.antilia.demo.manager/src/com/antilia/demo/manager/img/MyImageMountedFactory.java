package com.antilia.demo.manager.img;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequestCycle;

/**
 * 
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MyImageMountedFactory {

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
	
	private static ImageFromFolderWebResource dynamicResource;
	
	private static class ImageFromFolderWebResource extends DynamicImageResource {
		
		private static final long serialVersionUID = 1L;

		
		private File folder;
		
		public ImageFromFolderWebResource(File folder) {
			this.folder = folder;
			WebApplication.get().getSharedResources().add("mydynamicimage", this);
			WebApplication.get().mountSharedResource("mydinamicimage.png", "org.apache.wicket.Application/mydynamicimage");
		}
		
		@Override
		protected byte[] getImageData() {
			try {			
				// instead of reading it form class path read it form a folder
				String name = WebRequestCycle.get().getRequest().getParameter("name");
				return bytes(TestPage.class.getResourceAsStream(name));
			} catch (Exception e) {
				return null;
			}
		}

		public File getFolder() {
			return folder;
		}
	}
	
	public static Image createImage(String id, final String imageName, MODE mode) {
		if(mode.equals(MODE.FROM_CODE)) {
			return new Image(id, new ResourceReference(MyImageMountedFactory.class, imageName));
		} else {
			// maybe has some system property from where you read file?
			// e.g. File folder = new File(System.getProperty("xxx.externalImageFolder"));
			File folder = null;
			if(dynamicResource == null)
				dynamicResource = new ImageFromFolderWebResource(folder);
			return new Image(id) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onBeforeRender() {
					String path = WebRequestCycle.get().getRequest().getURL();
					path = path.substring(0, path.indexOf('/'));
					add(new AttributeModifier("src",true, new Model<String>("/"+path+"/mydinamicimage.png?name="+imageName)));
					super.onBeforeRender();
				}
			};
			//return new Image(id, new ImageFromFolderWebResource(imageName, folder));
		}
	}
}
