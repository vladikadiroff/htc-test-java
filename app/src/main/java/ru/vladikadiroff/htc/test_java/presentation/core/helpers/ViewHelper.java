package ru.vladikadiroff.htc.test_java.presentation.core.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

public class ViewHelper {
    public static View createView(@LayoutRes int layout, ViewGroup root) {
        return LayoutInflater.from(root.getContext()).inflate(layout, root, false);
    }
}
