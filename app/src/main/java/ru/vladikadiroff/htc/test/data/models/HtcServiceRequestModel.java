package ru.vladikadiroff.htc.test.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HtcServiceRequestModel {

    @SerializedName("company")
    public CompanyRequestModel company;

    public static class CompanyRequestModel {
        @SerializedName("name")
        public String name;
        @SerializedName("age")
        public String age = null;
        @SerializedName("competences")
        public List<String> competences = null;
        @SerializedName("employees")
        public List<EmployeeRequestModel> employees = null;

    }

    public static class EmployeeRequestModel {
        @SerializedName("name")
        public String name = null;
        @SerializedName("phone_number")
        public String phone = null;
        @SerializedName("skills")
        public List<String> skills = null;
    }

}
