// Influence Diagram
//   Elvira format 

idiagram  Untitled1 { 

// Network Properties

version = 1.0;
default node states = (present , absent);

// Network Variables 

node Sick(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =111;
pos_y =77;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (sick not);
}

node Dry(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =244;
pos_y =75;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (dry not);
}

node Loses(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =174;
pos_y =195;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (yes no);
}

node Sickf(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =377;
pos_y =150;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (sick not);
}

node Dryf(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =525;
pos_y =149;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (dry not);
}

node Losesf(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =467;
pos_y =269;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (yes no);
}

node Treat(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =319;
pos_y =54;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (treat not);
}

node Harvest(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =320;
pos_y =274;
relevance = 7.0;
purpose = "";
min = 0.0;
max = 1.0;
precision = 2;
}

// links of the associated graph:

link Sick Loses;

link Dry Loses;

link Sickf Losesf;

link Dryf Losesf;

link Sick Sickf;

link Dry Dryf;

link Treat Sickf;

link Sickf Harvest;

link Treat Harvest;

//Network Relationships: 

relation Sick { 
values= table (0.1 0.9 );
}

relation Dry { 
values= table (0.1 0.9 );
}

relation Loses Sick Dry { 
values= table (0.95 0.9 0.85 0.02 0.05 0.1 0.15 0.98 );
}

relation Losesf Sickf Dryf { 
values= table (0.95 0.9 0.85 0.02 0.05 0.1 0.15 0.98 );
}

relation Dryf Dry { 
values= table (0.6 0.05 0.4 0.95 );
}

relation Sickf Sick Treat { 
values= table (0.2 0.99 0.01 0.02 0.8 0.01 0.99 0.98 );
}

relation Harvest Sickf Treat { 
kind-of-relation = utility;
values= table (-5000.0 3000.0 12000.0 20000.0 );
}

}
