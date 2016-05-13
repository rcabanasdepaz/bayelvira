// Influence Diagram
//   Elvira format 

idiagram  Untitled1 { 

// Network Properties

version = 1.0;
default node states = (present , absent);

// Network Variables 

node Oil(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =196;
pos_y =43;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (dry wet soak);
}

node Seismic(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =200;
pos_y =168;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (closed open diff);
}

node Pay(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =334;
pos_y =42;
relevance = 7.0;
purpose = "";
min = 0.0;
max = 1.0;
precision = 2;
}

node Drill(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =343;
pos_y =168;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (drill not);
}

node Test(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =198;
pos_y =271;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (test not);
}

node Cost(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =327;
pos_y =268;
relevance = 7.0;
purpose = "";
min = 0.0;
max = 1.0;
precision = 2;
}

// links of the associated graph:

link Oil Seismic;

link Oil Pay;

link Seismic Drill;

link Drill Pay;

link Test Seismic;

link Test Cost;

//Network Relationships: 

relation Oil { 
values= table (0.5 0.3 0.2 );
}

relation Pay Oil Drill { 
kind-of-relation = utility;
values= table (-70.0 0.0 50.0 0.0 200.0 0.0 );
}

relation Seismic Oil Test { 
values= table (0.1 0.3333333333333333 0.3 0.3333333333333333 0.5 0.3333333333333333 0.3 0.3333333333333333 0.4 0.3333333333333333 0.4 0.3333333333333333 0.6 0.3333333333333333 0.3 0.3333333333333333 0.1 0.3333333333333333 );
}

relation Cost Test { 
kind-of-relation = utility;
values= table (-10.0 0.0 );
}

}

