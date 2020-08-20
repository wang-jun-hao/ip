import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> taskList;

    Duke() {
        this.taskList = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }

    private void initialise() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("delete")) {
                    int indexOfTaskToDelete = Integer.parseInt(input.substring(7));
                    delete(indexOfTaskToDelete);
                } else if (input.startsWith("done")) {
                    int indexOfDoneTask = Integer.parseInt(input.substring(5));
                    markAsDone(indexOfDoneTask);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    add(input);
                } else {
                    throw new CommandException();
                }
            } catch (DukeException e) {
                System.out.println("    ____________________________________________________________\n"
                        + "     " + e + "\n"
                        + "    ____________________________________________________________");
            }
        }
        exit();
    }

    private void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke \n     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    private void echo(String command) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + command + "\n" +
                "    ____________________________________________________________");
    }

    private void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }

    private void add(String input) throws ToDoException, DeadlineException, EventException {
        Task newTask;
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new ToDoException();
            }
            newTask = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new DeadlineException();
            }
            int indexOfSeparator = input.indexOf("/");
            newTask = new Deadline(input.substring(9, indexOfSeparator - 1),
                    input.substring(indexOfSeparator + 4));
        } else {
            if (input.length() <= 6) {
                throw new EventException();
            }
            int indexOfSeparator = input.indexOf("/");
            newTask = new Event(input.substring(6, indexOfSeparator - 1),
                    input.substring(indexOfSeparator + 4));
        }

        taskList.add(newTask);
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       " + newTask + "\n" +
                "     Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task in the list.\n" : " tasks in the list.\n") +
                "    ____________________________________________________________");

    }

    private void list() {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        ListIterator<Task> listIterator = taskList.listIterator();
        int i = 1;
        while (listIterator.hasNext()) {
            Task t = listIterator.next();
            System.out.println("     " + i + ". " + t);
            i++;
        }
        System.out.println("    ____________________________________________________________");
    }

    private void markAsDone(int indexOfDoneTask) {
        Task doneTask = taskList.get(indexOfDoneTask - 1);
        doneTask.markAsDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       " + doneTask + "\n" +
                "    ____________________________________________________________");
    }

    private void delete(int indexOfTaskToDelete) {
        Task taskToDelete = taskList.get(indexOfTaskToDelete - 1);
        taskList.remove(indexOfTaskToDelete - 1);
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task: \n" +
                "       " + taskToDelete + "\n" +
                "     Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task in the list.\n" : " tasks in the list.\n") +
                "    ____________________________________________________________");
    }
}
