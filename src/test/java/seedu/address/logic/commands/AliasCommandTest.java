package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AliasTable;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Reminder;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class AliasCommandTest {
    @Test
    public void constructor_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AliasCommand(null, null));
        assertThrows(NullPointerException.class, () -> new AliasCommand("Command", null));
        assertThrows(NullPointerException.class, () -> new AliasCommand(null, "Alias"));
    }

    @Test
    public void execute_correctAlias_aliasSuccessful() throws Exception {
        AliasCommandTest.ModelStubWithAliasTable modelStub = new AliasCommandTest.ModelStubWithAliasTable();

        CommandResult commandResult = new AliasCommand("test", "help").execute(modelStub);

        assertEquals(String.format(AliasCommand.MESSAGE_SUCCESS, "test", "help"), commandResult.getFeedbackToUser());
        assertEquals(
                AliasTable.getDefaultAliasTable().addAlias("test", "help"),
                modelStub.getUserPrefs().getAliasTable()
        );
    }

    @Test
    public void equals() {
        AliasCommand aliasExitCommand = new AliasCommand("test1", "exit");
        AliasCommand aliasHelpCommand = new AliasCommand("test2", "help");

        assertTrue(aliasExitCommand.equals(aliasExitCommand));
        assertTrue(aliasHelpCommand.equals(aliasHelpCommand));

        assertFalse(aliasExitCommand.equals(1));
        assertFalse(aliasHelpCommand.equals(null));

        assertFalse(aliasExitCommand.equals(aliasHelpCommand));
    }

    private class ModelStubWithAliasTable extends ModelStub {
        final ReadOnlyUserPrefs userPrefs = new UserPrefs();

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return userPrefs;
        }
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReminder(Reminder reminder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
}