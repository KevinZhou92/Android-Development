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

import com.jiuzhang.guojing.awesomeresume.model.Education;
import com.jiuzhang.guojing.awesomeresume.util.DateUtils;

import java.util.Arrays;

/**
 * Created by kevinzhou on 8/29/17.
 */

public class EducationEditActivity extends AppCompatActivity{

    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION_ID = "education_id";

    private Education education;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        education = getIntent().getParcelableExtra(KEY_EDUCATION);
        if (education != null) {
            setupUI();
        }
        setTitle(education == null ? "New Education" : "Edit Education");
    }

    public void setupUI() {
        ((EditText) findViewById(R.id.education_edit_school)).setText(education.school);
        ((EditText) findViewById(R.id.education_edit_major)).setText(education.major);
        ((EditText) findViewById(R.id.education_start_date)).setText(DateUtils.dateToString(education.startDate));
        ((EditText) findViewById(R.id.education_start_date)).setText(DateUtils.dateToString(education.endDate));
        ((EditText) findViewById(R.id.education_edit_courses)).setText(TextUtils.join("\n", education.courses));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveAndExit() {
        if (education == null) {
            education = new Education();
        }

        education.school = ((EditText) findViewById(R.id.education_edit_school)).getText().toString();
        education.major = ((EditText) findViewById(R.id.education_edit_major)).getText().toString();
        education.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.education_start_date)).getText().toString());
        education.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.education_end_date)).getText().toString());
        education.courses = Arrays.asList(TextUtils.split(((EditText) findViewById(R.id.education_edit_courses)).getText().toString(), "\n"));

        Intent resultIntent = new Intent(); // hashmap, key-value pair
        resultIntent.putExtra(KEY_EDUCATION, education);
        setResult(Activity.RESULT_OK, resultIntent); // serializable
        finish();
    }
}
