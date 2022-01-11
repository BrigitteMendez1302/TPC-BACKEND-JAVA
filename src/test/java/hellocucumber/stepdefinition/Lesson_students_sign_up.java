package hellocucumber.stepdefinition;

import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.repository.LessonStudentRepository;
import com.acme.tpc_backend.domain.repository.StudentRepository;
import com.acme.tpc_backend.domain.service.LessonStudentService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class Lesson_students_sign_up {
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private LessonRepository lessonRepository;
    @MockBean
    private LessonStudentRepository lessonStudentRepository;
    @Autowired
    private LessonStudentService lessonStudentService;

    @Given("the Student is created")
    public void theStudentIsCreated() {

    }

    @And("the lesson is created")
    public void theLessonIsCreated() {
    }

    @When("the student signs up for the first time for this lesson")
    public void theStudentSignsUpForTheFirstTimeForThisLesson() {
    }

    @Then("what returns should be {string}")
    public void whatReturnsShouldBe(String arg0) {
    }

    @When("the student signs up for the second time for this lesson")
    public void theStudentSignsUpForTheSecondTimeForThisLesson() {
    }

    @Then("the returned result should be {string}")
    public void theReturnedResultShouldBe(String arg0) {
    }

    @When("the student signs up for the a lesson that is full")
    public void theStudentSignsUpForTheALessonThatIsFull() {
    }

    @Then("the result for this operation should be {string}")
    public void theResultForThisOperationShouldBe(String arg0) {
    }
}
