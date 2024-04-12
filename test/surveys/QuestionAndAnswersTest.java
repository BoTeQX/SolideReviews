package surveys;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionAndAnswersTest {

    @Test
    public void getQuestion() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        assertEquals("question", questionAndAnswers.getQuestion());
    }

    @Test
    public void changeQuestion() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.changeQuestion("newQuestion");
        assertEquals("newQuestion", questionAndAnswers.getQuestion());
    }

    @Test
    public void addAnswer() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.addAnswer("answer");
        assertEquals("answer", questionAndAnswers.getAnswers().get(0));
    }

    @Test
    public void getAnswers() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.addAnswer("answer");
        assertEquals("answer", questionAndAnswers.getAnswers().get(0));
    }

    @Test
    public void isMultipleChoice() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        assertTrue(questionAndAnswers.isMultipleChoice());
    }

    @Test
    public void setMultipleChoice() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.setMultipleChoice(false);
        assertFalse(questionAndAnswers.isMultipleChoice());
    }

    @Test
    public void addChoice() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.addChoice("choice");
        assertEquals("choice", questionAndAnswers.getChoices().get(0));
    }

    @Test
    public void getChoices() {
        QuestionAndAnswers questionAndAnswers = new QuestionAndAnswers("question", true);
        questionAndAnswers.addChoice("choice");
        assertEquals("choice", questionAndAnswers.getChoices().get(0));
    }
}