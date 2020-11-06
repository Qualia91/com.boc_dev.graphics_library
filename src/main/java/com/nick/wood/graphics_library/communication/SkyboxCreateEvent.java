package com.nick.wood.graphics_library.communication;

import com.nick.wood.graphics_library.Window;
import com.nick.wood.graphics_library.objects.mesh_objects.Model;
import com.nick.wood.graphics_library.objects.render_scene.InstanceObject;
import com.nick.wood.graphics_library.objects.render_scene.Pair;
import com.nick.wood.graphics_library.objects.render_scene.RenderGraph;

import java.io.IOException;
import java.util.ArrayList;

public class SkyboxCreateEvent implements RenderUpdateEvent<Model> {

	private final InstanceObject instanceObject;
	private final Model model;
	private final String layerName;


	public SkyboxCreateEvent(InstanceObject instanceObject, Model model, String layerName) {
		this.instanceObject = instanceObject;
		this.model = model;
		this.layerName = layerName;
	}

	public InstanceObject getInstanceObject() {
		return instanceObject;
	}

	@Override
	public Model getData() {
		return model;
	}

	@Override
	public RenderInstanceEventType getType() {
		return RenderInstanceEventType.CREATE;
	}

	public void applyToGraphicsEngine(Window window) {
		if (window.getRenderGraphs().get(layerName) == null) {
			window.getRenderGraphs().put(layerName, new RenderGraph());
		}

		try {
			window.getMeshManager().createMesh(model.getMeshString());
		} catch (IOException e) {
			System.err.println("Mesh " + model.getMeshString() + " not found, using default for model " + model.getStringID());
		}
		window.getModelManager().addModel(model);
		window.getRenderGraphs().get(layerName).setSkybox(new Pair<>(model.getStringID(), instanceObject));
	}
}
