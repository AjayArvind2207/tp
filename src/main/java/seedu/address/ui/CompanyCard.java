package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.company.Company;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Company}.
 */
public class CompanyCard extends UiPart<Region> {

    private static final String FXML = "CompanyListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Company company;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;

    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private CheckBox applicationStatusCheckBox;

    @FXML
    private Label period;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CompanyCard(Company company, int displayedIndex) {
        super(FXML);
        this.company = company;
        id.setText(displayedIndex + ". ");
        name.setText(company.getName().fullName);
        setPhone();
        setPeriod();
        email.setText(company.getEmail().value);
        company.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.capitalise())));
        // Solution below inspired by
        // https://stackoverflow.com/questions/37785689/javafx-listener-to-check-for-a-boolean-value
        applicationStatusCheckBox.selectedProperty().bind(company.checkboxIsMarked());
        // Disable the checkbox to make it unclickable
        applicationStatusCheckBox.setDisable(true);

    }

    public void setPhone() {
        phone.setText(company.getPhone().toString());
    }

    public void setPeriod() {
        if (!company.getStartDate().isDatePresent()
                || !company.getEndDate().isDatePresent()) {
            period.setText("");
        } else {
            period.setText(company.getStartDate().toString() + " to " + company.getEndDate().toString());
        }
    }
}
