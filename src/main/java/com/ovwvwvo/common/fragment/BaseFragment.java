package com.ovwvwvo.common.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.ovwvwvo.common.R;
import com.ovwvwvo.common.view.BaseView;

/**
 * Created by ovwvwvo on 17/2/28.
 */

public class BaseFragment extends Fragment implements BaseView {

    protected ProgressDialog progressDialog;
    protected Dialog progressBarDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void showProgressDialog(int message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this.getActivity());
        }
        progressDialog.setMessage(getString(message));
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showProgressBar() {
        if (progressBarDialog == null) {
            progressBarDialog = new Dialog(this.getActivity(), R.style.Theme_Dialog_Progressbar);
            progressBarDialog.setContentView(R.layout.dialog_loading_indicator);
        }
        if (progressBarDialog != null && !progressBarDialog.isShowing()) {
            progressBarDialog.show();
        }
    }

    @Override
    public void dismissProgressBar() {
        if (progressBarDialog != null && progressBarDialog.isShowing()) {
            progressBarDialog.dismiss();
            progressBarDialog = null;
        }
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkErrorMessage() {
        Toast.makeText(getContext(), R.string.msg_network_error, Toast.LENGTH_SHORT).show();
    }
}