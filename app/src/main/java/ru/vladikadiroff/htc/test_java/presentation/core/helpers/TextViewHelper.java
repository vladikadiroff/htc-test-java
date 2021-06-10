package ru.vladikadiroff.htc.test_java.presentation.core.helpers;

import android.view.View;
import android.widget.TextView;

public class TextViewHelper {

    public static void setTextOrGone(TextView textView, String text) {
        if (text == null || text.isEmpty()) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }

}
