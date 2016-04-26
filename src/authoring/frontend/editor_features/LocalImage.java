package authoring.frontend.editor_features;

import java.io.InputStream;

import javafx.scene.image.Image;

/**
 * Extended Image class that contains URL.
 * 
 * @author Frank
 *
 */
public class LocalImage extends Image {

	private String myURL;

	public LocalImage(String url) {
		super(url);
		myURL = url;
	}

	public LocalImage(InputStream is) {
		super(is);

	}

	public LocalImage(String url, boolean backgroundLoading) {
		super(url, backgroundLoading);

	}

	public LocalImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio,
			boolean smooth) {
		super(url, requestedWidth, requestedHeight, preserveRatio, smooth);

	}

	public LocalImage(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio,
			boolean smooth) {
		super(is, requestedWidth, requestedHeight, preserveRatio, smooth);

	}

	public LocalImage(String url, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth,
			boolean backgroundLoading) {
		super(url, requestedWidth, requestedHeight, preserveRatio, smooth, backgroundLoading);

	}

	public String getURL() {
		return myURL;
	}

}
