package clippy.command;

import clippy.storage.Storage;

import clippy.task.Task;
import clippy.task.TaskList;

import clippy.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find and display all tasks matching a keyword when executed.
 */
public class FindCommand extends Command {
    private String keyword;
    
    /**
     * Constructs a command object that finds and displays all tasks matching a keyword when executed.
     * 
     * @param keyword User-specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns resulting message to be displayed by GUI after finding all tasks matching the keyword.
     * Executes the command.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI after finding all tasks matching the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchedTasks = getMatchedTasks(tasks);
        
        String output = getOutput(ui, matchedTasks);
        
        return output;
    }

    private ArrayList<Task> getMatchedTasks(TaskList tasks) {
        int numOfTasks = tasks.getSize();
        assert numOfTasks >= 0 : "Negative number of tasks in TaskList";

        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (int i = 1; i <= numOfTasks; i++) {
            Task currTask = tasks.getTask(i);
            String currDesc = currTask.getDesc();

            if (currDesc.contains(keyword)) {
                matchedTasks.add(currTask);
            }
        }
        return matchedTasks;
    }
    
    private String getOutput(Ui ui, ArrayList<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            return ui.showNoMatch();
        }
        
        String output = ui.showMatchingTaskHeader() + "\n";
        int i = 1;

        for (Task t : matchedTasks) {
            String indexOfTaskInListStatement = "at List Index: " + t.getTaskIndexInList();
            output += ui.showTaskWithIndex(i, t) + " " + indexOfTaskInListStatement + "\n";
            i++;
        }
        
        return output;
    }
}
