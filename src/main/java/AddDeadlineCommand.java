import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddCommand {
    String by;
    
    AddDeadlineCommand(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws 
            DateTimeParseException {
        Task newTask = new Deadline(taskDescription, by);
        tasks.add(newTask);
        ui.showAdded(newTask, tasks.size());
        
        storage.save(tasks);
    }
}
