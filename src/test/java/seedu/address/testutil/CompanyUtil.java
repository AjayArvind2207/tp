package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.company.Company;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class CompanyUtil {

    /**
     * Returns an add command string for adding the {@code company}.
     */
    public static String getAddCommand(Company company) {
        return AddCommand.COMMAND_WORD + " " + getCompanyDetails(company);
    }

    /**
     * Returns the part of command string for the given {@code company}'s details.
     */
    public static String getCompanyDetails(Company company) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + company.getName().fullName + " ");
        sb.append(PREFIX_PHONE + company.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + company.getEmail().value + " ");
        sb.append(PREFIX_STARTDATE + company.getStartDate().toString() + " ");
        sb.append(PREFIX_ENDDATE + company.getEndDate().toString() + " ");
        company.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.getTagName() + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCompanyDescriptor}'s details.
     */
    public static String getEditCompanyDescriptorDetails(EditCommand.EditCompanyDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getStartDate().ifPresent(startDate -> sb.append(PREFIX_STARTDATE)
                .append(startDate.toString()).append(" "));
        descriptor.getEndDate().ifPresent(endDate -> sb.append(PREFIX_ENDDATE).append(endDate.toString()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.getTagName()).append(" "));
            }
        }
        return sb.toString();
    }
}
