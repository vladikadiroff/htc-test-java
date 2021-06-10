package ru.vladikadiroff.htc.test_java.presentation.core.helpers;

import android.view.View;

import androidx.annotation.AttrRes;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class ChipGroupHelper {

    public static void replaceChipsByList(ChipGroup chipGroup, List<String> list, @AttrRes int chipAttr) {
        chipGroup.removeAllViews();
        if (list == null || list.isEmpty()) {
            chipGroup.setVisibility(View.GONE);
            return;
        }
        chipGroup.setVisibility(View.VISIBLE);
        for (String chip : list){
            Chip chipView = new Chip(chipGroup.getContext(), null, chipAttr);
            chipView.setText(chip);
            chipGroup.addView(chipView);
        }
    }

}
