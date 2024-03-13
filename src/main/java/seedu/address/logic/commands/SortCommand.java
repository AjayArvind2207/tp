package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Company;

/**
 * Lists all persons in the address book to the user in sorted order by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all persons";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Company> l = model.getAddressBook().getPersonList();
        List<Company> sorted = l.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);
        AddressBook ad = new AddressBook();
        ad.setPersons(sorted);
        model.setAddressBook(ad);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
