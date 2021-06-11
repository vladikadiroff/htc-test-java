package ru.vladikadiroff.htc.test.presentation.core.helpers;

import com.google.android.material.appbar.AppBarLayout;

public class AppbarHelper {

    public static void onAppBarScrollPositionChange(
            AppBarLayout appBar,
            OnScrollPositionChangedListener listener) {
        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            Float scrollPosition =
                    1f - (float) verticalOffset / (float) -appBarLayout.getTotalScrollRange();
            listener.onScrollPositionChanged(scrollPosition);
        });
    }

    public interface OnScrollPositionChangedListener {
        void onScrollPositionChanged(Float scrollPosition);
    }

}
