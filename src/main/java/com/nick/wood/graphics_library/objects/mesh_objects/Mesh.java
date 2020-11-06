package com.nick.wood.graphics_library.objects.mesh_objects;

import com.nick.wood.graphics_library.objects.DrawVisitor;
import com.nick.wood.graphics_library.objects.render_scene.InstanceObject;

import java.util.ArrayList;

public interface Mesh {

	void create();

	void destroy();

	void initRender();

	void endRender();

	default void draw(DrawVisitor drawVisitor, ArrayList<InstanceObject> value) {
		drawVisitor.draw(this, value);
	}

	int size();
}
