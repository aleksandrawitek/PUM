package pl.edu.uwr.pum.recyclerviewwordlistjava;

import java.util.Date;
import java.util.UUID;


public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public void setId(UUID Id){
        this.mId = Id;}

    public void setTitle(String Title){
        this.mTitle = Title;}

    public void setDate(Date Date){
        this.mDate = Date;}

    public void setSolved(boolean Solved){
        this.mSolved = Solved;}

    public boolean getSolved(){
        return this.mSolved;}


    public UUID getId() {
        return mId;}

    public String getTitle(){
        return mTitle;}

    public Date getDate(){
        return mDate;}

}