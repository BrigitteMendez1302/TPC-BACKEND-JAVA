package hellocucumber.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class StepDefinitionsUS03 {
    @Given("the student wants to register for a workshop")
    public void the_student_wants_to_register_for_a_workshop() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the student selects search by tutor and chooses one of the tutors")
    public void the_student_selects_search_by_tutor_and_chooses_one_of_the_tutors() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("the student selects search by tutor, chooses one of the tutors and there are not result")
    public void the_student_selects_search_by_tutor_chooses_one_of_the_tutors_and_there_are_not_result() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("the system filters and shows you the workshops available for that tutor.")
    public void the_system_filters_and_shows_you_the_workshops_available_for_that_tutor() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("the system display the message {string}")
    public void the_system_display_the_message_there_are_no_workshops_available_for_this_course(String string ) {
        // Write code here that turns the phrase above into concrete actions

    }
}
