package com.jiuzhang.guojing.awesomeresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.jiuzhang.guojing.awesomeresume.model.Experience;
import com.jiuzhang.guojing.awesomeresume.util.DateUtils;

import java.util.Arrays;

/**
 * Created by kevinzhou on 8/29/17.
 */

public class ExperienceEditActivity extends AppCompatActivity {

    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_ID = "experience_id";

    private Experience experience;
    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_experience_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        experience = getIntent().getParcelableExtra(KEY_EXPERIENCE);
        if (experience != null) {
            setupUI(experience);
        }

        setTitle(experience == null ? "New Experience" : "Edit Experience");

    }

    public void setupUI(Experience experience) {
        ((TextView) findViewById(R.id.experience_edit_company)).setText(experience.company);
        ((TextView) findViewById(R.id.experience_edit_title)).setText(experience.title);
        ((TextView) findViewById(R.id.experience_start_date)).setText(DateUtils.dateToString(experience.startDate));
        ((TextView) findViewById(R.id.experience_end_date)).setText(DateUtils.dateToString(experience.endDate));
        ((TextView) findViewById(R.id.experience_edit_details)).setText(TextUtils.join("\n", experience.details));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        if (experience == null) {
            experience = new Experience();
        }

        experience.company = ((EditText) findViewById(R.id.experience_edit_company)).getText().toString();
        experience.title = ((EditText) findViewById(R.id.experience_edit_title)).getText().toString();
        experience.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_start_date)).getText().toString());
        experience.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_end_date)).getText().toString());
        experience.details = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.experience_edit_details)).getText().toString(), "\n"));

        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXPERIENCE, experience);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
