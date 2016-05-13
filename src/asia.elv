// Bayesian Network
//   Elvira format 

idiagram  "Untitled1" { 

// Network Properties

visualprecision = "0.00";
version = 1.0;
default node states = ("absent" , "present");

// Network Variables 

node A(finite-states) {
title = "Tuberculosis";
comment = "Representa la enfermedad
Tuberculosis.";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =98;
pos_y =140;
relevance = 9.0;
purpose = "Enfermedad/Anomalía";
num-states = 2;
states = ("yes" "no");
}

node B(finite-states) {
title = "Lung Cancer";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =319;
pos_y =140;
relevance = 9.0;
purpose = "Enfermedad/Anomalía";
num-states = 2;
states = ("yes" "no");
}

node C(finite-states) {
title = "Tuberculosis or Cancer";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =212;
pos_y =249;
relevance = 5.0;
purpose = "Auxiliar";
num-states = 2;
states = ("yes" "no");
}

node D(finite-states) {
title = "Positive X-ray";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =112;
pos_y =347;
relevance = 5.0;
purpose = "Prueba";
num-states = 2;
states = ("yes" "no");
}

node E(finite-states) {
title = "Dyspnea";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =442;
pos_y =343;
relevance = 7.0;
purpose = "Signo";
num-states = 2;
states = ("yes" "no");
}

node F(finite-states) {
title = "Bronchitis";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =539;
pos_y =138;
relevance = 8.0;
purpose = "Disease";
num-states = 2;
states = ("yes" "no");
}

node G(finite-states) {
title = "Visit to Asia";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =118;
pos_y =34;
relevance = 7.0;
purpose = "Factor de Riesgo";
num-states = 2;
states = ("yes" "no");
}

node H(finite-states) {
title = "Smoker";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =406;
pos_y =31;
relevance = 7.0;
purpose = "Factor de Riesgo";
num-states = 2;
states = ("yes" "no");
}

// links of the associated graph:

link A C;

link B C;

link C D;

link C E;

link F E;

link H B;

link H F;

link G A;

//Network Relationships: 

relation C B A { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 1.0 1.0 0.0 0.0 0.0 0.0 1.0 );
}

relation D C { 
comment = "";
kind-of-relation = potential;
deterministic=false;
values= table (0.98 0.05 0.02 0.95 );
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

}
