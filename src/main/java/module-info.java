module com.nick.wood.graphics_library {
	requires com.nick.wood.maths;
	requires org.lwjgl.opengl;
	requires org.lwjgl.stb;
	requires org.lwjgl.glfw;
	requires org.lwjgl.opengles;
	requires org.lwjgl.assimp;
	requires org.lwjgl.opengl.natives;
	requires org.lwjgl.glfw.natives;
	requires org.lwjgl.natives;
	exports com.nick.wood.graphics_library;
	exports com.nick.wood.graphics_library.objects;
	exports com.nick.wood.graphics_library.objects.game_objects;
	exports com.nick.wood.graphics_library.input;
	exports com.nick.wood.graphics_library.lighting;
	exports com.nick.wood.graphics_library.objects.mesh_objects;
}