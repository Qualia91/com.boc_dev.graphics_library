package com.nick.wood.graphics_library.objects;

import com.nick.wood.maths.objects.matrix.Matrix4f;

public class Camera {

	private final String name;
	private final float near;
	private final float far;
	private final ProjectionType projectionType;
	private CameraType cameraType;
	private int width;
	private int height;
	private float fov;
	private Matrix4f projectionMatrix = null;

	public Camera(String name, CameraType cameraType, ProjectionType projectionType, int width, int height, float fov, float near, float far) {
		this.name = name;
		this.cameraType = cameraType;
		this.width = width;
		this.height = height;
		this.fov = fov;
		this.near = near;
		this.far = far;
		this.projectionType = projectionType;
		if (projectionType.equals(ProjectionType.PERSPECTIVE)) {
			this.projectionMatrix = Matrix4f.PerspectiveProjection((float) width / (float) height, fov, near, far);
		} else {
			this.projectionMatrix = Matrix4f.OrthographicProjection(width, height, near, far);
		}
	}

	public Camera(String name, float fov, float near, float far, ProjectionType projectionType) {
		this.name = name;
		this.fov = fov;
		this.near = near;
		this.far = far;
		this.projectionType = projectionType;
		this.cameraType = CameraType.PRIMARY;
	}

	public float getNear() {
		return near;
	}

	public float getFar() {
		return far;
	}

	public float getFov() {
		return fov;
	}

	public CameraType getCameraType() {
		return cameraType;
	}

	public void setCameraType(CameraType cameraType) {
		this.cameraType = cameraType;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public void setProjectionMatrix(Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}

	public void updateProjectionMatrix(int screenWidth, int screenHeight) {
		this.width = screenWidth;
		this.height = screenHeight;
		if (projectionMatrix == null) {
			if (projectionType.equals(ProjectionType.PERSPECTIVE)) {
				projectionMatrix = Matrix4f.PerspectiveProjection((float) width / (float) height, fov, near, far);
			} else {
				this.projectionMatrix = Matrix4f.OrthographicProjection(width, height, near, far);
			}

		} else {
			if (projectionType.equals(ProjectionType.PERSPECTIVE)) {
				projectionMatrix = projectionMatrix.updatePerspectiveProjection((float) width / (float) height, fov);
			} else {
				this.projectionMatrix = Matrix4f.OrthographicProjection(width, height, near, far);
			}
		}
	}

	public String getName() {
		return name;
	}
}
