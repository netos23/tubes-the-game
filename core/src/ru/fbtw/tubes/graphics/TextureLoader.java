package ru.fbtw.tubes.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;



import java.util.HashMap;

public class TextureLoader  implements Disposable {
    private static final String TEXTURE_SUFFIX = ".png";

    private HashMap<String, Texture> loadedTextures;


    private static final TextureLoader ourInstance = new TextureLoader();


    public static TextureLoader getInstance() {
        return ourInstance;
    }

    private TextureLoader() {
        loadedTextures = new HashMap<>();
    }

    public Texture loadTexture(String name) {
        if (loadedTextures.containsKey(name)) {
            return loadedTextures.get(name);
        } else {
            Texture texture;
            try {
                texture = new Texture(name + TEXTURE_SUFFIX);
                loadedTextures.put(name, texture);
            } catch (Exception ex) {
                ex.printStackTrace();
                texture = null;
            }

            return texture;
        }
    }

    public boolean disposeTexture(String name) {
        if (loadedTextures.containsKey(name)) {
            Texture texture = loadedTextures.get(name);
            loadedTextures.remove(name);
            texture.dispose();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void dispose() {
        for (Texture texture : loadedTextures.values()) {
            texture.dispose();
        }
    }
}
