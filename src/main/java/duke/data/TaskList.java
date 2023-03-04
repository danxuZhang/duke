package duke.data;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;

import duke.data.task.Task;
import duke.data.exception.DuplicateMarkException;
import duke.data.exception.TaskNotFoundException;
import duke.data.exception.DuplicateTaskException;

public final class TaskList implements Iterable<Task> {
    private final List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(Task... tasks) throws DuplicateTaskException {
        Set<Task> appeared = new HashSet<>();
        for (Task task : tasks) {
            if (appeared.contains(task)) {
                throw new DuplicateTaskException();
            } else {
                taskList.add(task);
                appeared.add(task);
            }
        }
    }

    public TaskList(Collection<Task> tasks) throws DuplicateTaskException {
        Set<Task> appeared = new HashSet<>();
        for (Task task : tasks) {
            if (appeared.contains(task)) {
                throw new DuplicateTaskException();
            } else {
                taskList.add(task);
                appeared.add(task);
            }
        }
    }

    public TaskList(TaskList other) {
        taskList.addAll(other.taskList);
    }

    /**
     * Gets the number of tasks in the list.
     * @return number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Checks whether a task is in the task list or not.
     * @param toCheck task to be checked.
     * @return true if yes, else false.
     */
    public boolean containsTask(Task toCheck) {
        for (Task task : taskList) {
            if (toCheck.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a task at a given index.
     * @param listIndex index of the task in the list.
     * @return task at the index.
     * @throws IndexOutOfBoundsException if the index is out of bound.
     */
    public Task getTask(int listIndex) throws IndexOutOfBoundsException{
        int index = listIndex - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     * @param toAdd task to be added.
     * @throws DuplicateTaskException if there already exists the same task.
     */
    public void addTask(Task toAdd) throws DuplicateTaskException {
        if (containsTask(toAdd)) {
            throw new DuplicateTaskException();
        }
        taskList.add(toAdd);
    }

    /**
     * Marks a task to be done.
     * @param listIndex index of the task to be marked.
     * @throws IndexOutOfBoundsException if the index is out of bound.
     * @throws DuplicateMarkException if the task is already marked.
     */
    public void markTask(int listIndex) throws IndexOutOfBoundsException, DuplicateMarkException {
        int index = listIndex - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(index).mark();
    }

    /**
     * Unmarks a task to be not done yet.
     * @param listIndex index of the task to be unmarked.
     * @throws IndexOutOfBoundsException if the index is out of bound.
     * @throws DuplicateMarkException if the task is already unmarked.
     */
    public void unmarkTask(int listIndex) throws IndexOutOfBoundsException, DuplicateMarkException {
        int index = listIndex - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(index).unmark();
    }

    /**
     * Deletes a task at from the task list.
     * @param toDelete task to be deleted.
     * @throws TaskNotFoundException if there is no such task in the task list.
     */
    public void deleteTask(Task toDelete) throws TaskNotFoundException {
        final boolean deleted = taskList.remove(toDelete);
        if (!deleted) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Deletes a task from the task list.
     * @param listIndex index of the task to be deleted.
     * @throws IndexOutOfBoundsException if the input index is out of bound.
     */
    public void deleteTask(int listIndex) throws IndexOutOfBoundsException {
        int index = listIndex - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(index);
    }

    /**
     * Deletes all tasks in the task list.
     */
    public void clear() {
        taskList.clear();
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof TaskList
                    && ((TaskList) obj).taskList.equals(this.taskList);
        }
    }
}
