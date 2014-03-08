package org.powerbot.script.rs3;

import java.lang.ref.WeakReference;

import org.powerbot.bot.rs3.client.AbstractModel;
import org.powerbot.bot.rs3.client.RSInteractable;
import org.powerbot.bot.rs3.client.RSInteractableData;
import org.powerbot.bot.rs3.client.RSInteractableLocation;

public class RenderableModel extends Model {
	private final WeakReference<RSInteractable> interactable;

	public RenderableModel(final ClientContext ctx, final AbstractModel model, final RSInteractable interactable) {
		super(ctx, model);
		this.interactable = new WeakReference<RSInteractable>(interactable);
	}

	@Override
	public int getX() {
		final RSInteractable interactable = this.interactable.get();
		final RSInteractableData data = interactable != null ? interactable.getData() : null;
		final RSInteractableLocation location = data != null ? data.getLocation() : null;
		if (location != null) {
			return (int) location.getX();
		}
		return -1;
	}

	@Override
	public int getY() {
		final RSInteractable interactable = this.interactable.get();
		final RSInteractableData data = interactable != null ? interactable.getData() : null;
		final RSInteractableLocation location = data != null ? data.getLocation() : null;
		if (location != null) {
			return (int) location.getY();
		}
		return -1;
	}

	@Override
	public byte getPlane() {
		final RSInteractable interactable = this.interactable.get();
		return interactable != null ? interactable.getPlane() : -1;
	}


	@Override
	public void update() {
	}
}