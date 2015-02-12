package com.kazinak.ModernArtUI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class MoreInformationDialog extends Dialog implements View.OnClickListener {
    public Activity activity;

    public MoreInformationDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog);

        TextView visitView = (TextView) findViewById(R.id.visitView);
        TextView notNowView = (TextView) findViewById(R.id.notNowView);

        visitView.setOnClickListener(this);
        notNowView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.visitView:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                activity.startActivity(intent);
                break;
            case R.id.notNowView:
                dismiss();
                break;
            default:
                break;
        }
    }
}
