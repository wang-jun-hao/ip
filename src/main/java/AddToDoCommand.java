public class AddToDoCommand extends AddCommand {
    AddToDoCommand(String taskDescription) {
        super(taskDescription);
    }
    
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        String output = ui.showAdded(newTask, tasks.size());

        tasks.updateAllTaskIndices();
        storage.save(tasks);
        
        return output;
    }
}
