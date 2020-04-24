package arknights.entity.model;

import arknights.entity.enemy.EnemyBase;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

import java.util.List;
public class WomanModel<T extends LivingEntity> extends HumanModel<T> {
    private List<ModelRenderer> field_228286_w_ = Lists.newArrayList();
    private final ModelRenderer naizi;
    public WomanModel(float modelSize) {
        super(modelSize);
        this.naizi = new ModelRenderer(this,12, 23);
        naizi.rotateAngleX=-1.8326F;
        naizi.rotateAngleY=0.0F;
        naizi.rotateAngleZ=0.0F;
        naizi.func_228301_a_(-3.0F, -1.5F, -1.5F, 6F, 3F, 3F, modelSize+0.3F);
        naizi.setRotationPoint(0.0F, 3.5F, -0.5F);
    }
    public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.func_225597_a_(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        this.naizi.copyModelAngles(this.bipedBody);
    }
    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return Iterables.concat(super.func_225600_b_(), ImmutableList.of(this.naizi));
    }
}
