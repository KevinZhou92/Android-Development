package com.jiuzhang.guojing.awesomeresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuzhang.guojing.awesomeresume.model.BasicInfo;
import com.jiuzhang.guojing.awesomeresume.model.Education;
import com.jiuzhang.guojing.awesomeresume.model.Experience;
import com.jiuzhang.guojing.awesomeresume.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_EDUCATION_EDIT = 100;
    private static final int REQ_CODE_EXPERIENCE_EDIT = 101;

    private BasicInfo basicInfo;
    private List<Education> educations;
    private List<Experience> experiences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fakeData();
        setupUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (resultCode == Activity.RESULT_OK) {
           switch (requestCode) {
               case REQ_CODE_EDUCATION_EDIT:
                   Education result = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
                   updateEducation(result);
                   break;
               case REQ_CODE_EXPERIENCE_EDIT:
                   Experience experience = data.getParcelableExtra(ExperienceEditActivity.KEY_EXPERIENCE);
                   updateExperience(experience);
                   break;
           }
       }
    }
    public void updateExperience(Experience experience) {
        boolean found = false;
        for (int i = 0; i < experiences.size(); i++) {
            Experience e = experiences.get(i);
            if (TextUtils.equals(e.id, experience.id)) {
                experiences.set(i, experience);
                found = true;
                break;
            }
        }

        if (!found) {
            experiences.add(experience);
        }
        setupExperiences();
    }

    public void updateEducation(Education education) {
        boolean found = false;
        for (int i = 0; i < educations.size(); ++i) {
            Education e = educations.get(i);
            if (TextUtils.equals(e.id, education.id)) {
                found = true;
                educations.set(i, education);
                break;
            }
        }

        if (!found) {
            educations.add(education);
        }
        setupEducations();
    }

    private void setupUI() {
        setContentView(R.layout.activity_main);

        setupBasicInfoUI();
        setupEducations();
        setupExperiences();
    }

    private void setupBasicInfoUI() {
        ((TextView) findViewById(R.id.name)).setText(basicInfo.name);
        ((TextView) findViewById(R.id.email)).setText(basicInfo.email);
    }


    private void setupEducations() {
        LinearLayout educationsContainer = (LinearLayout) findViewById(R.id.educations);
        educationsContainer.removeAllViews();

        for (Education education : educations) {
            educationsContainer.addView(getEducationView(education));
        }
        findViewById(R.id.add_education_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
    }

    private View getEducationView(final Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        String dateString = DateUtils.dateToString(education.startDate) + "~" + DateUtils.dateToString(education.endDate);
        ((TextView) view.findViewById(R.id.education_school)).setText(education.school + "(" + dateString + ")");
        ((TextView) view.findViewById(R.id.education_courses)).setText(formatItems(education.courses));
        view.findViewById(R.id.edit_education_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDUCATION_EDIT);
            }
        });
        return view;
    }


    private void setupExperiences() {
        LinearLayout experienceContainer = (LinearLayout) findViewById(R.id.experiences);
        experienceContainer.removeAllViews();

        for (Experience experience : experiences) {
            experienceContainer.addView(getExperienceView(experience));
        }

        findViewById(R.id.add_experience_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE_EDIT);
            }
        });
    }

    private View getExperienceView(final Experience experience) {
        View view = getLayoutInflater().inflate(R.layout.experience_item, null);
        String dateString = DateUtils.dateToString(experience.startDate) + "~" + DateUtils.dateToString(experience.endDate);
        ((TextView) view.findViewById(R.id.experience_company)).setText(experience.company + "(" + dateString + ")");
        ((TextView) view.findViewById(R.id.experience_details)).setText(formatItems(experience.details));
        view.findViewById(R.id.edit_experience_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExperienceEditActivity.class);
                intent.putExtra(ExperienceEditActivity.KEY_EXPERIENCE, experience);
                startActivityForResult(intent, REQ_CODE_EXPERIENCE_EDIT);
            }
        });
        return view;
    }

    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.name = "Jing Guo";
        basicInfo.email = "guojing@jiuzhang.com";

        Education education1 = new Education();
        Education education2 = new Education();

        education1.school = "THU";
        education1.major = "Computer Science";
        education1.startDate = DateUtils.stringToDate("09/2013");
        education1.endDate = DateUtils.stringToDate("12/2015");
        education1.courses = new ArrayList<>();
        education1.courses.add("Database");
        education1.courses.add("Algorithms");
        education1.courses.add("Networks");


        education2.school = "THU";
        education2.major = "Computer Science";
        education2.startDate = DateUtils.stringToDate("09/2013");
        education2.endDate = DateUtils.stringToDate("12/2015");
        education2.courses = new ArrayList<>();
        education2.courses.add("Database");
        education2.courses.add("Algorithms");
        education2.courses.add("Networks");

        educations = new ArrayList<>();
        educations.add(education1);
        educations.add(education2);

        Experience experience = new Experience();
        experience.company = "Google";
        experience.title = "Director";
        experience.startDate = DateUtils.stringToDate("09/2013");
        experience.endDate = DateUtils.stringToDate("12/2015");
        experience.details = new ArrayList<>();
        experience.details.add("worked as a leader");

        experiences = new ArrayList<>();
        experiences.add(experience);

    }

    public static String formatItems(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item: items) {
            sb.append(' ').append('-').append(' ').append(item).append('\n');
        }
        // delete the last \n
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
