/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram.maze;

import elvira.Configuration;
import elvira.Continuous;
import elvira.FiniteStates;
import elvira.IDiagram;
import elvira.Link;
import elvira.LinkList;
import elvira.LogicalNode;
import elvira.Node;
import elvira.NodeList;
import elvira.Relation;
import elvira.RelationList;
import elvira.ValuesSet;
import elvira.parser.ParseException;
import elvira.potential.LogicalExpression;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author rcabanas
 */
public class MazeIDsolver {

    private Maze maze;
    private int steps = 0;
    private String outPath = "";
    private String name = "";
    IDiagram id;
    private int posX, posY;
    private final char[] directions = {'n', 'e', 's', 'w', 'k'};
    private double pFront = 0.89;
    private double pLeft = 0.01;
    private double pRight = 0.01;
    private double pBack = 0.001;
    private double pSame = 0.089;

    public MazeIDsolver(Maze maze) {
        this.maze = maze;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void generateID() throws ParseException, IOException {
        id = new IDiagram();
        id.setName(name);

        NodeList nodes = addNodes();
        LinkList links = addLinks();
        Vector<Relation> relations = addRelations();

    }

    private Vector<Relation> addRelations() {

        Vector<Relation> relations = new Vector<Relation>();
        NodeList nodes = id.getNodeList();

        Relation r;
        Vector<Node> vars;

        //Utility
        vars = new Vector<Node>();
        vars.add(nodes.getNode("u"));
        vars.add(nodes.getNode("x_" + Integer.toString(steps)));
        vars.add(nodes.getNode("y_" + Integer.toString(steps)));
        r = new Relation(vars);
        r.setKind(Relation.UTILITY);
        vars.remove(nodes.getNode("u"));
        PotentialTable pot = new PotentialTable(vars);
        r.setValues(pot);
        relations.add(r);

        id.setRelationList(relations);

        initChanceRelations();

        initPotentials();

        initConstraints();

        return relations;

    }

    private void initConstraints() {

        //Decision constraints
   
    for (int i = 0; i < steps - 1; i++) {
            for (int d = 0; d < 2; d++) {

                Vector<Node> vars = new Vector<Node>();
                vars.add(id.getNode("d_" + Integer.toString(i)));
                vars.add(id.getNode("d_" + Integer.toString(i + 1)));
                Relation r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);
                Vector values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i)))).getStates().elementAt(d));
                ValuesSet set = new ValuesSet(id.getNode("d_" + Integer.toString(i)), values, false);
                LogicalNode antecesor = new LogicalNode(set);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i + 1)))).getStates().elementAt(d + 2));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i + 1)), values, true);
                LogicalNode consequent = new LogicalNode(set);
                LogicalExpression exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);

                r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i + 1)))).getStates().elementAt(d));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i + 1)), values, false);
                antecesor = new LogicalNode(set);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i)))).getStates().elementAt(d + 2));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i)), values, true);
                consequent = new LogicalNode(set);
                exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);

                r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i)))).getStates().elementAt(d + 2));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i)), values, false);
                antecesor = new LogicalNode(set);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i + 1)))).getStates().elementAt(d + 0));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i + 1)), values, true);
                consequent = new LogicalNode(set);
                exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);

                r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i + 1)))).getStates().elementAt(d + 2));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i + 1)), values, false);
                antecesor = new LogicalNode(set);
                values = new Vector();
                values.add((((FiniteStates) id.getNode("d_" + Integer.toString(i)))).getStates().elementAt(d + 0));
                set = new ValuesSet(id.getNode("d_" + Integer.toString(i)), values, true);
                consequent = new LogicalNode(set);
                exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);
            }
        }


        for (int i = 0; i < steps; i++) {
            for (int x = 0; x < maze.getSizeX(); x++) {

                Vector<Node> vars = new Vector<Node>();
                vars.add(id.getNode("x_" + Integer.toString(i)));
                vars.add(id.getNode("x_" + Integer.toString(i + 1)));
                Relation r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);

                Vector values = new Vector();
                values.add((((FiniteStates) id.getNode("x_" + Integer.toString(i)))).getStates().elementAt(x));
                ValuesSet set = new ValuesSet(id.getNode("x_" + Integer.toString(i)), values, false);
                LogicalNode antecesor = new LogicalNode(set);
                values = new Vector();


                values.add((((FiniteStates) id.getNode("x_" + Integer.toString(i + 1)))).getStates().elementAt(x));
                if (x > 0) {
                    values.add((((FiniteStates) id.getNode("x_" + Integer.toString(i + 1)))).getStates().elementAt(x - 1));
                }
                if (x < maze.getSizeX() - 1) {
                    values.add((((FiniteStates) id.getNode("x_" + Integer.toString(i + 1)))).getStates().elementAt(x + 1));
                }

                set = new ValuesSet(id.getNode("x_" + Integer.toString(i + 1)), values, false);
                LogicalNode consequent = new LogicalNode(set);
                LogicalExpression exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);

            }
        }
        
        
        for (int i = 0; i < steps; i++) {
            for (int y = 0; y < maze.getSizeY(); y++) {

                Vector<Node> vars = new Vector<Node>();
                vars.add(id.getNode("y_" + Integer.toString(i)));
                vars.add(id.getNode("y_" + Integer.toString(i + 1)));
                Relation r = new Relation(vars);
                r.setKind(Relation.CONSTRAINT);

                Vector values = new Vector();
                values.add((((FiniteStates) id.getNode("y_" + Integer.toString(i)))).getStates().elementAt(y));
                ValuesSet set = new ValuesSet(id.getNode("y_" + Integer.toString(i)), values, false);
                LogicalNode antecesor = new LogicalNode(set);
                values = new Vector();


                values.add((((FiniteStates) id.getNode("y_" + Integer.toString(i + 1)))).getStates().elementAt(y));
                if (y > 0) {
                    values.add((((FiniteStates) id.getNode("y_" + Integer.toString(i + 1)))).getStates().elementAt(y - 1));
                }
                if (y < maze.getSizeY() - 1) {
                    values.add((((FiniteStates) id.getNode("y_" + Integer.toString(i + 1)))).getStates().elementAt(y + 1));
                }

                set = new ValuesSet(id.getNode("y_" + Integer.toString(i + 1)), values, false);
                LogicalNode consequent = new LogicalNode(set);
                LogicalExpression exp = new LogicalExpression(antecesor, consequent, LogicalNode.IMPLICATION);
                r.setValues(exp);
                id.getRelationList().add(r);

            }
        }

    }

    private void initChanceRelations() {
        NodeList nodes = id.getNodeList();
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.elementAt(i);
            if (n.getKindOfNode() == Node.CHANCE) {
                Vector<Node> vars = n.getParentNodes().toVector();
                vars.insertElementAt(n, 0);

                PotentialTable pot = new PotentialTable(vars);
                Relation r = new Relation(pot);
                id.getRelationList().add(r);
            }
        }

    }

    private void initPotentials() {
        //Initial position X_0
        PotentialTable pot = (PotentialTable) id.getRelation(id.getNode("x_0")).getValues();
        Configuration conf = new Configuration(pot.getVariables());
        conf.putValue("x_0", posX);
        pot.setValue(conf, 1);
        id.getRelation(id.getNode("x_0")).setValues(pot);

        pot = (PotentialTable) id.getRelation(id.getNode("y_0")).getValues();
        conf = new Configuration(pot.getVariables());

        //Initial position Y_0
        for (int x = 0; x < maze.getSizeX(); x++) {

            conf.putValue("x_0", x);
            if (x == posX) {
                conf.putValue("y_0", posY);
                pot.setValue(conf, 1);
            } else {
                double sum = 0;
                for (int y = 0; y < maze.getSizeY(); y++) {

                    conf.putValue("y_0", y);
                    if (y != maze.getSizeY() - 1) {
                        sum += 1.0 / maze.getSizeY();
                        pot.setValue(conf, 1.0 / maze.getSizeY());
                    } else {
                        pot.setValue(conf, 1.0 - sum);
                    }

                }
            }
        }

        for (int i = 0; i < steps; i++) {
            initSensorPotential(i, 'n');
            initSensorPotential(i, 's');
            initSensorPotential(i, 'w');
            initSensorPotential(i, 'e');

            initPositionXPotentials(i + 1);
            initPositionYPotentials(i + 1);
        }

        initUtilityPotential();

    }

    private void initUtilityPotential() {
        PotentialTable pot = (PotentialTable) id.getRelation(id.getNode("u")).getValues();
        Configuration conf = new Configuration(pot.getVariables());

        conf.putValue("x_" + Integer.toString(steps), maze.getGoalX());
        conf.putValue("y_" + Integer.toString(steps), maze.getGoalY());
        pot.setValue(conf, 1);

    }

    private void initSensorPotential(int step, char direction) {

        FiniteStates n = (FiniteStates) id.getNode(direction + "s_" + Integer.toString(step));
        PotentialTable pot = (PotentialTable) id.getRelation(n).getValues();
        Configuration conf = new Configuration(pot.getVariables());

        for (int x = 0; x < maze.getSizeX(); x++) {
            for (int y = 0; y < maze.getSizeY(); y++) {

                conf.putValue("x_" + Integer.toString(step), x);
                conf.putValue("y_" + Integer.toString(step), y);

                int w = 0;
                if (maze.isWall(x, y, direction)) {
                    w = 1;
                }

                conf.putValue(n, 0); //prob. of wall=false
                pot.setValue(conf, 1 - w);
                conf.putValue(n, 1); //prob. of wall=true
                pot.setValue(conf, w);

            }
        }

    }

    private void initPositionXPotentials(int step) {

        FiniteStates n = (FiniteStates) id.getNode("x_" + Integer.toString(step));
        PotentialTable pot = (PotentialTable) id.getRelation(n).getValues();
        Configuration conf = new Configuration(pot.getVariables());

        for (int x = 0; x < maze.getSizeX(); x++) {
            for (int y = 0; y < maze.getSizeY(); y++) {
                for (int d = 0; d < directions.length; d++) {

                    double p[] = getNeighboursProbs(directions[d]);
                    double pn = p[0];
                    double pe = p[1];
                    double ps = p[2];
                    double pw = p[3];
                    double pk = p[4];

                    int w[] = maze.getNeighbourWalls(x, y);
                    int wn = w[0];
                    int we = w[1];
                    int ws = w[2];
                    int ww = w[3];

                    double alfa = 1 / (wn * pn + we * pe + ws * ps + ww * pw + pk);

                    conf.putValue("x_" + Integer.toString(step - 1), x);
                    conf.putValue("y_" + Integer.toString(step - 1), y);
                    conf.putValue("d_" + Integer.toString(step - 1), d);

                    if (x > 0) {
                        conf.putValue("x_" + Integer.toString(step), x - 1);
                        pot.setValue(conf, alfa * ww * pw);
                    }
                    if (x < maze.getSizeX() - 1) {
                        conf.putValue("x_" + Integer.toString(step), x + 1);
                        pot.setValue(conf, alfa * we * pe);
                    }

                    conf.putValue("x_" + Integer.toString(step), x);
                    pot.setValue(conf, alfa * (wn * pn + ws * ps + pk));

                }
            }
        }
        pot.normalizeOver(n);
    }

    private void initPositionYPotentials(int step) {

        FiniteStates n = (FiniteStates) id.getNode("y_" + Integer.toString(step));
        PotentialTable pot = (PotentialTable) id.getRelation(n).getValues();
        /*
         * PotentialTable pot =
         * PotentialTable.getOnes(id.getRelation(n).getVariables().toVector());
         * pot.normalizeOver(n);
         id.getRelation(n).setValues(pot);
         */
        Configuration conf = new Configuration(pot.getVariables());

        for (int x = 0; x < maze.getSizeX(); x++) {
            for (int y = 0; y < maze.getSizeY(); y++) {
                for (int d = 0; d < directions.length; d++) {

                    double p[] = getNeighboursProbs(directions[d]);
                    double pn = p[0];
                    double pe = p[1];
                    double ps = p[2];
                    double pw = p[3];
                    double pk = p[4];

                    int w[] = maze.getNeighbourWalls(x, y);
                    int wn = w[0];
                    int we = w[1];
                    int ws = w[2];
                    int ww = w[3];

                    // double alfa = 1/(wn*pn + we*pe + ws*ps + ww*pw + pk);
                    conf.putValue("x_" + Integer.toString(step - 1), x);
                    conf.putValue("y_" + Integer.toString(step - 1), y);
                    conf.putValue("d_" + Integer.toString(step - 1), d);

                    double alfa;

                    if (x > 0) {
                        conf.putValue("x_" + Integer.toString(step), x - 1);
                        conf.putValue("y_" + Integer.toString(step), y);
                        pot.setValue(conf, 1);
                    }
                    if (x < maze.getSizeX() - 1) {
                        conf.putValue("x_" + Integer.toString(step), x + 1);
                        conf.putValue("y_" + Integer.toString(step), y);
                        pot.setValue(conf, 1);
                    }

                    alfa = 1 / (wn * pn + ws * ps + pk);
                    conf.putValue("x_" + Integer.toString(step), x);

                    if (y > 0) {
                        conf.putValue("y_" + Integer.toString(step), y - 1);
                        pot.setValue(conf, alfa * (wn * pn));
                    }

                    if (y < maze.getSizeY() - 1) {
                        conf.putValue("y_" + Integer.toString(step), y + 1);
                        pot.setValue(conf, alfa * (ws * ps));
                    }
                    conf.putValue("y_" + Integer.toString(step), y);
                    pot.setValue(conf, alfa * pk);

                    //Impossible values for the next x
                    for (int xnext = 0; xnext < maze.getSizeX(); xnext++) {
                        if (xnext < x - 1 || xnext > x + 1) {
                            double sum = 0;
                            conf.putValue("x_" + Integer.toString(step), xnext);
                            for (int ynext = 0; ynext < maze.getSizeY() - 1; ynext++) {
                                conf.putValue("y_" + Integer.toString(step), ynext);
                                
                                double v = 0;
                                if(ynext==0)
                                    v=1;
                                
                                //same prob.
                                //double v = 1.0 / maze.getSizeY();
                                pot.setValue(conf, v);
                                sum += v;

                            }

                            conf.putValue("y_" + Integer.toString(step), maze.getSizeY() - 1);
                            pot.setValue(conf, 1 - sum);

                        }

                    }

                }
            }
        }

        // pot.normalizeOver(n);
    }
    //{'n','e','s','w',k};

    private double[] getNeighboursProbs(char direction) {

        double p[] = new double[5];
        if (direction == 'n') {
            p[0] = pFront;
            p[1] = pRight;
            p[2] = pBack;
            p[3] = pLeft;
            p[4] = pSame;
        } else if (direction == 'e') {
            p[1] = pFront;
            p[2] = pRight;
            p[3] = pBack;
            p[0] = pLeft;
            p[4] = pSame;
        } else if (direction == 's') {
            p[2] = pFront;
            p[3] = pRight;
            p[0] = pBack;
            p[1] = pLeft;
            p[4] = pSame;

        } else if (direction == 'w') {
            p[3] = pFront;
            p[0] = pRight;
            p[1] = pBack;
            p[2] = pLeft;
            p[4] = pSame;

        } else if (direction == 'k') {
            p[0] = 0;
            p[1] = 0;
            p[2] = 0;
            p[3] = 0;
            p[4] = 1;
        }

        return p;

    }

    private void addLink(String parentName, String childName) {

        Link l;
        Node parent = id.getNode(parentName);
        Node child = id.getNode(childName);
        l = new Link(parent, child, true);
        child.addParent(l);
        parent.addChild(l);
        id.getLinkList().insertLink(l);

    }

    private LinkList addLinks() {

        NodeList nodes = id.getNodeList();

        Link l;

        addLink("x_" + Integer.toString(steps), "u");
        addLink("y_" + Integer.toString(steps), "u");
        addLink("x_" + Integer.toString(steps), "y_" + Integer.toString(steps));

        for (int i = steps - 1; i >= 0; i--) {

            addLink("d_" + Integer.toString(i), "x_" + Integer.toString(i + 1));
            addLink("d_" + Integer.toString(i), "y_" + Integer.toString(i + 1));
            addLink("ns_" + Integer.toString(i), "d_" + Integer.toString(i));
            addLink("es_" + Integer.toString(i), "d_" + Integer.toString(i));
            addLink("ss_" + Integer.toString(i), "d_" + Integer.toString(i));
            addLink("ws_" + Integer.toString(i), "d_" + Integer.toString(i));
            addLink("x_" + Integer.toString(i), "x_" + Integer.toString(i + 1));
            addLink("x_" + Integer.toString(i), "y_" + Integer.toString(i + 1));
            addLink("y_" + Integer.toString(i), "x_" + Integer.toString(i + 1));
            addLink("y_" + Integer.toString(i), "y_" + Integer.toString(i + 1));
            addLink("x_" + Integer.toString(i), "ns_" + Integer.toString(i));
            addLink("x_" + Integer.toString(i), "ss_" + Integer.toString(i));
            addLink("x_" + Integer.toString(i), "ws_" + Integer.toString(i));
            addLink("x_" + Integer.toString(i), "es_" + Integer.toString(i));
            addLink("y_" + Integer.toString(i), "ns_" + Integer.toString(i));
            addLink("y_" + Integer.toString(i), "ss_" + Integer.toString(i));
            addLink("y_" + Integer.toString(i), "ws_" + Integer.toString(i));
            addLink("y_" + Integer.toString(i), "es_" + Integer.toString(i));
            addLink("x_" + Integer.toString(i), "y_" + Integer.toString(i));

        }

        return id.getLinkList();
    }

    private NodeList addNodes() {

        int posX = 900;
        int posY = 200;

        NodeList nodes = new NodeList();

        Continuous u = new Continuous();
        u.setName("u");
        u.setKindOfNode(Node.UTILITY);
        u.setPosX(posX);
        u.setPosY(posY);
        nodes.insertNode(u);

        posX -= 50;

        FiniteStates x_n;
        x_n = new FiniteStates("x_" + Integer.toString(steps), maze.getSizeX());
        String statesX[] = getPositionStates("x", 0, maze.getSizeX());
        x_n.setStates(statesX);
        x_n.setPosX(posX);
        x_n.setPosY(posY - 60);
        nodes.insertNode(x_n);



        FiniteStates y_n;
        y_n = new FiniteStates("y_" + Integer.toString(steps), maze.getSizeY());
        String statesY[] = getPositionStates("y", 0, maze.getSizeY());
        y_n.setStates(statesY);
        y_n.setPosX(posX);
        y_n.setPosY(posY + 60);
        nodes.insertNode(y_n);

        for (int i = steps - 1; i >= 0; i--) {
            FiniteStates d_i;
            posX -= 80;
            d_i = new FiniteStates("d_" + Integer.toString(i), 5);
            String stat[] = {"North", "East", "South", "West", "Not_move"};
            d_i.setStates(stat);
            d_i.setKindOfNode(Node.DECISION);
            d_i.setPosX(posX);
            d_i.setPosY(posY);
            nodes.insertNode(d_i);

            posX -= 80;

            FiniteStates ns_i;
            ns_i = new FiniteStates("ns_" + Integer.toString(i), 2);
            ns_i.setPosX(posX);
            ns_i.setPosY(posY + 90);
            nodes.insertNode(ns_i);

            FiniteStates es_i;
            es_i = new FiniteStates("es_" + Integer.toString(i), 2);
            es_i.setPosX(posX);
            es_i.setPosY(posY + 30);
            nodes.insertNode(es_i);

            FiniteStates ss_i;
            ss_i = new FiniteStates("ss_" + Integer.toString(i), 2);
            ss_i.setPosX(posX);
            ss_i.setPosY(posY - 30);
            nodes.insertNode(ss_i);

            FiniteStates ws_i;
            ws_i = new FiniteStates("ws_" + Integer.toString(i), 2);
            ws_i.setPosX(posX);
            ws_i.setPosY(posY - 90);
            nodes.insertNode(ws_i);

            posX -= 80;

            FiniteStates x_i;
            x_i = new FiniteStates("x_" + Integer.toString(i), maze.getSizeX());
            x_i.setStates(statesX);
            x_i.setPosX(posX);
            x_i.setPosY(posY - 60);
            nodes.insertNode(x_i);

            FiniteStates y_i;
            y_i = new FiniteStates("y_" + Integer.toString(i), maze.getSizeY());
            y_i.setStates(statesY);
            y_i.setPosX(posX);
            y_i.setPosY(posY + 60);
            nodes.insertNode(y_i);

        }

        id.setNodeList(nodes);

        return nodes;

    }

    public void save() throws IOException {

        System.out.println("Generando ID: "+outPath + name + ".elv");
        id.save(outPath + name + ".elv");

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    private String[] getPositionStates(String name, int min, int n) {
        String states[] = new String[n];

        for (int i = 0; i < n; i++) {
            states[i] = name + Integer.toString(i + min);
        }
        return states;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        Maze m = new Maze();

        
         boolean w[][] = {{true, true, true, true, true, true, true, true,
         true}, {true, true, false, false, false, false, false, true, true},
         {true, false, false, true, false, true, false, false, true}, {true,
         true, false, true, false, true, false, true, true}, {true, false,
         false, true, false, true, false, false, true}, {true, true, false,
         false, false, false, false, true, true}, {true, true, true, true,
         true, true, true, true, true},};
         

        //          boolean w[][] = {{false, false, false}, {false, true, false}, {false, false, false}};

   /*     int w[][] = {{1, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0, 0}};*/
        m.setGoal(5, 0);
//m.setGoal(1, 0);
        m.setWalls(w);

        m.printMaze();


        for (int s = 1; s < 4; s++) {
            for (int x = 0; x < m.getSizeX(); x++) {
                for (int y = 0; y < m.getSizeY(); y++) {

                    if (!m.isWall(x, y)) {

                        MazeIDsolver solver = new MazeIDsolver(m);
                        solver.setSteps(s);
                        solver.setOutPath("ids/maze/journalBCT/");
                        solver.setPos(x, y);
                        String name = "testmaze_" + Integer.toString(solver.getSteps()) + "steps_" + Integer.toString(solver.getMaze().getSizeX()) + "x" + Integer.toString(solver.getMaze().getSizeX()) + "_" + Integer.toString(x) + "_" + Integer.toString(y);
                        solver.setName(name);
                        solver.generateID();
                        solver.save();
                    }
                }
            }
        }

    }
}
