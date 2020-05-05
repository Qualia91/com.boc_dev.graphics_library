package com.nick.wood.graphics_library_3d.input;

import java.util.UUID;

public interface Control {

	UUID getUuid();
	void reset();
	void mouseMove(double dx, double dy, boolean shiftPressed);
	void leftLinear();
	void rightLinear();
	void forwardLinear();
	void backLinear();
	void upLinear();
	void downLinear();
	void leftRoll();
	void rightRoll();
	void upPitch();
	void downPitch();
	void leftYaw();
	void rightYaw();
	void action();
	void setObjectBeingControlled(Object objectBeingControlled);

}
