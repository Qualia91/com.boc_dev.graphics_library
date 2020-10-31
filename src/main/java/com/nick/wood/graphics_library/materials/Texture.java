package com.nick.wood.graphics_library.materials;

import java.io.IOException;

public interface Texture {

	void destroy();

	void create() throws IOException;

	int getId();

	void setId(int textureId);
}
