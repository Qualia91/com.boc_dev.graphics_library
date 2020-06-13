package com.nick.wood.graphics_library.objects.mesh_objects;

import com.nick.wood.graphics_library.Material;
import com.nick.wood.maths.objects.srt.Transform;

public class MeshBuilder {

	private MeshType meshType = MeshType.SPHERE;
	private boolean invertedNormals = false;
	private String texture = "/textures/white.png";
	private String normalTexture = null;
	private Transform transformation = Transform.Identity;
	private int triangleNumber = 5;
	private Material material;
	private String text = "DEFAULT_STRING";
	private String fontFile = "/font/gothic.png";
	private int rowNum = 16;
	private int colNum = 16;
	private int waterSquareWidth = 100;
	private int waterHeight = 0;
	private String modelFile = "D:\\Software\\Programming\\projects\\Java\\GraphicsLibrary\\src\\main\\resources\\models\\sphere.obj";
	private float[][] terrainHeightMap = new float[][] {
			{0, 0},
			{0, 0},
	};
	private double cellSpace = 1;
	private boolean flag = false;

	public MeshObject build() {

		if (material == null) {
			material = new Material(texture);

			if (normalTexture != null) {
				material.setNormalMap(normalTexture);
			}
		}

		MeshObject meshObject = switch (meshType) {
			case SPHERE -> new SphereMesh(triangleNumber, material, invertedNormals, transformation);
			case CUBOID -> new CubeMesh(material, invertedNormals, transformation);
			case MODEL -> new ModelMesh(modelFile, material, invertedNormals, transformation);
			case SQUARE -> new Square(material, transformation);
			case TEXT -> new TextItem(text, fontFile, rowNum, colNum, transformation);
			case TERRAIN -> new Terrain(terrainHeightMap, material, cellSpace);
			case WATER -> new Terrain(waterSquareWidth, waterHeight, material, cellSpace);
			case POINT -> new Point(transformation, material);
		};

		meshObject.setTextureViaFBO(flag);

		return meshObject;
	}

	public MeshBuilder setWaterSquareWidth(int waterSquareWidth) {
		this.waterSquareWidth = waterSquareWidth;
		return this;
	}
	public MeshBuilder setWaterHeight(int waterHeight) {
		this.waterHeight = waterHeight;
		return this;
	}
	public MeshBuilder setModelFile(String modelFile) {
		this.modelFile = modelFile;
		return this;
	}
	public MeshBuilder setText(String text) {
		this.text = text;
		return this;
	};
	public MeshBuilder setFontFile(String fontFile) {
		this.fontFile = fontFile;
		return this;
	};
	public MeshBuilder setRowNumber(int rowNum) {
		this.rowNum = rowNum;
		return this;
	};
	public MeshBuilder setColNumber(int colNum) {
		this.colNum = colNum;
		return this;
	};
	public MeshBuilder setMeshType(MeshType meshType) {
		this.meshType = meshType;
		return this;
	};
	public MeshBuilder setInvertedNormals(boolean invertedNormals) {
		this.invertedNormals = invertedNormals;
		return this;
	};
	public MeshBuilder setTexture(String texture) {
		this.texture = texture;
		return this;
	};
	public MeshBuilder setNormalTexture(String normalTexture) {
		this.normalTexture = normalTexture;
		return this;
	};
	public MeshBuilder setTransform(Transform transformation) {
		this.transformation = transformation;
		return this;
	};
	public MeshBuilder setTriangleNumber(int triangleNumber) {
		this.triangleNumber = triangleNumber;
		return this;
	};
	public MeshBuilder setMaterial(Material material) {
		this.material = material;
		return this;
	};
	public MeshBuilder setTerrainHeightMap(float[][] terrainHeightMap) {
		this.terrainHeightMap = terrainHeightMap;
		return this;
	}
	public MeshBuilder setCellSpace(double cellSpace) {
		this.cellSpace = cellSpace;
		return this;
	}

	public MeshBuilder setTextureViaFbo(boolean flag) {
		this.flag = flag;
		return this;
	}
}
