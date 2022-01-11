package hellocucumber.stepdefinition;

import com.acme.tpc_backend.controller.*;
import com.acme.tpc_backend.domain.model.*;
import com.acme.tpc_backend.domain.repository.*;
import com.acme.tpc_backend.domain.service.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

public class StepDefinitionsUS08 {
    User user = new User();
    List<Suggestion> suggestions;

    @MockBean
    SuggestionRepository suggestionRepository;

    @Autowired
    SuggestionService suggestionService;
    SuggestionsController suggestionsController;
    Page<Suggestion> suggestionPage;
    Pageable pageable;


    @Given("the coodinator whishes to see the list of suggestions")
    public void the_coodinator_whishes_to_see_the_list_of_suggestions() {
        Suggestion suggestion = new Suggestion();
        String message = "Quiero que hayan mas horario de tutoria";
        suggestion.setId(1L).setMessage(message);
        Suggestion suggestion1 = new Suggestion();
        String message1 = "Quiero que hayan mas horarios de talleres";
        suggestion1.setId(2L).setMessage(message1);
        suggestions  = new ArrayList<>();
        suggestionPage = new PageImpl<>(suggestions);
        suggestions.add(suggestion);
        suggestions.add(suggestion1);
        //when(suggestionRepository.findAll()).thenReturn(suggestions);

    }

    @When("the coordinator see the list")
    public void the_coordinator_see_the_list() {

        user.setSuggestions(suggestions);
        user.setId(12L);

    }

    @Then("the system display a list of suggestions")
    public void the_system_display_a_list_of_suggestions() {
        when(suggestionRepository.findAll()).thenReturn(suggestions);
        Page<Suggestion> save = suggestionService.getAllSuggestions(pageable);
        assertThat(save).isEqualTo(suggestions);
    }

    @When("the coordinator see the list of suggestions and there are no results")
    public void the_coordinator_see_the_list_of_suggestions_and_there_are_no_results() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



    @Then("x system displays the message {string}")
    public void x_system_displays_the_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}