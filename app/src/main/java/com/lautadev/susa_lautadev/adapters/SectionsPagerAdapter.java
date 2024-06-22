package com.lautadev.susa_lautadev.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lautadev.susa_lautadev.fragments.BeneficioFragment;
import com.lautadev.susa_lautadev.fragments.CuentaFragment;
import com.lautadev.susa_lautadev.fragments.UbicacionFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CuentaFragment();
            case 1:
                return new UbicacionFragment();
            case 2:
                return new BeneficioFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Tres tabs
    }
}
