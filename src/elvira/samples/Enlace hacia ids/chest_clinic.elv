// Influence Diagram
//   Elvira format 

idiagram  "" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node A(finite-states) {
title = "Tuberculosis";
comment = "Representa la enfermedad
Tuberculosis.";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =266;
pos_y =168;
relevance = 9.0;
purpose = "Enfermedad/Anomal�a";
num-states = 2;
states = ("yes" "no");
}

node B(finite-states) {
title = "Lung Cancer";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =462;
pos_y =168;
relevance = 9.0;
purpose = "Enfermedad/Anomal�a";
num-states = 2;
states = ("yes" "no");
}

node C(finite-states) {
title = "Tuberculosis or Cancer";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =400;
pos_y =257;
relevance = 5.0;
purpose = "Auxiliar";
num-states = 2;
states = ("yes" "no");
}

node D(finite-states) {
title = "Positive X-ray";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =287;
pos_y =352;
relevance = 5.0;
purpose = "Prueba";
num-states = 2;
states = ("yes" "no");
}

node E(finite-states) {
title = "Dyspnea";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =631;
pos_y =345;
relevance = 7.0;
purpose = "Signo";
num-states = 2;
states = ("yes" "no");
}

node F(finite-states) {
title = "Bronchitis";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =724;
pos_y =171;
relevance = 8.0;
purpose = "Disease";
num-states = 2;
states = ("yes" "no");
}

node G(finite-states) {
title = "Visit to Asia";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =266;
pos_y =72;
relevance = 7.0;
purpose = "Factor de Riesgo";
num-states = 2;
states = ("yes" "no");
}

node H(finite-states) {
title = "Smoker";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =584;
pos_y =73;
relevance = 7.0;
purpose = "Factor de Riesgo";
num-states = 2;
states = ("yes" "no");
}

node TakeXray(finite-states) {
title = "Take X-ray?";
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =122;
pos_y =121;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node UX(continuous) {
title = "Utility of taking X-Ray";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =120;
pos_y =306;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node Hospitalize(finite-states) {
title = "Hospitalize?";
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =858;
pos_y =122;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node UH(continuous) {
title = "Utility of Hospitalization";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =861;
pos_y =314;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link A C;

link A UX;

link A UH;

link B C;

link B UH;

link C D;

link C E;

link F E;

link G A;

link H B;

link H F;

link Hospitalize UH;

link TakeXray D;

link TakeXray Hospitalize;

link TakeXray UX;

//Network Relationships: 

relation C B A { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 1.0 0.0 0.0 0.0 0.0 1.0 );
}

relation E C F { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.9 0.8 0.7 0.1 0.1 0.2 0.3 0.9 );
}

relation G { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.01 0.99 );
}

relation H { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.5 0.5 );
}

relation B H { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.1 0.01 0.9 0.99 );
}

relation F H { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.6 0.3 0.4 0.7 );
}

relation A G { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.05 0.01 0.95 0.99 );
}

relation UX TakeXray A { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 1.0 10.0 10.0 );
}

relation D C TakeXray { 
comment = "new";
deterministic=false;
values= table (0.5 0.5 0.5 0.5 0.5 0.5 0.5 0.5 );
}

relation UH Hospitalize B A { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (180.0 160.0 120.0 15.0 2.0 0.0 4.0 40.0 );
}

}
