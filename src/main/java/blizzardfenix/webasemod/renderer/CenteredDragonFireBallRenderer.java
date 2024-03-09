package blizzardfenix.webasemod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.DragonFireballRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CenteredDragonFireBallRenderer extends DragonFireballRenderer {
	   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(new ResourceLocation("textures/entity/enderdragon/dragon_fireball.png"));

	public CenteredDragonFireBallRenderer(EntityRendererProvider.Context manager) {
		super(manager);
	}

	/**
	 * Adapted from {@link DragonFireballRenderer.render} in order to be able to insert the translations in between.
	 */
	@Override
	public void render(DragonFireball entity, float number1, float number2, PoseStack matrixStack, MultiBufferSource renderBuffer, int number3) {
		matrixStack.pushPose();
		matrixStack.scale(2.0F, 2.0F, 2.0F);
		// Translating up to correct when viewing from the side
		matrixStack.translate(0, 0.25, 0);
		matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		// Translate it along the coordinate system that looks at the camera to correct when viewing from the top or bottom
		matrixStack.translate(0, -0.25, 0);
		Pose matrixstack$entry = matrixStack.last();
		Matrix4f matrix4f = matrixstack$entry.pose();
		Matrix3f matrix3f = matrixstack$entry.normal();
		VertexConsumer ivertexbuilder = renderBuffer.getBuffer(RENDER_TYPE);
		vertex(ivertexbuilder, matrix4f, matrix3f, number3, 0.0F, 0, 0, 1);
		vertex(ivertexbuilder, matrix4f, matrix3f, number3, 1.0F, 0, 1, 1);
		vertex(ivertexbuilder, matrix4f, matrix3f, number3, 1.0F, 1, 1, 0);
		vertex(ivertexbuilder, matrix4f, matrix3f, number3, 0.0F, 1, 0, 0);
		matrixStack.popPose();
		superEntityRender(entity, number1, number2, matrixStack, renderBuffer, number3);
	}

	/**
	 * Adapted from {@link DragonFireballRenderer#vertex} because it is obscured
	 */
	private static void vertex(VertexConsumer vertexBuilder, Matrix4f pose, Matrix3f normal, int number1, float number2, int number3,
			int u, int v) {
		vertexBuilder.vertex(pose, number2 - 0.5F, (float) number3 - 0.25F, 0.0F).color(255, 255, 255, 255)
				.uv((float) u, (float) v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(number1).normal(normal, 0.0F, 1.0F, 0.0F)
				.endVertex();
	}

	/**
	 * Adapted from {@link EntityRenderer#render} because it is obscured by
	 * SpriteRenderer
	 * 
	 * @param entity
	 * @param number1
	 * @param number2
	 * @param matrixStack
	 * @param renderBuffer
	 * @param number3
	 */
	public void superEntityRender(DragonFireball entity, float number1, float number2, PoseStack matrixStack, MultiBufferSource renderBuffer, int number3) {
		net.minecraftforge.client.event.RenderNameTagEvent renderNameplateEvent = new net.minecraftforge.client.event.RenderNameTagEvent(entity,
				entity.getDisplayName(), this, matrixStack, renderBuffer, number3, number2);
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(renderNameplateEvent);
		if (renderNameplateEvent.getResult() != net.minecraftforge.eventbus.api.Event.Result.DENY
				&& (renderNameplateEvent.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW || this.shouldShowName(entity))) {
			this.renderNameTag(entity, renderNameplateEvent.getContent(), matrixStack, renderBuffer, number3);
		}
	}
}
