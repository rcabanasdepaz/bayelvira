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
title = "Visit to Asia?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =291;
pos_y =328;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node S(finite-states) {
title = "Smoker?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =671;
pos_y =424;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node T(finite-states) {
title = "Has tuberculosis?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =288;
pos_y =259;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node L(finite-states) {
title = "Has lung cancer?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =560;
pos_y =273;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node B(finite-states) {
title = "Has bronchitis?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =771;
pos_y =275;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node E(finite-states) {
title = "Tuberculosis or lung cancer?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =284;
pos_y =171;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node D(finite-states) {
title = "Dyspnoea?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =401;
pos_y =87;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node Test(finite-states) {
title = "Take X-ray?";
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =158;
pos_y =451;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node X(finite-states) {
title = "Positive X-ray?";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =195;
pos_y =84;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node Hospitalize(finite-states) {
title = "Hospitalize?";
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =525;
pos_y =32;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("yes" "no");
}

node U1(continuous) {
title = "Utility of Hospitalize";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =868;
pos_y =95;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node U2(continuous) {
title = "Utility of taking X-ray";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =429;
pos_y =431;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link A T;

link A Test;

link B D;

link D Hospitalize;

link E D;

link E X;

link Hospitalize U1;

link L E;

link L U1;

link S B;

link S L;

link T E;

link T U1;

link T U2;

link Test U2;

link Test X;

link X Hospitalize;

//Network Relationships: 

relation A { 
comment = "";
deterministic=false;
values= table (0.01 0.99 );
}

relation S { 
comment = "";
deterministic=false;
values= table (0.5 0.5 );
}

relation T A { 
comment = "";
deterministic=false;
values= table (0.05 0.01 0.95 0.99 );
}

relation L S { 
comment = "";
deterministic=false;
values= table (0.1 0.01 0.9 0.99 );
}

relation B S { 
comment = "";
deterministic=false;
values= table (0.6 0.3 0.4 0.7 );
}

relation E T L { 
comment = "";
deterministic=false;
values= table (1.0 1.0 1.0 0.0 0.0 0.0 0.0 1.0 );
}

relation D E B { 
comment = "";
deterministic=false;
values= table (0.9 0.7 0.8 0.1 0.1 0.3 0.2 0.9 );
}

relation X Test E { 
comment = "";
deterministic=false;
values= table (0.98 0.05 0.5 0.5 0.02 0.95 0.5 0.5 );
}

relation U1 Hospitalize T L { 
comment = "";
kind-of-relation = utility;
deterministic=false;
values= table (180.0 160.0 2.0 0.0 120.0 15.0 4.0 40.0 );
}

relation U2 Test T { 
comment = "";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 10.0 1.0 10.0 );
}

}
