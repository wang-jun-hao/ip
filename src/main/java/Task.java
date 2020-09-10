public abstract class Task {
    protected String desc;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public abstract String generateSaveFileData();
    
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + desc;
    }
}
