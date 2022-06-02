/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.model;

import java.io.Serializable;

/**
 *
 * @author 27769
 */
public class Issue implements Serializable{
    private int issueID;
    private int userid;
    private int zipCode;
    private String typeofissue;
    private String IssueDiscription;
    private String StreetName;
    private String town;
    private String issuePicture;
    private String status;
    private String assignedStatus;

    public Issue() {
    }

    public Issue( int userid,int zipCode, String typeofissue, String IssueDiscription, String StreetName, String town, String issuePicture, String status,String assignedStatus) {
        this.userid = userid;
        this.zipCode = zipCode;
        this.typeofissue = typeofissue;
        this.IssueDiscription = IssueDiscription;
        this.StreetName = StreetName;
        this.town = town;
        this.issuePicture = issuePicture;
        this.status = status;
        this.assignedStatus = assignedStatus;
    }

    public Issue(int userid,int zipCode, String typeofissue, String IssueDiscription, String StreetName, String town, String issuePicture) {
        this.userid = userid;
        this.zipCode = zipCode;
        this.typeofissue = typeofissue;
        this.IssueDiscription = IssueDiscription;
        this.StreetName = StreetName;
        this.town = town;
        this.issuePicture = issuePicture;
    }

    /**
     * @return the issueID
     */
    public int getIssueID() {
        return issueID;
    }

    /**
     * @param issueID the issueID to set
     */
    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    /**
     * @return the zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the typeofissue
     */
    public String getTypeofissue() {
        return typeofissue;
    }

    /**
     * @param typeofissue the typeofissue to set
     */
    public void setTypeofissue(String typeofissue) {
        this.typeofissue = typeofissue;
    }

    /**
     * @return the IssueDiscription
     */
    public String getIssueDiscription() {
        return IssueDiscription;
    }

    /**
     * @param IssueDiscription the IssueDiscription to set
     */
    public void setIssueDiscription(String IssueDiscription) {
        this.IssueDiscription = IssueDiscription;
    }

    /**
     * @return the StreetName
     */
    public String getStreetName() {
        return StreetName;
    }

    /**
     * @param StreetName the StreetName to set
     */
    public void setStreetName(String StreetName) {
        this.StreetName = StreetName;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the issuePicture
     */
    public String getIssuePicture() {
        return issuePicture;
    }

    /**
     * @param issuePicture the issuePicture to set
     */
    public void setIssuePicture(String issuePicture) {
        this.issuePicture = issuePicture;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the assignedStatus
     */
    public String getAssignedStatus() {
        return assignedStatus;
    }

    /**
     * @param assignedStatus the assignedStatus to set
     */
    public void setAssignedStatus(String assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    /**
     * @return the userid
     */
    public int getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    
    
    
}
