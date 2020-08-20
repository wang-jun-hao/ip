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
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("done ")) {
                int indexOfDoneTask = Integer.parseInt(input.substring(5));
                markAsDone(indexOfDoneTask);
            } else {
                add(input);
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

    private void add(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new ToDo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            int indexOfSeparator = input.indexOf("/");
            newTask = new Deadline(input.substring(9, indexOfSeparator - 1),
                    input.substring(indexOfSeparator + 4));
        } else {
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
}
