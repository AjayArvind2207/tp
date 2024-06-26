package seedu.address.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.ReminderSettings;
import seedu.address.model.company.exceptions.CompanyAlreadyMarkedException;
import seedu.address.model.company.exceptions.CompanyAlreadyUnmarkedException;
import seedu.address.model.company.exceptions.CompanyNotFoundException;
import seedu.address.model.company.exceptions.DuplicateCompanyException;

/**
 * A list of companies that enforces uniqueness between its elements and does not allow nulls.
 * A company is considered unique by comparing using {@code Company#isSameCompany(Company)}. As such, adding
 * and updating of companies uses Company#isSameCompany(Company) for equality so as to ensure that the company being
 * added or updated is unique in terms of identity in the UniqueCompanyList. However, the removal of a company uses
 * Company#equals(Object) so as to ensure that the company with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Company#isSameCompany(Company)
 */
public class UniqueCompanyList implements Iterable<Company> {

    private final ObservableList<Company> internalList = FXCollections.observableArrayList();
    private final ObservableList<Company> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent company as the given argument.
     */
    public boolean contains(Company toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCompany);
    }

    /**
     * Adds a company to the list.
     * The company must not already exist in the list.
     */
    public void add(Company toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCompanyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent company from the list.
     * The company must exist in the list.
     */
    public void remove(Company toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CompanyNotFoundException();
        }
    }

    public void setCompany(UniqueCompanyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the list.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the list.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CompanyNotFoundException();
        }

        if (!target.isSameCompany(editedCompany) && contains(editedCompany)) {
            throw new DuplicateCompanyException();
        }

        internalList.set(index, editedCompany);
    }

    /**
     * Replaces the contents of this list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setCompany(List<Company> companies) {
        requireAllNonNull(companies);
        if (!companiesAreUnique(companies)) {
            throw new DuplicateCompanyException();
        }

        internalList.setAll(companies);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Company> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the reminder list as an unmodifiable {@code ObservabeList}
     */
    public ObservableList<Company> asUnmodifiableReminderList(ReminderSettings reminderSettings) {
        long numOfDays = reminderSettings.getNumOfDays();
        List<Company> filteredList = internalList.stream()
                .filter(company-> company.toRemind(numOfDays))
                .collect(Collectors.toList());
        return FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(filteredList));
    }

    @Override
    public Iterator<Company> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueCompanyList)) {
            return false;
        }

        UniqueCompanyList otherUniqueCompanyList = (UniqueCompanyList) other;
        return internalList.equals(otherUniqueCompanyList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code companies} contains only unique companies.
     */
    private boolean companiesAreUnique(List<Company> companies) {
        for (int i = 0; i < companies.size() - 1; i++) {
            for (int j = i + 1; j < companies.size(); j++) {
                if (companies.get(i).isSameCompany(companies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list of companies by their name in ascending order, and then by their start date in ascending order.
     */
    public void sortCompanyListByName() {
        Comparator<Company> compareByName = Comparator.comparing(company -> company.getName().fullName.toLowerCase());
        Comparator<Company> compareByStartDate = Comparator.comparing(company -> company.getStartDate().getDate());
        FXCollections.sort(internalList, compareByName.thenComparing(compareByStartDate));
    }

    /**
     * Sorts the list of companies by their start date in ascending order, and then by their name in ascending order.
     */
    public void sortCompanyListByStartDate() {
        Comparator<Company> compareByStartDate = Comparator.comparing(company -> company.getStartDate().getDate());
        Comparator<Company> compareByName = Comparator.comparing(company -> company.getName().fullName.toLowerCase());
        FXCollections.sort(internalList, compareByStartDate.thenComparing(compareByName));
    }

    /**
     * Sorts the list of companies by their name in ascending order, and then by their start date in ascending order.
     */
    public void sortCompanyListByEndDate() {
        Comparator<Company> compareByStartDate = Comparator.comparing(company -> company.getEndDate().getDate());
        Comparator<Company> compareByName = Comparator.comparing(company -> company.getName().fullName.toLowerCase());
        FXCollections.sort(internalList, compareByStartDate.thenComparing(compareByName));
    }

    /**
     * Marks the given company as applied.
     */
    public void mark(Company target) {
        requireNonNull(target);
        if (!internalList.contains(target)) {
            throw new CompanyNotFoundException();
        } else if (target.isMarked()) {
            throw new CompanyAlreadyMarkedException();
        } else {
            target.mark();
        }
    }

    /**
     * Unmarks the given company as applied.
     */
    public void unmark(Company target) {
        requireNonNull(target);
        if (!internalList.contains(target)) {
            throw new CompanyNotFoundException();
        } else if (!target.isMarked()) {
            throw new CompanyAlreadyUnmarkedException();
        } else {
            target.unmark();
        }
    }

    /**
     * Returns true if the given company is marked.
     */
    public boolean isMarked(Company target) {
        requireNonNull(target);
        if (!internalList.contains(target)) {
            throw new CompanyNotFoundException();
        } else {
            return target.isMarked();
        }
    }
}
