package org.example;

import org.example.Pages.QualificationsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QualificationTests extends SetUp {

    QualificationsPage qualifications;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        qualifications = new QualificationsPage();
    }

    @Test(priority = 1)
    public void testWorkExperienceCRUD() {
        qualifications.loginAndNavigateToQualifications();

        String company = "Tech Corp";
        qualifications.addWorkExperience(company, "QA Engineer", "2020-01-01", "2023-01-01", "Automated Testing");
        qualifications.assertRecordAdded("Work Experience", company);

        String newCompany = "Tech Corp Updated";
        qualifications.updateWorkExperience(company, newCompany, "Senior QA");
        qualifications.assertRecordAdded("Work Experience", newCompany);

        qualifications.deleteWorkExperience(newCompany);
        qualifications.assertRecordDeleted("Work Experience", newCompany);
    }

    @Test(priority = 2)
    public void testEducationCRUD() {
        qualifications.loginAndNavigateToQualifications();

        String institute = "Cairo University";
        qualifications.addEducation("Bachelor's Degree", institute, "Computer Engineering", "2019", "3.8", "2015-09-01", "2019-06-01");
        qualifications.assertRecordAdded("Education", institute);

        String newInstitute = "Cairo University Updated";
        qualifications.updateEducation(institute, newInstitute, "4.0");
        qualifications.assertRecordAdded("Education", newInstitute);

        qualifications.deleteEducation(newInstitute);
        qualifications.assertRecordDeleted("Education", newInstitute);
    }

    @Test(priority = 3)
    public void testSkillCRUD() {
        qualifications.loginAndNavigateToQualifications();

        String skill = "Java";
        qualifications.addSkill(skill, "5", "Backend Dev");
        qualifications.assertRecordAdded("Skills", skill);

        qualifications.updateSkill(skill, "6");
        qualifications.assertRecordAdded("Skills", "6");
        qualifications.deleteSkill(skill);
        qualifications.assertRecordDeleted("Skills", skill);
    }

    @Test(priority = 4)
    public void testLanguageCRUD() {
        qualifications.loginAndNavigateToQualifications();

        String lang = "English";
        qualifications.addLanguage(lang, "Speaking", "Mother Tongue", "Native");
        qualifications.assertRecordAdded("Languages", lang);

        qualifications.updateLanguage(lang, "Writing");
        qualifications.assertRecordAdded("Languages", "Writing");

        qualifications.deleteLanguage(lang);
        qualifications.assertRecordDeleted("Languages", lang);
    }

    @Test(priority = 5)
    public void testLicenseCRUD() {
        qualifications.loginAndNavigateToQualifications();

        String licenseType = "CISSP";
        String number = "L123456";
        qualifications.addLicense(licenseType, number, "2022-01-01", "2027-01-01");
        qualifications.assertRecordAdded("License", number);

        String newNumber = "L999999";
        qualifications.updateLicense(licenseType, newNumber);
        qualifications.assertRecordAdded("License", newNumber);

        qualifications.deleteLicense(licenseType);
        qualifications.assertRecordDeleted("License", licenseType);
    }
}