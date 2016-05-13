// Influence Diagram
//   Elvira format 

idiagram  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node IncreaseDistinction(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =1268;
pos_y =250;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node Interdepency(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =333;
pos_y =254;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 4;
states = ("Domination" "Symbiosis" "Dependancy" "FreePlayers");
}

node NumberOfBuyers(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =220;
pos_y =43;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 3;
states = ("One" "Few" "Many");
}

node Asymetry(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =830;
pos_y =261;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 3;
states = ("High" "Moderate" "None");
}

node Information(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =842;
pos_y =42;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 3;
states = ("Proprietary" "Restricted" "Perfect Avaibility");
}

node ProductDifferentiation(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =1046;
pos_y =43;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 4;
states = ("Extensive" "Moderate" "Low" "None");
}

node EntryExitBarriers(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =650;
pos_y =42;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 4;
states = ("Legal" "High" "Significant" "None");
}

node Competition(finite-states) {
title = "Competition";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =828;
pos_y =154;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 2;
states = ("Intense" "Limited");
}

node NumberOfCompetitors(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =457;
pos_y =42;
relevance = 7.0;
purpose = "";
HR_Grp="2";
num-states = 4;
states = ("One" "Two" "Few" "Many");
}

// Links of the associated graph:

link Asymetry IncreaseDistinction;

link Competition Asymetry;

link EntryExitBarriers Competition;

link EntryExitBarriers Information;

link Information Competition;

link Information ProductDifferentiation;

link Interdepency Asymetry;

link NumberOfBuyers Interdepency;

link NumberOfBuyers NumberOfCompetitors;

link NumberOfCompetitors EntryExitBarriers;

link NumberOfCompetitors Interdepency;

link ProductDifferentiation Competition;

link ProductDifferentiation IncreaseDistinction;

//Network Relationships: 

relation IncreaseDistinction ProductDifferentiation Asymetry { 
comment = "";
kind-of-relation = utility;
deterministic=false;
values= table (2.0 3.0 -11.0 1.0 -7.0 8.0 -3.0 6.0 7.0 4.0 5.0 -15.0 );
}

relation Interdepency NumberOfCompetitors NumberOfBuyers { 
comment = "";
deterministic=false;
values= table (0.05 0.6000000000000001 0.98 0.02 0.3 0.6 0.0 0.14 0.25 0.01 0.05 0.0 0.9 0.08000000000000002 0.0 0.43 0.38 0.1 0.2 0.5 0.05 0.01 0.3 0.05 0.05 0.22000000000000003 0.01 0.5 0.17 0.0 0.6 0.05 0.05 0.98 0.32 0.05 0.0 0.10000000000000002 0.01 0.05 0.15 0.3 0.2 0.31 0.65 0.0 0.33 0.9 );
}

relation Asymetry Competition Interdepency { 
comment = "";
deterministic=false;
values= table (0.1 0.0 0.0 0.0 1.0 0.7 0.57 0.30000000000000004 0.6 0.43 0.3 0.0 0.0 0.3 0.43 0.6000000000000001 0.3 0.57 0.7 1.0 0.0 0.0 0.0 0.10000000000000002 );
}

relation Competition ProductDifferentiation EntryExitBarriers Information { 
comment = "";
deterministic=false;
values= table (0.02 0.04 0.06 0.08 0.1 0.12 0.14 0.16 0.18 0.2 0.22 0.24 0.26 0.28 0.3 0.32 0.34 0.36 0.38 0.4 0.42 0.44 0.46 0.48 0.52 0.54 0.56 0.58 0.6 0.62 0.64 0.66 0.68 0.7 0.72 0.74 0.76 0.78 0.8 0.82 0.84 0.86 0.88 0.9 0.92 0.94 0.96 0.98 0.98 0.96 0.94 0.92 0.9 0.88 0.86 0.84 0.82 0.8 0.78 0.76 0.74 0.72 0.7 0.68 0.66 0.64 0.62 0.6 0.58 0.56 0.54 0.52 0.48 0.46 0.44 0.42 0.4 0.38 0.36 0.34 0.32 0.3 0.28 0.26 0.24 0.22 0.2 0.18 0.16 0.14 0.12 0.1 0.08 0.06 0.04 0.02 );
}

}
