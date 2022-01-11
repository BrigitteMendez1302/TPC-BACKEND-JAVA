package hellocucumber.stepdefinition;

import com.acme.tpc_backend.domain.model.Career;
import com.acme.tpc_backend.domain.model.Lesson;
import com.acme.tpc_backend.domain.repository.LessonRepository;
import com.acme.tpc_backend.domain.service.LessonService;
import com.acme.tpc_backend.service.LessonServiceImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ht.Le;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class get_lessons_in_range {

    @Autowired
    private LessonService _lessonService;
    @MockBean
    private static LessonRepository _lessonRepository;
    private Page<Lesson> lessons;
    List<Lesson> lessonList;
    int items;
    @Given("the tutor wants to list lessons according to a range of dates")
    public void theTutorWantsToListLessonsAccordingToARangeOfDates() {
        Lesson lesson = new Lesson();
        Lesson lesson1 = new Lesson();
        Lesson lesson2 = new Lesson();
        lessonList = new LinkedList<>();
        lessonList.add(lesson);
        lessonList.add(lesson1);
        lessonList.add(lesson2);

        lessons = new PageImpl<>(lessonList);
    }

    @When("the tutor enters the params start {string}, end {string}")
    public void theTutorEntersTheParamsStartEnd(String arg0, String arg1) {
        Date s;
        Date f;
        try{
            s = new SimpleDateFormat("dd-MM-yyyy").parse(arg0);
            f = new SimpleDateFormat("dd-MM-yyyy").parse(arg1);
        }
        catch (ParseException e){
            throw new IllegalArgumentException("mal formato");
        }
        when(_lessonRepository.getLessonsInRange(s, f))
                .thenReturn(lessonList);
        _lessonService = new LessonServiceImpl();

        items = _lessonService.getAllInRange(s, f).size();

    }

    @Then("the result for this scenario should be the number of lessons in this range {int}")
    public void theResultForThisScenarioShouldBeTheNumberOfLessonsInThisRange(int arg0) {
        Assert.assertEquals(items, arg0);
    }

    @And("any lesson is returned start {string}, end {string}")
    public void anyLessonIsReturnedStartEnd(String arg0, String arg1) {
        lessonList = new LinkedList<>();
        lessons = new PageImpl<>(lessonList);
        Date s;
        Date f;
        try{
            s = new SimpleDateFormat("dd-MM-yyyy").parse(arg0);
            f = new SimpleDateFormat("dd-MM-yyyy").parse(arg1);
        }
        catch (ParseException e){
            throw new IllegalArgumentException("mal formato");
        }
        when(_lessonRepository.getLessonsInRange(s, f))
                .thenReturn(lessonList);
        _lessonService = new LessonServiceImpl();
        items = _lessonService.getAllInRange(s, f).size();
    }

    @Then("the message that returns for this scenario should return {int}")
    public void theMessageThatReturnsForThisScenarioShouldComeWithAnError(int num) {
        Assert.assertEquals(items, num);
    }
}
