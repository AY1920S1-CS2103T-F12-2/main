package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.VisitReport;
import seedu.address.ui.VisitRecordWindow;

/**
 * Changes the visitList of an existing person in the address book.
 */
public class AddVisitCommand extends Command {
    public static final String COMMAND_WORD = "addVisit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the visitation record of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing record will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_VISIT + "[DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_VISIT + "31/12/2019";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added visit to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed visit from Person: %1$s";

    private final Index index;
    private final String date;

    /**
     * @param index of the person in the filtered person list to edit the visitList
     * @param date of the person to be updated to
     */
    public AddVisitCommand(Index index, String date) {
        requireAllNonNull(index, date);

        this.index = index;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(personToEdit), false, true, false, index.getOneBased(), date);
    }

    /**
     * Generates a command execution success message based on whether the visitList is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !date.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddVisitCommand)) {
            return false;
        }

        // state check
        AddVisitCommand e = (AddVisitCommand) other;
        return index.equals(e.index)
                && date.equals(e.date);
    }
}