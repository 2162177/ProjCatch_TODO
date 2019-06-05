package catchBox;

import ga.IntVectorIndividual;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {

    public CatchIndividual(CatchProblemForGA problem, int size) {
        super(problem, size);
    }

    public CatchIndividual(CatchIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        //TODO
        fitness = 0;
        LinkedList<Cell> boxes = problem.getCellsBoxes();
        Cell catchCell = problem.getCellCatch();
        Cell door = problem.getDoor();
        fitness = computeFitnessAux(catchCell,boxes.get(genome[0]-1));
        for (int i = 0; i <genome.length - 1; i++) {
            fitness+=computeFitnessAux(boxes.get(genome[i]-1),boxes.get(genome[i+1]-1));

        }
        fitness+=computeFitnessAux(boxes.get(genome[genome.length-1]-1),door);

        return fitness;
    }

    public int[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i <genome.length ; i++) {
            sb.append(genome[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(CatchIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }

    public double computeFitnessAux(Cell cell1,Cell cell2){
        LinkedList<Pair> pares = problem.getPairs();
        double ret=0;

        for (int i = 0; i < pares.size(); i++) {
            Pair par = pares.get(i);
            if (par.getCell1().equals(cell1) && par.getCell2().equals(cell2) || par.getCell2().equals(cell1) && par.getCell1().equals(cell2)) { ///de onde vei a ordem das caixa, é do genome???
                ret =  par.getValue();
                break;
            }
        }
        return ret;
    }


}
