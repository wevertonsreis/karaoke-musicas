package br.com.karaokemusicas.listener;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import br.com.karaokemusicas.fragment.OpcoesBottomSheetFragment;

public class MaisOpcoesListener implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private OpcoesBottomSheetFragment opcoesBottomSheetFragment;

    public MaisOpcoesListener(FragmentManager fragmentManager, OpcoesBottomSheetFragment opcoesBottomSheetFragment) {
        this.fragmentManager = fragmentManager;
        this.opcoesBottomSheetFragment = opcoesBottomSheetFragment;
    }

    @Override
    public void onClick(View v) {
        opcoesBottomSheetFragment.show(fragmentManager, OpcoesBottomSheetFragment.TAG);
    }

}