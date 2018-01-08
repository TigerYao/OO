package com.taiwan.oomatcher.entity.task;

/**
 * 作 者: huanghuojun
 * 描 述:
 * 版 本:
 * 创建日期: 2017/12/28 15:37
 */

public class TaskCompanionRequirment {
    private int id;
    private String billNo;
    private int billId;
    private int aUserId;

    private String genderCode;

    private String languageCode;

    private int minAge;

    private int maxAge;

    private int minHeight;

    private int maxHeight;

    private int minWeight;

    private int maxWeight;

    private String hairColorCode;

    private String hairColor;

    private String eyeColorCode;

    private String eyeColor;

    private String occupationCode;

    private String occupation;

    private String nationalityCode;

    private String nationality;

    private int localSevice;

    private int abroadSevice;

    private int authFlag;

    public int getAbroadSevice() {
        return abroadSevice;
    }

    public void setAbroadSevice(int abroadSevice) {
        this.abroadSevice = abroadSevice;
    }

    public int getaUserId() {
        return aUserId;
    }

    public void setaUserId(int aUserId) {
        this.aUserId = aUserId;
    }

    public int getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(int authFlag) {
        this.authFlag = authFlag;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColorCode() {
        return eyeColorCode;
    }

    public void setEyeColorCode(String eyeColorCode) {
        this.eyeColorCode = eyeColorCode;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairColorCode() {
        return hairColorCode;
    }

    public void setHairColorCode(String hairColorCode) {
        this.hairColorCode = hairColorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getLocalSevice() {
        return localSevice;
    }

    public void setLocalSevice(int localSevice) {
        this.localSevice = localSevice;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }
}
