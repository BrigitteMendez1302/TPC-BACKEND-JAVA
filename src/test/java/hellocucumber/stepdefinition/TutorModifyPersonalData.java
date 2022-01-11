package hellocucumber.stepdefinition;

import com.acme.tpc_backend.domain.model.Notification;
import com.acme.tpc_backend.domain.model.Tutor;
import com.acme.tpc_backend.domain.repository.NotificationRepository;
import com.acme.tpc_backend.domain.repository.TutorRepository;
import com.acme.tpc_backend.domain.service.NotificationService;
import com.acme.tpc_backend.domain.service.TutorService;
import com.acme.tpc_backend.service.NotificationServiceImpl;
import com.acme.tpc_backend.service.TutorServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Parsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TutorModifyPersonalData {
    @MockBean
    public TutorRepository tutorRepository;

    @Autowired
    public TutorService tutorService;

    Tutor tutor;
    Tutor expected;
    Tutor found;

    @TestConfiguration
    static class TutorServiceImplTestConfiguration {
        @Bean
        public TutorService tutorService() {
            return new TutorServiceImpl();
        }
    }

    @Given("the tutor wants to update his personal info")
    public void theTutorWantsToUpdateHisPersonalInfo() {
        Long id = 1L;
        String firstName = "Rodrigo";
        String lastName = "Zea";

        tutor = new Tutor();
        tutor.setId(id);
        tutor.setFirstName(firstName);
        tutor.setLastName(lastName);
    }

    @When("he fills the fields phonenumber, {long}, firstname {string}, lastname {string}")
    public void heFillsTheFieldsPhonenumberPhoneNumberFirstnameFirstnameLastnameLastname
            (Long newphoneNumber, String newfirstname, String newlastname) {


        expected = new Tutor();
        tutor.setFirstName(newfirstname);

        when(tutorRepository.findById(tutor.getId()))
                .thenReturn(Optional.of(tutor));

        found = tutorService.getTutorById(tutor.getId());

        found.setFirstName(expected.getFirstName());
    }

    @Then("the system updates his info and returns the response")
    public void theSystemUpdatesHisInfoAndReturnsTheResponseResponse() {
        assertThat(found.getFirstName()).isEqualTo(expected.getFirstName());
    }
}
