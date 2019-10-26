package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private AliasTable aliasTable;
    private Reminder reminders;
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
        aliasTable = AliasTable.getDefaultAliasTable();
        reminders = Reminder.getDefaultReminders();
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        setAliasTable(newUserPrefs.getAliasTable());
        setReminders(newUserPrefs.getReminders());
    }

    public AliasTable getAliasTable() {
        return aliasTable;
    }

    public void setAliasTable(AliasTable aliasTable) {
        requireNonNull(aliasTable);
        this.aliasTable = aliasTable;
    }

    public Reminder getReminders() {
        return reminders;
    }

    public void setReminders(Reminder reminders) {
        requireNonNull(reminders);
        this.reminders = reminders;
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && aliasTable.equals(o.aliasTable)
                && reminders.equals(o.reminders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, aliasTable, reminders);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nAlias table : " + aliasTable);
        sb.append("\nAlias table : " + reminders);
        return sb.toString();
    }

    public boolean removeAlias(String alias) {
        return aliasTable.removeAlias(alias);
    }

    public void addAlias(String alias, String aliasTo) {
        aliasTable.addAlias(alias, aliasTo);
    }

    public String applyAlias(String commandText) {
        return aliasTable.applyAlias(commandText);
    }

    public void addReminder(int type, String description, int days) {
        reminders.addReminder(type, description, days);
    }
}
