package backend.hinter;

import backend.History.DragAndDropCommand;
import gui.controllers.ColumnController;
import gui.controllers.GoalController;
import gui.controllers.PackController;
import gui.controllers.PreviewController;
import interfaces.Card;

import java.util.ArrayList;

/**
 * Abstraktní třída pro generování backendu
 *
 * @author xbures29+xhalam14
 */
public class Hinter {
    ArrayList<ColumnController> columns;
    ArrayList<GoalController> goals;
    PreviewController preview;
    PackController pack;

    public Hinter() {
        this.columns = new ArrayList<>();
        this.goals = new ArrayList<>();
    }

    /**
     * Přidat ColumnController
     * @param column Controller Sloupce
     */
    public void addColumns(ColumnController column) {
        this.columns.add(column);
    }

    /**
     * Přidat GoalController
     * @param goal Cílový balíček
     */
    public void addGoals(GoalController goal) {
        this.goals.add(goal);
    }

    /**
     * Nastavit Preview
     * @param preview "odhazovací" balíček
     */
    public void setPreview(PreviewController preview) {
        this.preview = preview;
    }

    /**
     *  Nastavit Pack
     *  @param pack lízací balíček
     */
    public void setPack(PackController pack) {
        this.pack = pack;
    }

    /**
     * Provést nápovědu
     */
    public void hint() {
        if (this.onGoal()) return;
        if (this.onPack()) return;
        this.pack.showHint();
    }

    private boolean onGoal() {
        for (GoalController goal : this.goals) {
            for (ColumnController column : this.columns) {
                if (!column.getBackend().isEmpty() && (new DragAndDropCommand(column.getBackend(), goal.getBackend(), null)).executable()) {
                    goal.showHint();
                    column.showHint();
                    return true;
                }
            }
            if (!preview.getBackend().isEmpty() && (new DragAndDropCommand(preview.getBackend(), goal.getBackend(), null)).executable()) {
                goal.showHint();
                preview.showHint();
                return true;
            }
        }

        return false;
    }

    private boolean onPack() {
        for (ColumnController columnDest : this.columns) {
            for (ColumnController columnSrc : this.columns) {
                if (columnDest.equals(columnSrc)) continue;
                ArrayList<Card> sources = columnSrc.getAllVisibleCards();
                for (Card card : sources) {
                    if (!columnSrc.getBackend().isEmpty() &&
                            (new DragAndDropCommand(columnSrc.getBackend(), columnDest.getBackend(), card)).executable()) {
                        columnDest.showHint();
                        columnSrc.showHint(card);
                        return true;
                    }
                }
            }
            if (!preview.getBackend().isEmpty() && (new DragAndDropCommand(preview.getBackend(), columnDest.getBackend(), null)).executable()) {
                columnDest.showHint();
                preview.showHint();
                return true;
            }
        }
        return false;
    }
}
