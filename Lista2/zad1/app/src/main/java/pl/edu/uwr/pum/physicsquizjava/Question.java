package pl.edu.uwr.pum.physicsquizjava;

public class Question {
    private int textId;
    private boolean answer;
    private boolean checked;

    public Question(int textId, boolean answer, boolean checked) {
        this.textId = textId;
        this.answer = answer;
        this.checked = checked;
    }

    public int getTextId() {
        return textId;
    }

    public boolean isAnswer() {
        return answer;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean newChecked){this.checked = newChecked;}

}
