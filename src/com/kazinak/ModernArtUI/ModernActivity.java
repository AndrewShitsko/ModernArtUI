package com.kazinak.ModernArtUI;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class ModernActivity extends Activity {

    private SeekBar seekBar = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final View blueRectangle = findViewById(R.id.blue_rectangle);
        final View pinkRectangle = findViewById(R.id.pink_rectangle);
        final View redRectangle = findViewById(R.id.red_rectangle);
        final View darkBlueRectangle = findViewById(R.id.dark_blue_rectangle);

        final int originalBlue = ((ColorDrawable) blueRectangle.getBackground()).getColor();
        final int originalPink = ((ColorDrawable) pinkRectangle.getBackground()).getColor();
        final int originalRed = ((ColorDrawable) redRectangle.getBackground()).getColor();
        final int originalDarkBlue = ((ColorDrawable) darkBlueRectangle.getBackground()).getColor();

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                onSetBackgroundColor(blueRectangle, originalBlue);
                onSetBackgroundColor(pinkRectangle, originalPink);
                onSetBackgroundColor(redRectangle, originalRed);
                onSetBackgroundColor(darkBlueRectangle, originalDarkBlue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            private void onSetBackgroundColor(View rectangle, int originalColor) {
                float[] hsvColor = new float[3];
                Color.colorToHSV(originalColor, hsvColor);
                hsvColor[0] = hsvColor[0] + progressChanged;
                hsvColor[0] = hsvColor[0] % 360;
                rectangle.setBackgroundColor(Color.HSVToColor(Color.alpha(originalColor), hsvColor));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_more_information) {
            MoreInformationDialog dialog = new MoreInformationDialog(ModernActivity.this);
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
