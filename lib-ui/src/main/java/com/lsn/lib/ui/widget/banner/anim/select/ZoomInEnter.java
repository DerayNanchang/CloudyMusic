package com.lsn.lib.ui.widget.banner.anim.select;

import android.animation.ObjectAnimator;
import android.view.View;
import com.lsn.lib.ui.widget.banner.anim.BaseAnimator;

public class ZoomInEnter extends BaseAnimator {

    public ZoomInEnter() {
        this.mDuration = 200;
    }

    @Override
    public void setAnimation(View view) {
        this.mAnimatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.0F, 1.5F),
                ObjectAnimator.ofFloat(view, "scaleY", 1.0F, 1.5F));
    }
}
