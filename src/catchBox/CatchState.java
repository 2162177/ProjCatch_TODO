package catchBox;

import agentSearch.Action;
import agentSearch.State;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes



    protected int[][] matrix;

    public int getLineAgent() {
        return lineAgent;
    }

    public int getColumnAgent() {
        return columnAgent;
    }

    private int lineAgent;
    private int columnAgent;
    private int lineGoal;
    private int columnGoal;


    public CatchState(int[][] matrix) {
        //TODO
        //1.
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.CATCH) {
                    lineAgent = i;
                    columnAgent = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();

    }


    //2.
    public boolean canMoveUp() {
        //TODO
       return lineAgent != 0 && matrix[lineAgent-1][columnAgent]!=Properties.WALL;
    }



    public boolean canMoveRight() {
        //TODO
        return columnAgent != matrix.length - 1 && matrix[lineAgent][columnAgent+1]!=Properties.WALL;

    }




    public boolean canMoveDown() {
        //TODO
        return lineAgent != matrix.length - 1 && matrix[lineAgent+1][columnAgent]!=Properties.WALL;
    }



    public boolean canMoveLeft() {
        //TODO
        return columnAgent != 0 && matrix[lineAgent][columnAgent-1]!=Properties.WALL;
    }



    public void moveUp() {
        //TODO isto e posicao atual fica vazia e a de cima fica ocupada
        matrix[lineAgent][columnAgent] = Properties.EMPTY ;//<--vazio
        matrix[--lineAgent][columnAgent] = Properties.CATCH; //<--carrinho
    }

    public void moveRight() {
        //TODO
        matrix[lineAgent][columnAgent] = Properties.EMPTY ;//<--vazio
        matrix[lineAgent][++columnAgent] = Properties.CATCH; //<--carrinho
    }

    public void moveDown() {
        //TODO
        matrix[lineAgent][columnAgent] = Properties.EMPTY ;//<--vazio
        matrix[++lineAgent][columnAgent] = Properties.CATCH; //<--carrinho
    }

    public void moveLeft() {
        //TODO
        matrix[lineAgent][columnAgent] = Properties.EMPTY ;//<--vazio
        matrix[lineAgent][--columnAgent] = Properties.CATCH; //<--carrinho
    }

    public int getNumBox() {
        //TODO
        return 0;
    }

    public void setCellCatch(int line, int column) {
        //TODO
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        this.lineAgent = line;
        this.columnAgent = column;
        matrix[lineAgent][columnAgent] = Properties.CATCH;

    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setGoal(int line, int column) {
        //TODO
        this.lineGoal = line;
        this.columnGoal = column;
    }

    public int getSteps() {
        //TODO
        return 0;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public CatchState clone() {
        //TODO
        return new CatchState(matrix);
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

    public double computeDistances(Cell goalPosition) {
        return Math.abs(lineAgent-goalPosition.getLine())+Math.abs(columnAgent-goalPosition.getColumn());

    }
}
